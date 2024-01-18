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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;

import database.GuestDB;

public class ManageRoom extends JFrame implements ActionListener{
	private GuestDB guest;
	
	private JLabel roomNumber;
	private JTextField nameTField, paymentTField;
	private DateTimePicker checkin_dt, checkout_dt;
	
	private JButton test;
	
	private int roomNo;
	
	//this constructor is a JFrame that show when the room is press
	public ManageRoom(int roomNo) {
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
		
		JLabel checkInLabel = new JLabel("Check-in");
		checkInLabel.setBounds(20, 150, 130, 30);
		checkInLabel.setForeground(Color.white);
		checkInLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		checkin_dt = new DateTimePicker();
		checkin_dt.setBounds(150, 150, 300, 30);
		
		JLabel checkOutLabel = new JLabel("Check-out");
		checkOutLabel.setBounds(20, 200, 130, 30);
		checkOutLabel.setForeground(Color.white);
		checkOutLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		checkout_dt = new DateTimePicker();
		checkout_dt.setBounds(150, 200, 300, 30);
		
		JLabel paymentLabel = new JLabel("Payment");
		paymentLabel.setBounds(20, 250, 130, 30);
		paymentLabel.setForeground(Color.white);
		paymentLabel.setFont(new Font("Altone Trial Regular", Font.PLAIN, 18));
		paymentTField = new JTextField();
		paymentTField.setBounds(150, 250, 300, 30);
		
		test = new JButton("test");
		test.setBounds(300, 300, 100, 30);
		test.addActionListener(this);
		
		
		this.setSize(500, 400);
		this.setLayout(null);
		this.add(roomNumber);
		this.add(guestName);
		this.add(nameTField);
		this.add(checkInLabel);
		this.add(checkin_dt);
		this.add(checkOutLabel);
		this.add(checkout_dt);
		this.add(paymentLabel);
		this.add(paymentTField);
		this.add(test);
		
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == test) {
			System.out.println(checkin_dt.getDatePicker() + " " + checkin_dt.getTimePicker());
			System.out.println();
			String name = nameTField.getText();
			DatePicker checkin_date = checkin_dt.getDatePicker();
			TimePicker checkin_time = checkin_dt.getTimePicker();
			DatePicker checkout_date = checkout_dt.getDatePicker();
			TimePicker checkout_time = checkout_dt.getTimePicker();
			int payment = Integer.valueOf(paymentTField.getText());
			
			guest = new GuestDB();
			guest.insertData(roomNo, name, checkin_date, checkin_time, checkout_date, checkout_time, payment);
			
			this.setVisible(false);
			this.dispose();
		}
	}
}
