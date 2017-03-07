import java.awt.Color;
import java.awt.Font;
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

public class StoryPanel extends JPanel{
	JTextArea message;
	JButton home;
	ImagePanel peer, box,room,sticker,action;
	Font font;
	JFrame main;
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
        message.setText("When you are done reading the current text, "
        		+ "you can tap the arrow button on the lower right to proceed to the next part of the story.");
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
		JButton nextButton = new JButton(icon);
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
		nextButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
					main.setContentPane(new EndStoryPanel(main));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	/*addAction("play");
		    	changePeer("liam");
		    	String msg = "It was the Christmas of 1939 and I had just been let go It was the Christmas of 1939 and I had just been let go It was the Christmas of 1939 and I had just been let go It was the Christmas of 1939 and I had just been let go It was the Christmas of 1939 and I had just been let go It was the Christmas of 1939 and I had just been let go ";
		    			//I carried myself down O'Connell street but I succumbed to the pressure. There I lay, outside the Post Office, head in my hands. Not even the soft company of snow flakes." +
		    		    //"He was a small man, lost in an overcoat. His eyes were sad, surveying my miserable presence. He must have been curious then to know why a man in a suit would be slouched on the ground. \"Bad day?\" he asked. I gazed back, intrigued by the street lights illuminating his deep wrinkles and the immense bags drooping from his eyes. ";
		    		        
		    	messageDialog(msg);*/
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
        
        
        this.add(home);
		this.add(peer);
		this.add(box);
		this.add(room);
	}
	
	public void changePeer(String p)
	{
		this.remove(peer);
		if(p.equals("sarah"))
			peer = new ImagePanel("assets/sarah.png");
		else if(p.equals("liam"))
			peer = new ImagePanel("assets/liam.png");
		peer.setLocation(-70, 100);
		this.add(peer);
		this.add(box);
		this.add(room);
		this.revalidate();
		this.repaint();
	}
	public void messageDialog(String msg)
	{
		//remove space sa harap if start ng sentence 
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
					message.setText(cut);
					msg = msg.substring(endIndex);
				}
				else{
					message.setText(msg);
					msg="";
				}
			}
		}
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
		else if(act.equals("play"))
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
	
}
