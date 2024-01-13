import javax.swing.JFrame;
import javax.swing.JLabel;

public class trynofunction {
	public trynofunction() {
		System.out.println("Hello World!");
	}
	
public static void main(String[] args) {
	System.out.println("Hi Aaron");
	
	JFrame frame = new JFrame("AAron");
	JLabel halo = new JLabel("HALO!");
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(halo);
	frame.setBounds(100,100,500,500);
	frame.setVisible(true);
	
}
}
