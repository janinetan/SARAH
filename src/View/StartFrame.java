package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import driver.StoryGenerator;

public class StartFrame extends JFrame {

	public static int frameWidth= 1657;
	public static int frameHeight=1000;
	private StartMenuPanel contentPane;

	
	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
	public StartFrame(StoryGenerator controller) throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(30, 30, frameWidth, frameHeight);
		setTitle("SARAH");
		//setResizable(false);
		contentPane = new StartMenuPanel(this,controller);
		setContentPane(contentPane);
	}

}
