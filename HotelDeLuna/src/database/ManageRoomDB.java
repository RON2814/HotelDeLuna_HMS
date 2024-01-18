package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ManageRoomDB {
	private MyCredential myCred = new MyCredential();
	private final String sqlConnection = "jdbc:sqlserver://all-for-one.database.windows.net:1433;database=Hotel De Luna;user=plsdontheck112@all-for-one;password="+myCred.getPass()+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	private Connection connect;
	
	private int roomNo;
	
	private int roomAvailability;
	private int guestId;
	private String guestName;
	private String checkIn, checkOut;
	
	public ManageRoomDB(int roomNo) {
		this.roomNo = roomNo;
		
		String query = "SELECT * FROM [dbo].[Manage_Room] WHERE room_no = '"+roomNo+"'";
		try {
			System.out.println("Connecting...");
			connect = DriverManager.getConnection(sqlConnection);
			System.out.println("Connected");
			Statement statement = connect.createStatement();
			ResultSet resSet = statement.executeQuery(query);
			while(resSet.next()) {
				roomAvailability = resSet.getInt(2);
				guestId = resSet.getInt(3);
				guestName = resSet.getString(4);
				checkIn = resSet.getString(5);
				checkOut = resSet.getString(6);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateRoom(int roomAvail, int guestId,String guestName, String checkIn, String checkOut) {
		String query = String.format("UPDATE [dbo].[Manage_Room] SET room_avail = '%d', guest_id = '%d', guest_name = '%s', CheckIn = '%s', checkOut = '%s' WHERE room_no = '%d'",
									roomAvail, guestId, guestName, checkIn, checkOut, roomNo);
		String query2 = "UPDATE [dbo].[Manage_Room] SET room_avail = '"+roomAvail+"', guest_id = '"+guestId+"', guest_name = '"+guestName+"', CheckIn = '"+checkIn+"', checkOut = '"+checkOut+"' WHERE room_no = '"+roomNo+"'";
		System.out.println(query);
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate(query);
			System.out.println("Update Data Complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//getter for roomAvail, guestId, guestName, checkIn, checkOut
	public int getRoomAvail() {
		return roomAvailability;
	}
	public int getGuestId() {
		return guestId;
	}
	public String getGuestName() {
		return guestName;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	
}
