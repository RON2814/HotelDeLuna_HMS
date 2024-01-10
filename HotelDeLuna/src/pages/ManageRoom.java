package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DateTimePicker;

public class ManageRoom extends JFrame{
	JLabel roomNumber;
	JTextField nameField;
	DateTimePicker checkin_dt, checkout_dt;
	public ManageRoom(int roomNo) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/CODE Bold.otf")));
		} catch (IOException|FontFormatException e) {
			//Handle exception
		}
		
		roomNumber = new JLabel("ROOM " + roomNo);
		roomNumber.setForeground(Color.white);
		roomNumber.setBounds(0, 10, 500, 50);
		roomNumber.setHorizontalAlignment(JLabel.CENTER);
		roomNumber.setFont(new Font("The Next Font", Font.PLAIN, 35));
		
		nameField = new JTextField();
		nameField.setBounds(150, 100, 300, 30);
		
		checkin_dt = new DateTimePicker();
		checkin_dt.setBounds(150, 150, 300, 30);
		
		checkout_dt = new DateTimePicker();
		checkout_dt.setBounds(150, 200, 300, 30);
		
		this.setSize(new Dimension(500, 500));
		this.setLayout(null);
		this.add(roomNumber);
		this.add(nameField);
		this.add(checkin_dt);
		this.add(checkout_dt);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(22,22,22));
	}
}
