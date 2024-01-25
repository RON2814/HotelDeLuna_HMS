package pages;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	DashboardPanel dash = new DashboardPanel();
	ManageRoom manage = new ManageRoom();
	
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
