package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;
import viewElements.ImagePanel;

public class EndStoryPanel extends JPanel{
	private JTextArea message;
	private ImagePanel peer,box,room,sticker,action;
	private Font font;
	private JFrame main;
	private ImageIcon icon;
	private Image image1;
	private Image myImage;
	private BufferedImage image;
	
	public EndStoryPanel(JFrame main, String bgImagepath) throws IOException
	{
		this.main = main;
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,(int)(StartFrame.frameWidth*98.6/100),(int)(StartFrame.frameHeight*94.5/100));
		System.out.println(bgImagepath);
		setBackground(bgImagepath);
		setLayout(new BorderLayout());
		
		font = new Font("Comic Sans MS", Font.PLAIN, (int)(StartFrame.frameHeight*4.5/100));
		/*
		BufferedImage img = new ImgUtils().scaleImage(1640,700,"assets/park.png");
		room = new ImagePanel(img);
		room.setLocation(0, 0);
		
		peer = new ImagePanel("assets/sarah.png");
		peer.setLocation(-70, 100);*/
		
		ImagePanel peer1 = new ImagePanel("");
		image = ImageIO.read(new File("assets/sarah.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*35/100, StartFrame.frameHeight*90/100,Image.SCALE_SMOOTH);
		peer1.setImage(image1);
//		peer1.setLocation(-70, 100);
//		leftPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameWidth*5/100, -StartFrame.frameWidth*1/100, 0, 0));
//		leftPanel.add(peer1);
		
		BufferedImage img1 = new ImgUtils().scaleImage((int)(StartFrame.frameWidth*98.6/100),StartFrame.frameHeight*40/100,"assets/story_dialog_box.png");
		box = new ImagePanel(img1);
		SpringLayout layout = new SpringLayout();

        message = new JTextArea();
        message.setText("Yey! You have now completed the story about Pneumonia. I hope you learned a lot! Do you want to hear another story?");
        message.setSize(StartFrame.frameWidth*60/100,StartFrame.frameHeight*35/100);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall( message );
        //message.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        BufferedImage buttonIcon = ImageIO.read(new File("assets/btn-yes.png"));
		JButton yesButton = new JButton(new ImageIcon(buttonIcon));
		yesButton.setBorder(BorderFactory.createEmptyBorder());
		yesButton.setContentAreaFilled(false);
		yesButton.setBorderPainted(false);
		yesButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/btn-yes_clicked.png");
		    	yesButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/btn-yes.png");
		    	yesButton.setIcon(image);
		    }
		});
		yesButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	StartFrameController.displayTheme();
		    }
		});
		BufferedImage buttonIcon1 = ImageIO.read(new File("assets/btn-no.png"));
		JButton noButton = new JButton(new ImageIcon(buttonIcon1));
		noButton.setBorder(BorderFactory.createEmptyBorder());
		noButton.setContentAreaFilled(false);
		noButton.setBorderPainted(false);
		noButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/btn-no_clicked.png");
		    	noButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/btn-no.png");
		    	noButton.setIcon(image);
		    }
		});
		noButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/*not working when mouselistener code was added*/
		        StartFrameController.displayStartMenu();
		        
		    }
		});

		layout.putConstraint(SpringLayout.WEST, noButton, 20, SpringLayout.EAST, yesButton);
		box.add(yesButton);
        box.add(noButton);
		box.add(message);
		box.add(peer1);
		
		// For horizontal Alignment of story message
		layout.putConstraint(SpringLayout.EAST, message, -70, SpringLayout.EAST, box);
		// For Vertical Alignment of story message
		layout.putConstraint(SpringLayout.NORTH, message, 10, SpringLayout.NORTH, box);
				
		// For horizontal Alignment of story message
		layout.putConstraint(SpringLayout.EAST, message, -70, SpringLayout.EAST, box);
		// For Vertical Alignment of story message
		layout.putConstraint(SpringLayout.NORTH, message, 10, SpringLayout.NORTH, box);

		layout.putConstraint(SpringLayout.WEST, peer1, 10, SpringLayout.WEST, box);
		layout.putConstraint(SpringLayout.NORTH, peer1, -200, SpringLayout.NORTH, box);
		
		// For horizontal Alignment of button panel
		layout.putConstraint(SpringLayout.WEST, yesButton, 610, SpringLayout.WEST, box);
		// For Vertical Alignment of button panel
		layout.putConstraint(SpringLayout.NORTH, yesButton, 10, SpringLayout.SOUTH, message);
		layout.putConstraint(SpringLayout.NORTH, noButton, 10, SpringLayout.SOUTH, message);
		

        box.setLayout(layout);
        box.setLocation(0, 500);
        
        //this.add(peer1);
		this.add(box,BorderLayout.SOUTH);
		//this.add(room);
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
	
	public void setButtonIcon(String filepath, JButton button){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(filepath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double w = StartFrame.frameWidth*28/100;
			double h = StartFrame.frameHeight*30/100;
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			button.setIcon(imageIcon);
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
