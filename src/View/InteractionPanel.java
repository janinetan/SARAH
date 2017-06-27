package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;
import viewElements.ImagePanel;

public class InteractionPanel extends JPanel{
	JTextArea message,confirmHome;
	CustomTextArea answer;
	JButton home;
	JPanel leftPanel, rightPanel;
	BufferedImage image;
	ImageIcon icon;
	Image image1;
	private Image myImage;
	public InteractionPanel(JFrame main, String vp, String msg) throws IOException {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		//setBackground(new Color(197,229,240));
		setBackground("assets/park.png");
		setLayout(new BorderLayout());
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setOpaque(false);
		leftPanel = new JPanel(new BorderLayout());
		Font font = new Font("Comic Sans MS", Font.PLAIN, 50);
		
	/*	BufferedImage img = new ImgUtils().scaleImage(1640,700,"assets/park.png");
		ImagePanel room = new ImagePanel(img);
		room.setLocation(0, 0);*/
		
		Boolean isSarahWorried = false;
		Boolean isLiamSick = false;
		
		ImagePanel peer1 = new ImagePanel("");
		image = ImageIO.read(new File("assets/"+vp+".png"));
		if(vp.equalsIgnoreCase("sarah") && isSarahWorried){
			image = ImageIO.read(new File("assets/worried sarah.png"));
		}
			
		if(vp.equalsIgnoreCase("liam") && isLiamSick){
			image = ImageIO.read(new File("assets/sick liam.png"));
		}
		
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*35/100, StartFrame.frameWidth*65/100,Image.SCALE_SMOOTH);
		peer1.setImage(image1);
//		peer1.setLocation(-70, 100);
		leftPanel.add(peer1);
		
		ImagePanel dialog = new ImagePanel("");
		image = ImageIO.read(new File("assets/"+vp+"_dialog_box1.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*70/100,StartFrame.frameHeight*45/100,Image.SCALE_SMOOTH);
		dialog.setImage(image1);
		dialog.setOpaque(false);
		
		SpringLayout sarahLayout = new SpringLayout();
		dialog.setLayout(sarahLayout);
		//dialog.setLocation(550, 25);
		//dialog.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		message = new JTextArea(msg);
        //message.setSize(850,100);
        message.setSize(StartFrame.frameWidth*70/100-StartFrame.frameWidth*10/100,StartFrame.frameHeight*50/100-StartFrame.frameHeight*5/100);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall(message);
        //message.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        dialog.add(message);
        
		// For horizontal Alignment
        sarahLayout.putConstraint(SpringLayout.WEST, message, 120, SpringLayout.WEST, dialog);
		
		// For Vertical Alignment
		sarahLayout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, dialog);
		
		BufferedImage img1 = new ImgUtils().scaleImage(StartFrame.frameWidth*70/100,StartFrame.frameHeight*50/100,"assets/story_dialog_box.png");
		ImagePanel box = new ImagePanel(img1);
		box.setOpaque(false);
		SpringLayout layout = new SpringLayout();

        box.setLayout(layout);
//        box.setLocation(0, 500);
        

        answer = new CustomTextArea();
        answer.setPlaceholder("Type your answer here...");
        answer.setSize(StartFrame.frameWidth*70/100-StartFrame.frameWidth*2/100,StartFrame.frameHeight*50/100-StartFrame.frameHeight*10/100);
        answer.setFont(font);
        answer.setWrapStyleWord(true);
        answer.setLineWrap(true);
        answer.setRows(4);
        answer.setBorder(UIManager.getBorder("JLabel"));
        answer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        answer.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					StartFrameController.sendUserResponse(answer.getText());
                }    
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
        }
    );
//		layout.putConstraint(SpringLayout.WEST, answer, 5, SpringLayout.WEST, box);
//		layout.putConstraint(SpringLayout.NORTH, answer, 15, SpringLayout.NORTH, box);
     
        BufferedImage nextButtonIcon = ImageIO.read(new File("assets/tap_next.png"));
        ImageIcon icon = new ImageIcon(nextButtonIcon);
        Image image = icon.getImage().getScaledInstance(StartFrame.frameWidth * 7/100, StartFrame.frameHeight * 10/100,Image.SCALE_SMOOTH);
        icon = new ImageIcon(image, icon.getDescription());
		JButton nextButton = new JButton(icon);
		nextButton.setBorder(BorderFactory.createEmptyBorder());
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/tap_next.png");
		        Image image = icon.getImage().getScaledInstance(StartFrame.frameWidth * 7/100, StartFrame.frameHeight * 10/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	nextButton.setIcon(icon);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/tap_next.png");
		        Image image = icon.getImage().getScaledInstance(StartFrame.frameWidth * 7/100, StartFrame.frameHeight * 10/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	nextButton.setIcon(icon);
		    }
		});
		nextButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	StartFrameController.sendUserResponse(answer.getText());
		    }
		});

		layout.putConstraint(SpringLayout.EAST, nextButton, -15, SpringLayout.EAST, box);
		layout.putConstraint(SpringLayout.SOUTH, nextButton, -15, SpringLayout.SOUTH, box);

		box.add(nextButton);
//		box.add(answer);
		
		box.setComponentZOrder(nextButton, 0);
//		box.setComponentZOrder(answer, 1);
		
		rightPanel.add(dialog, BorderLayout.NORTH);
		rightPanel.add(box,BorderLayout.SOUTH);
		/*BufferedImage homeButtonIcon = ImageIO.read(new File("assets/home_button.png"));
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
	    });
		
		this.add(home);
		this.add(peer1);
		this.add(dialog);
		this.add(box);
//		this.add(room);
		
		this.setComponentZOrder(home, 0);
        this.setComponentZOrder(peer1, 1);
        this.setComponentZOrder(dialog, 2);
        this.setComponentZOrder(box, 3);*/
//        this.setComponentZOrder(room, 4);
		this.add(leftPanel,BorderLayout.WEST);
		this.add(rightPanel,BorderLayout.EAST);
	}
	public String getUserInput(){
		return answer.getText();
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
	
	@Override
    protected void paintComponent(Graphics g){ 
        super.paintComponent(g);    
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(myImage, 0, 0, null);
    } 
}
