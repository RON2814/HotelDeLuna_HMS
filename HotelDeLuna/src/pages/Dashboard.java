package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends JPanel implements ActionListener{
	ManageRoom mr;
	
	private int roomsButtonRow;
	private int roomspanelHeight = (130+20)*2;
	
	private JPanel roomspanel, roomspanelButtons;
	private JButton room1, room2, room3, room4, room5,
					room6, room7, room8, room9, room10;
	private JLabel navTitle;
	public Dashboard() {
		roomspanel = new JPanel();
		roomspanelButtons = new JPanel();
		navTitle = new JLabel("HOTEL DE LUNA");
		
		roomspanel.setBounds(35, 75, 1210, 620);
		roomspanel.setBackground(Color.gray);
		roomspanel.setLayout(null);
		roomspanel.add(roomspanelButtons);
		
		roomspanelButtons.setBounds(10, 10, 1190, roomspanelHeight);
		roomspanelButtons.setLayout(new GridLayout(2, 5, 10, 10));
		
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/CODE Bold.otf")));
		} catch (IOException|FontFormatException e) {
			//Handle exception
		}
		
		room1 = new JButton();
		room1.setText("<html><p style='text-align:center;'>Room 1<br>John Aaron<br>TIME</p></html>");
		room1.setFont(new Font("Code Bold", Font.PLAIN, 12));
		room1.setFocusable(false);
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
		roomspanelButtons.add(room10);
		
		navTitle.setBounds(50, 15, 400, 50);
		navTitle.setFont(new Font("The Next Font", Font.PLAIN, 30));
		navTitle.setForeground(Color.white);
		
		this.setLayout(null);
		this.add(roomspanel);
		this.add(navTitle);
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(new Color(22,22,22));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == room1) {
			System.out.println("test 1 button is pressed");
			mr = new ManageRoom(1);
			mr.setVisible(true);
		}
	}
}
