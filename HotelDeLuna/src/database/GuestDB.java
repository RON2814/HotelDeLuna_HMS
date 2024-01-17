package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

public class GuestDB {
	private String guestName;;
	private String checkIn_date, checkIn_time, checkOut_date, checkOut_time;
	private int roomAvailability;
	private MyCredential myCred = new MyCredential();
	private final String sqlConnection = "jdbc:sqlserver://all-for-one.database.windows.net:1433;database=Hotel De Luna;user=plsdontheck112@all-for-one;password="+myCred.getPass()+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	private Connection connect;
	public GuestDB() {
		try {
			System.out.println("Connecting...");
			connect = DriverManager.getConnection(sqlConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertData(int roomNo, String guestName, DatePicker checkin_date, TimePicker checkin_time, DatePicker checkout_date, TimePicker checkout_time, int payment) {
		String query = "INSERT INTO [dbo].[Guest] (room_no, guest_name, checkin_date, checkin_time, checkout_date, checkout_time, payment)"
					 + "VALUES ('"+roomNo+"', '"+guestName+"', '"+checkin_date+"', '"+checkin_time+"', '"+checkout_date+"', '"+checkout_time+"', '"+payment+"');";
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate(query);
			System.out.println("Insert Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String retrieveGuestData(int guestId) {
		String query = "SELECT * FROM [dbo].[Guest] WHERE guest_id = '"+guestId+"'";
		String getGuestName = null;
		try {
			Statement statement = connect.createStatement();
			ResultSet resSet = statement.executeQuery(query);
			while(resSet.next()) {
				getGuestName = resSet.getString(1);
			}
			System.out.println("Retrieve Guest Name Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getGuestName;
	}
	
	public int getGuestId() {
		
		return 0;
	}
	
	public void printData() {
		String query = "SELECT * FROM [dbo].[Guest]";
		try {
			Statement statement = connect.createStatement();
			ResultSet resSet = statement.executeQuery(query);
			while(resSet.next()) {
				System.out.println(resSet.getInt(1) + ", " + resSet.getInt(2) + ", " + resSet.getString(3) + ", " + resSet.getString(4) + ", " + resSet.getString(5) + ", " + resSet.getString(6) + ", " + resSet.getString(7) + ", " + resSet.getInt(8) + ", " + resSet.getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
