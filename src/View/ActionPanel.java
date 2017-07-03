package View;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import viewElements.ImagePanel;

public class ActionPanel extends JPanel{
	private Image myImage;
	private JPanel bodyPanel;
	
	private String actionBubbleImage = "assets/action_bubble.png";
	
	public ActionPanel (String backgroundImagepath, String peerImagepath, String icnImagepath) {
		// TODO Auto-generated constructor stub
		

		bodyPanel = new JPanel();
		bodyPanel.setOpaque(false);
		bodyPanel.setSize(StartFrame.s);
		bodyPanel.setLayout(new BorderLayout());
//		bodyPanel.add(setLowerControlHolder(), BorderLayout.SOUTH);   
//		bodyPanel.add(setUpperControlHolder(), BorderLayout.NORTH);
		setContent(peerImagepath, icnImagepath);		
		
		this.setBackground(backgroundImagepath);
		this.setLayout(new BorderLayout());
		this.setSize(StartFrame.s);
		this.add(bodyPanel, BorderLayout.CENTER);
	}
	
	public void setContent(String peerImagepath, String icnImagepath){
		bodyPanel.removeAll();
		bodyPanel.add(setVP(peerImagepath), BorderLayout.EAST);
		bodyPanel.add(setAction(icnImagepath), BorderLayout.CENTER);
	}
	
	public JPanel setVP(String peerImagepath){
		JPanel vpanel = new JPanel();
		vpanel.setOpaque(false);
		double h = StartFrame.h;		
		double w = StartFrame.w*0.40;			
		vpanel.setLayout(new BorderLayout());
		int gap = (new Double(h*0.05)).intValue();
		vpanel.setBorder(BorderFactory.createEmptyBorder(gap, 0, gap, 0));
		
		ImagePanel vpHolder = new ImagePanel("");
		h = StartFrame.h*0.90;		
		w = StartFrame.w*0.40;			
		vpHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setImagePanelImage(peerImagepath, vpHolder);
		
		vpanel.add(vpHolder, BorderLayout.CENTER);
		return vpanel;
	}
	
	public JPanel setAction(String icnImagepath){
		JPanel apanel = new JPanel();
		apanel.setOpaque(false);
		double h = StartFrame.h;
		double w = StartFrame.w*0.60;
		apanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		int gap = (new Double(h*0.20)).intValue();
		apanel.setBorder(BorderFactory.createEmptyBorder(gap, 0, gap, 0));
		
		ImagePanel bubbleHolder = new ImagePanel("");
		h = StartFrame.h*0.50;		
		w = StartFrame.w*0.55;			
		bubbleHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setImagePanelImage(actionBubbleImage, bubbleHolder);
		bubbleHolder.setLayout(new FlowLayout(FlowLayout.CENTER));
		gap = (new Double(h*0.15)).intValue();
		bubbleHolder.setBorder(BorderFactory.createEmptyBorder(gap, 0, gap, 0));
		
		ImagePanel actionHolder = new ImagePanel("");
		actionHolder.setOpaque(false);
		h = StartFrame.h*0.3;		
		w = StartFrame.w*0.20;			
		actionHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setImagePanelImage(icnImagepath, actionHolder);
		
		bubbleHolder.add(actionHolder);
		apanel.add(bubbleHolder);
		
		return apanel;
	}
	
	public void setBackground( String backgroundImagePath ){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(backgroundImagePath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = StartFrame.h;		
			double w = StartFrame.w;	
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			this.myImage = image;
			this.revalidate();
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setImagePanelImage(String filepath, ImagePanel imgPanel){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(filepath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = imgPanel.getPreferredSize().getHeight();
			double w = imgPanel.getPreferredSize().getWidth();
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			imgPanel.setImage(image);
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
