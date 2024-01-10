package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginPage extends JPanel{
	ImageIcon title = new ImageIcon("src/assets/TitleLogo.png");
	
	public LoginPage() {
		JLabel lblTitle = new JLabel();
		Image img = title.getImage().getScaledInstance(350, 170, Image.SCALE_SMOOTH);
		title = new ImageIcon(img);
		lblTitle.setIcon(title);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setBounds(0, 0, 350, 170);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.gray);
		loginPanel.setBounds(615, 160, 500, 400);
		
		JTextField loginUser = new JTextField(1);
		
		this.setLayout(null);
		this.add(lblTitle);
		this.add(loginPanel);
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(new Color(22,22,22));
	}
	
}
