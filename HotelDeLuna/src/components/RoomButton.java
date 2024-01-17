package components;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import pages.ManageRoom;

public class RoomButton extends JButton implements ActionListener, MouseListener{
	private int roomStatus;
	private int roomNo;
	private int guestId;
	
	private ManageRoom manage;
	
	private ImageIcon availableStatus = new ImageIcon("src/assets/AVAILABLE_NoticeIcon.png");
	private ImageIcon on_goingStatus = new ImageIcon("src/assets/ONGOING_NoticeIcon.png");
	private ImageIcon times_upStatus = new ImageIcon("src/assets/TIMESUP_NoticeIcon.png");
	private ImageIcon reservedStatus = new ImageIcon("src/assets/RESERVED_NoticeIcon.png");
	
	public RoomButton() {
		
	}
	/* 
	 * Status NAMING
	 * 1 = Available Icon
	 * 2 = On going Icon
	 * 3 = Times up Icon
	 * 4 = Reserved Icon
	 */
	public RoomButton(int roomNumber, String guestName, String checkinDateTime, String checkoutDateTime, int status) {
		ImageIcon[] statusIcons = {availableStatus, on_goingStatus, times_upStatus, reservedStatus};
		Image icon = statusIcons[status-1].getImage().getScaledInstance(128, 16, Image.SCALE_SMOOTH);
		ImageIcon statusImageIcon = new ImageIcon(icon);
		roomStatus = status;
		roomNo = roomNumber;
		
		String textInButton = "<html><p style='font-size:20px; text-align:center;'>Room "+roomNumber+"</p>"
							+ "<p style='text-align:center;'><br>"+guestName+"<br>"+checkinDateTime+"<br>"+checkoutDateTime+"</p></html>";
		//importing the font and use them.
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/CODE Bold.otf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		
		this.setFont(new Font("Code Bold", Font.PLAIN, 18));
		this.setIcon(statusImageIcon);
		this.setText(textInButton);
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.TOP);
		this.setFocusable(false);
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("This is pressed. " + roomNo);
		manage = new ManageRoom(roomNo);
	}
	
	//Mouse Listener Yes
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
