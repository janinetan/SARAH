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
	private JTextArea message;
	private CustomTextArea answer;
	private JPanel leftPanel, rightPanel;
	private BufferedImage image;
	private ImageIcon icon;
	private Image image1;
	private Image myImage;
	
	public InteractionPanel(JFrame main, String vp, String msg, String bgImagepath) throws IOException {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,(int)(StartFrame.frameWidth*98.6/100),(int)(StartFrame.frameHeight*94.5/100));
		setBackground(bgImagepath);
		setLayout(new BorderLayout());
		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameWidth*7/100, 0,0,0));
		rightPanel.setOpaque(false);
		leftPanel = new JPanel(new BorderLayout());
		leftPanel.setOpaque(false);
		Font font = new Font("Comic Sans MS", Font.PLAIN, (int)(StartFrame.frameHeight*4.5/100));
		
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
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*35/100, StartFrame.frameHeight*90/100,Image.SCALE_SMOOTH);
		peer1.setImage(image1);
//		peer1.setLocation(-70, 100);
		leftPanel.setBorder(BorderFactory.createEmptyBorder(StartFrame.frameWidth*5/100, -StartFrame.frameWidth*1/100, 0, 0));
		leftPanel.add(peer1);
		
		ImagePanel dialog = new ImagePanel("");
		image = ImageIO.read(new File("assets/"+vp+"_dialog_interaction.png"));
		icon = new ImageIcon(image);
		image1 = icon.getImage().getScaledInstance(StartFrame.frameWidth*70/100,StartFrame.frameHeight*40/100,Image.SCALE_SMOOTH);
		dialog.setImage(image1);
		dialog.setOpaque(false);
		
		SpringLayout sarahLayout = new SpringLayout();
		dialog.setLayout(sarahLayout);
		//dialog.setLocation(550, 25);
		//dialog.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		message = new JTextArea(msg);
        //message.setSize(850,100);
        message.setSize(StartFrame.frameWidth*60/100,StartFrame.frameHeight*45/100);
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
        sarahLayout.putConstraint(SpringLayout.WEST, message, 110, SpringLayout.WEST, dialog);
		
		// For Vertical Alignment
		sarahLayout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, dialog);
		
		BufferedImage img1 = new ImgUtils().scaleImage(StartFrame.frameWidth*70/100,StartFrame.frameHeight*44/100,"assets/story_dialog_box.png");
		ImagePanel box = new ImagePanel(img1);
		box.setOpaque(false);
		SpringLayout layout = new SpringLayout();

        box.setLayout(layout);

        answer = new CustomTextArea();
        answer.setPlaceholder("Type your answer here...");
        answer.setSize(StartFrame.frameWidth*68/100,StartFrame.frameHeight*40/100);
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
		layout.putConstraint(SpringLayout.WEST, answer, 5, SpringLayout.WEST, box);
		layout.putConstraint(SpringLayout.NORTH, answer, 15, SpringLayout.NORTH, box);
     
		box.add(answer);
		
		rightPanel.add(dialog, BorderLayout.NORTH);
		rightPanel.add(box,BorderLayout.SOUTH);

		this.add(leftPanel,BorderLayout.WEST);
		this.add(rightPanel,BorderLayout.EAST);
	}
	public String getUserInput(){
		return answer.getText();
	}
	
	public void setAnotherMessage(String msg){
		message.setText(msg);
        answer.setPlaceholder("Type your answer here...");
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
