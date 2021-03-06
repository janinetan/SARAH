package viewElements;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import View.InteractionPanel;
import View.LocationPanel;
import View.StartFrame;
import View.StoryPanel2;
import View.WelcomePanel;
import driver.StartFrameController;

public class PanelBackNext extends JPanel {

	private JButton btnBack, btnNext;
	private String btnBackImage = "assets/btn-back.png";
	private String btnBackImageClicked = "assets/btn-back_clicked.png";
	private String btnNextImage = "assets/btn-nextLarge.png";
	private String btnNextImageClicked = "assets/btn-nextLarge_clicked.png";
	
	
	public PanelBackNext() {
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(0,0,StartFrame.frameHeight*5/100,StartFrame.frameHeight*5/100));
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnBack = new JButton();
		btnBack.setBorder(BorderFactory.createEmptyBorder());
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.addMouseListener(new BtnMouseListener());
		btnBack.addActionListener(new ButtonListener());
		setButtonIcon(btnBackImage, btnBack);
		
		btnNext = new JButton();
		btnNext.setBorder(BorderFactory.createEmptyBorder());
		btnNext.setContentAreaFilled(false);
		btnNext.setBorderPainted(false);
		btnNext.addMouseListener(new BtnMouseListener());
		btnNext.addActionListener(new ButtonListener());
		setButtonIcon(btnNextImage, btnNext);
		
		JFrame frame = StartFrameController.getFrame();
		
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"clickButton");

		frame.getRootPane().getActionMap().put("clickButton",new AbstractAction(){
	        public void actionPerformed(ActionEvent ae){
	        	btnNext.doClick();
			}
	    });
		
        this.add(btnBack);
        this.add(btnNext);
	}
	
	
	private class BtnMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			String filepath = null;
			if (button == btnBack){
				filepath = btnBackImageClicked;
			}
			else if (button == btnNext){
				filepath = btnNextImageClicked;
			}
			setButtonIcon(filepath, button);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			String filepath = null;
			if (button == btnBack){
				filepath = btnBackImage;
			}
			else if (button == btnNext){
				filepath = btnNextImage;
			}
			setButtonIcon(filepath, button);
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	
	public void setButtonIcon(String filepath, JButton button){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(filepath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double w = StartFrame.frameWidth*30/100;
			double h = StartFrame.frameHeight*15/100;
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			button.setIcon(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			JPanel curPanel = StartFrameController.getFramePanel();
			if ( curPanel instanceof LocationPanel){
				if ( button == btnBack )
					StartFrameController.displayWelcome();
				else if ( button == btnNext ){
					String theme = ((LocationPanel)curPanel).getTheme();
					if (theme.equals("")){
				    	DialogueError dch = new DialogueError((java.awt.Frame)null, "Oops", true);
			    	}
			    	else {
				        StartFrameController.displayStartStory(theme);
				    }
				}
			}
			else if (curPanel instanceof WelcomePanel ){
				if ( button == btnBack )
					StartFrameController.displayStartMenu();
				else if ( button == btnNext )
					StartFrameController.displayTheme();		    	
			}
		}
	}
	
}
