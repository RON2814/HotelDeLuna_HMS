package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Guest {
	private final String DB_URL = "jdbc:mysql://localhost:3306/sample";
	public Guest() {
		try {
			Connection connect = DriverManager.getConnection(DB_URL, "root", "");
			Statement statement = connect.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
