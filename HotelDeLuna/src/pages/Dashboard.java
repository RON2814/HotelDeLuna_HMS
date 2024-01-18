package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.RealTimeClock;
import components.RoomButton;
import database.GuestDB;
import database.ManageRoomDB;

public class Dashboard extends JPanel{
	//database
	private GuestDB guest; 
	private ManageRoomDB manageDb;
	private int guestId;
	
	private ManageRoom mr;
	
	private int roomsButtonRow;
	private int roomspanelHeight = (130+10)*2;
	
	private JPanel roomspanel, roomspanelButtons;
	private RoomButton room1, room2, room3, room4, room5,
					room6, room7, room8, room9, room10;
	private JLabel navTitle;
	private RealTimeClock timer;
	
	public Dashboard() {
		//import font
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/Roboto-Regular.ttf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		//initializing components here..
		roomspanel = new JPanel();
		roomspanelButtons = new JPanel();
		navTitle = new JLabel("HOTEL DE LUNA");
		
		roomspanel.setBounds(35, 75, 1210, 600);
		roomspanel.setBackground(new Color(0,30,25));
		roomspanel.setLayout(null);
		roomspanel.add(roomspanelButtons);
		
		roomspanelButtons.setBounds(10, 10, 1190, roomspanelHeight);
		roomspanelButtons.setLayout(new GridLayout(2, 5, 10, 10));
		roomspanelButtons.setBackground(new Color(255,255,255,0));
		
		//testing database v0.1
		manageDb = new ManageRoomDB(1);
		System.out.println(manageDb.getGuestId() + "-" + manageDb.getGuestName());
		
		//buttons for manage room...also can add more here
		room1 = new RoomButton(1, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(), manageDb.getCheckOut(), manageDb.getRoomAvail());
		room2 = new RoomButton(2, 2, "Testing No2", "CHECK IN", "CHECK OUT", 2);
		room3 = new RoomButton(3, 3, "Testing No3", "CHECK IN", "CHECK OUT", 3);
		room4 = new RoomButton(4, 4, "Testing No4", "CHECK IN", "CHECK OUT", 4);
		room5 = new RoomButton();
		room6 = new RoomButton();
		room7 = new RoomButton();
		room8 = new RoomButton();
		room9 = new RoomButton();
		room10 = new RoomButton();
		
		roomspanelButtons.add(room1);
		roomspanelButtons.add(room2);
		roomspanelButtons.add(room3);
		roomspanelButtons.add(room4);
		roomspanelButtons.add(room5);
		roomspanelButtons.add(room6);
		roomspanelButtons.add(room7);
		roomspanelButtons.add(room8);
		roomspanelButtons.add(room9);
		roomspanelButtons.add(room10);
		
		navTitle.setBounds(50, 15, 400, 50);
		navTitle.setFont(new Font("The Next Font", Font.PLAIN, 30));
		navTitle.setForeground(Color.white);
		
		timer = new RealTimeClock();
		
		this.setLayout(null);
		this.add(roomspanel);
		this.add(navTitle);
		this.add(timer);
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(new Color(25,25,25));
	}
	
	public void repaintTheDashboard() {
		roomspanelButtons.revalidate();
		roomspanelButtons.repaint();
		this.revalidate();
		this.repaint();
	}
	
}
