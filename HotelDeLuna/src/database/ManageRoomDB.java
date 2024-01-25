package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

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
	
	public ManageRoomDB() {
		try {
			System.out.println("Connecting...");
			connect = DriverManager.getConnection(sqlConnection);
			System.out.println("Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getRoomData(int roomNo) {
		this.roomNo = roomNo;
		
		String query = "SELECT * FROM [dbo].[Manage_Room] WHERE room_no = '"+roomNo+"'";
		try {
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
	
	public void updateRoom(int roomAvail, LocalDate checkin_date, String guestName, String checkIn, String checkOut) {
		//query is old and query2 is and copy of old just did it for checking
		//now query1 is the updated.
		/*String query = String.format("UPDATE [dbo].[Manage_Room] SET room_avail = '%d', guest_id = '%d', guest_name = '%s', CheckIn = '%s', checkOut = '%s' WHERE room_no = '%d'",
									roomAvail, guestId, guestName, checkIn, checkOut, roomNo);*/
		String query1 = String.format("UPDATE [dbo].[Manage_Room] SET Room_Avail = '%d', Guest_Id = (SELECT Guest_Id FROM [dbo].[Guest] WHERE Guest_name = '%s' AND CheckIn_Date = '%s'), Guest_name = '%s', CheckIn = '%s', checkOut = '%s' WHERE Room_no = '%d'",
									roomAvail, guestName, checkin_date, guestName, checkIn, checkOut, roomNo);
		//String query2 = "UPDATE [dbo].[Manage_Room] SET room_avail = '"+roomAvail+"', guest_id = '"+guestId+"', guest_name = '"+guestName+"', CheckIn = '"+checkIn+"', checkOut = '"+checkOut+"' WHERE room_no = '"+roomNo+"'";
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate(query1);
			System.out.println("Update Data from MANAGE_ROOM table is complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoom(int roomNo) {
		String query = "UPDATE [dbo].[Manage_Room] SET Room_Avail='1', Guest_Id='0', Guest_name='', CheckIn='', CheckOut='' WHERE Room_no='"+roomNo+"'";
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate(query);
			System.out.println("Data delete from MANAGE_ROOM table is complete.");
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
