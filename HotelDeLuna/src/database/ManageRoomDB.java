package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ManageRoomDB {
	private MyCredential myCred = new MyCredential();
	private final String sqlConnection = "jdbc:sqlserver://all-for-one.database.windows.net:1433;database=Hotel De Luna;user=plsdontheck112@all-for-one;password="+myCred.getPass()+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	private Connection connect;
	
	public ManageRoomDB() {
		try {
			System.out.println("Connecting...");
			connect = DriverManager.getConnection(sqlConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void getData
	
}
