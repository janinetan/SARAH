package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import driver.StoryWorldManager;
import viewElements.ImagePanel;
import viewElements.PanelYesNo;

public class TutorialPanel extends JPanel {
	private Font font;
	private JFrame main;
	private BufferedImage image;
	private ImageIcon icon;
	private Image image1;
	private Image myImage;
	private static int ctr = 1;
	
	public TutorialPanel(JFrame main) throws IOException
	{
//		ArrayList<String> filepaths = new ArrayList<String>();
//		filepaths.add("assets/tutorial-1");
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,(int)(StartFrame.frameWidth*98.6/100),(int)(StartFrame.frameHeight*94.5/100));
		this.setLayout(new BorderLayout());
		
		this.setSize(StartFrame.s);
		ctr = 1;
		setBackground("assets/tutorial-" + ctr + ".png");
		ctr++;
		Font font = new Font("Comic Sans MS", Font.PLAIN, (int)(StartFrame.frameHeight*4/100));
	}
	
	public int nextBg(){
		if (ctr < 6 && ctr !=2){
			this.removeAll();
			setBackground("assets/tutorial-" + ctr + ".png");
			ctr++;
			this.repaint();
			this.revalidate();
			return 0;
		}
		else if(ctr ==2)
		{
			String msg = "In this screen, Sarah will ask you a question and you should type your answer in the text box below. Once you are done, click the blue button on the lower right." ;
			try {
				this.add(new TutorialInteractionPanel("sarah",msg,(new StoryWorldManager()).getLocationBg("park")), BorderLayout.CENTER);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ctr++;
			return 1;
			
		}
		this.removeAll();
		this.repaint();
		this.revalidate();
		return 2;
	}
	
	public void setBackground( String backgroundImagePath ){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(backgroundImagePath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = StartFrame.frameHeight*94.5/100;	
			double w = StartFrame.frameWidth*98.6/100;
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

