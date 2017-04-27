package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;

public class LocationPanel extends JPanel{
	private JTextArea message; 
	private String theme="";
	private ImageIcon imageIcon;
	private JButton locationButton1,locationButton2,locationButton3;
	BufferedImage image;
	ImageIcon icon;
	Image image1;
	public LocationPanel(JFrame main) throws IOException 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(new BorderLayout());
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, 60);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameHeight*2/100,0,0,0));
		dialogPanel.setOpaque(false);
		ImagePanel box = new ImagePanel();
		image = ImageIO.read(new File("assets/cloud2.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*80/100, StartFrame.frameHeight*30/100,Image.SCALE_SMOOTH);
		box.setImage(image1);
		SpringLayout dialogLayout = new SpringLayout();
        box.setLayout(dialogLayout);
        message = new JTextArea("Where do you want to go?");
        message.setSize(1000, 200);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall( message );
        message.setBorder(UIManager.getBorder("JLabel"));
		box.add(message);
		
		// For horizontal Alignment
		dialogLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, StartFrame.frameWidth*10/100, SpringLayout.HORIZONTAL_CENTER, box);

		// For Vertical Alignment
		dialogLayout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, box);

		dialogPanel.add(box);
        
        JPanel themes = new JPanel();
        SpringLayout themesLayout = new SpringLayout();
        //themes.setBounds(30, 450, 1600, 270);
        themes.setBounds(50, 310, 1500, 500);
        //themes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        themes.setOpaque(false);

		themes.setLayout(themesLayout);
        
        image = ImageIO.read(new File("assets/loc1.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*28/100, StartFrame.frameHeight*30/100,Image.SCALE_SMOOTH);
		locationButton1 = new JButton(new ImageIcon(image1));
		locationButton1.setBorder(BorderFactory.createEmptyBorder());
		locationButton1.setContentAreaFilled(false);
		locationButton1.setBorderPainted(false);
		locationButton1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	imageIcon = new ImageIcon("assets/loc1_clicked.png");
		    	locationButton1.setIcon(imageIcon);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("home"))
		    	{
			    	imageIcon = new ImageIcon("assets/loc1.png");
			    	locationButton1.setIcon(imageIcon);
			    	
			    	
		    	}
		    }
		});
		locationButton1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "home";
		        imageIcon = new ImageIcon("assets/loc2.png");
		        locationButton2.setIcon(imageIcon);
		    	imageIcon = new ImageIcon("assets/loc3.png");
		    	locationButton3.setIcon(imageIcon);
		    }
		});
		
		image = ImageIO.read(new File("assets/loc2.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*28/100, StartFrame.frameHeight*30/100,Image.SCALE_SMOOTH);
		locationButton2 = new JButton(new ImageIcon(image1));
		locationButton2.setBorder(BorderFactory.createEmptyBorder());
		locationButton2.setContentAreaFilled(false);
		locationButton2.setBorderPainted(false);
		locationButton2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	imageIcon = new ImageIcon("assets/loc2_clicked.png");
		    	locationButton2.setIcon(imageIcon);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("school"))
		    	{
			    	imageIcon = new ImageIcon("assets/loc2.png");
			    	locationButton2.setIcon(imageIcon);
		    	}
		    }
		});
		locationButton2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "school";
		        imageIcon = new ImageIcon("assets/loc1.png");
		        locationButton1.setIcon(imageIcon);
		    	imageIcon = new ImageIcon("assets/loc3.png");
		    	locationButton3.setIcon(imageIcon);
		    }
		});
		
		image = ImageIO.read(new File("assets/loc3.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*28/100, StartFrame.frameHeight*30/100,Image.SCALE_SMOOTH);
		locationButton3 = new JButton(new ImageIcon(image1));
		locationButton3.setBorder(BorderFactory.createEmptyBorder());
		locationButton3.setContentAreaFilled(false);
		locationButton3.setBorderPainted(false);
		locationButton3.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	imageIcon = new ImageIcon("assets/loc3_clicked.png");
		    	locationButton3.setIcon(imageIcon);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("park")){
			    	imageIcon = new ImageIcon("assets/loc3.png");
			    	locationButton3.setIcon(imageIcon);
		    	}
		    }
		});
		locationButton3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "park";
		        imageIcon = new ImageIcon("assets/loc1.png");
		        locationButton1.setIcon(imageIcon);
		    	imageIcon = new ImageIcon("assets/loc2.png");
		    	locationButton2.setIcon(imageIcon);
		    }
		});
		
		
		
		themes.add(locationButton1);
		themes.add(locationButton2);
		themes.add(locationButton3);
		themesLayout.putConstraint(SpringLayout.WEST, locationButton1, 0, SpringLayout.WEST,themes);
		themesLayout.putConstraint(SpringLayout.WEST, locationButton2, 50, SpringLayout.EAST, locationButton1);
		themesLayout.putConstraint(SpringLayout.WEST, locationButton3, 50, SpringLayout.EAST, locationButton2);
		
        BufferedImage nextButtonIcon = ImageIO.read(new File("assets/next_button.png"));
		JButton nextButton = new JButton(new ImageIcon(nextButtonIcon));
		nextButton.setBorder(BorderFactory.createEmptyBorder());
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setBounds(1150,780,500,200);
		nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/next_button_clicked.png");
		    	nextButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/next_button.png");
		    	nextButton.setIcon(image);
		    }
//		    public void mouseClicked(java.awt.event.MouseEvent evt)
//		    {
//		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
//		        startButton.setIcon(image);
//		    }
		    
		});
		nextButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	StartFrameController.displayStartStory(theme);
		    }
		});
		
		BufferedImage backButtonIcon = ImageIO.read(new File("assets/back_button.png"));
		JButton backButton = new JButton(new ImageIcon(backButtonIcon));
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setBounds(600,780,500,200);
		backButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/back_button_clicked.png");
		    	backButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/back_button.png");
		    	backButton.setIcon(image);
		    }
//		    public void mouseClicked(java.awt.event.MouseEvent evt)
//		    {
//		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
//		        startButton.setIcon(image);
//		    }
		    
		});
		backButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/*not working when mouselistener code was added*/
		    	ImageIcon image = new ImageIcon("assets/back_button_clicked.png");
		    	backButton.setIcon(image); 
		        StartFrameController.displayWelcome();
		    }
		});
        
		this.add(dialogPanel, BorderLayout.NORTH);
		this.add(themes);
		this.add(nextButton);
		this.add(backButton);
		validate();
	}
	
	public String getTheme(){
		return theme;
	}
}
