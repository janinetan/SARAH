import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartFrame extends JFrame {

	public static int frameWidth= 1657;
	public static int frameHeight=1000;
	private StartMenuPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
	public StartFrame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(30, 30, frameWidth, frameHeight);
		setTitle("SARAH");
		//setResizable(false);
		contentPane = new StartMenuPanel(this);
		setContentPane(contentPane);
	}

}
