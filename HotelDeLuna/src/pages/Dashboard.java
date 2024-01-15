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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.RoomButton;
import database.Guest;

public class Dashboard extends JPanel implements ActionListener, MouseListener{
	Guest g;
	
	ManageRoom mr;
	
	private int roomsButtonRow;
	private int roomspanelHeight = (130+10)*2;
	
	ImageIcon availIcon = new ImageIcon("src/assets/AVAILABLE_NoticeIcon.png");
	Image img = availIcon.getImage().getScaledInstance(128, 16, Image.SCALE_SMOOTH);
	
	private JPanel roomspanel, roomspanelButtons;
	private JButton room1, room2, room3, room4, room5,
					room6, room7, room8, room9, room10;
	private JLabel navTitle;
	
	RoomButton rb = new RoomButton(1, "John Aaron", "1", "1", 1);
	public Dashboard() {
		availIcon = new ImageIcon(img);
		roomspanel = new JPanel();
		roomspanelButtons = new JPanel();
		navTitle = new JLabel("HOTEL DE LUNA");
		
		roomspanel.setBounds(35, 75, 1210, 620);
		roomspanel.setBackground(new Color(0,30,25));
		roomspanel.setLayout(null);
		roomspanel.add(roomspanelButtons);
		
		roomspanelButtons.setBounds(10, 10, 1190, roomspanelHeight);
		roomspanelButtons.setLayout(new GridLayout(2, 5, 10, 10));
		roomspanelButtons.setBackground(new Color(255,255,255,0));
		
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/CODE Bold.otf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		
		room1 = new JButton();
		room1.setText("<html><p style='font-size:20px; text-align:center;'>Room 1</p><p style='text-align:center;'><br>John Aaron<br>TIME<br>TIME</p></html>");
		room1.setIcon(availIcon);
		room1.setIconTextGap(2);
		room1.setHorizontalTextPosition(JButton.CENTER);
		room1.setVerticalTextPosition(JButton.TOP);
		room1.setFont(new Font("Code Bold", Font.PLAIN, 18));
		room1.setFocusable(false);
		room1.setBackground(new Color(255,255,255,0));
		room1.addActionListener(this);
		
		room2 = new JButton();
		room2.addActionListener(this);
		
		room3 = new JButton();
		room3.addActionListener(this);
		
		room4 = new JButton();
		room4.addActionListener(this);
		
		room5 = new JButton();
		room5.addActionListener(this);
		
		room6 = new JButton();
		room6.addActionListener(this);
		
		room7 = new JButton();
		room7.addActionListener(this);
		
		room8 = new JButton();
		room8.addActionListener(this);
		
		room9 = new JButton();
		room9.addActionListener(this);
		
		room10 = new JButton();
		room10.addActionListener(this);
		
		roomspanelButtons.add(room1);
		roomspanelButtons.add(room2);
		roomspanelButtons.add(room3);
		roomspanelButtons.add(room4);
		roomspanelButtons.add(room5);
		roomspanelButtons.add(room6);
		roomspanelButtons.add(room7);
		roomspanelButtons.add(room8);
		roomspanelButtons.add(room9);
		//roomspanelButtons.add(room10);
		roomspanelButtons.add(rb);
		
		navTitle.setBounds(50, 15, 400, 50);
		navTitle.setFont(new Font("The Next Font", Font.PLAIN, 30));
		navTitle.setForeground(Color.white);
		
		this.setLayout(null);
		this.add(roomspanel);
		this.add(navTitle);
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(new Color(25,25,25));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == room1) {
			System.out.println("test 1 button is pressed");
			mr = new ManageRoom(1);
			mr.setVisible(true);
		}
		if(e.getSource() == room2) {
			g = new Guest();
			g.printData();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
