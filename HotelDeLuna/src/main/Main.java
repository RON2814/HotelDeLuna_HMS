package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import pages.MainFrame;

public class Main {

	public static void main(String[] args) {
		//importing the font and use them in the project.
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/TheNextFont.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/Roboto-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/CODE Bold.otf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/AltoneTrial-Regular.ttf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		
		new MainFrame();
	}

}
