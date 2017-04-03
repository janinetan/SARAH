package View;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;


public class StoryPanel extends JPanel{
	JTextArea message,confirmHome;
	JButton home, nextButton;
	ImagePanel peer1,peer2, box,room,sticker,action;
	Font font;
	JFrame main;
	ArrayList<String> messageParts;
	boolean status = true;
	
	private int counter;
	private boolean isLast;
	
	public StoryPanel() throws IOException
	{
		System.out.println("hi");
		this.main = main;
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		font = new Font("Comic Sans MS", Font.PLAIN, 50);
		
		BufferedImage img = new ImgUtils().scaleImage(StartFrame.frameWidth,StartFrame.frameHeight,"assets/park.png");
		room = new ImagePanel(img);
		room.setLocation(0, 0);
		

		BufferedImage image = ImageIO.read(new File("assets/sarah.png"));
		ImageIcon icon = new ImageIcon(image);
		Image image1 = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
		peer1 = new ImagePanel(image1);
		peer1.setLocation(0, 100);
		
		image = ImageIO.read(new File("assets/liam.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(icon.getIconWidth() * 60/100,icon.getIconHeight() * 60/100,Image.SCALE_SMOOTH);
		peer2 = new ImagePanel(image1);
		
		peer1.setLocation(0, 100);
		peer2.setLocation(1200,200);
		
		//BufferedImage img1 = new ImgUtils().scaleImage(1640,450,"assets/sarah_dialog_box2.png");
		
		box = new ImagePanel("assets/sarah_dialog_box2.png");
		SpringLayout layout = new SpringLayout();
		
        message = new JTextArea();
        //message.setText("When you are done reading the current text, "
        //		+ "you can tap the arrow button on the lower right to proceed to the next part of the story.");
        message.setSize(950,100);
        message.setSize(750,400);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall( message );
        message.setBorder(UIManager.getBorder("JLabel"));
        message.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     
        image = ImageIO.read(new File("assets/tap_next.png"));
        icon = new ImageIcon(image);
        image1 = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
        icon = new ImageIcon(image1, icon.getDescription());
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
		nextButton.addActionListener(new NextBtnListener());
		

		box.add(nextButton);
		
		box.add(message);
		
		// For horizontal Alignment of story message
		layout.putConstraint(SpringLayout.EAST, message, -70, SpringLayout.EAST, box);

		// For Vertical Alignment of story message
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, box);
		
		// For horizontal alignment of next button
		layout.putConstraint(SpringLayout.EAST, nextButton, -30, SpringLayout.EAST, box);
		
		// For Vertical alignment of next button
		layout.putConstraint(SpringLayout.SOUTH, nextButton, -30, SpringLayout.SOUTH, box);

        box.setLayout(layout);
        box.setLocation(420, 400);
        
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
				    	d.dispose();
				    	StartFrameController.displayStartMenu();
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
				    	d.dispose();
				        
				    }
				});
		        buttonPanel.add(yesButton);
		        buttonPanel.add(noButton);
		        d.add(buttonPanel);
		        d.setVisible(true);
		    }
	    });/*
        BufferedImage icon2 = ImageIO.read(new File("assets/sick_liam.png"));
		ImageIcon pIcon = new ImageIcon(icon2);
		Image image1 = pIcon.getImage().getScaledInstance(pIcon.getIconWidth() * 60/100,pIcon.getIconHeight() * 60/100,Image.SCALE_SMOOTH);
		peer2 = new ImagePanel(image1);
		peer2.setLocation(1000, 50);*/
        //peer2.setComponentZOrder(comp, index); can change order of painting
