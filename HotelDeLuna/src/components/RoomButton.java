package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import components.RoundedButton;
import pages.MainFrame;
import pages.ManageRoom;

public class RoomButton extends JButton implements ActionListener{
	private int roomNo;
	private int guestId;
	
	private ManageRoom manage;
	
	private String textInButton;
	
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
	public RoomButton(int roomNumber,int guestId, String guestName, String checkinDateTime, String checkoutDateTime, int status) {
		/*mageIcon[] statusIcons = {availableStatus, on_goingStatus, times_upStatus, reservedStatus};
		if(status < 1) {
			status = 1;
		}
		Image icon = statusIcons[status-1].getImage().getScaledInstance(128, 16, Image.SCALE_SMOOTH);
		ImageIcon statusImageIcon = new ImageIcon(icon); */
		roomNo = roomNumber;
		this.guestId = guestId;
		
		textInButton = "<html><p style='font-size:15px; text-align:center;'>Room "+roomNumber+"</p>"
						+ "<p style='text-align:center;'><br>"+guestName+"<br>"+checkinDateTime+"<br>"+checkoutDateTime+"</p></html>";

		 
		
		setForeground(Color.WHITE);
		setFont(new Font("Code Bold", Font.PLAIN, 18));
		setBackground(Color.decode("#004aad"));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setFocusable(false);
		setBorder(null);
		setIcon(roomAvailability(status));
		setText(textInButton);
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.TOP);
		addActionListener(this);
		
	}
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 100); // Set a fixed preferred size for the button
    }
	@Override
	protected void paintComponent(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    int width = getWidth();
	    int height = getHeight();
	    g2d.setColor(getBackground());
	    g2d.fillRoundRect(0, 0, width, height, 50, 50);

	    super.paintComponent(g);
	}
	
	
	
	
	public void setButtonText(int roomNo, int guestId, String guestName, String checkin, String checkout) {
		textInButton = "<html><p style='margin:0; font-size:20px; text-align:center;'>Room "+roomNo+"</p>"
				+ "<p style='margin:0; text-align:center;'>"+guestName+"<br>"+checkin+"<br>"+checkout+"</p></html>";
		this.setText(textInButton);
		this.revalidate();
		this.repaint();
	}
	
	public int getRoomNo() {
		return this.roomNo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(guestId);
		manage = new ManageRoom(roomNo, guestId, this);
	}
	
	//update the text inside this button
	public void updateText(String guestName, String checkinDateTime, String checkoutDateTime, int status) {
		textInButton = "<html><p style='font-size:15px; text-align:center;'>Room "+roomNo+"</p>"
						+ "<p style='text-align:center;'><br>"+guestName+"<br>"+checkinDateTime+"<br>"+checkoutDateTime+"</p></html>";
		this.setText(textInButton);
		this.setIcon(roomAvailability(status));
	}
	
	//imageicon for room availability
	public ImageIcon roomAvailability(int status) {
		/* 
		 * Status NAMING
		 * 1 = Available Icon
		 * 2 = On going Icon
		 * 3 = Times up Icon
		 * 4 = Reserved Icon
		 */
		
		ImageIcon[] statusIcons = {availableStatus, on_goingStatus, times_upStatus, reservedStatus};
		if(status < 1) {
			status = 1;
		}
		Image icon = statusIcons[status-1].getImage().getScaledInstance(128, 16, Image.SCALE_SMOOTH);
		ImageIcon statusImageIcon = new ImageIcon(icon);
		
		return statusImageIcon;
	}
	
}
