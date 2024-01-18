package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;

public class RealTimeClock extends JLabel{
	Calendar calendar;
	SimpleDateFormat dateTimeFormat;
	String dateTime;
	
	public RealTimeClock() {
		dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateTime = dateTimeFormat.format(Calendar.getInstance().getTime());
		
		this.setBounds(1062, 685, 200, 20);
		this.setFont(new Font("Roboto Regular", Font.BOLD, 20));
		this.setForeground(Color.white);
		this.setText(dateTime);
	}
	
}