//        sticker = new ImagePanel("assets/action_bubble.png");
//		sticker.setLocation(550, 50);
		
        
        
        this.add(home);
        //this.add(sticker);
		this.add(box);
		this.add(peer1);
		this.add(peer2);
		this.add(room);
		
		this.setComponentZOrder(home, 0);
        //this.setComponentZOrder(sticker, 1);
        this.setComponentZOrder(box, 1);
        this.setComponentZOrder(peer1, 2);
        this.setComponentZOrder(peer2, 3);
        this.setComponentZOrder(room, 4);
		
        
		//addPeer("sarah","liam");
		//changeBackground("park");
	}
	
	public void changePeer(String p)
	{
		this.remove(peer1);
		if(p.equalsIgnoreCase("sarah"))
			peer1 = new ImagePanel("assets/sarah.png");
		else if(p.equalsIgnoreCase("liam"))
			peer1 = new ImagePanel("assets/liam.png");
		else if(p.equalsIgnoreCase("sick liam"))
			peer1 = new ImagePanel("assets/sick_liam.png");
		
		peer1.setLocation(0, 100);
		
		this.add(home);
		this.add(peer1);
		this.add(box); 
		this.add(room);
			
		this.setComponentZOrder(home, 0);
        this.setComponentZOrder(peer1, 1);
        this.setComponentZOrder(box, 2);
        this.setComponentZOrder(room, 3);
        
       
		this.revalidate();
		this.repaint();
	}
	
	public void addPeer(String p1, String p2) throws IOException
	{
		BufferedImage icon = null;
		BufferedImage icon1 = null;
		ImageIcon pIcon;
		Image image1;
		
		this.removeAll();
		if(p1.equalsIgnoreCase("sarah"))
			icon = ImageIO.read(new File("assets/sarah.png"));
		else if(p1.equalsIgnoreCase("liam"))
			icon = ImageIO.read(new File("assets/liam.png"));
		else if(p1.equalsIgnoreCase("sick liam"))
			icon = ImageIO.read(new File("assets/sick_liam.png"));
		
		if(p2.equalsIgnoreCase("sarah"))
			icon1 = ImageIO.read(new File("assets/sarah.png"));
		else if(p2.equalsIgnoreCase("liam"))
			icon1 = ImageIO.read(new File("assets/liam.png"));
		else if(p2.equalsIgnoreCase("sick liam"))
			icon1 = ImageIO.read(new File("assets/sick_liam.png"));
		

		pIcon = new ImageIcon(icon);
		image1 = pIcon.getImage().getScaledInstance(pIcon.getIconWidth() * 70/100,pIcon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
		peer1 = new ImagePanel(image1);
		
		pIcon = new ImageIcon(icon1);
		image1 = pIcon.getImage().getScaledInstance(pIcon.getIconWidth() * 70/100,pIcon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
		peer2 = new ImagePanel(image1);
		
		peer1.setLocation(0, 100);
		peer2.setLocation(1150,100);
		
		this.add(home);
		this.add(peer1);
		this.add(box); 
		this.add(peer2);
		this.add(room);
		
		this.setComponentZOrder(home, 0);
        this.setComponentZOrder(box, 1);
        this.setComponentZOrder(peer1, 2);
        this.setComponentZOrder(peer2, 3);
        this.setComponentZOrder(room, 4);
        
        
		this.revalidate();
		this.repaint();
	}
	
	public void addAction(String act)
	{
		String path="";
		this.removeAll();
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
		//action.setLocation(700, 130);

		// For horizontal Alignment of sticker
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, action, -30, SpringLayout.HORIZONTAL_CENTER, sticker);

		// For Vertical Alignment of sticker
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, action, -30, SpringLayout.VERTICAL_CENTER, sticker);
		sticker.add(action);
		
		this.add(home);
		this.add(sticker);
		this.add(peer1);
		this.add(box);
		this.add(room);
		
		this.setComponentZOrder(home, 0);
		this.setComponentZOrder(sticker, 1);
        this.setComponentZOrder(peer1, 2);
        this.setComponentZOrder(box, 3);
        this.setComponentZOrder(room, 4);
        
		this.revalidate();
		this.repaint();
	}
	
	public void changeBackground(String place)
	{
		BufferedImage img;
		String path = "";
		
		if(place.equalsIgnoreCase("room"))
			path ="assets/room.png";
		else if(place.equalsIgnoreCase("park"))
			path = "assets/park.png";
		
		img= new ImgUtils().scaleImage(1640,700,path);
		room = new ImagePanel(img);
		

		this.add(home);
		this.add(peer1);
		this.add(box); 
		this.add(room);
		
		this.setComponentZOrder(home, 0);
        this.setComponentZOrder(peer1, 1);
        this.setComponentZOrder(box, 2);
        this.setComponentZOrder(room, 3);
        
		this.revalidate();
		this.repaint();
	}
	
	public void changeBackgroundTwoPeers(String place, String p1, String p2) throws IOException
	{
		changeBackground(place);
		addPeer(p1,p2);
	}
	
	public ArrayList<String> cutMessageDialog(String msg)
	{
		//remove space sa harap if start ng sentence 
		ArrayList<String> s = new ArrayList();
		int startIndex =0, endIndex=0;
		String temp="", cut;
		if(msg.length()<=230)
//			message.setText(msg); removed 23c084e
			s.add(msg);
			
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
	
	public void changeDialogBox(String peer){
		this.removeAll();
		if(peer.equalsIgnoreCase("sarah"))
			box = new ImagePanel("assets/sarah_dialog_box2.png");
		else if(peer.equalsIgnoreCase("liam"))
			box = new ImagePanel("assets/liam_dialog_box2.png");
		
		SpringLayout layout = new SpringLayout();
		box.add(nextButton);
		box.add(message);
		
		// For horizontal Alignment of story message
		layout.putConstraint(SpringLayout.EAST, message, -70, SpringLayout.EAST, box);
		// For Vertical Alignment of story message
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, box);
		
		// For horizontal alignment of next button
		layout.putConstraint(SpringLayout.EAST, nextButton, -30, SpringLayout.EAST, box);
		// For Vertical alignment of next button
		layout.putConstraint(SpringLayout.SOUTH, nextButton, -30, SpringLayout.SOUTH, box);

        box.setLayout(layout);
        box.setLocation(420, 400);

		this.add(home);
		this.add(box);
		this.add(peer1);
		this.add(peer2);
		this.add(room);
		
		this.setComponentZOrder(home, 0);
        this.setComponentZOrder(box, 1);
        this.setComponentZOrder(peer1, 2);
        this.setComponentZOrder(peer2, 3);
        this.setComponentZOrder(room, 4);
		
		this.repaint();
		this.revalidate();
	}
	
	public void displayMessage(String peer, String msg){
		//changePeer(peer);
		changeDialogBox(peer);
		//addAction("sleep");
		messageParts = new ArrayList<String>();
		messageParts = cutMessageDialog(msg);
		message.setText(messageParts.get(0));
		counter = 1;
	}
	public void displayMessageAction(String peer, String msg, String act) throws IOException{	
		displayMessage(peer, msg);
		addAction(act);
	}
	
	public class NextBtnListener implements ActionListener{	    
		@Override
		public void actionPerformed(ActionEvent e) {
	    	JButton button = (JButton) e.getSource();
	    	if(counter < messageParts.size()){
		    	message.setText(messageParts.get(counter));
		    	counter++;
	    	}
	    	else if (counter == messageParts.size()){
	    		try {
					StartFrameController.playEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}
		}
	}
}
