package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import components.RoomButton;
import database.GuestDB;
import database.ManageRoomDB;


public class DashboardPanel extends JPanel implements ActionListener{
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
	private JLabel timer;
	private JTextField searchBar;
	
	private int circleSize = 50;
	
	Timer actionTimer;
	private  Color[] colors = {Color.black, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED};
	private BufferedImage available, ongoing, exceed, reserved, logo, search;
	private JToggleButton searchButton;
	private JLabel  userButton, logoutButton;
	
	public DashboardPanel() {
			
		try {
			available = null;
    		ongoing = null;
    		exceed = null;
    		reserved = null;
    		logo = null;
    		search = null;
			// Load images
    		available = ImageIO.read(getClass().getResource("/assets/available.png"));
    		ongoing = ImageIO.read(getClass().getResource("/assets/Ongoing.png"));
    		exceed = ImageIO.read(getClass().getResource("/assets/exceed.png"));
    		reserved = ImageIO.read(getClass().getResource("/assets/reserved.png"));
    		logo = ImageIO.read(getClass().getResource("/assets/logo.png"));
    		search = ImageIO.read(getClass().getResource("/assets/search.png"));
    		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//initializing components here..
		roomspanel = new JPanel();
		roomspanelButtons = new JPanel();
		//navTitle = new JLabel("HOTEL DE LUNA");
		timer = new JLabel();
		actionTimer = new Timer();
		
		roomspanel.setBounds(20, 230, 976, 340);
		roomspanel.setBackground(Color.WHITE);
		roomspanel.setLayout(null);
		roomspanel.add(roomspanelButtons);
		
		roomspanelButtons.setBounds(13, 30, 930, roomspanelHeight + 20);
		roomspanelButtons.setLayout(new GridLayout(2, 5, 10, 5));
		roomspanelButtons.setBackground(Color.WHITE);
		
		//testing database v0.1
		manageDb = new ManageRoomDB(1);
		System.out.println(manageDb.getGuestId() + "-" + manageDb.getGuestName());
		
		//buttons for manage room...also can add more here
		room1 = new RoomButton(1, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(), manageDb.getCheckOut(), manageDb.getRoomAvail());
		room2 = new RoomButton(2, 2, "Testing No2", "CHECK IN", "CHECK OUT", 2);
		room3 = new RoomButton(3, 3, "Testing No3", "CHECK IN", "CHECK OUT", 3);
		room4 = new RoomButton(4, 4, "Testing No4", "CHECK IN", "CHECK OUT", 4);
		room5 = new RoomButton(5, 5, "Testing No4", "CHECK IN", "CHECK OUT", 4);
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
		
//		navTitle.setBounds(50, 15, 400, 50);
//		navTitle.setFont(new Font("The Next Font", Font.PLAIN, 30));
//		navTitle.setForeground(Color.white);
		//baliw nako
		timer.setBounds(1062,685,200,20);
		timer.setFont(new Font("Roboto Regular", Font.BOLD, 20));
		timer.setForeground(Color.white);
		currentDateTime();
		
		this.setLayout(null);
		this.add(roomspanel);
		
		//this.add(navTitle);
		this.add(timer);
		this.setPreferredSize(new Dimension(1015, 585));
		this.setBackground(Color.WHITE);
		
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		Graphics2D graphics2d = (Graphics2D) graphics.create();
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		GradientPaint gradient = new GradientPaint(430, 0, Color.BLACK, getWidth(), getHeight(), Color.decode("#004aad"));
		
		Font fontOne = new Font("Arial", Font.BOLD, 11);
		Font fontTwo = new Font("Felix Titling", Font.BOLD, 15);    
		
		graphics2d.setColor(Color.decode("#004aad"));
		graphics2d.fillRoundRect(-50, 25, 100, 195, 50, 50);
		
		graphics2d.setPaint(gradient);
		graphics2d.fillRoundRect(80, 25, 880, 195, 50, 50);
		
		//roundedrectangle background of textfield
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRoundRect(420, 45, 500, 35, 30, 30);
		
		//circles graphics white
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillOval(460, 110, circleSize, circleSize);
		graphics2d.fillOval(585, 110, circleSize, circleSize);
		graphics2d.fillOval(705, 110, circleSize, circleSize);
		graphics2d.fillOval(825, 110, circleSize, circleSize);
		
		graphics2d.setFont(fontOne);
		graphics2d.drawString("Available", 460, 180);
		graphics2d.drawString("Ongoing", 585, 180);
		graphics2d.drawString("Exceed", 709, 180);
		graphics2d.drawString("Reserved", 825, 180);
		
		graphics2d.drawImage(available, 463,  112, 45, 45, null );
		graphics2d.drawImage(ongoing, 588,  114, 43, 43, null );
		graphics2d.drawImage(exceed, 709,  114, 43, 43, null );
		graphics2d.drawImage(reserved, 829,  114, 40, 40, null );		
		graphics2d.drawImage(logo, 110,  35, 280, 160, null );
		//graphics2d.drawImage(search, 878,  50, 25, 25, null );
		
		
		graphics2d.setFont(fontTwo);
		graphics2d.drawString("Hotel  De  Luna", 185, 180);
		
		 searchBar = new searchPlaceHolder("Room Search");
		 searchBar.setFont(new Font("Arial", Font.PLAIN, 13));
		 searchBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, (colors)[0]));
	     searchBar.setBounds(435, 45, 400, 36);
	     add(searchBar);
	     searchBar.setColumns(10);
	     searchBar.setForeground(Color.black);
	     searchBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, (colors)[0]));

	        // when clicked, the placeholder will disappear
	        searchBar.addFocusListener(new FocusListener() {
	          public void focusGained(FocusEvent e) {
	            if (searchBar.getText().equals(" Room Search")) {
	            	searchBar.setText("");
	            	searchBar.setForeground(Color.BLACK);
	            }
	          }

	          public void focusLost(FocusEvent e) {
	            if (searchBar.getText().isEmpty()) {
	            	searchBar.setText(" Room Search");
	            	searchBar.setForeground(Color.BLACK);
	            }
	          }
	        });
	        
	        
	        searchButton = new JToggleButton();
	        searchButton.setBounds(875,48, 30, 30);
	        searchButton.setBackground(Color.WHITE);
	        searchButton.setFocusable(false);
	        searchButton.setContentAreaFilled(false);
	        searchButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, (colors)[0]));
	        searchButton.setIcon(new ImageIcon("//C:/Users/Admin/git/HotelDeLuna_HMS/HotelDeLuna/src/assets/searchIcon.png"));
	        searchButton.addActionListener(e -> {
	            
	            
	        });
	        
	        add(searchButton);
	        
	        
	        userButton = new JLabel();
	        userButton.setBounds(15,80, 35, 35);
	        userButton.setBackground(Color.WHITE);
	        userButton.setFocusable(false);
	        userButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, (colors)[0]));
	        userButton.setIcon(new ImageIcon("//C:/Users/Admin/git/HotelDeLuna_HMS/HotelDeLuna/src/assets/userIcon.png"));
	        userButton.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	              
	            	
	            }
	        });
	        
	        add(userButton);
	        
	        logoutButton = new JLabel();
	        logoutButton.setBounds(15,130, 35, 35);
	        logoutButton.setBackground(Color.WHITE);
	        logoutButton.setFocusable(false);
	        logoutButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, (colors)[0]));
	        logoutButton.setIcon(new ImageIcon("//C:/Users/Admin/git/HotelDeLuna_HMS/HotelDeLuna/src/assets/logoutIcon.png"));
	        logoutButton.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	           
	            	
	            }
	        });
	        
	        add(logoutButton);
	        
	        
	        
	        
	}
	        class searchPlaceHolder extends JTextField{
			    public String text;
			    public searchPlaceHolder(String name) {
			      this.text = name;
			    }
			    public void paintComponent(Graphics g) {
			      super.paintComponent(g);
			      Graphics2D graphics2d = (Graphics2D) g.create();
					
			        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			      
			      if(getText().isBlank()) {
			      graphics2d.setColor(Color.BLACK);
			      graphics2d.drawString(text, getInsets().left, g.getFontMetrics().getAscent() + getInsets().top + 15);
			      
			      }
			    }
			  }
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() == room1) {
//			System.out.println("test 1 button is pressed");
//			mr = new ManageRoom(1);
//			mr.setVisible(true);
//		}
//		if(e.getSource() == room2) {
//			guest = new GuestDB();
//			//g.printData();
//			System.out.println(guest.retrieveGuestData(2));
//		}
//		if(e.getSource() == room3) {
//			guest = new GuestDB();
//			guest.printData();
//		}
	}
	
	public void currentDateTime() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		 LocalDateTime now = LocalDateTime.now();
		 timer.setText(dtf.format(now));
		 timer.revalidate();
		 timer.repaint();
	}
	
}
