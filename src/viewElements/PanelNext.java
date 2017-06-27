package viewElements;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import View.InteractionPanel;
import View.StartFrame;
import View.StoryPanel;
import View.StoryPanelRaisa;
import driver.StartFrameController;

public class PanelNext extends JPanel {

	private JButton btnNext;
	
	public PanelNext() {
		this.setOpaque(false);
		double h = StartFrame.h*0.25*0.50;
		double w = StartFrame.w;	
		this.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		this.addNextButton(); 
	}
	
	public void addNextButton(){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("assets/btn-next.png"));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = this.getPreferredSize().getHeight() - 10;
			Image image = imageIcon.getImage().getScaledInstance((new Double(h)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			btnNext = new JButton(imageIcon);
			btnNext.setContentAreaFilled(false);
	        btnNext.setBorderPainted(false);
			btnNext.setBorder(null);
			btnNext.addActionListener(new ButtonListener());
			this.add(btnNext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (StartFrameController.getFramePanel() instanceof StoryPanelRaisa && button == btnNext){
				if ( StoryPanelRaisa.isOkay )
					StartFrameController.playEvent();
				else
					StoryPanelRaisa.reflectInMsgArea();
			}
			else if (StartFrameController.getFramePanel() instanceof InteractionPanel ){
//		    	StartFrameController.sendUserResponse(((InteractionPanel)StartFrameController.getFramePanel()).getUserInput());
			}
		}
	}
	
}
