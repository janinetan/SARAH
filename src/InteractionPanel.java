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
	JTextArea greeting;
	
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
		sarah.setLocation(-40, 100);
		
		ImagePanel dialog = new ImagePanel("assets/sarah_dialog_box.png");
		dialog.setLocation(550, 100);
		
		ImagePanel box = new ImagePanel("assets/story_dialog_box.png");
		SpringLayout layout = new SpringLayout();

        greeting = new JTextArea();
        greeting.setText("you can tap the arrow button on the lower right to proceed to the next part of the story.");
        greeting.setSize(830,100);
        greeting.setFont(font);
        greeting.setWrapStyleWord(true);
        greeting.setLineWrap(true);
        greeting.setOpaque(false);
        greeting.setEditable(false);
        greeting.setFocusable(false);
        greeting.getCaret().deinstall( greeting );
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
		    	greeting.setText("hi");
		        
		        
		    }
		});
		

		box.add(nextButton);
		box.add(greeting);
		
		// For horizontal Alignment
		layout.putConstraint(SpringLayout.EAST, greeting, -15, SpringLayout.EAST, box);

		// For Vertical Alignment
		layout.putConstraint(SpringLayout.NORTH, greeting, 15, SpringLayout.NORTH, box);
		
		
		layout.putConstraint(SpringLayout.EAST, nextButton, -15, SpringLayout.EAST, box);
		layout.putConstraint(SpringLayout.SOUTH, nextButton, -15, SpringLayout.SOUTH, box);

        box.setLayout(layout);
        box.setLocation(0, 575);
		
		this.add(sarah);
		this.add(dialog);
		this.add(box);
		this.add(room);
	}
}
