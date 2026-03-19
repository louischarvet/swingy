package swingy.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Scanner;

// must restrain access to the database !!

public class DatabaseManager {
	private static final String	DB_DIR = "data";
	private static final String	DB_FILE = "swingy.db";
	private static final String	JDBC_URL = "jdbc:sqlite:" + DB_DIR + "/" + DB_FILE;

	// private static final String	HERO_TABLE = "hero";
	// private static final String	ARTIFACT_TABLE = "artifact";

	private Map< String, TableManager >	tables = new HashMap<>();

	public DatabaseManager() {
		try {
			initDatabase();
		} catch (Exception e) {
			System.err.println("DatabaseManager(): " + e.getMessage());
			System.exit(1);
		}
	}

	// preparedStatement is expensive. If possible, prepare once and reuse
	// the prepared statement

	public void initDatabase() throws Exception {
		Path	dbPath = Paths.get(DB_DIR, DB_FILE);

		if (!Files.exists(Paths.get(DB_DIR)))
			Files.createDirectory(Paths.get(DB_DIR));

		if (!Files.exists(dbPath)) {
			TableManager	heroTable = new TableManager.Builder()
				.setName("hero")
				.setColumn("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
				.setColumn("level", "INTEGER NOT NULL DEFAULT 1")
				.setColumn("name", "TEXT NOT NULL")
				.setColumn("class", "TEXT NOT NULL") //
				.setColumn("attack", "INTEGER NOT NULL DEFAULT 0")
				.setColumn("defense", "INTEGER NOT NULL DEFAULT 0")
				.setColumn("hit_points", "INTEGER NOT NULL DEFAULT 0")
				.setColumn("weapon", "INTEGER") // FOREIGN KEY (weapon) REFERENCES artifact(id)
				.setColumn("armor", "INTEGER")
				.setColumn("helm", "INTEGER")
				.build();

			TableManager	artifactTable = new TableManager.Builder()
				.setName("artifact")
				.setColumn("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
				.setColumn("level", "TEXT NOT NULL")
				.setColumn("type", "TEXT NOT NULL")
				.build();

			tables.put("hero", heroTable);
			tables.put("artifact", artifactTable);

			tables.get("hero").create();
			tables.get("artifact").create();
		}
	}

	public void	insert(String table, String column, String value) {
		this.tables.get(table).insert(column, value);
		// error if no table found ?
	}

	public void	insert(String table, String column, int value) {
		this.tables.get(table).insert(column, value);
		// error if no table found ?
	}

	private static class TableManager {
		private	String	name;
		private	Map< String, String >	columns;

		private TableManager(Builder builder) {
			this.name = builder.name;
			this.columns = builder.columns;
		}

		private void	create() {
			try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
				Statement	statement = connection.createStatement();
				String	sql = "CREATE TABLE IF NOT EXISTS " + this.name
					+ getColumnsInit();

				statement.execute(sql);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				// remonter l'erreur !
			}
		}

		/**
		 * Insert TEXT
		 */
		private void	insert(String column, String value) {
			try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
				String	sql = "INSERT INTO " + this.name + "(" + column + ") VALUES(?)";

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setString(1, value);
					preparedStatement.executeUpdate();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				// remonter l'erreur !
			}
		}

		/**
		 * Insert INTEGER
		 */
		private void	insert(String column, int value) {
			try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
				String	sql = "INSERT INTO " + this.name + "(" + column + ") VALUES(?)";

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setInt(1, value);
					preparedStatement.executeUpdate();
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				// remonter l'erreur !
			}
		}

		private String	getColumnsInit() {
			String	columnsInit = new String("(");
			int	i = 0,
				size = this.columns.size();

			for (Map.Entry< String, String > entry : this.columns.entrySet()) {
				columnsInit += entry.getKey() + " " + entry.getValue();
				if (++i < size)
					columnsInit += ", ";
			}
			columnsInit += ")";

			return columnsInit;
		}

		/**
		 * TableManager.Builder
		 */

		private static class Builder {
			private String	name;
			private Map< String, String >	columns = new HashMap<>();

			private Builder	setName(String p_name) {
				this.name = p_name;
				return this;
			}

			private Builder	setColumn(String column, String type) {
				this.columns.put(column, type);
				return this;
			}

			private TableManager	build() {
				return new TableManager(this);
			}
		}
	}
}