package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import viewElements.PanelHome;
import viewElements.PanelNext;

public class StartFrame extends JFrame {
//
//	public static int frameWidth= 1657;
//	public static int frameHeight=1000;
//	public static int frameWidth= 1657 + 500;
//	public static int frameHeight=1000 + 500;
//	private StartMenuPanel contentPane;
	private JScrollPane scrPane;
	private JPanel curPanel;
	private JLayeredPane lPane;

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

//	public StartFrame() throws IOException {
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        double w = dim.width*0.70;		// 70% of screen
//        double h = dim.height*0.80;	// 80% of screen
//		this.setSize((new Double(w)).intValue(), (new Double(h)).intValue());
//		frameWidth = (new Double(w)).intValue();
//		frameHeight = (new Double(h)).intValue();
//		
//		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
//		this.setTitle("SARAH");
//		this.setLayout(new BorderLayout());
//		
//		curPanel = new StartMenuPanelTest();
//		this.setContentPane(curPanel);
//		
//		this.setVisible(true);
//	}
	
	public static double w;
	public static double h;
	public static Dimension s;
	Scanner sc = new Scanner(System.in);

	public StartFrame() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        w = dim.width*0.70;		// 70% of screen
        h = dim.height*0.80;	// 80% of screen

        int innerW = (int) (w - getInsets().left - getInsets().right);
        int innerH = (int) (h - getInsets().top - getInsets().bottom);
        s = new Dimension(innerW, innerH);
        
        this.setSize((new Double(w)).intValue(), (new Double(h)).intValue());
		frameWidth = (new Double(w)).intValue();
		frameHeight = (new Double(h)).intValue();

		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setTitle("SARAH");
		this.setLayout(new BorderLayout());
		
//		StoryPanel mainpanel = new StoryPanel("assets/loc-playground.png");
		
		lPane = new JLayeredPane();
		try {
			changePanel(new StartMenuPanelTest());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.add(lPane, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void changePanel (JPanel panel){
		curPanel = panel;
		lPane.removeAll();
		
		if (panel instanceof StoryPanelRaisa ){
			JPanel homePanel = new JPanel();
			homePanel.setOpaque(false);
			homePanel.setSize(StartFrame.s);
			homePanel.setLayout(new BorderLayout());
			homePanel.add(new PanelHome(), BorderLayout.NORTH);
			
			JPanel nextPanel = new JPanel();
			nextPanel.setOpaque(false);
			nextPanel.setSize(StartFrame.s);
			nextPanel.setLayout(new BorderLayout());
			nextPanel.add(new PanelNext(), BorderLayout.SOUTH);
			lPane.add(homePanel, new Integer(1), 0);
			lPane.add(nextPanel, new Integer(2), 0);
		}
		
		lPane.add(panel, new Integer(0), 0);
		
		lPane.repaint();
		lPane.revalidate();
	}
	
//	public void changePanel(JPanel panel){
////		panel.setPreferredSize(new Dimension(frameWidth, frameHeight));
////		scrPane = new JScrollPane(panel);
////		add(scrPane);
////		add(panel);
//		curPanel.removeAll();
//		curPanel = panel;
//		this.setContentPane(curPanel);
//		this.revalidate();
//		this.repaint();
//		System.out.println("done");
//	}
	
	public JPanel getCurPanel() {
		return curPanel;
	}

}
