package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import driver.StoryGenerator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartMenuPanel extends JPanel{
	StoryGenerator controller;
	public StartMenuPanel(JFrame main,StoryGenerator controller) throws IOException
	{	
		this.controller = controller;
		
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
        ImagePanel title = new ImagePanel("assets/title.png");
        title.setLocation(150, 0);
        ImagePanel character = new ImagePanel("assets/tilted_sarah.png");
        character.setLocation(700, 0);
        
		BufferedImage buttonIcon = ImageIO.read(new File("assets/start_button.png"));
		JButton startButton = new JButton(new ImageIcon(buttonIcon));
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setBounds(400,350,500,200);
		startButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
		    	startButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/start_button.png");
		    	startButton.setIcon(image);
		    }
//		    public void mouseClicked(java.awt.event.MouseEvent evt)
//		    {
//		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
//		        startButton.setIcon(image);
//		    }
		    
		});
		
		startButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/*not working when mouselistener code was added*/
		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
		        startButton.setIcon(image); 
					try {
						main.setContentPane(new WelcomePanel(main,controller));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        
		    }
		});
		
		BufferedImage buttonIcon1 = ImageIO.read(new File("assets/tutorial_button.png"));
		JButton instructionsButton = new JButton(new ImageIcon(buttonIcon1));
		instructionsButton.setBorder(BorderFactory.createEmptyBorder());
		instructionsButton.setContentAreaFilled(false);
		instructionsButton.setBorderPainted(false);
		instructionsButton.setBounds(400,500,500,200);
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
		        try {
					main.setContentPane(new StoryPanel(main,controller));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		    }
		});
		
		BufferedImage buttonIcon2 = ImageIO.read(new File("assets/about_button.png"));
		JButton aboutButton = new JButton(new ImageIcon(buttonIcon2));
		aboutButton.setBorder(BorderFactory.createEmptyBorder());
		aboutButton.setContentAreaFilled(false);
		aboutButton.setBorderPainted(false);
		aboutButton.setBounds(400,650,500,200);
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
		
		this.add(startButton);
		this.add(instructionsButton);
		this.add(aboutButton);
		this.add(title);
		this.add(character);
		validate();
	}
}
