package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class StoryPanelRaisa extends JPanel{
	private JPanel upperControlHolder, lowerControlHolder;
	private ItemLoggerPanel activityLogPanel;
	private ItemLoggerPanel healthLogPanel;
	private ItemLoggerPanel sicknessLogPanel;
	private ItemLoggerPanel symptomLogPanel;
	private Image myImage;
	private ImagePanel msgHolder;
	private ImagePanel sarahHolder, liamHolder;
	private String sarahDefaultImage = "assets/vp-sarah.png";
	private String liamDefaultImage = "assets/vp-liam.png";
	
	public StoryPanelRaisa(String backgroundImagePath) {
		// TODO Auto-generated constructor stub
		this.setBackground(backgroundImagePath);
		this.setLayout(new BorderLayout());
		this.add(setUpperControlHolder(), BorderLayout.NORTH);
		this.add(setLowerControlHolder(), BorderLayout.SOUTH);
		this.add(setStoryCharacterHolder(), BorderLayout.CENTER);
	}
	
	public void test(){
		setVPImage("assets/vp-liam.png", sarahHolder);
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
	
	public void setVPImage(String filepath, ImagePanel imgPanel){
		BufferedImage bufferedImage;
		System.out.println(filepath);
		try {
			bufferedImage = ImageIO.read(new File(filepath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = imgPanel.getPreferredSize().getHeight();
			double w = imgPanel.getPreferredSize().getWidth();
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
				    System.out.println(h + ":" + w);
			imgPanel.setImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JPanel setUpperControlHolder(){
		upperControlHolder = new JPanel();
		upperControlHolder.setOpaque(false);
		double h = StartFrame.h*0.25;		
		double w = StartFrame.w;				
		upperControlHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		upperControlHolder.setLayout(new BorderLayout());
		
		upperControlHolder.add(new PanelHome(), BorderLayout.NORTH);
		upperControlHolder.add(setActivityStatusHolder(), BorderLayout.SOUTH);
		return upperControlHolder;
	}
	
	public JPanel setActivityStatusHolder(){
		JPanel activityStatusHolder = new JPanel();
		activityStatusHolder.setOpaque(false);
		double h = upperControlHolder.getPreferredSize().getHeight()*0.70;
		double w = upperControlHolder.getPreferredSize().getWidth();	
		activityStatusHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		activityStatusHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		activityLogPanel = new ItemLoggerPanel();
		activityLogPanel.setSize(upperControlHolder, 0.08*6, 0.70);
		activityLogPanel.setTitle("History of Actions");
		
		symptomLogPanel = new ItemLoggerPanel();
		symptomLogPanel.setSize(upperControlHolder, 0.08*3, 0.70);
		symptomLogPanel.setTitle("Gathered Symptoms");
		
		activityStatusHolder.add(symptomLogPanel);
		activityStatusHolder.add(activityLogPanel);
		return activityStatusHolder;
	}
	
	public JPanel setLowerControlHolder(){
		lowerControlHolder = new JPanel();
		lowerControlHolder.setOpaque(false);
		lowerControlHolder.setBackground(Color.BLACK);
		double h = StartFrame.h*0.18;		
		double w = StartFrame.w;				
		lowerControlHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		lowerControlHolder.setLayout(new BorderLayout());
		
		lowerControlHolder.add(setSicknessStatusHolder(), BorderLayout.SOUTH);
		return lowerControlHolder;
	}
	
	public JPanel setSicknessStatusHolder(){
		JPanel sicknessStatusHolder = new JPanel();
		sicknessStatusHolder.setOpaque(false);
		double h = lowerControlHolder.getPreferredSize().getHeight();
		double w = lowerControlHolder.getPreferredSize().getWidth();	
		sicknessStatusHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		sicknessStatusHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		sicknessLogPanel = new ItemLoggerPanel();
		sicknessLogPanel.setSize(lowerControlHolder, 0.08*3, 0.9);
		sicknessLogPanel.setTitle("Possible Sickness");
		
		healthLogPanel = new ItemLoggerPanel();
		healthLogPanel.setSize(lowerControlHolder, 0.08*6, 0.9);
		healthLogPanel.setTitle("Liam Health Status");
		
		sicknessStatusHolder.add(sicknessLogPanel);
		sicknessStatusHolder.add(healthLogPanel);
		
		return sicknessStatusHolder;
	}
	
	public JLayeredPane setStoryCharacterHolder(){
		JLayeredPane storyElementHolder = new JLayeredPane();
		
		msgHolder = new ImagePanel("");
		displayMessage("sarah", "hello");
		
		JPanel characterHolder = new JPanel();
		double h = StartFrame.h*0.57;	
		double w = StartFrame.w;	
		
		characterHolder.setBounds(0, 0, (new Double(w)).intValue(), (new Double(h)).intValue());
		double gap = StartFrame.w*0.25;
		characterHolder.setLayout(new FlowLayout( FlowLayout.CENTER, (new Double(gap)).intValue(), 0));
		characterHolder.setOpaque(false);
		
		sarahHolder = new ImagePanel("");
		h = StartFrame.h*0.65;
		w = StartFrame.w*0.25;
		sarahHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setVPImage(sarahDefaultImage, sarahHolder);
		
		liamHolder = new ImagePanel("");                                                                   
		h = StartFrame.h*0.55;                                                                               
		w = StartFrame.w*0.22;
		liamHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setVPImage(liamDefaultImage, liamHolder);
		
		characterHolder.add(sarahHolder);
		characterHolder.add(liamHolder);
		
		storyElementHolder.add(characterHolder, new Integer(0), 0);
		storyElementHolder.add(msgHolder, new Integer(1), 0);
		
		return storyElementHolder;
	}
	
	public void displayMessage( String vp, String message ){
		BufferedImage bufferedImage;
		String imagePath = "assets/msg-" + vp.toLowerCase() + ".png";
		try {
			bufferedImage = ImageIO.read(new File(imagePath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = StartFrame.h*0.57*0.4;		
			double w = StartFrame.w*0.50;
			double x = StartFrame.w - w - w/2;
			double y = StartFrame.h*0.57*0.45;
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			msgHolder = new ImagePanel(image);
			msgHolder.setBounds((new Double(x)).intValue(),(new Double(y)).intValue(), (new Double(w)).intValue(), (new Double(h)).intValue());
			
			msgHolder.repaint();
			msgHolder.revalidate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void addActivity(String imagePath, String time){
		activityLogPanel.addItem(imagePath, time);
	}
	
	public void addLiamHealthStatus(String imagePath, String healthStatus){
		healthLogPanel.addItem(imagePath, healthStatus);
	}
	
	public void addSymptom(String imagePath, String symptomName){
		symptomLogPanel.addItem(imagePath, symptomName);
	}
	
	public void addSickness(String imagePath, String sicknessName){
		sicknessLogPanel.addItem(imagePath, sicknessName);
	}
	
	@Override
    protected void paintComponent(Graphics g){ 
        super.paintComponent(g);    
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(myImage, 0, 0, null);
    } 
}
