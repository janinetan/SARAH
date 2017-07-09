package viewElements;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

import View.ActionPanel;
import View.InteractionPanel;
import View.StartFrame;
import View.StoryPanel;
import View.StoryPanel2;
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
		
		JFrame frame = StartFrameController.getFrame();
		
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"clickButton");

		frame.getRootPane().getActionMap().put("clickButton",new AbstractAction(){
	        public void actionPerformed(ActionEvent ae){
	        	btnNext.doClick();
			}
	    });
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (StartFrameController.getFramePanel() instanceof StoryPanel2 && button == btnNext){
				if ( StoryPanel2.isOkay )
					StartFrameController.playEvent();
				else
					StoryPanel2.reflectInMsgArea();
			}
			else if (StartFrameController.getFramePanel() instanceof InteractionPanel ){
				String userInput = ((InteractionPanel)StartFrameController.getFramePanel()).getUserInput();
				if ( userInput.equals("Type your answer here...") ){
					DialogueError dch = new DialogueError((java.awt.Frame)null, "Oops", true);
				}
				else{
					StartFrameController.sendUserResponse(userInput);
				}
			}
			else if (StartFrameController.getFramePanel() instanceof ActionPanel ){
				StartFrameController.playEvent();
			}
			//delayEnable(btnNext, 1500);
		}
	}
	public void delayEnable(JButton b, final long ms) {
	    b.setEnabled(false);
	   
	    new SwingWorker() {
	        @Override protected Object doInBackground() throws Exception {
	            Thread.sleep(ms);
	            return null;
	        }
	        @Override protected void done() {
	            b.setEnabled(true);
	        }
	    }.execute();
	}
	
}
