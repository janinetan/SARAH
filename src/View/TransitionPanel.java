package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;
import viewElements.ImagePanel;

public class TransitionPanel extends JPanel{
	private JTextArea message;
	private CustomTextArea answer;
	private JPanel leftPanel, rightPanel;
	private BufferedImage image;
	private ImageIcon icon;
	private Image image1;
	private Image myImage;
	private JPanel IP;
	
	public TransitionPanel(JFrame main) throws IOException {
		IP = this;
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,(int)(StartFrame.frameWidth*98.6/100),(int)(StartFrame.frameHeight*94.5/100));
		//setBackground("assets/setandrise.gif");
		setLayout(new BorderLayout());
		setOpaque(false);
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameWidth*7/100, 0,0,0));
		rightPanel.setOpaque(false);
		leftPanel = new JPanel(new BorderLayout());
		leftPanel.setOpaque(false);
		Font font = new Font("Comic Sans MS", Font.PLAIN, (int)(StartFrame.frameHeight*8/100));
		
		ImageIcon icon = new ImageIcon("assets/setandrise.gif");
        JLabel bg = new JLabel();
        bg.setIcon(new ImageIcon(icon.getImage().getScaledInstance((int)(StartFrame.frameWidth*98.6/100),(int)(StartFrame.frameHeight), Image.SCALE_DEFAULT)));
        JLabel sentence = new JLabel("After a few days...");
        sentence.setFont(font);
	        
		JPanel background = new JPanel();
		background.setSize(StartFrame.s);
		background.setLayout(new BorderLayout());
		background.add(bg);
		
		JPanel label = new JPanel();
		label.setSize(StartFrame.s);
		label.setOpaque(false);
		label.setLayout(new GridBagLayout());
		label.add(sentence);
		
		JLayeredPane mainPanel = new JLayeredPane();
		mainPanel.setSize(StartFrame.s);
		mainPanel.add(background, new Integer(0), 0);
		mainPanel.add(label, new Integer(1), 0);
		this.add(mainPanel);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		    @Override
		   public void run() {
		       /*try {
		    	   System.out.println("hello");
		    	   CALL FUNCTION HERE TO GO TO NEXT PANEL
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		    }
		}, 7000);
	}
	
	public void setBackground( String backgroundImagePath ){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(backgroundImagePath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = StartFrame.frameHeight;		
			double w = StartFrame.frameWidth;	
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			this.myImage = image;
			this.revalidate();
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
    protected void paintComponent(Graphics g){ 
        super.paintComponent(g);    
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(myImage, 0, 0, null);
    } 
}
