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

import driver.StartFrameController;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartMenuPanelTest extends JPanel{

	public StartMenuPanelTest() throws IOException
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);

		BufferedImage buttonIcon = ImageIO.read(new File("assets/title.png"));
        ImageIcon icon = new ImageIcon(buttonIcon);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 50/100,icon.getIconHeight() * 50/100,Image.SCALE_SMOOTH);
        ImagePanel title = new ImagePanel(image);
        title.setLocation(150, 0);

        buttonIcon = ImageIO.read(new File("assets/tilted_sarah.png"));
        icon = new ImageIcon(buttonIcon);
        image = icon.getImage().getScaledInstance(icon.getIconWidth() * 50/100,icon.getIconHeight() * 50/100,Image.SCALE_SMOOTH);
        ImagePanel character = new ImagePanel(image);
        character.setLocation(700, 0);
        
        icon = new ImageIcon("assets/start_button.png");
        image = icon.getImage().getScaledInstance(icon.getIconWidth() * 50/100,icon.getIconHeight() * 50/100,Image.SCALE_SMOOTH);
        icon = new ImageIcon(image, icon.getDescription());
		JButton startButton = new JButton(icon);
		startButton.setBorder(BorderFactory.createEmptyBorder());
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setBounds(400,350,500,200);
		startButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/start_button_clicked.png");
		    	Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 50/100,icon.getIconHeight() * 50/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	startButton.setIcon(icon);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/start_button.png");
		    	Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 50/100,icon.getIconHeight() * 50/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	startButton.setIcon(icon);
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
				StartFrameController.displayWelcome();
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
		        StartFrameController.displayStory();
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
