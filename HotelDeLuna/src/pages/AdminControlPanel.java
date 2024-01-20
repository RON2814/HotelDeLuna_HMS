package pages;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import components.RoundedButton;
public class AdminControlPanel extends JFrame  {

	
	//Encapsulate and Initializing variables
	private JPanel panel;	
	String[] columnOneNames = {"Username", "Password"};
    Object[][] dataOne = new Object[14][2];  
    String[] columnTwoNames = {"Name", "Check-in", "Check-out", "Payment", "Payment Method", "Room Status"};
    Object[][] dataTwo = new Object[14][2];
    private BufferedImage adminImg;
    private DefaultTableModel modelOne, modelTwo;
    private JScrollPane scrollPaneOne, scrollPaneTwo;
    private JTable tableOne, tableTwo;
	
    //constructor
	public AdminControlPanel () {
		
		
		//loading the image
		try {
			adminImg = null;
			adminImg = ImageIO.read(getClass().getResource("/assets/admin.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		//methods
		setSize(850, 515);
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
		setLocationRelativeTo(null);
		setLayout(null);
		
		
		//adding the adminPanel to the frame
		panel = new adminPanel();
		panel.setBounds(0,0, 850, 515);
		panel.setLayout(null);
		add(panel);
		
	}
	
	//panel added to the frame for layout and designing the ui, components with graphics
	class adminPanel extends JPanel {
		
		protected void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			
			//fonts
			Font fontOne = new Font("Arial", Font.BOLD, 17);
			Font fontTwo = new Font("Arial Black", Font.BOLD, 15);
			
			//stroke for the line weight of the border around the tables
			BasicStroke stroke = new BasicStroke(3);
			
			//graphics2d for clearer graphisc
			Graphics2D graphics2d = (Graphics2D) graphics.create();
			
			//Rendering to make graphics clear not pixelated
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
			//the whole background of the frame
			graphics2d.setColor(Color.decode("#004aad"));
			graphics2d.fillRoundRect(0,0, 850, 515, 30, 30);
		
			//design at the leftside vertically
			graphics2d.setColor(Color.WHITE);
			graphics2d.fillRect(-5, 0, 60, 515);
			
			//background round rect of the admin access string
			graphics2d.fillRoundRect(30, 35, 200, 50, 30, 30);
			
			//admin access string
			graphics2d.setColor(Color.decode("#004aad"));
			graphics2d.setFont(fontOne);
			graphics2d.drawString("Admin Access", 105, 65);
			
			//rounded border design of the tables
			graphics2d.setColor(Color.WHITE);
			graphics2d.setStroke(stroke);
			graphics2d.drawRoundRect(90, 110, 270, 350, 30, 30);
			graphics2d.drawRoundRect(385, 110, 440, 350, 30, 30);
			
			//strings defining the tables
			graphics2d.setFont(fontTwo);
			graphics2d.drawString("Employee Account Log", 105, 140);
			graphics2d.drawString("Guest History", 400, 140);
			
			//image of admin 
			graphics2d.drawImage(adminImg, 62, 44, 30, 30, null);
			
			//1st table for employee account log /username and password
	        modelOne = new DefaultTableModel(dataOne, columnOneNames);
	        tableOne = new JTable(modelOne);
	        tableOne.setBounds(50,50, 100, 100);
	        tableOne.setForeground(Color.BLUE);
	        scrollPaneOne = new JScrollPane(tableOne);
	        scrollPaneOne.setBounds(105,170, 242, 260);
	        tableOne.setFillsViewportHeight(true);	        
	        add(scrollPaneOne);
	        
	        
	        //2nd table for history of the guests /Name, Check-in, Check-out, Payment, payment method and room status
	        modelTwo = new DefaultTableModel(dataTwo, columnTwoNames);
	        tableTwo = new JTable(modelTwo);
	        tableTwo.setBounds(50,50, 100, 100);
	        tableTwo.setForeground(Color.BLUE);
	        scrollPaneTwo = new JScrollPane(tableTwo);
	        scrollPaneTwo.setBounds(397,170, 410, 260);
	        tableTwo.setFillsViewportHeight(true);	        
	        add(scrollPaneTwo);
	       
	        
	        //separate class that extends JButton to apply rounded corner button
	        RoundedButton logoutButton = new RoundedButton("Logout");
	        logoutButton.setBounds(745, 473, 80, 30);
	        logoutButton.setFont(new Font("Arial Black", Font.BOLD, 11));
	        logoutButton.setForeground(Color.decode("#004aad"));
	        logoutButton.setBackground(Color.WHITE);
	        add(logoutButton);
	        logoutButton.addActionListener(e -> {
	          
	        	dispose();
	        	
	        });
	        
		}
		
		
	}
	
	//main to run the whole code
	public static void main(String [] args) {
		
		AdminControlPanel adminFrame = new AdminControlPanel();
		adminFrame.setVisible(true);
		
		
	}
	
	
}
