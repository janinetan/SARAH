package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartMenuPanelTest extends JPanel{
	JPanel buttonPanel;
	BufferedImage image;
	ImageIcon icon;
	Image image1;
	Color c;
	public StartMenuPanelTest() throws IOException
	{
		System.out.println("hi");
		c = new Color(197,229,240);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(c);
		SpringLayout mainLayout = new SpringLayout();
		setLayout(new BorderLayout());
		
		SpringLayout leftLayout = new SpringLayout();
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(leftLayout);
		leftPanel.setBackground(c);
		
        ImagePanel title = new ImagePanel();
		image = ImageIO.read(new File("assets/title.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*45/100, StartFrame.frameHeight*30/100,Image.SCALE_SMOOTH);
		title.setImage(image1);
        //title.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		leftPanel.setBorder(BorderFactory.createEmptyBorder(0,StartFrame.frameWidth*15/100,0,0));
        
        JPanel rightPanel = new JPanel();
        ImagePanel character = new ImagePanel();
		image = ImageIO.read(new File("assets/tilted_sarah.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*28/100,StartFrame.frameHeight*80/100,Image.SCALE_SMOOTH);
		character.setImage(image1);
        //character.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //rightPanel.add(character, BorderLayout.PAGE_END);
        
        rightPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameHeight*10/100,0,0,StartFrame.frameWidth*10/100));
        rightPanel.add(character);
        rightPanel.setOpaque(false);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,StartFrame.frameWidth*9/100,0,0));
        buttonPanel.setBackground(c);
		image = ImageIO.read(new File("assets/start_button.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*27/100, StartFrame.frameHeight*15/100,Image.SCALE_SMOOTH);
		JButton startButton = new JButton(new ImageIcon(image1));
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		//startButton.setBounds(400,350,500,200);
		startButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
		    	startButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/start_button.png");
		    	startButton.setIcon(image);
		    }
		    
		});
		
		startButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
		        startButton.setIcon(image); 
				StartFrameController.displayWelcome();
		    }
		});
		
		image = ImageIO.read(new File("assets/tutorial_button.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*27/100, StartFrame.frameHeight*15/100,Image.SCALE_SMOOTH);
		JButton instructionsButton = new JButton(new ImageIcon(image1));
		instructionsButton.setBorder(BorderFactory.createEmptyBorder());
		instructionsButton.setContentAreaFilled(false);
		instructionsButton.setBorderPainted(false);
		instructionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/tutorial_button_clicked.png");
		    	instructionsButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/tutorial_button.png");
		    	instructionsButton.setIcon(image);
		    }
		});
		instructionsButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ImageIcon image = new ImageIcon("assets/tutorial_button_clicked.png");
		        instructionsButton.setIcon(image);
		        StartFrameController.displayStory();
		    }
		});
		
		image = ImageIO.read(new File("assets/about_button.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*27/100, StartFrame.frameHeight*15/100,Image.SCALE_SMOOTH);
		JButton aboutButton = new JButton(new ImageIcon(image1));
		aboutButton.setBorder(BorderFactory.createEmptyBorder());
		aboutButton.setContentAreaFilled(false);
		aboutButton.setBorderPainted(false);
		aboutButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/about_button_clicked.png");
		        aboutButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/about_button.png");
		    	aboutButton.setIcon(image);
		    }
		});
		aboutButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ImageIcon image = new ImageIcon("assets/about_button_clicked.png");
		    	aboutButton.setIcon(image);
		    }
		});
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createVerticalStrut(StartFrame.frameHeight*2/100));
		buttonPanel.add(instructionsButton);
		buttonPanel.add(Box.createVerticalStrut(StartFrame.frameHeight*2/100));
		buttonPanel.add(aboutButton);
		leftPanel.add(title);
		leftPanel.add(buttonPanel);
		
		leftLayout.putConstraint(SpringLayout.NORTH, title, StartFrame.frameHeight*2/100 , SpringLayout.NORTH, leftPanel);
		leftLayout.putConstraint(SpringLayout.NORTH, buttonPanel, StartFrame.frameHeight*2/100 , SpringLayout.SOUTH, title);
		
		this.add(leftPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);

		mainLayout.putConstraint(SpringLayout.NORTH, leftPanel, 100, SpringLayout.NORTH, this);
		mainLayout.putConstraint(SpringLayout.NORTH, character, 100, SpringLayout.NORTH, this);
		
		mainLayout.putConstraint(SpringLayout.WEST, leftPanel, 100, SpringLayout.WEST, this);
		mainLayout.putConstraint(SpringLayout.WEST, character, 100, SpringLayout.EAST, leftPanel);
		validate();
	}
}
