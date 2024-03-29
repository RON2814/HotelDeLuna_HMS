package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import components.RoomButton;
import database.ManageRoomDB;

public class DashboardPanel extends JPanel implements ActionListener {
	// database object calling
	private ManageRoomDB manageDb;

	private int roomspanelHeight = (130 + 10) * 2;

	private JPanel roomspanel, roomspanelButtons;
	private RoomButton room1, room2, room3, room4, room5, room6, room7, room8, room9, room10;
	private JLabel timer;
	private JTextField searchBar;

	private int circleSize = 50;

	Timer actionTimer;
	private Color[] colors = { Color.black, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED };
	private BufferedImage available, ongoing, exceed, reserved, logo, search;
	private JToggleButton searchButton;
	private JLabel userButton, logoutButton;

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
		// initializing components here..
		roomspanel = new JPanel();
		roomspanelButtons = new JPanel();
		// navTitle = new JLabel("HOTEL DE LUNA");
		timer = new JLabel();
		actionTimer = new Timer();

		roomspanel.setBounds(20, 230, 976, 340);
		roomspanel.setBackground(Color.WHITE);
		roomspanel.setLayout(null);
		roomspanel.add(roomspanelButtons);

		roomspanelButtons.setBounds(13, 30, 930, roomspanelHeight + 20);
		roomspanelButtons.setLayout(new GridLayout(2, 5, 10, 5));
		roomspanelButtons.setBackground(Color.WHITE);

		// inicialize database each buttons
		manageDb = new ManageRoomDB();

		// buttons for manage room...also can add more here
		manageDb.getRoomData(1);
		room1 = new RoomButton(1, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(2);
		room2 = new RoomButton(2, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(3);
		room3 = new RoomButton(3, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(4);
		room4 = new RoomButton(4, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(5);
		room5 = new RoomButton(5, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(6);
		room6 = new RoomButton(6, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(7);
		room7 = new RoomButton(7, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(8);
		room8 = new RoomButton(8, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(9);
		room9 = new RoomButton(9, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());
		manageDb.getRoomData(10);
		room10 = new RoomButton(10, manageDb.getGuestId(), manageDb.getGuestName(), manageDb.getCheckIn(),
				manageDb.getCheckOut(), manageDb.getRoomAvail());

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
		timer.setBounds(1062, 685, 200, 20);
		timer.setFont(new Font("Roboto Regular", Font.BOLD, 20));
		timer.setForeground(Color.white);
		currentDateTime();

		this.setLayout(null);

		this.add(roomspanel);
		// this.add(navTitle);
		this.add(timer);
		this.setPreferredSize(new Dimension(1015, 585));
		this.setBackground(Color.WHITE);

	}

	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		Graphics2D graphics2d = (Graphics2D) graphics.create();
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		GradientPaint gradient = new GradientPaint(430, 0, Color.BLACK, getWidth(), getHeight(),
				Color.decode("#004aad"));

		Font fontOne = new Font("Arial", Font.BOLD, 11);
		Font fontTwo = new Font("Felix Titling", Font.BOLD, 15);

		graphics2d.setColor(Color.decode("#004aad"));
		graphics2d.fillRoundRect(-50, 25, 100, 195, 50, 50);

		graphics2d.setPaint(gradient);
		graphics2d.fillRoundRect(80, 25, 880, 195, 50, 50);

		// roundedrectangle background of textfield
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRoundRect(420, 45, 500, 35, 30, 30);

		// circles graphics white
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

		graphics2d.drawImage(available, 463, 112, 45, 45, null);
		graphics2d.drawImage(ongoing, 588, 114, 43, 43, null);
		graphics2d.drawImage(exceed, 709, 114, 43, 43, null);
		graphics2d.drawImage(reserved, 829, 114, 40, 40, null);
		graphics2d.drawImage(logo, 110, 35, 280, 160, null);
		// graphics2d.drawImage(search, 878, 50, 25, 25, null );

		graphics2d.setFont(fontTwo);
		graphics2d.drawString("Hotel  De  Luna", 185, 180);

		searchBar = new searchPlaceHolder("Room Search");
		searchBar.setFont(new Font("Arial", Font.PLAIN, 13));
		searchBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, (colors)[0]));
		searchBar.setBounds(435, 45, 400, 36);
		this.add(searchBar);
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
		searchButton.setBounds(875, 48, 30, 30);
		searchButton.setBackground(Color.WHITE);
		searchButton.setFocusable(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, (colors)[0]));
		searchButton.setIcon(new ImageIcon("src/assets/searchIcon.png"));
		searchButton.addActionListener(e -> {

		});

		this.add(searchButton);

		// user profile button
		userButton = new JLabel();
		userButton.setBounds(15, 80, 35, 35);
		userButton.setBackground(Color.WHITE);
		userButton.setFocusable(false);
		userButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, (colors)[0]));
		userButton.setIcon(new ImageIcon("src/assets/userIcon.png"));
		userButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		// logout icon below the profile icon
		logoutButton = new JLabel();
		logoutButton.setBounds(15, 130, 35, 35);
		logoutButton.setBackground(Color.WHITE);
		logoutButton.setFocusable(false);
		logoutButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, (colors)[0]));
		logoutButton.setIcon(new ImageIcon("src/assets/logoutIcon.png"));
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int logout = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
						JOptionPane.OK_CANCEL_OPTION);
				if (logout == JOptionPane.YES_OPTION) {

					MainFrame frame = new MainFrame();
					frame.setVisible(false);
					frame.dispose();
					frame.setVisible(false);
					frame.dispose();
					System.out.println("LOGOUT PRESSED");

					Login login = new Login();
					login.setVisible(true);
				}
			}
		});
		this.add(logoutButton);
		this.add(userButton);
	}

	class searchPlaceHolder extends JTextField {
		public String text;

		public searchPlaceHolder(String name) {
			this.text = name;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graphics2d = (Graphics2D) g.create();

			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			if (getText().isBlank()) {
				graphics2d.setColor(Color.BLACK);
				graphics2d.drawString(text, getInsets().left, g.getFontMetrics().getAscent() + getInsets().top + 15);

			}
		}
	}

	public void currentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime now = LocalDateTime.now();
		timer.setText(dtf.format(now));
		timer.revalidate();
		timer.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
