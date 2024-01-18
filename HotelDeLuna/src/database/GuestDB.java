package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

public class GuestDB {
	private int guestId;
	private int roomNo;
	private int roomAvailability;
	private String guestName;
	private String checkIn_date, checkIn_time, checkOut_date, checkOut_time;
	private int payment;
	private String paymentMethod;
	
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
	
	public void insertData(int roomNo, String guestName, LocalDate checkin_d, LocalTime checkin_t, LocalDate checkout_d, LocalTime checkout_t, int payment) {
		String query = "INSERT INTO [dbo].[Guest] (room_no, guest_name, checkin_date, checkin_time, checkout_date, checkout_time, payment)"
					 + "VALUES ('"+roomNo+"', '"+guestName+"', '"+checkin_d+"', '"+checkin_t+"', '"+checkout_d+"', '"+checkout_t+"', '"+payment+"');";
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate(query);
			System.out.println("Insert Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveGuestData(int guestId) {
		String query = "SELECT * FROM [dbo].[Guest] WHERE guest_id = '"+guestId+"'";
		try {
			Statement statement = connect.createStatement();
			ResultSet resSet = statement.executeQuery(query);
			while(resSet.next()) {
				this.guestId = resSet.getInt(1);
				this.roomNo = resSet.getInt(2);
				this.guestName = resSet.getString(3);
				this.checkIn_date = resSet.getString(4);
				this.checkIn_time = resSet.getString(5);
				this.checkOut_date = resSet.getString(6);
				this.checkOut_time = resSet.getString(7);
				this.payment = resSet.getInt(8);
				this.paymentMethod = resSet.getString(9);
			}
			System.out.println("Retrieve Guest Name Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//getters for the time and date check in and check out
	public String getCheckInDate() {
		return this.checkIn_date;
	}
	public String getCheckInTime() {
		return this.checkIn_time;
	}
	public String getCheckOutDate() {
		return this.checkOut_date;
	}
	public String getCheckOutTime() {
		return this.checkOut_time;
	}
	public int getPayment() {
		return this.payment;
	}
	
	
	public void updateGuestData(int guestId, String name, LocalDate checkin_date, LocalTime checkin_time, LocalDate checkout_date, LocalTime checkout_time, int payment) {
		String query = "UPDATE [dbo].[Guest] SET guest_name = '"+name+"', checkin_date = '"+checkin_date+"', checkin_time = '"+checkin_time+"', checkout_date = '"+checkout_date+"', checkout_time = '"+checkout_time+"', payment = '"+payment+"' WHERE guest_id = '"+guestId+"';";
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate(query);
			System.out.println("Update Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int getGuestId(String guestName, LocalDate checkin_d, LocalTime checkin_t) {
		String query = String.format("SELECT guest_id FROM [dbo].[Guest] WHERE (guest_name = '%s' AND checkin_date = '%s') AND (guest_name = '%s' AND checkout_time = '%s')"
						, guestName, checkin_d, guestName, checkin_d);
		try {
			Statement statement = connect.createStatement();
			ResultSet resSet = statement.executeQuery(query);
			while(resSet.next()) {
				guestId = resSet.getInt(1);
			}
			System.out.println("Retrieve Guest ID Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guestId;
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
