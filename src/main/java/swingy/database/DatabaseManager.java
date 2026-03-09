package swingy.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Scanner;

public class DatabaseManager {
	private static final String	JDBC_URL = "jdbc:sqlite:./test.db";
	private static final String	TABLE = "testTable";
	// String[] columns

	public DatabaseManager() {
		createTable(TABLE);
		insertValue(TABLE, "testColumn", "testValue");
		printTable(TABLE);
	}

	// preparedStatement is expensive. If possible, prepare once and reuse
	// the prepared statement

	// validate tableName ?
	private static void	createTable(String tableName) {
		try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
			Statement	statement = connection.createStatement();
			String	sql = "CREATE TABLE IF NOT EXISTS " + tableName
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

	// public static void	main(String[] args) {
	// 	try {
	// 		Class.forName("org.sqlite.JDBC");
	// 		createTable(TABLE);
	// 		insertValue(TABLE, "testColumn", "testValue");
	// 	} catch (ClassNotFoundException e) {
	// 		System.err.println("main: " + e.getMessage());
	// 	}
	// }
}