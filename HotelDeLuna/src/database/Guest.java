package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Guest {
	MyCredential myCred = new MyCredential();
	private final String sqlConnection = "jdbc:sqlserver://all-for-one.database.windows.net:1433;database=Hotel De Luna;user=plsdontheck112@all-for-one;password="+myCred.getPass()+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	private Connection connect;
	public Guest() {
		try {
			System.out.println("Connecting...");
			connect = DriverManager.getConnection(sqlConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertData(String guestName, String check_in, String check_out, int payment) {
		String query = "INSERT INTO [dbo].[Guest] (guest_name, check_in, check_out, payment) VALUES ('"+guestName+"', '"+check_in+"', '"+check_out+"', '"+payment+"');";
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate(query);
			System.out.println("Insert Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveData() {
		
	}
	
	public void printData() {
		String query = "SELECT * FROM [dbo].[Guest]";
		try {
			Statement statement = connect.createStatement();
			ResultSet resSet = statement.executeQuery(query);
			while(resSet.next()) {
				System.out.println(resSet.getInt(1) + ", " + resSet.getString(2) + ", " + resSet.getString(3) + ", " + resSet.getString(4) + ", " + resSet.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
