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
import javax.swing.JDialog;
import javax.swing.JPanel;

import View.StartFrame;

public class PanelHome extends JPanel {
	
	private JButton btnHome;
	
	public PanelHome(){
		this.setOpaque(false);
		double h = StartFrame.h*0.25*0.50;
		double w = StartFrame.w;	
		this.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		this.addHomeButton();
	}
	
	public void addHomeButton(){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("assets/btn-home.png"));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = this.getPreferredSize().getHeight() - 10;
			Image image = imageIcon.getImage().getScaledInstance((new Double(h)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			btnHome = new JButton(imageIcon);
			btnHome.setContentAreaFilled(false);
	        btnHome.setBorderPainted(false);
			btnHome.setBorder(null);
			btnHome.addActionListener(new ButtonListener());
			this.add(btnHome);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (button == btnHome){
		    	DialogueConfirmHome dch = new DialogueConfirmHome((java.awt.Frame)null, "Go to Home", true);
			}
		}
	}
}
