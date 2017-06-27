package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import viewElements.ItemLoggerPanel;

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
	
	public static boolean isOkay = true;
	private static JTextArea msgTextArea;
	private static ArrayList<String> msgList;
	private static int msgCounter;
	private int msgLength = 150;
	
	public StoryPanelRaisa(String backgroundImagePath) {
		// TODO Auto-generated constructor stub
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setOpaque(false);
		bodyPanel.setSize(StartFrame.s);
		bodyPanel.setLayout(new BorderLayout());
		bodyPanel.add(setLowerControlHolder(), BorderLayout.SOUTH);   
		bodyPanel.add(setStoryCharacterHolder(), BorderLayout.CENTER);
		bodyPanel.add(setUpperControlHolder(), BorderLayout.NORTH);
				
		this.setBackground(backgroundImagePath);
		this.setLayout(new BorderLayout());
		this.setSize(StartFrame.s);
		this.add(bodyPanel, BorderLayout.CENTER);
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
	
	public JPanel setUpperControlHolder(){
		upperControlHolder = new JPanel();
		upperControlHolder.setOpaque(false);
		double h = StartFrame.h*0.25;		
		double w = StartFrame.w;				
		upperControlHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		upperControlHolder.setLayout(new BorderLayout());
		
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
		double h = StartFrame.h*0.5*0.7;		
		double w = StartFrame.w*0.48;
		double x = StartFrame.w/2 - w/2 ;
		double y = StartFrame.h*0.5 - h - h*0.15;
		msgHolder.setBounds((new Double(x)).intValue(),(new Double(y)).intValue(), (new Double(w)).intValue(), (new Double(h)).intValue());
		msgHolder.setLayout(new FlowLayout());
		
		int fontSize = msgHolder.getBounds().height / 8;
		Font font = new Font("Comic Sans MS", Font.PLAIN, fontSize);
		msgTextArea = new JTextArea(5, 20);
//        msgTextArea.setText("When you are done reading the current text, "
//        		+ "you can tap the arrow button on the lower right to proceed to the next part of the story.");
//        message.setSize(950,100);
//        msgTextArea.setSize(750,400);
        msgTextArea.setFont(font);
        msgTextArea.setWrapStyleWord(true);
        msgTextArea.setLineWrap(true);
        msgTextArea.setOpaque(false);
        msgTextArea.setEditable(false);
        msgTextArea.setFocusable(false);
        msgTextArea.getCaret().deinstall( msgTextArea );
        
        msgHolder.add(msgTextArea);
        
		JPanel characterHolder = new JPanel();
		h = StartFrame.h*0.6;	
		w = StartFrame.w;	
		y = StartFrame.h*-0.08;
		
		characterHolder.setBounds(0, (new Double(y)).intValue(), (new Double(w)).intValue(), (new Double(h)).intValue());
		characterHolder.setLayout(new FlowLayout( FlowLayout.CENTER ));
		characterHolder.setOpaque(false);
		
		JPanel sarahPanel = new JPanel();
		sarahPanel.setLayout( new BorderLayout() );
		sarahPanel.setOpaque(false);
		double sarahGap = StartFrame.h*0.6*0.10;
		sarahPanel.setBorder(new EmptyBorder((new Double(sarahGap)).intValue(), 0, 0, 0));
		sarahHolder = new ImagePanel("");
		h = StartFrame.h*0.65;
		w = StartFrame.w*0.27;
		sarahHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setVPImage(sarahDefaultImage, sarahHolder);
		sarahPanel.add(sarahHolder, BorderLayout.CENTER);
		
		JPanel gapPanel = new JPanel();
		gapPanel.setOpaque(false);
		double gap = StartFrame.w*0.40;
		gapPanel.setPreferredSize(new Dimension((new Double(gap)).intValue(), (new Double(h)).intValue()));
		
		JPanel liamPanel = new JPanel();
		liamPanel.setLayout( new BorderLayout() );
		liamPanel.setOpaque(false);
		double liamGap = StartFrame.h*0.6*0.177;
		liamPanel.setBorder(new EmptyBorder((new Double(liamGap)).intValue(), 0, 0, 0));
		liamHolder = new ImagePanel("");
		h = StartFrame.h*0.58;                                                                               
		w = StartFrame.w*0.24;
		liamHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setVPImage(liamDefaultImage, liamHolder);
		liamPanel.add(liamHolder, BorderLayout.CENTER);
		
		characterHolder.add(sarahPanel);
		characterHolder.add(gapPanel);
		characterHolder.add(liamPanel);
		
		storyElementHolder.add(characterHolder, new Integer(0), 0);
		storyElementHolder.add(msgHolder, new Integer(1), 0);
		
		return storyElementHolder;
	}
	
	// 	ADD FILEPATH AS VARIABLE
	public void displayMessage( String vp, String message ){
		
		String filepath = "assets/worried sarah.png";
		BufferedImage bufferedImage;
		String imagePath = "assets/msg-" + vp.toLowerCase() + ".png";
		
		ImagePanel charHolder = null;
		if (vp.equalsIgnoreCase("sarah")){
			charHolder = sarahHolder;
		}
		else if (vp.equalsIgnoreCase("liam")){
			charHolder = liamHolder;
		}
		this.setVPImage(filepath, charHolder);
		
		try {
			bufferedImage = ImageIO.read(new File(imagePath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = StartFrame.h*0.5*0.7;		
			double w = StartFrame.w*0.48;
			double x = StartFrame.w/2 - w/2 ;
			double y = StartFrame.h*0.5 - h - h*0.15;
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			msgHolder.setImage(image);
//			msgHolder.setBounds((new Double(x)).intValue(),(new Double(y)).intValue(), (new Double(w)).intValue(), (new Double(h)).intValue());
			
			if (vp.equalsIgnoreCase("sarah")){
				msgHolder.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
			}
			else if (vp.equalsIgnoreCase("liam")){
				msgHolder.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
			}
			
			msgHolder.repaint();
			msgHolder.revalidate();
			this.revalidate();
			this.repaint();
			
			this.cutMessageDialog(message);
			msgCounter = 0;
			this.reflectInMsgArea();
			
			this.revalidate();
			this.repaint();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void reflectInMsgArea(){
		msgTextArea.setText(msgList.get(msgCounter));
		msgTextArea.repaint();
		msgTextArea.revalidate();
		
		if (msgCounter + 1 != msgList.size()){
			msgCounter++;
			isOkay = false;
		}
		else {
			isOkay = true;
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
	
	public ArrayList<String> cutMessageDialog(String msg)
	{
		msgList = new ArrayList();
		int endIndex=0;
		String temp="", cut;
		if(msg.length()<=msgLength)
//			message.setText(msg); removed 23c084e
			msgList.add(msg);
			
		else
		{
			while(msg.length()!=0)
			{
				if(msg.length()>=msgLength){
					temp = msg.substring(0, msgLength);
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
					msgList.add(cut);
					msg = msg.substring(endIndex);
				}
				else{
					msgList.add(msg);
					msg="";
				}
			}
		}
		return msgList;
	}
}
