package viewElements;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import View.InteractionPanel;
import View.LocationPanel;
import View.StartFrame;
import View.WelcomePanel;
import driver.StartFrameController;

public class DialogueError extends JDialog{
	
	private JButton btnOk;
	private String btnOkImage = "assets/btn-ok.png";
	private String btnOkImageClicked = "assets/btn-ok_clicked.png";
	private Font font;
	private double width, height;
	
	public DialogueError(Frame f, String s, boolean b){
		super(f,s,b);
		
		FlowLayout flow = new FlowLayout();
    	flow.setAlignment(FlowLayout.CENTER);

    	this.getContentPane().setBackground(new Color(197,229,240));
    	this.getContentPane().setLayout(flow);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        height = StartFrame.h*0.4;
		width = StartFrame.w*0.5;
        
        this.setSize(new Dimension((new Double(width)).intValue(), (new Double(height)).intValue()));
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        
        font = new Font("Comic Sans MS", Font.PLAIN, (new Double(height/9)).intValue());
        
        this.setLayout(new BorderLayout());
        this.add(getDialogueContent(), BorderLayout.CENTER);
        this.add(getControlPanel(), BorderLayout.SOUTH);
        this.setVisible(true);
	}
	
	public JTextArea getDialogueContent(){
		String message = getErrorMessage();
		JTextArea content = new JTextArea(); 
		content.setText(message);
        content.setBackground(new Color(197,229,240));
        double h = StartFrame.h*0.4*0.95;
		double w = StartFrame.w*0.5*0.9;
		
        content.setSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
        content.setFont(font);
        content.setWrapStyleWord(true);
        content.setLineWrap(true);
        content.setOpaque(false);
        content.setEditable(false);
        content.setFocusable(false);
        int leftright = (new Double(h*0.10)).intValue();
        int updown = (new Double(h*0.05)).intValue();
        content.setMargin(new Insets(updown, leftright, leftright, updown));
        content.getCaret().deinstall( content );
		return content;
	}
	
	public String getErrorMessage(){
		JPanel curpanel = StartFrameController.getFramePanel();
		if (curpanel instanceof LocationPanel){
			return "Please select your location.";
		}
		else if (curpanel instanceof WelcomePanel){
			return "Please enter your name.";
		}
		else if (curpanel instanceof InteractionPanel){
			return "Please enter your response.";
		}
		else{
			return "Unidentified error.";
		}
	}
	
	public JPanel getControlPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(197,229,240));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, (new Double(width*0.05)).intValue(), (new Double(height*0.05)).intValue()));
        
		btnOk = new JButton();
		btnOk.setBorder(BorderFactory.createEmptyBorder());
		btnOk.setContentAreaFilled(false);
		btnOk.setBorderPainted(false);
		btnOk.addMouseListener(new BtnMouseListener());
		btnOk.addActionListener(new ButtonListener());
		setButtonIcon(btnOkImage, btnOk);
		
        buttonPanel.add(btnOk);
        return buttonPanel;
	}
	
	public void setButtonIcon(String filepath, JButton button){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(filepath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			double h = height*0.25;
			double w = width*0.40;
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(image);
			button.setIcon(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class BtnMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			String filepath = null;
			if (button == btnOk){
				filepath = btnOkImageClicked;
			}
			setButtonIcon(filepath, button);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			String filepath = null;
			if (button == btnOk){
				filepath = btnOkImage;
			}
			setButtonIcon(filepath, button);
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton button = (JButton) arg0.getSource();
			if (button == btnOk){
				// do nothing
			}
			dispose();
		}
	}
	
}
