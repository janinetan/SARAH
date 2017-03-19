package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class StartFrame extends JFrame {

//	public static int frameWidth= 1657;
//	public static int frameHeight=1000;
	public static int frameWidth= 1657 + 500;
	public static int frameHeight=1000 + 500;
//	private StartMenuPanel contentPane;
	private JScrollPane scrPane;
	private JPanel curPanel;

	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
	public StartFrame() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(30, 30, frameWidth/2, frameHeight/2);
		setTitle("SARAH");
		//setResizable(false);

//		for hd desktop display
//		contentPane = new StartMenuPanel(this);
//		setContentPane(contentPane);
		
//		for non hd desktop display
		curPanel = new StartMenuPanel();
		curPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));
		scrPane = new JScrollPane(curPanel);
		add(scrPane);
	}
	
	public void changePanel(JPanel panel){
		this.curPanel = panel;
		this.remove(scrPane);
		this.revalidate();
		panel.setPreferredSize(new Dimension(frameWidth, frameHeight));
		scrPane = new JScrollPane(panel);
		add(scrPane);
		this.revalidate();
	}
	
	public JPanel getCurPanel() {
		return curPanel;
	}

}
