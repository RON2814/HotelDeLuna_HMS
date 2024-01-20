package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class SplashScreen extends JFrame {
	
	
	JProgressBar progressBar;
	JLabel eduzone, quote;
	private JPanel panel;
	BufferedImage logo;
	
	SplashScreen(){
		
		try {
			logo = null;
			logo = ImageIO.read(getClass().getResource("/assets/logo.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
				
				progressBar = new JProgressBar(0, 2000);
				progressBar.setBounds(0,295, 502, 35);
				progressBar.setMinimum(0); 
		        progressBar.setMaximum(100);				
				progressBar.setForeground(Color.white);
				progressBar.setBackground(Color.decode("#004aad"));
				Border border = BorderFactory.createLineBorder(Color.decode("#004aad"));
				progressBar.setBorder(border);
				add(progressBar);
							
				setSize(502, 320);
				setIconImage(Toolkit.getDefaultToolkit().getImage("//C:/Users/Admin/git/HotelDeLuna_HMS/HotelDeLuna/src/assets/logo.png"));
				setLayout(null);
				setLocationRelativeTo(null);
				setUndecorated(true);
				
				panel = new logoPanel();
				panel.setBounds(0, 0, getWidth(), getHeight());
				panel.setBackground(Color.decode("#004aad"));
				add(panel);
							
				setVisible(true);
				
				simulateLoading();
	}
	
	class logoPanel extends JPanel {
		
		protected void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			
			  Graphics2D graphics2d = (Graphics2D) graphics.create();
			  Font fontOne = new Font ("Felix Titling", Font.BOLD, 25);
			  
			  
		        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		        
		        
		        graphics.setColor(Color.WHITE);
		        graphics.setFont(fontOne);
		        graphics.drawString("Hotel De Luna", 152 , 260);
		        
		        graphics.drawImage(logo, 10, 17, 500, 300, null);
			
		}
	}
	
	private void simulateLoading() {
		  Thread thread = new Thread(() -> {
	            for (int i = 0; i <= 100; i++) {
	                try {
	                    Thread.sleep(20); 
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                progressBar.setValue(i);
	            }
	            if (progressBar.getValue() == 100) {
	            	dispose();
	            	
	                Login accessFrame = new Login();
	                accessFrame.setVisible(true);
	            }
	        });
	        thread.start();
	   
	}
	
	
	public static void main(String [] args) {
		
		new SplashScreen();
	}

}
