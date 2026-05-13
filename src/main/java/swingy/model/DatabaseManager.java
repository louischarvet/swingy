package swingy.model;

import java.lang.StringBuilder;

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

import swingy.model.character.Hero;

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

	//	if (!Files.exists(dbPath)) {
	//		System.out.println("DatabaseManager: !Files.exists(" + dbPath + ")");
			
			TableManager	heroTable = new TableManager.Builder()
				.withName("hero")
				.withColumn("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
				.withColumn("level", "INTEGER NOT NULL DEFAULT 1")
				.withColumn("name", "TEXT NOT NULL")
				.withColumn("klass", "TEXT NOT NULL") //
				.withColumn("attack", "INTEGER NOT NULL DEFAULT 1")
				.withColumn("defense", "INTEGER NOT NULL DEFAULT 1")
				.withColumn("hit_points", "INTEGER NOT NULL DEFAULT 1") ////////
				.withColumn("weapon", "INTEGER") // FOREIGN KEY (weapon) REFERENCES artifact(id)
				.withColumn("armor", "INTEGER")
				.withColumn("helm", "INTEGER")
				.build();

			TableManager	artifactTable = new TableManager.Builder()
				.withName("artifact")
				.withColumn("id", "INTEGER PRIMARY KEY AUTOINCREMENT")
				.withColumn("level", "TEXT NOT NULL")
				.withColumn("type", "TEXT NOT NULL")
				.build();

			tables.put("hero", heroTable);
			tables.put("artifact", artifactTable);

			tables.get("hero").create();
			tables.get("artifact").create();
	//	}
	}

	public void	insert(Hero hero) {
		this.tables.get("hero").insert(hero);
		// err ?
	}

	public void	insert(String table, String column, String value) {
		this.tables.get(table).insert(column, value);
		// error if no table found ?
	}

	public void	insert(String table, String column, int value) {
		this.tables.get(table).insert(column, value);
		// error if no table found ?
	}

	// resultset ?
	public List< String >	getHeroStrings() {
		return this.tables.get("hero").getAllStrings();
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
		 * Insert HERO
		 */
		private void	insert(Hero hero) {
			try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
				String	sql = "INSERT INTO " + this.name
				+ "(name, klass, level, attack, defense, hit_points) VALUES(?, ?, ?, ?, ?, ?)";
			
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setString(1, hero.getName());
					preparedStatement.setString(2, hero.getKlass());
					preparedStatement.setInt(3, hero.getLevel());
					preparedStatement.setInt(4, hero.getAttack());
					preparedStatement.setInt(5, hero.getDefense());
					preparedStatement.setInt(6, hero.getHitPoints());

					preparedStatement.executeUpdate();
				} catch (Exception e) {
					System.err.println(e);
				}
			} catch (Exception e) {
				System.err.println(e); /////// error mgmt
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

		// T ? generic type
		// ne pas creer d'objet Hero
		private List< String >	getAllStrings() {
			List< String >	list = new ArrayList<>();

			try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
				String	query = new StringBuilder()
					.append("SELECT * FROM ")
					.append(this.name)
					.toString();

				try (Statement statement = connection.createStatement()) {
					try (ResultSet resultSet = statement.executeQuery(query)) {
						while (resultSet.next()) {
							String	heroString = new StringBuilder()
								.append(resultSet.getString("name")).append(", ")
								.append(resultSet.getString("klass")).append(", level ")
								.append(resultSet.getInt("level")).append('\n') //////
								.toString();
							list.add(heroString);
						}
					}
				}
			} catch (Exception e) {
				System.err.println("Error in getAll(): " + e.getMessage());
			}

			return list;
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

			private Builder	withName(String p_name) {
				this.name = p_name;
				return this;
			}

			private Builder	withColumn(String column, String type) {
				this.columns.put(column, type);
				return this;
			}

			private TableManager	build() {
				return new TableManager(this);
			}
		}
	}
}