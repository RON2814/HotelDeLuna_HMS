package pages;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	LoginPage login = new LoginPage();
	
	public MainFrame() {
		this.add(login);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
