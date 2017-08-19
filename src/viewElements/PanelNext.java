package viewElements;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

import View.ActionPanel;
import View.InteractionPanel;
import View.StartFrame;
import View.StoryPanel2;
import View.TutorialInteractionPanel;
import View.TutorialPanel;
import driver.StartFrameController;

public class PanelNext extends JPanel {

	private JButton btnNext;
	JPanel p = this;
	JPanel arrowNextButtonPanel;
	JPanel glowPanel;

	JLabel arrow;
	
	public PanelNext() {
		this.setOpaque(false);
		double h = StartFrame.h*0.25;
		double w = StartFrame.w*0.99;	
		JLayeredPane wholeNextPanel = new JLayeredPane();
		arrowNextButtonPanel = new JPanel();
		arrowNextButtonPanel.setOpaque(false);
		arrowNextButtonPanel.setSize((new Double(w)).intValue(), (new Double(h)).intValue());
		arrowNextButtonPanel.setLayout(new BorderLayout());
		arrowNextButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,new Double(StartFrame.h*.024).intValue()));
		//arrowNextButtonPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		//remove comment to this arrowNextButton.add(addArrow(), BorderLayout.NORTH);
		glowPanel = new JPanel();
		glowPanel.setOpaque(false);
		glowPanel.setSize((new Double(w)).intValue(), (new Double(h)).intValue());
		glowPanel.setLayout(new BorderLayout());
		glowPanel.setBorder(null);
		//glowPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
		glowPanel.add(addArrow(),BorderLayout.EAST);
		arrowNextButtonPanel.add(addNextButton(),BorderLayout.EAST);
		this.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.setLayout(new BorderLayout());
		
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//this.addNextButton(); 
		//this.add(arrowNextButtonPanel,BorderLayout.EAST);
		wholeNextPanel.add(arrowNextButtonPanel, new Integer(1), 0);
		wholeNextPanel.add(glowPanel, new Integer(0), 0);
		this.add(wholeNextPanel);
	}
	
	public JButton addNextButton(){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("assets/btn-next.png"));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = arrowNextButtonPanel.getSize().getHeight()*.70;
			Image image = imageIcon.getImage().getScaledInstance((new Double(h)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			btnNext = new JButton(imageIcon);
			btnNext.setContentAreaFilled(false);
	        btnNext.setBorderPainted(false);
			btnNext.setBorder(null);
			//btnNext.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			btnNext.addActionListener(new ButtonListener());
			delayEnable(btnNext, 1000);
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
		return btnNext;
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
			else if (StartFrameController.getFramePanel() instanceof TutorialPanel || StartFrameController.getFramePanel() instanceof TutorialInteractionPanel){
				StartFrameController.nextTutorialsPage();
			}
			delayEnable(btnNext, 1000);
		}
	}
	public void delayEnable(JButton b, final long ms) {
	    b.setEnabled(false);
	    //b.setVisible(false);
	    arrow.setVisible(false);
	    new SwingWorker() {
	        @Override protected Object doInBackground() throws Exception {
	            Thread.sleep(ms);
	            return null;
	        }
	        @Override protected void done() {
	        	b.setEnabled(true);
	            //b.setVisible(true);
	        	if (!(StartFrameController.getFramePanel() instanceof TutorialPanel || StartFrameController.getFramePanel() instanceof TutorialInteractionPanel)){      
		            Action showArrow = new AbstractAction()
		            {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							arrow.setVisible(true);
						}
		            };
		            InactivityListener listener = new InactivityListener(p, showArrow, 10000);
		            listener.start();
		       }
	        	else
	        		arrow.setVisible(true);
	        }
	    }.execute();
	}
	
	public JLabel addArrow()
	{
			arrow = new JLabel();
			Image image =Toolkit.getDefaultToolkit().createImage("assets/g.gif");
			ImageIcon xIcon = new ImageIcon(image);
			double h = arrowNextButtonPanel.getSize().getHeight();
			image = xIcon.getImage().getScaledInstance((new Double(h*.90)).intValue(), (new Double(h*.90)).intValue(), Image.SCALE_DEFAULT);
			xIcon.setImage(image);
			arrow.setIcon(xIcon);
			arrow.setBorder(null);
		return arrow;
	}
	
}
