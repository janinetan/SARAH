package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;
import viewElements.ImagePanel;
import viewElements.PanelYesNo;

public class TutorialEndStoryPanel extends JPanel{
	private JTextArea message;
	private ImagePanel peer,box,room,sticker,action;
	private Font font;
	private JFrame main;
	private JPanel leftPanel, rightPanel;
	private BufferedImage image;
	private ImageIcon icon;
	private Image image1;
	private Image myImage;
	
	public TutorialEndStoryPanel(JFrame main, String bgImagepath, String sickMessage) throws IOException
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,(int)(StartFrame.frameWidth*98.6/100),(int)(StartFrame.frameHeight*94.5/100));
		setBackground(bgImagepath);
		setLayout(new BorderLayout());
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameWidth*2/100,0,0, StartFrame.frameWidth*2/100));
		rightPanel.setOpaque(false);
		leftPanel = new JPanel(new BorderLayout());
		leftPanel.setOpaque(false);
		Font font = new Font("Comic Sans MS", Font.PLAIN, (int)(StartFrame.frameHeight*4/100));
		
		ImagePanel peer1 = new ImagePanel("");
		image = ImageIO.read(new File("assets/sarah.png"));
		
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*35/100, StartFrame.frameHeight*90/100,Image.SCALE_SMOOTH);
		peer1.setImage(image1);
//		peer1.setLocation(-70, 100);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameWidth*5/100, -StartFrame.frameWidth*1/100, 0, 0));
		leftPanel.add(peer1);
		
		ImagePanel dialog = new ImagePanel("");
		image = ImageIO.read(new File("assets/sarah_dialog_interaction.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*67/100,StartFrame.frameHeight*30/100,Image.SCALE_SMOOTH);
		dialog.setImage(image1);
		dialog.setOpaque(false);
		
		SpringLayout sarahLayout = new SpringLayout();
		dialog.setLayout(sarahLayout);
		//dialog.setLocation(550, 25);
		//dialog.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		message = new JTextArea(sickMessage);
        //message.setSize(850,100);
        message.setSize(StartFrame.frameWidth*55/100,StartFrame.frameHeight*25/100);
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
        sarahLayout.putConstraint(SpringLayout.WEST, message, StartFrame.frameWidth*9/100, SpringLayout.WEST, dialog);
		
		// For Vertical Alignment
		sarahLayout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, dialog);
		
		rightPanel.add(dialog, BorderLayout.NORTH);
		rightPanel.add(new PanelYesNo(),BorderLayout.SOUTH);

		this.add(leftPanel,BorderLayout.WEST);
		this.add(rightPanel,BorderLayout.EAST);
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
