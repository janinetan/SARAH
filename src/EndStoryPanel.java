import java.awt.Color;
import java.awt.Font;
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

public class EndStoryPanel extends JPanel{
	JTextArea message;
	JButton home;
	ImagePanel peer,box,room,sticker,action;
	Font font;
	JFrame main;
	
	public EndStoryPanel(JFrame main) throws IOException
	{
		this.main = main;
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		font = new Font("Comic Sans MS", Font.PLAIN, 50);
		
		BufferedImage img = new ImgUtils().scaleImage(1640,700,"assets/cartoon_room.png");
		room = new ImagePanel(img);
		room.setLocation(0, 0);
		
		peer = new ImagePanel("assets/sarah.png");
		peer.setLocation(-70, 100);
		
		BufferedImage img1 = new ImgUtils().scaleImage(1640,450,"assets/story_dialog_box.png");
		box = new ImagePanel(img1);
		SpringLayout layout = new SpringLayout();

        message = new JTextArea();
        message.setText("Yey! You have now completed the story about Pneumonia. I hope you learned a lot! Do you want to hear another story?");
        message.setSize(950,100);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall( message );
        message.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JPanel buttonPanel = new JPanel();
        SpringLayout buttonLayout = new SpringLayout();
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.setSize(710,200);
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        BufferedImage buttonIcon = ImageIO.read(new File("assets/yes.png"));
		JButton yesButton = new JButton(new ImageIcon(buttonIcon));
		yesButton.setBorder(BorderFactory.createEmptyBorder());
		yesButton.setContentAreaFilled(false);
		yesButton.setBorderPainted(false);
		yesButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/yes_clicked.png");
		    	yesButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/yes.png");
		    	yesButton.setIcon(image);
		    }
		});
		yesButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					main.setContentPane(new ThemePanel(main));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		    }
		});
		BufferedImage buttonIcon1 = ImageIO.read(new File("assets/no.png"));
		JButton noButton = new JButton(new ImageIcon(buttonIcon1));
		noButton.setBorder(BorderFactory.createEmptyBorder());
		noButton.setContentAreaFilled(false);
		noButton.setBorderPainted(false);
		noButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/no_clicked.png");
		    	noButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/no.png");
		    	noButton.setIcon(image);
		    }
		});
		noButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/*not working when mouselistener code was added*/
		        try {
					main.setContentPane(new StartMenuPanel(main));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		    }
		});

		buttonLayout.putConstraint(SpringLayout.WEST, noButton, 20, SpringLayout.EAST, yesButton);
		buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        
		box.add(message);
		box.add(buttonPanel);
		
		// For horizontal Alignment of story message
		layout.putConstraint(SpringLayout.EAST, message, -70, SpringLayout.EAST, box);
		// For Vertical Alignment of story message
		layout.putConstraint(SpringLayout.NORTH, message, 10, SpringLayout.NORTH, box);
		
		// For horizontal Alignment of button panel
		layout.putConstraint(SpringLayout.EAST, buttonPanel, 20, SpringLayout.EAST, box);
		// For Vertical Alignment of button panel
		//layout.putConstraint(SpringLayout.SOUTH, message, 10, SpringLayout.NORTH, buttonPanel);
		

        box.setLayout(layout);
        box.setLocation(0, 500);
        
        this.add(peer);
		this.add(box);
		this.add(room);
	}
}
