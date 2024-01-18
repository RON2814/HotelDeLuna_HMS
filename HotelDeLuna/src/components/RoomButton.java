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
import pages.ManageRoom;

public class RoomButton extends JButton implements ActionListener, MouseListener{
	private int roomStatus;
	private int roomNo;
	private String guestName;
	private String checkIn, checkOut;
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
		ImageIcon[] statusIcons = {availableStatus, on_goingStatus, times_upStatus, reservedStatus};
		Image icon = statusIcons[status-1].getImage().getScaledInstance(128, 16, Image.SCALE_SMOOTH);
		ImageIcon statusImageIcon = new ImageIcon(icon);
		roomStatus = status;
		roomNo = roomNumber;
		this.guestId = guestId;
		
		textInButton = "<html><p style='font-size:20px; text-align:center;'>Room "+roomNumber+"</p>"
						+ "<p style='text-align:center;'><br>"+guestName+"<br>"+checkinDateTime+"<br>"+checkoutDateTime+"</p></html>";
		//importing the font and use them.
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/CODE Bold.otf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		
		 
		
		setForeground(Color.WHITE);
		setFont(new Font("Code Bold", Font.PLAIN, 18));
		setBackground(Color.decode("#004aad"));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setFocusable(false);
		setBorder(null);
		setIcon(statusImageIcon);
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
		textInButton = "<html><p style='font-size:20px; text-align:center;'>Room "+roomNo+"</p>"
				+ "<p style='text-align:center;'><br>"+guestName+"<br>"+checkin+"<br>"+checkout+"</p></html>";
		this.setText(textInButton);
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("This is pressed. " + roomNo);
		System.out.println(guestId);
		manage = new ManageRoom(roomNo, guestId);
		this.revalidate();
		this.repaint();
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
