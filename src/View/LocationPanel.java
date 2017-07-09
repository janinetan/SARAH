package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;
import viewElements.DialogueConfirmHome;
import viewElements.DialogueError;
import viewElements.ImagePanel;
import viewElements.PanelBackNext;

public class LocationPanel extends JPanel{
	private JTextArea message; 
	private String theme = "";
	private JButton locationButton1,locationButton2,locationButton3;
	private BufferedImage image;
	private ImageIcon icon;
	private Image image1;
	
	private String btnLoc1Image = "assets/loc1.png";
	private String btnLoc2Image = "assets/loc2.png";
	private String btnLoc3Image = "assets/loc3.png";
	private String btnLoc1Clicked = "assets/loc1_clicked.png";
	private String btnLoc2Clicked = "assets/loc2_clicked.png";
	private String btnLoc3Clicked = "assets/loc3_clicked.png";
	
	private String theme1 = "room";
	private String theme2 = "classroom";
	private String theme3 = "park";
	
	public LocationPanel(JFrame main) throws IOException {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth*99/100,(int)(StartFrame.frameHeight*94.5/100));
		setBackground(new Color(197,229,240));
		setLayout(new BorderLayout());
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, StartFrame.frameWidth*4/100);
		
		JPanel dialogPanel = new JPanel();
		dialogPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameHeight*6/100,0,0,0));
		dialogPanel.setOpaque(false);
		ImagePanel box = new ImagePanel("");
		image = ImageIO.read(new File("assets/cloud2.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*80/100, StartFrame.frameHeight*30/100,Image.SCALE_SMOOTH);
		box.setImage(image1);
		SpringLayout dialogLayout = new SpringLayout();
        box.setLayout(dialogLayout);
        message = new JTextArea("Where do you want to go?");
        message.setSize(StartFrame.frameWidth*55/100, StartFrame.frameHeight*20/100);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall( message );
        message.setBorder(UIManager.getBorder("JLabel"));
		box.add(message);
		
		// For horizontal Alignment
		dialogLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0, SpringLayout.HORIZONTAL_CENTER, box);

		// For Vertical Alignment
		dialogLayout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, box);

		dialogPanel.add(box);
        
        JPanel location = new JPanel();
        location.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameHeight*2/100,StartFrame.frameHeight*6/100,0,StartFrame.frameHeight*6/100));
        location.setOpaque(false);
        
        SpringLayout locationLayout = new SpringLayout(); 
		location.setLayout(locationLayout);
        
        locationButton1 = new JButton();
		locationButton1.setBorder(BorderFactory.createEmptyBorder());
		locationButton1.setContentAreaFilled(false);
		locationButton1.setBorderPainted(false);
		locationButton1.addMouseListener(new BtnMouseListener());
		locationButton1.addActionListener(new ButtonListener());
		setButtonIcon(btnLoc1Image, locationButton1);
		
		
		locationButton2 = new JButton();
		locationButton2.setBorder(BorderFactory.createEmptyBorder());
		locationButton2.setContentAreaFilled(false);
		locationButton2.setBorderPainted(false);
		locationButton2.addMouseListener(new BtnMouseListener());
		locationButton2.addActionListener(new ButtonListener());
		setButtonIcon(btnLoc2Image, locationButton2);
		
		
		locationButton3 = new JButton();
		locationButton3.setBorder(BorderFactory.createEmptyBorder());
		locationButton3.setContentAreaFilled(false);
		locationButton3.setBorderPainted(false);
		locationButton3.addMouseListener(new BtnMouseListener());
		locationButton3.addActionListener(new ButtonListener());
		setButtonIcon(btnLoc3Image, locationButton3);
		

		location.add(locationButton1);
		location.add(locationButton2);
		location.add(locationButton3);
		locationLayout.putConstraint(SpringLayout.WEST, locationButton1, 0, SpringLayout.WEST,location);
		locationLayout.putConstraint(SpringLayout.WEST, locationButton2, 50, SpringLayout.EAST, locationButton1);
		locationLayout.putConstraint(SpringLayout.WEST, locationButton3, 50, SpringLayout.EAST, locationButton2);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setOpaque(false);
		bottomPanel.add(new PanelBackNext(),BorderLayout.EAST);
		
		this.add(dialogPanel, BorderLayout.NORTH);
		this.add(location,BorderLayout.CENTER);
		//this.add(bottomPanel,BorderLayout.SOUTH);
		validate();
	}
	
	public String getTheme(){
		return theme;
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
	
	private class BtnMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			String filepath = null;
			if (button == locationButton1){
				filepath = btnLoc1Clicked;
			}
			else if (button == locationButton2){
				filepath = btnLoc2Clicked;
			}
			else if (button == locationButton3){
				filepath = btnLoc3Clicked;
			}
			setButtonIcon(filepath, button);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			String filepath = null;
			if (button == locationButton1 && !theme.equals(theme1)){
				filepath = btnLoc1Image;
			}
			else if (button == locationButton2 && !theme.equals(theme2)){
				filepath = btnLoc2Image;
			}
			else if (button == locationButton3 && !theme.equals(theme3)){
				filepath = btnLoc3Image;
			}
			if (filepath != null)
				setButtonIcon(filepath, button);
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (button == locationButton1){
		    	theme = theme1;
		    	setButtonIcon(btnLoc2Image, locationButton2);
		    	setButtonIcon(btnLoc3Image, locationButton3);
			}
			else if (button == locationButton2){
				theme = theme2;
				setButtonIcon(btnLoc1Image, locationButton1);
				setButtonIcon(btnLoc3Image, locationButton3);
			}
			else if (button == locationButton3){
				theme = theme3;
				setButtonIcon(btnLoc1Image, locationButton1);
				setButtonIcon(btnLoc2Image, locationButton2);
			}
	        StartFrameController.displayStartStory(theme);
	        //StartFrameController.displayTransition();
	        //StartFrameController.displayEnd(theme);
		}
	}
}
