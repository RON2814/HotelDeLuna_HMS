package pages;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import components.RoundedButton;



public class Login extends JFrame  {

	//Encapsulation and Instantiate
	private JPanel panel;
	private static final int IMAGE_WIDTH = 400;
	private static final int IMAGE_HEIGHT = 500;
	private BufferedImage logoBlue, background, userIcon;
    static JTextField name;
    static JPasswordField password;
    private  Color[] colors = {Color.black, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED};  
    private JToggleButton showHideButton;
    private Font fontOne = new Font("Arial Black", Font.PLAIN, 13);
    private Font fontTwo = new Font("Arial", Font.BOLD, 20);
    private Font fontThree = new Font("Arial", Font.PLAIN, 15);
    private ImageIcon showIcon = new ImageIcon("//C:/Users/Admin/git/HotelDeLuna_HMS/HotelDeLuna/src/assets/hide.png"); 
    private ImageIcon hideIcon = new ImageIcon("//C:/Users/Admin/git/HotelDeLuna_HMS/HotelDeLuna/src/assets/eye.png");
	private JLabel exit;
    //Main to run the whole code
	public static void main(String [] args) {
		
		Login frame = new Login();
		frame.setVisible(true);
	}
	
	//constructor of this class
	public Login() {
		
		
		//load images
		try {		
			logoBlue = null;
			background = null;
			userIcon = null; 	
			
			logoBlue = ImageIO.read(getClass().getResource("/assets/logoBlue.png"));
			background = ImageIO.read(getClass().getResource("/assets/background.png"));
			userIcon = ImageIO.read(getClass().getResource("/assets/user.png"));
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//coordinates of the frame
		setSize(800, 500);
		setIconImage(Toolkit.getDefaultToolkit().getImage("//C:/Users/Admin/git/HotelDeLuna_HMS/HotelDeLuna/src/assets/logo.png"));
		setUndecorated(true);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
		setLocationRelativeTo(null);
		
		//adding the loginUi class that extends JPanel to the frame
		panel = new loginUi();
		panel.setBounds(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
		panel.setBackground(Color.WHITE);
		panel.setOpaque(false);
		panel.setLayout(null);
	    add(panel);
	}
	
	//panel added to the frame for layout and designing the ui, components with graphics
	@SuppressWarnings("serial")
	class loginUi extends JPanel {		
		protected void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			
			//graphics2d for clearer graphics
			Graphics2D graphics2d = (Graphics2D) graphics.create();
			//stroke for line weight of borders
			BasicStroke stroke = new BasicStroke(2);
			//Rendering to make graphics clear not pixelated
	        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			//to draw images
	        graphics2d.drawImage(background, 0,0, 800, 500, null);
	        graphics2d.drawImage(logoBlue, 430, 50, 70, 40, null);
	        graphics2d.drawImage(userIcon, 690,228, 20, 17, null);
	        //string hotel de luna
	        graphics2d.setColor(Color.BLACK);
	        graphics2d.setFont(fontOne);
	        graphics2d.drawString("Hotel De Luna", 500,73);	
	        //string log in to your acc
	        graphics2d.setFont(fontTwo);
	        graphics2d.drawString("Log in to your account", 448,145); 
	        //string welcome back
	        graphics2d.setFont(fontThree);
	        graphics2d.drawString("welcome back!", 448,175);	        
	        //border design  for textfields
	        graphics2d.setColor(Color.BLACK);
            graphics2d.setStroke(stroke);
	        graphics2d.drawRoundRect(448, 220, 270, 35, 30, 30);
	        graphics2d.drawRoundRect(448, 290, 270, 35, 30, 30);
	        
	        //textfield of username 
	        name = new usernamePlaceHolder("Username");
	        name.setFont(new Font("Arial", Font.PLAIN, 13));
	        name.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, (colors)[0]));
	        name.setBounds(460, 222, 230, 33);
	        add(name);
	        name.setColumns(10);
	        name.setForeground(Color.black);
	        name.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, (colors)[0]));

	        // when clicked, the placeholder will disappear
