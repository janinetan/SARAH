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

import driver.StartFrameController;

public class InteractionPanel extends JPanel{
	JTextArea message,confirmHome;
	CustomTextArea answer;
	JButton home;
	
	public InteractionPanel(JFrame main) throws IOException {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, 50);
		
		BufferedImage img = new ImgUtils().scaleImage(1640,700,"assets/cartoon_room.png");
		ImagePanel room = new ImagePanel(img);
		room.setLocation(0, 0);
		
		ImagePanel sarah = new ImagePanel("assets/sarah.png");
		sarah.setLocation(-70, 100);
		
		ImagePanel dialog = new ImagePanel("assets/sarah_dialog_box1.png");
		SpringLayout sarahLayout = new SpringLayout();

		dialog.setLayout(sarahLayout);
		dialog.setLocation(550, 25);
		//dialog.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		message = new JTextArea("Playing will make you get tired and you might get worse. Ana, what do you think?");
        message.setSize(850,100);
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

		BufferedImage img1 = new ImgUtils().scaleImage(1640,450,"assets/story_dialog_box.png");
		ImagePanel box = new ImagePanel(img1);
		SpringLayout layout = new SpringLayout();

        box.setLayout(layout);
        box.setLocation(0, 500);
        

        answer = new CustomTextArea();
        answer.setPlaceholder("Type your answer here...");
        answer.setSize(1000,400);
        answer.setFont(font);
        answer.setWrapStyleWord(true);
        answer.setLineWrap(true);
        answer.setRows(5);
       
//        answer.setOpaque(false);
        /*answer.setEditable(false);
        answer.setFocusable(false);
        answer.getCaret().deinstall( answer );
        answer.setBorder(UIManager.getBorder("JLabel"));*/
        answer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
		layout.putConstraint(SpringLayout.EAST, answer, -15, SpringLayout.EAST, box);
		layout.putConstraint(SpringLayout.NORTH, answer, 15, SpringLayout.NORTH, box);
     
        BufferedImage nextButtonIcon = ImageIO.read(new File("assets/tap_next.png"));
        ImageIcon icon = new ImageIcon(nextButtonIcon);
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
		    	answer.setText("hi");
		    }
		});

		layout.putConstraint(SpringLayout.EAST, nextButton, -15, SpringLayout.EAST, box);
		layout.putConstraint(SpringLayout.SOUTH, nextButton, -15, SpringLayout.SOUTH, box);

		box.add(nextButton);
		box.add(answer);
		
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
	    });
		
		this.add(sarah);
		this.add(dialog);
		this.add(box);
		this.add(room);
	}
	public String getUserInput()
	{
		return answer.getText();
	}
}
