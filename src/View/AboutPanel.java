package View;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AboutPanel extends JPanel{
	private Image myImage;
	
	public AboutPanel() {
		this.setBackground("assets/about.png");
		this.setLayout(new BorderLayout());
		this.setSize(StartFrame.frameWidth,StartFrame.frameHeight);
	}
	public void setBackground( String backgroundImagePath ){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(backgroundImagePath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = StartFrame.h;		
			double w = StartFrame.w*99/100;	
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			this.myImage = image;
			this.revalidate();
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g){ 
	        super.paintComponent(g);    
	        Graphics2D g2 = (Graphics2D) g;
	        g2.drawImage(myImage, 0, 0, null);
	} 
}