//	        name.addFocusListener(new FocusListener() {
//	          public void focusGained(FocusEvent e) {
//	            if (name.getText().equals(" Username")) {
//	               name.setText("");
//	               name.setForeground(Color.BLACK);
//	            }
//	          }
//
//	          public void focusLost(FocusEvent e) {
//	            if (name.getText().isEmpty()) {
//	              name.setText(name.getText());
//	              name.setForeground(Color.BLACK);
//	            }
//	          }
//	        });
	        
	        //password field of password
	        password = new passwordPlaceHolder("Password");
	        password.setBounds(460, 292, 230, 33);	
	        password.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, (colors)[0]));
	        add(password);
	 
	        //button with icon of cross eye and eye for show and hiding the password
	        showHideButton = new JToggleButton(showIcon);
	        showHideButton.setBounds(690,300, 23, 20);
	        showHideButton.setBackground(Color.WHITE);
	        showHideButton.setFocusable(false);
	        showHideButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, (colors)[0]));
	        showHideButton.setSelectedIcon(hideIcon);
	        showHideButton.addActionListener(e -> {
	            if (showHideButton.isSelected()) {
	                password.setEchoChar((char) 0);
	                
	            } else {
	            	password.setText("");
	                password.setEchoChar('*'); // Unicode for black circle (default echo char for password fields)
	                
	            }
	        });

	        add(showHideButton);	        
	        showHideButton.setForeground(Color.black);
	        
	        // when clicked, the placeholder will disappear
	        showHideButton.addFocusListener(new FocusListener() {
	          public void focusGained(FocusEvent e) {
	            if (showHideButton.getText().equals(" Password")) {
	            	showHideButton.setText("");
	            	showHideButton.setForeground(Color.BLACK);
	            }
	          }

	          public void focusLost(FocusEvent e) {
	            if (showHideButton.getText().isEmpty()) {
	            	showHideButton.setText(" Password");
	            	showHideButton.setForeground(Color.BLACK);
	            }
	          }
	        });
	        
	        exit = new JLabel("X");
	        exit.setBounds(760, 10, 50, 25);
	        exit.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 21));
	        exit.setForeground(Color.decode("#004aad"));
	        exit.addMouseListener(new MouseAdapter() {	          
	            public void mouseClicked(MouseEvent e) {
	            	
	            	dispose();
	            }	            
	        });   
	        add(exit);
	        
	        //separate class that extends JButton to make button rounded
	        RoundedButton signInButton = new RoundedButton("SIGN IN");
	        signInButton.setBounds(515, 353, 147, 40);
	        signInButton.setFont(new Font("Arial Black", Font.BOLD, 11));
	        signInButton.setBackground(Color.decode("#004aad"));
	        add(signInButton);
	        signInButton.addMouseListener(new MouseAdapter() {
	            public void mouseEntered(MouseEvent e) {
	            	signInButton.setForeground(Color.decode("#004aad"));
	            	signInButton.setBackground(Color.WHITE);
            }
	            public void mouseExited(MouseEvent e) {
	            	signInButton.setBackground(Color.decode("#004aad"));
	            	signInButton.setForeground(Color.WHITE);
	            }
	            
	            public void mouseClicked(MouseEvent e) {
	            	
	            	dispose();
	            	
	            	MainFrame frame = new MainFrame();
	            	frame.setVisible(true);
		}	
	        });
		}
		
		//class that extends JTextfield for placeholder - username
		class usernamePlaceHolder extends JTextField{
		    public String text;
		    public usernamePlaceHolder(String name) {
		      this.text = name;
		      setFont(this.getFont());
		    }
		    public void paintComponent(Graphics g) {
		      super.paintComponent(g);
		      Graphics2D graphics2d = (Graphics2D) g.create();
				
		        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		      
		      if(!text.isEmpty()) {
		      graphics2d.setColor(Color.BLACK);
		      graphics2d.drawString(text, getInsets().left, getInsets().top + 20);
		      
		      }
		    }
		  }
		
		//class that extends JPasswordField for placeholder - username
		class passwordPlaceHolder extends JPasswordField{
		    public String text;
		    public passwordPlaceHolder(String password) {
		      this.text = password;
		      setFont(this.getFont());
		    }
		    
		    public void paintComponent(Graphics g) {
		      super.paintComponent(g);   
		      Graphics2D graphics2d = (Graphics2D) g.create();
				
		       graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		       graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		      
		      if(!text.isEmpty()) {
		      graphics2d.setColor(Color.BLACK);
		      graphics2d.drawString(text, getInsets().left, getInsets().top + 20);
		      
		      }		      
		    }
		}
	
		}
	}


