package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;

import components.RoomButton;
import database.GuestDB;
import database.ManageRoomDB;

public class ManageRoom extends JFrame implements ActionListener{
	private GuestDB guest;
	ManageRoomDB manageDb;
	
	private JLabel roomNumber;
	private JTextField nameTField, paymentTField;
	private DatePicker checkin_date, checkout_date;
	private TimePicker checkin_time, checkout_time;
	
	private int guestId;
	
	private JButton test;
	
	private int roomNo;
	private String guestName;
	
	//this constructor is a JFrame that show when the room is press
	public ManageRoom(int roomNo, int guestId) {
		//this will import the Font .otf or .ttf file
		
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
		this.getContentPane().setBackground(new Color(22,22,22));
		
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/AltoneTrial-Regular.ttf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		this.roomNo = roomNo;
				
		//this is the ManageRoomDB by using this I can dynamically put to TextField
		manageDb = new ManageRoomDB(roomNo);
		guest = new GuestDB();
		guest.retrieveGuestData(manageDb.getGuestId());
		this.guestId = manageDb.getGuestId();
		this.guestName = manageDb.getGuestName();
		
		roomNumber = new JLabel("ROOM " + roomNo);
		roomNumber.setForeground(Color.white);
		roomNumber.setBounds(0, 15, 500, 50);
		roomNumber.setHorizontalAlignment(JLabel.CENTER);
		roomNumber.setFont(new Font("The Next Font", Font.PLAIN, 35));
		
		//form to fill in (Guess name, Check-in, Check-out, Payment) labels and textboxes
		//format (Label -> Textbox)
		JLabel guestName = new JLabel("Guest Name");
		guestName.setBounds(20, 100, 130, 30);
		guestName.setForeground(Color.white);
		guestName.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		nameTField = new JTextField();
		nameTField.setBounds(150, 100, 300, 30);
		nameTField.setText(manageDb.getGuestName()); //get name from database
		
		JLabel checkInLabel = new JLabel("Check-in");
		checkInLabel.setBounds(20, 150, 130, 30);
		checkInLabel.setForeground(Color.white);
		checkInLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		checkin_date = new DatePicker();
		checkin_date.setBounds(150, 150, 200, 30);
		checkin_time = new TimePicker();
		checkin_time.setBounds(350, 150, 100, 30);
		System.out.println(guest.getCheckInDate());
		System.out.println(guest.getPayment());
		JLabel checkOutLabel = new JLabel("Check-out");
		checkOutLabel.setBounds(20, 200, 130, 30);
		checkOutLabel.setForeground(Color.white);
		checkOutLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		checkout_date = new DatePicker();
		checkout_date.setBounds(150, 200, 200, 30);
		checkout_time = new TimePicker();
		checkout_time.setBounds(350, 200, 100, 30);
		JLabel paymentLabel = new JLabel("Payment");
		paymentLabel.setBounds(20, 250, 130, 30);
		paymentLabel.setForeground(Color.white);
		paymentLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		paymentTField = new JTextField();
		paymentTField.setBounds(150, 250, 300, 30);
		paymentTField.setText(String.valueOf(guest.getPayment()));
		
		
		try {
			//adding time and date from database
			String[] checkindateArr = guest.getCheckInDate().split("-");
			String[] checkintimeArr = guest.getCheckInTime().split(":");
			checkin_date.setDate(LocalDate.of(Integer.valueOf(checkindateArr[0]), Integer.valueOf(checkindateArr[1]), Integer.valueOf(checkindateArr[2])));
			checkin_time.setTime(LocalTime.of(Integer.valueOf(checkintimeArr[0]), Integer.valueOf(checkintimeArr[1])));
			
			
			//adding time and date from database
			String[] checkoutdateArr = guest.getCheckOutDate().split("-");
			String[] checkouttimeArr = guest.getCheckOutTime().split(":");
			checkout_date.setDate(LocalDate.of(Integer.valueOf(checkoutdateArr[0]), Integer.valueOf(checkoutdateArr[1]), Integer.valueOf(checkoutdateArr[2])));
			checkout_time.setTime(LocalTime.of(Integer.valueOf(checkouttimeArr[0]), Integer.valueOf(checkouttimeArr[1])));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		test = new JButton("test");
		test.setBounds(300, 300, 100, 30);
		test.addActionListener(this);
		
		
		this.setSize(500, 400);
		this.setLayout(null);
		this.add(roomNumber);
		this.add(guestName);
		this.add(nameTField);
		this.add(checkInLabel);
		this.add(checkin_date);
		this.add(checkin_time);
		this.add(checkOutLabel);
		this.add(checkout_date);
		this.add(checkout_time);
		this.add(paymentLabel);
		this.add(paymentTField);
		this.add(test);
		
		this.setLocationRelativeTo(null);
<<<<<<< HEAD
		
=======
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(22,22,22));
>>>>>>> branch 'master' of https://github.com/RON2814/HotelDeLuna_HMS
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == test) {
			System.out.println();
			String name = nameTField.getText();
			LocalDate checkin_d = checkin_date.getDate();
			LocalTime checkin_t = checkin_time.getTime();
			LocalDate checkout_d = checkout_date.getDate();
			LocalTime checkout_t = checkout_time.getTime();
			int payment = Integer.valueOf(paymentTField.getText());
			
			String checkinCom = checkin_date +" "+ checkin_time;
			String checkoutCom = checkout_date +" "+ checkout_time;
			
			if(guestId == 0) {
				guest.insertData(roomNo, name, checkin_d, checkin_t, checkout_d, checkout_t, payment);
				guestId = guest.getGuestId(name, checkin_d, checkin_t);
				System.out.println("//in press button// Guest Id: "+guestId);
			}
			
			guest.updateGuestData(guestId, name, checkin_d, checkin_t, checkout_d, checkout_t, payment);
			manageDb.updateRoom(1, guestId, name, checkinCom, checkoutCom);
			
			this.setVisible(false);
			this.dispose();
			
			Dashboard db = new Dashboard();
			RoomButton rb = new RoomButton();
			rb.setButtonText(roomNo, guestId, guestName, checkinCom, checkoutCom);
		}
	}
}
