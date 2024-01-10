package pages;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	LoginPage login = new LoginPage();
	Dashboard dash = new Dashboard();
	
	public MainFrame() {
		this.setTitle("HOTEL DE LUNA");
		this.add(dash);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
