import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class InteractionPanel extends JPanel{
	JTextArea message,answer;
	
	public InteractionPanel(JFrame main) throws IOException {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, 50);
		
		BufferedImage img = new ImgUtils().scaleImage(1500,700,"assets/cartoon_room.png");
		ImagePanel room = new ImagePanel(img);
		room.setLocation(0, 0);
		
		ImagePanel sarah = new ImagePanel("assets/sarah.png");
		sarah.setLocation(-70, 100);
		
		ImagePanel dialog = new ImagePanel("assets/sarah_dialog_box1.png");
		dialog.setLocation(550, 40);
		SpringLayout sarahLayout = new SpringLayout();
		message = new JTextArea();
        message.setSize(830,100);
        message.setFont(font);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setOpaque(false);
        message.setEditable(false);
        message.setFocusable(false);
        message.getCaret().deinstall(message);
        
		// For horizontal Alignment
		sarahLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, message, 0, SpringLayout.HORIZONTAL_CENTER, dialog);
		
		// For Vertical Alignment
		sarahLayout.putConstraint(SpringLayout.VERTICAL_CENTER, message, 0, SpringLayout.VERTICAL_CENTER, dialog);

		
		ImagePanel box = new ImagePanel("assets/story_dialog_box.png");
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.EAST, answer, -15, SpringLayout.EAST, box);
		layout.putConstraint(SpringLayout.NORTH, answer, 15, SpringLayout.NORTH, box);

        box.setLayout(layout);
        box.setLocation(0, 575);

        answer = new JTextArea();
        answer.setSize(830,100);
        answer.setFont(font);
        answer.setWrapStyleWord(true);
        answer.setLineWrap(true);
        answer.setOpaque(false);
        answer.setEditable(false);
        answer.setFocusable(false);
        answer.getCaret().deinstall( answer );
        //greeting.setBorder(UIManager.getBorder("JLabel"));
        //greeting.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     
        BufferedImage buttonIcon1 = ImageIO.read(new File("assets/tap_next.png"));
        ImageIcon icon = new ImageIcon(buttonIcon1);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
        icon = new ImageIcon(image, icon.getDescription());
		JButton nextButton = new JButton(icon);
		nextButton.setBorder(BorderFactory.createEmptyBorder());
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/tap_next_clicked.png");
		        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	nextButton.setIcon(icon);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon icon = new ImageIcon("assets/tap_next.png");
		        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() * 70/100,icon.getIconHeight() * 70/100,Image.SCALE_SMOOTH);
		        icon = new ImageIcon(image, icon.getDescription());
		    	nextButton.setIcon(icon);
		    }
		});
		nextButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	answer.setText("hi");
		    }
		});

		layout.putConstraint(SpringLayout.EAST, nextButton, -15, SpringLayout.EAST, box);
		layout.putConstraint(SpringLayout.SOUTH, nextButton, -15, SpringLayout.SOUTH, box);

		box.add(nextButton);
		box.add(answer);
		
		
		this.add(sarah);
		this.add(dialog);
		this.add(box);
		this.add(room);
	}
}
