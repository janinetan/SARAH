package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;
import viewElements.ImagePanel;
import viewElements.PanelBackNext;

public class WelcomePanel extends JPanel{
	public static CustomTextField name;
	private BufferedImage image;
	private ImageIcon icon;
	private Image image1;
	public WelcomePanel() throws IOException 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth*99/100,(int)(StartFrame.frameHeight*94.5/100));
		setBackground(new Color(197,229,240));
		setLayout(new BorderLayout());
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, StartFrame.frameWidth*4/100);
		
		ImagePanel liam = new ImagePanel("");
		image = ImageIO.read(new File("assets/liam_flipped.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*40/100, StartFrame.frameWidth*70/100,Image.SCALE_SMOOTH);
		liam.setImage(image1);
		/*ImagePanel box = new ImagePanel("assets/liam_dialog_box.png");
        box.setLayout(new BorderLayout());
        box.setLocation(0, 400);
        
        JTextArea greeting = new JTextArea("Hi, I’m Liam. Let’s play together!!");
        greeting.setSize(900, 400);
        greeting.setFont(font);
        greeting.setWrapStyleWord(true);
        greeting.setLineWrap(true);
        greeting.setOpaque(false);
        greeting.setEditable(false);
        greeting.setFocusable(false);
        greeting.setBorder(UIManager.getBorder("JLabel"));
     
		box.add(greeting, BorderLayout.EAST);*/
		
        JPanel dialogPanel = new JPanel(new BorderLayout());
		ImagePanel box = new ImagePanel();
		image = ImageIO.read(new File("assets/dialog_box.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*60/100,StartFrame.frameHeight*40/100,Image.SCALE_SMOOTH);
		box.setImage(image1);
		SpringLayout layout = new SpringLayout();
		box.setLayout(layout);
        JTextArea greeting = new JTextArea("Hi, I’m Liam.\nWhat's your name?");
        //greeting.setSize(550, 200);
        greeting.setSize(box.getWidth()*70/100,box.getHeight()*90/100);
        greeting.setFont(font);
        greeting.setWrapStyleWord(true);
        greeting.setLineWrap(true);
        greeting.setOpaque(false);
        greeting.setEditable(false);
        greeting.setFocusable(false);
        greeting.getCaret().deinstall( greeting );
        greeting.setBorder(UIManager.getBorder("JLabel"));
        //greeting.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		box.add(greeting);
		dialogPanel.add(box);
		dialogPanel.setOpaque(false);
		dialogPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameHeight*15/100,0,0,0));
		// For horizontal Alignment
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, greeting, 0, SpringLayout.HORIZONTAL_CENTER, box);

		// For Vertical Alignment
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, greeting, 0, SpringLayout.VERTICAL_CENTER, box);

        
        
		/*JLabel welcome = new JLabel("Hello there! What's your name?");
		welcome.setFont(font);
		welcome.setBounds(100,-50,1000,300);*/
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setOpaque(false);
//		rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel namePanel = new JPanel();
		SpringLayout nameLayout = new SpringLayout();
		namePanel.setLayout(nameLayout);
		namePanel.setOpaque(false);
		namePanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameHeight*5/100,StartFrame.frameWidth*8/100,0,0));
		name = new CustomTextField();
		name.setPlaceholder("Type Name Here");
		name.setOpaque(false);
		name.setBorder(UIManager.getBorder("JLabel"));
//		name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name.setFont(font);
		name.setColumns(StartFrame.frameWidth*2/150);
		JLabel line = new JLabel("________________");
//		line.setBounds(630,480,950,100);
		line.setFont(font);

        namePanel.add(name);
        namePanel.add(line);
        
        nameLayout.putConstraint(SpringLayout.NORTH, name, 0, SpringLayout.NORTH, namePanel);
        nameLayout.putConstraint(SpringLayout.NORTH, line, name.getHeight()+StartFrame.frameHeight*3/100, SpringLayout.NORTH, name);
        
		//this.add(welcome);
		rightPanel.add(dialogPanel,BorderLayout.NORTH);
		rightPanel.add(namePanel,BorderLayout.CENTER);
		rightPanel.add(new PanelBackNext(),BorderLayout.SOUTH);
		
		/*this.add(name);
		this.add(line);
		this.add(box);*/
		this.add(liam,BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
		validate();
	}
	
	public static String getPlayerName(){
		return name.getText();
	}
	
	
}
