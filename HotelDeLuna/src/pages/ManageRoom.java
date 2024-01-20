package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import components.RoomButton;
import database.GuestDB;
import database.ManageRoomDB;

public class ManageRoom extends JFrame implements ActionListener{
	//database create a variable for for global use
	private GuestDB guest;
	ManageRoomDB manageDb;
	
	//components on this class
	private JLabel roomNumber;
	private JTextField nameTField, paymentTField;
	private DatePicker checkin_date, checkout_date;
	private TimePicker checkin_time, checkout_time;
	private JRadioButton paymentMethodCash, paymentMethodCard;
	private JRadioButton roomStatusOngiong, roomStatusReserved;
	private JButton submitBtn, markAsDoneBtn, deleteBtn;
	
	private int guestId;
	
	private int roomNo;
	private String guestName;
	
	//used in radio button f 
	private String paymentMethod;
	private String roomStats;
	
	//this constructor is a JFrame that show when the room is press
	public ManageRoom(int roomNo, int guestId) {
		//this will import the Font .otf or .ttf file
		
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
		this.getContentPane().setBackground(new Color(22,22,22));
		
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
		guestName.setBounds(20, 85, 130, 30);
		guestName.setForeground(Color.white);
		guestName.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		nameTField = new JTextField();
		nameTField.setBounds(185, 85, 300, 30);
		nameTField.setText(manageDb.getGuestName()); //get name from database
		
		JLabel checkInLabel = new JLabel("Check-in");
		checkInLabel.setBounds(20, 135, 130, 30);
		checkInLabel.setForeground(Color.white);
		checkInLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		checkin_date = new DatePicker();
		checkin_date.setBounds(185, 135, 200, 30);
		checkin_time = new TimePicker();
		checkin_time.setBounds(385, 135, 100, 30);
		System.out.println(guest.getCheckInDate());
		System.out.println(guest.getPayment());
		JLabel checkOutLabel = new JLabel("Check-out");
		checkOutLabel.setBounds(20, 185, 130, 30);
		checkOutLabel.setForeground(Color.white);
		checkOutLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		checkout_date = new DatePicker();
		checkout_date.setBounds(185, 185, 200, 30);
		checkout_time = new TimePicker();
		checkout_time.setBounds(385, 185, 100, 30);
		JLabel paymentLabel = new JLabel("Payment");
		paymentLabel.setBounds(20, 235, 130, 30);
		paymentLabel.setForeground(Color.white);
		paymentLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		paymentTField = new JTextField();
		paymentTField.setBounds(185, 235, 300, 30);
		paymentTField.setText(String.valueOf(guest.getPayment()));
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setBounds(20, 285, 180, 30);
		lblPaymentMethod.setForeground(Color.white);
		lblPaymentMethod.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		paymentMethodCash = new JRadioButton("Cash");
		paymentMethodCash.setBounds(185, 285, 70, 30);
		paymentMethodCash.setOpaque(false);
		paymentMethodCash.setFocusable(false);
		paymentMethodCash.setForeground(Color.white);
		paymentMethodCash.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		paymentMethodCard = new JRadioButton("Credit Card");
		paymentMethodCard.setBounds(300, 285, 130, 30);
		paymentMethodCard.setOpaque(false);
		paymentMethodCard.setFocusable(false);
		paymentMethodCard.setForeground(Color.white);
		paymentMethodCard.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		
		JLabel lblRoomStatus = new JLabel("Status");
		lblRoomStatus.setBounds(20, 335, 180, 30);
		lblRoomStatus.setForeground(Color.white);
		lblRoomStatus.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		roomStatusOngiong = new JRadioButton("Ongoing");
		roomStatusOngiong.setBounds(185, 335, 130, 30);
		roomStatusOngiong.setOpaque(false);
		roomStatusOngiong.setFocusable(false);
		roomStatusOngiong.setForeground(Color.white);
		roomStatusOngiong.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		roomStatusReserved = new JRadioButton("Reserved");
		roomStatusReserved.setBounds(300, 335, 130, 30);
		roomStatusReserved.setOpaque(false);
		roomStatusReserved.setFocusable(false);
		roomStatusReserved.setForeground(Color.white);
		roomStatusReserved.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		
		
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
		
		submitBtn = new JButton("Submit");
		submitBtn.setBounds(380, 390, 100, 30);
		submitBtn.addActionListener(this);
		
		markAsDoneBtn = new JButton("Done");
		markAsDoneBtn.setBounds(270, 390, 100, 30);
		markAsDoneBtn.addActionListener(this);
		markAsDoneBtn.setBackground(Color.green);
		markAsDoneBtn.setForeground(Color.white);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(160, 390, 100, 30);
		deleteBtn.addActionListener(this);
		deleteBtn.setBackground(Color.red);
		deleteBtn.setForeground(Color.white);
		
		this.setSize(510, 450);
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
		this.add(lblPaymentMethod);
		this.add(paymentMethodCash);
		this.add(paymentMethodCard);
		this.add(lblRoomStatus);
		this.add(roomStatusOngiong);
		this.add(roomStatusReserved);
		this.add(submitBtn);
		this.add(markAsDoneBtn);
		this.add(deleteBtn);
		
		this.setLocationRelativeTo(null);

		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(22,22,22));
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitBtn) {
			System.out.println();
			String name = nameTField.getText();
			LocalDate checkin_d = checkin_date.getDate();
			LocalTime checkin_t = checkin_time.getTime();
			LocalDate checkout_d = checkout_date.getDate();
			LocalTime checkout_t = checkout_time.getTime();
			int payment = Integer.valueOf(paymentTField.getText());
			
			//check in date and time combined used for dashboard buttons
			String checkinCom = checkin_date +" "+ checkin_time;
			String checkoutCom = checkout_date +" "+ checkout_time;
			
			if(guestId == 0) {
				guest.insertData(roomNo, name, checkin_d, checkin_t, checkout_d, checkout_t, payment);
				guestId = guest.getGuestId(name, checkin_d, checkin_t);
				System.out.println("//in press button// Guest Id: "+guestId);
			}
			
			guest.updateGuestData(guestId, name, checkin_d, checkin_t, checkout_d, checkout_t, payment);
			manageDb.updateRoom(1, checkin_d, name, checkinCom, checkoutCom);
			
			this.setVisible(false);
			this.dispose();
			
			DashboardPanel db = new DashboardPanel();
			RoomButton rb = new RoomButton();
			rb.setButtonText(roomNo, guestId, guestName, checkinCom, checkoutCom);
		}
	}
}
