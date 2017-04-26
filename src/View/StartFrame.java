package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class StartFrame extends JFrame {
//
//	public static int frameWidth= 1657;
//	public static int frameHeight=1000;
//	public static int frameWidth= 1657 + 500;
//	public static int frameHeight=1000 + 500;
//	private StartMenuPanel contentPane;
	private JScrollPane scrPane;
	private JPanel curPanel;

	/**
	 * Create the frame.
	 * @return 
	 * @throws IOException 
	 */
//	public StartFrame() throws IOException {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		frameWidth =(int)screenSize.getWidth()*70/100;
//		frameHeight = (int)screenSize.getHeight()*70/100;
//		setBounds(30, 30, frameWidth,frameHeight);
//		setTitle("SARAH");
//		//setResizable(false);
//
////		for hd desktop display
//		
//		
////		for non hd desktop display
////		curPanel = new StartMenuPanelTest();
////		curPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));
////		scrPane = new JScrollPane(curPanel);
////		add(scrPane);
//		
//		
//	}
	
	public static int frameWidth;
	public static int frameHeight;

	public StartFrame() throws IOException {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        double w = dim.width*0.70;		// 70% of screen
        double h = dim.height*0.80;	// 80% of screen
		this.setSize((new Double(w)).intValue(), (new Double(h)).intValue());
		frameWidth = (new Double(w)).intValue();
		frameHeight = (new Double(h)).intValue();
		
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle("SARAH");
		this.setLayout(new BorderLayout());
		
		curPanel = new StartMenuPanelTest();
		this.setContentPane(curPanel);
		
		this.setVisible(true);
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
