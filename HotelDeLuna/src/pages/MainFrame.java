package pages;

import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	LoginPage login = new LoginPage();
	DashboardPanel dash = new DashboardPanel();
	
	
	public MainFrame() {
		this.setTitle("HOTEL DE LUNA");
		
		
		this.setUndecorated(true);	
		this.add(dash);	
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
