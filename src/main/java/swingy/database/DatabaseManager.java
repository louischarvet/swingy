package swingy.database;

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
	private static final String	DB_FILE = "test.db";
	private static final String	JDBC_URL = "jdbc:sqlite:" + DB_DIR + "/" + DB_FILE;

	private static final String	HERO_TABLE = "heroes";
	// String[] columns

	public DatabaseManager() {
		try {
			initDatabase();
		} catch (Exception e) {
			System.err.println("DatabaseManager(): " + e.getMessage());
			System.exit(1);
		}
		insertValue(HERO_TABLE, "testColumn", "testValue");

		printTable(HERO_TABLE);
	}

	// preparedStatement is expensive. If possible, prepare once and reuse
	// the prepared statement

	public static void initDatabase() throws Exception {
		Path	dbPath = Paths.get(DB_DIR, DB_FILE);

		if (!Files.exists(Paths.get(DB_DIR)))
			Files.createDirectory(Paths.get(DB_DIR));

		if (!Files.exists(dbPath)) {
			createTable(HERO_TABLE);
			// insert pre-constructed heroes
		}
	}

	// validate table ?
	private static void	createTable(String table) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
			Statement	statement = connection.createStatement();
			String	sql = "CREATE TABLE IF NOT EXISTS " + table
				+ "(testColumn TEXT NOT NULL)";

			statement.execute(sql);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void	insertValue(String table, String column, String value) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
			String	sql = "INSERT INTO " + table + "(" + column + ") VALUES(?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, value);
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void	printTable(String table) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
			String	sql = "SELECT * FROM " + table;
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						System.out.println("print: " + resultSet.getString("testColumn"));
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}