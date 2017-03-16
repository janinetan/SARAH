import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;


public class StoryPanel extends JPanel{
	JTextArea message,confirmHome;
	JButton home, nextButton;
	ImagePanel peer, box,room,sticker,action;
	Font font;
	JFrame main;
	ArrayList<String> messageParts;
	public StoryPanel(JFrame main) throws IOException
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
        //message.setText("When you are done reading the current text, "
        //		+ "you can tap the arrow button on the lower right to proceed to the next part of the story.");
        message.setSize(950,100);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall( message );
        //message.setBorder(UIManager.getBorder("JLabel"));
        //message.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     
        BufferedImage buttonIcon1 = ImageIO.read(new File("assets/tap_next.png"));
        ImageIcon icon = new ImageIcon(buttonIcon1);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
        icon = new ImageIcon(image, icon.getDescription());
		nextButton = new JButton(icon);
		nextButton.setBorder(BorderFactory.createEmptyBorder());
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/tap_next_clicked.png");
		        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	nextButton.setIcon(icon);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/tap_next.png");
		        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	nextButton.setIcon(icon);
		    }
		    
		});
		
		

		box.add(nextButton);
		box.add(message);
		
		// For horizontal Alignment of story message
		layout.putConstraint(SpringLayout.EAST, message, -70, SpringLayout.EAST, box);

		// For Vertical Alignment of story message
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, box);
		
		// For horizontal alignment of next button
		layout.putConstraint(SpringLayout.EAST, nextButton, -15, SpringLayout.EAST, box);
		
		// For Vertical alignment of next button
		layout.putConstraint(SpringLayout.SOUTH, nextButton, -15, SpringLayout.SOUTH, box);

        box.setLayout(layout);
        box.setLocation(0, 500);
        
        BufferedImage homeButtonIcon = ImageIO.read(new File("assets/home_button.png"));
        ImageIcon homeIcon = new ImageIcon(homeButtonIcon);
        home = new JButton(homeIcon);
        home.setBorder(BorderFactory.createEmptyBorder());
        home.setContentAreaFilled(false);
        home.setBorderPainted(false);
        home.setBounds(1450,10,150,150);
        home.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	FlowLayout flow = new FlowLayout();
		    	flow.setAlignment(FlowLayout.CENTER);
		    	JDialog d = new JDialog((java.awt.Frame)null, "Go to Home", true);
		    	d.getContentPane().setBackground(new Color(197,229,240));
		    	d.getContentPane().setLayout(flow);
		        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        d.setSize(600, 400);
		        final Toolkit toolkit = Toolkit.getDefaultToolkit();
		        final Dimension screenSize = toolkit.getScreenSize();
		        final int x = (screenSize.width - d.getWidth()) / 2;
		        final int y = (screenSize.height - d.getHeight()) / 2;
		        d.setLocation(x, y);
		        // label with original font
		        confirmHome = new JTextArea();
		        confirmHome.setText("Are you sure you want to exit the story and go to the main menu?");
		        confirmHome.setBackground(new Color(197,229,240));
		        confirmHome.setSize(550,100);
		        confirmHome.setFont(font);
		        confirmHome.setWrapStyleWord(true);
		        confirmHome.setLineWrap(true);
		        confirmHome.setOpaque(false);
		        confirmHome.setEditable(false);
		        confirmHome.setFocusable(false);
		        confirmHome.getCaret().deinstall( confirmHome );
		        d.add(confirmHome);
		        
		        FlowLayout buttonLayout = new FlowLayout();
		        buttonLayout.setAlignment(FlowLayout.CENTER);
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.setBackground(new Color(197,229,240));
		        buttonPanel.setLayout(buttonLayout);
		        BufferedImage buttonIcon = null;
				try {
					buttonIcon = ImageIO.read(new File("assets/yes.png"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ImageIcon yesIcon = new ImageIcon(buttonIcon);
				Image image = yesIcon.getImage().getScaledInstance(yesIcon.getIconWidth() * 70/100,yesIcon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
				yesIcon = new ImageIcon(image, yesIcon.getDescription());
				JButton yesButton = new JButton(yesIcon);
				yesButton.setBorder(BorderFactory.createEmptyBorder());
				yesButton.setContentAreaFilled(false);
				yesButton.setBorderPainted(false);
				yesButton.addMouseListener(new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	ImageIcon image = new ImageIcon("assets/yes_clicked.png");
				    	Image image1 = image.getImage().getScaledInstance(image.getIconWidth() * 70/100,image.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
				    	image = new ImageIcon(image1, image.getDescription());
				    	yesButton.setIcon(image);
				    }

				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	ImageIcon image = new ImageIcon("assets/yes.png");
				    	Image image1 = image.getImage().getScaledInstance(image.getIconWidth() * 70/100,image.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
				    	image = new ImageIcon(image1, image.getDescription());
				    	yesButton.setIcon(image);
				    }
				});
				yesButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	try {
							main.setContentPane(new StoryPanel(main));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        
				    }
				});
				BufferedImage buttonIcon1 = null;
				try {
					buttonIcon1 = ImageIO.read(new File("assets/no.png"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				ImageIcon noIcon = new ImageIcon(buttonIcon1);
				Image image1 = noIcon.getImage().getScaledInstance(noIcon.getIconWidth() * 70/100,noIcon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
				noIcon = new ImageIcon(image1, noIcon.getDescription());
				JButton noButton = new JButton(noIcon);
				noButton.setBorder(BorderFactory.createEmptyBorder());
				noButton.setContentAreaFilled(false);
				noButton.setBorderPainted(false);
				noButton.addMouseListener(new java.awt.event.MouseAdapter() {
				    public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	ImageIcon image = new ImageIcon("assets/no_clicked.png");
				    	Image image1 = image.getImage().getScaledInstance(image.getIconWidth() * 70/100,image.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
				    	image = new ImageIcon(image1, image.getDescription());
				    	noButton.setIcon(image);
				    }

				    public void mouseExited(java.awt.event.MouseEvent evt) {
				    	ImageIcon image = new ImageIcon("assets/no.png");
				    	Image image1 = image.getImage().getScaledInstance(image.getIconWidth() * 70/100,image.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
				    	image = new ImageIcon(image1, image.getDescription());
				    	noButton.setIcon(image);
				    }
				});
				noButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	/*not working when mouselistener code was added*/
				        try {
							main.setContentPane(new ThemePanel(main));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        
				    }
				});
		        buttonPanel.add(yesButton);
		        buttonPanel.add(noButton);
		        d.add(buttonPanel);
		        d.setVisible(true);
		    }
	    });
        
        displayMessage("The Blue Whales just played their first baseball game of the new season; I believe there is much to be excited about. Although they lost, it was against an excellent team that had won the championship last year. The Blue Whales fell behind early but showed excellent teamwork and came back to tie the game. The team had 15 hits and scored 8 runs. That’s excellent! Unfortunately, they had 5 fielding errors, which kept the other team in the lead the entire game. The game ended with the umpire making a bad call, and if the call had gone the other way, the Blue Whales might have actually won the game. It wasn’t a victory, but I say the Blue Whales look like they have a shot at the championship, especially if they continue to improve.");
        
        this.add(home);
		this.add(peer);
		this.add(box);
		this.add(room);
	}
	
	public void changePeer(String p)
	{
		this.remove(peer);
		if(p.equalsIgnoreCase("sarah"))
			peer = new ImagePanel("assets/sarah.png");
		else if(p.equalsIgnoreCase("liam"))
			peer = new ImagePanel("assets/liam.png");
		peer.setLocation(-70, 100);
		this.add(peer);
		this.add(box);
		this.add(room);
		this.revalidate();
		this.repaint();
	}
	public ArrayList<String> cutMessageDialog(String msg)
	{
		//remove space sa harap if start ng sentence 
		ArrayList<String> s = new ArrayList();
		int startIndex =0, endIndex=0;
		String temp="", cut;
		if(msg.length()<=230)
			message.setText(msg);
		else
		{
			while(msg.length()!=0)
			{
				if(msg.length()>=230){
					temp = msg.substring(0, 230);
					endIndex = temp.lastIndexOf(".")+1;
					if(endIndex == 0)
						endIndex = temp.lastIndexOf(",")+1;
					if(endIndex == 0)
						endIndex = temp.lastIndexOf("!")+1;
					if(endIndex == 0)
						endIndex = temp.lastIndexOf("?")+1;
					if(endIndex == 0)
						endIndex = temp.lastIndexOf(";")+1;
					if(endIndex == 0)
						endIndex = temp.lastIndexOf(" ")+1;
					cut = temp.substring(0,endIndex);
					s.add(cut);
					//message.setText(cut);
					msg = msg.substring(endIndex);
				}
				else{
					//message.setText(msg);
					s.add(msg);
					msg="";
				}
			}
		}
		return s;
	}
	
	public void addAction(String act)
	{
		String path="";
		this.remove(peer);
		this.remove(box);
		this.remove(room);
		sticker = new ImagePanel("assets/action_bubble.png");
		sticker.setLocation(550, 50);
		SpringLayout layout = new SpringLayout();
		sticker.setLayout(layout);
		
		if(act.equals("take medicine"))
			path = "assets/take_medicine.png";
		else if(act.equals("sleep"))
			path = "assets/sleep_emoji.png";
		else if(act.equals("play"))
			path = "assets/basketball.png";
		action = new ImagePanel(path);
		action.setLocation(700, 130);

		// For horizontal Alignment of story message
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, action, 20, SpringLayout.HORIZONTAL_CENTER, sticker);

		// For Vertical Alignment of story message
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, action, -50, SpringLayout.VERTICAL_CENTER, sticker);
		sticker.add(action);
		
		this.add(sticker);
		this.add(peer);
		this.add(box);
		this.add(room);
		this.revalidate();
		this.repaint();
	}
	public void displayMessage(String msg)
	{
		messageParts = new ArrayList<String>();
		messageParts = cutMessageDialog(msg);
		message.setText(messageParts.get(0));
		nextButton.addActionListener(new ActionListener() {
			int counter = 1;
		    public void actionPerformed(ActionEvent e) {
		    	if(counter<=messageParts.size()){
			    	message.setText(messageParts.get(counter));
			    	counter++;
		    	}
		    }
		});
	}
	
}
