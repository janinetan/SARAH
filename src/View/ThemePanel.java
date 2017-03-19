package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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

public class ThemePanel extends JPanel{
	private JTextArea message; 
	private String theme="";
	private ImageIcon image;
	private JButton sickButton1,sickButton2,sickButton3,sickButton4,sickButton5,sickButton6;
	public ThemePanel(JFrame main) throws IOException 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, 60);
		
		ImagePanel box = new ImagePanel("assets/cloud1.png");
		SpringLayout dialogLayout = new SpringLayout();

        message = new JTextArea("What do you want to learn about?");
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
		dialogLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0, SpringLayout.HORIZONTAL_CENTER, box);

		// For Vertical Alignment
		dialogLayout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, box);

        box.setLayout(dialogLayout);
        box.setLocation(0, 20);
        
        JPanel themes = new JPanel();
        SpringLayout themesLayout = new SpringLayout();
        //themes.setBounds(30, 450, 1600, 270);
        themes.setBounds(300, 310, 1300, 500);
        //themes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        themes.setOpaque(false);

		themes.setLayout(themesLayout);
        
        BufferedImage buttonIcon1 = ImageIO.read(new File("assets/sick1.png"));
		sickButton1 = new JButton(new ImageIcon(buttonIcon1));
		sickButton1.setBorder(BorderFactory.createEmptyBorder());
		sickButton1.setContentAreaFilled(false);
		sickButton1.setBorderPainted(false);
		sickButton1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	image = new ImageIcon("assets/sick1_clicked.png");
		    	sickButton1.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("rashes"))
		    	{
			    	image = new ImageIcon("assets/sick1.png");
			    	sickButton1.setIcon(image);
			    	
			    	
		    	}
		    }
		});
		sickButton1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "rashes";
		        image = new ImageIcon("assets/sick2.png");
		        sickButton2.setIcon(image);
		    	image = new ImageIcon("assets/sick3.png");
		    	sickButton3.setIcon(image);
		    	image = new ImageIcon("assets/sick4.png");
		    	sickButton4.setIcon(image);
		    	image = new ImageIcon("assets/sick5.png");
		    	sickButton5.setIcon(image);
		    	image = new ImageIcon("assets/sick6.png");
		    	sickButton6.setIcon(image);
		    }
		});
		
		BufferedImage buttonIcon2 = ImageIO.read(new File("assets/sick2.png"));
		sickButton2 = new JButton(new ImageIcon(buttonIcon2));
		sickButton2.setBorder(BorderFactory.createEmptyBorder());
		sickButton2.setContentAreaFilled(false);
		sickButton2.setBorderPainted(false);
		sickButton2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	image = new ImageIcon("assets/sick2_clicked.png");
		    	sickButton2.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("sneezing"))
		    	{
			    	image = new ImageIcon("assets/sick2.png");
			    	sickButton2.setIcon(image);
		    	}
		    }
		});
		sickButton2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "sneezing";
		        image = new ImageIcon("assets/sick1.png");
		        sickButton1.setIcon(image);
		    	image = new ImageIcon("assets/sick3.png");
		    	sickButton3.setIcon(image);
		    	image = new ImageIcon("assets/sick4.png");
		    	sickButton4.setIcon(image);
		    	image = new ImageIcon("assets/sick5.png");
		    	sickButton5.setIcon(image);
		    	image = new ImageIcon("assets/sick6.png");
		    	sickButton6.setIcon(image);
		    }
		});
		
		BufferedImage buttonIcon3 = ImageIO.read(new File("assets/sick3.png"));
		sickButton3 = new JButton(new ImageIcon(buttonIcon3));
		sickButton3.setBorder(BorderFactory.createEmptyBorder());
		sickButton3.setContentAreaFilled(false);
		sickButton3.setBorderPainted(false);
		sickButton3.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	image = new ImageIcon("assets/sick3_clicked.png");
		    	sickButton3.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("fever")){
			    	image = new ImageIcon("assets/sick3.png");
			    	sickButton3.setIcon(image);
		    	}
		    }
		});
		sickButton3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "fever";
		        image = new ImageIcon("assets/sick1.png");
		        sickButton1.setIcon(image);
		    	image = new ImageIcon("assets/sick2.png");
		    	sickButton2.setIcon(image);
		    	image = new ImageIcon("assets/sick4.png");
		    	sickButton4.setIcon(image);
		    	image = new ImageIcon("assets/sick5.png");
		    	sickButton5.setIcon(image);
		    	image = new ImageIcon("assets/sick6.png");
		    	sickButton6.setIcon(image);
		    }
		});
		
		BufferedImage buttonIcon4 = ImageIO.read(new File("assets/sick4.png"));
		sickButton4 = new JButton(new ImageIcon(buttonIcon4));
		sickButton4.setBorder(BorderFactory.createEmptyBorder());
		sickButton4.setContentAreaFilled(false);
		sickButton4.setBorderPainted(false);
		sickButton4.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/sick4_clicked.png");
		    	sickButton4.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("stomach")){
			    	ImageIcon image = new ImageIcon("assets/sick4.png");
			    	sickButton4.setIcon(image);
		    	}
		    }
		});
		sickButton4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "stomach";
		        image = new ImageIcon("assets/sick1.png");
		        sickButton1.setIcon(image);
		    	image = new ImageIcon("assets/sick2.png");
		    	sickButton2.setIcon(image);
		    	image = new ImageIcon("assets/sick3.png");
		    	sickButton3.setIcon(image);
		    	image = new ImageIcon("assets/sick5.png");
		    	sickButton5.setIcon(image);
		    	image = new ImageIcon("assets/sick6.png");
		    	sickButton6.setIcon(image);
		    }
		});
		
		BufferedImage buttonIcon5 = ImageIO.read(new File("assets/sick5.png"));
		sickButton5 = new JButton(new ImageIcon(buttonIcon5));
		sickButton5.setBorder(BorderFactory.createEmptyBorder());
		sickButton5.setContentAreaFilled(false);
		sickButton5.setBorderPainted(false);
		sickButton5.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	image = new ImageIcon("assets/sick5_clicked.png");
		    	sickButton5.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("cough"))
		    	{
			    	ImageIcon image = new ImageIcon("assets/sick5.png");
			    	sickButton5.setIcon(image);
		    	}
		    }
		});
		sickButton5.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	image = new ImageIcon("assets/sick5_clicked.png");
		    	sickButton5.setIcon(image);
		        theme = "cough";
		        image = new ImageIcon("assets/sick1.png");
		        sickButton1.setIcon(image);
		    	image = new ImageIcon("assets/sick2.png");
		    	sickButton2.setIcon(image);
		    	image = new ImageIcon("assets/sick3.png");
		    	sickButton3.setIcon(image);
		    	image = new ImageIcon("assets/sick4.png");
		    	sickButton4.setIcon(image);
		    	image = new ImageIcon("assets/sick6.png");
		    	sickButton6.setIcon(image);
		    }
		});
		
		
		BufferedImage buttonIcon6 = ImageIO.read(new File("assets/sick6.png"));
		sickButton6 = new JButton(new ImageIcon(buttonIcon6));
		sickButton6.setBorder(BorderFactory.createEmptyBorder());
		sickButton6.setContentAreaFilled(false);
		sickButton6.setBorderPainted(false);
		sickButton6.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	image = new ImageIcon("assets/sick6_clicked.png");
		    	sickButton6.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	if(!theme.equals("difficulty breathing")){
			    	image = new ImageIcon("assets/sick6.png");
			    	sickButton6.setIcon(image);
		    	}
		    }
		});
		sickButton6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        theme = "difficulty breathing";
		        image = new ImageIcon("assets/sick1.png");
		        sickButton1.setIcon(image);
		    	image = new ImageIcon("assets/sick2.png");
		    	sickButton2.setIcon(image);
		    	image = new ImageIcon("assets/sick3.png");
		    	sickButton3.setIcon(image);
		    	image = new ImageIcon("assets/sick4.png");
		    	sickButton4.setIcon(image);
		    	image = new ImageIcon("assets/sick5.png");
		    	sickButton5.setIcon(image);
		    }
		});
		
		
		themes.add(sickButton1);
		themes.add(sickButton2);
		themes.add(sickButton3);
		themes.add(sickButton4);
		themes.add(sickButton5);
		themes.add(sickButton6);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton1, 0, SpringLayout.WEST,themes);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton2, 100, SpringLayout.EAST, sickButton1);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton3, 100, SpringLayout.EAST, sickButton2);
/*		themesLayout.putConstraint(SpringLayout.WEST, sickButton4, 30, SpringLayout.EAST, sickButton3);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton5, 30, SpringLayout.EAST, sickButton4);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton6, 30, SpringLayout.EAST, sickButton5);*/
		
		themesLayout.putConstraint(SpringLayout.NORTH, sickButton4, 30, SpringLayout.SOUTH, sickButton1);
		themesLayout.putConstraint(SpringLayout.NORTH, sickButton5, 30, SpringLayout.SOUTH, sickButton2);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton5, 100, SpringLayout.EAST, sickButton4);
		themesLayout.putConstraint(SpringLayout.NORTH, sickButton6, 30, SpringLayout.SOUTH, sickButton3);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton6, 100, SpringLayout.EAST, sickButton5);
		
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
		    	/*not working when mouselistener code was added*/
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
        
		this.add(box);
		this.add(themes);
		this.add(nextButton);
		this.add(backButton);
		validate();
	}
	
	public String getTheme(){
		return theme;
	}
}
