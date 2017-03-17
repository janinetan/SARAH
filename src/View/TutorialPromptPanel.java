package View;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import driver.StartFrameController;

public class TutorialPromptPanel extends JPanel{

	public TutorialPromptPanel(JFrame main) throws IOException
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, 60);
		
		ImagePanel sarah = new ImagePanel("assets/sarah.png");
		sarah.setLocation(-40, 100);
		
		ImagePanel box = new ImagePanel("assets/tutorial.png");
		SpringLayout layout = new SpringLayout();

        JTextArea question = new JTextArea("Would you like a tutorial?");
        question.setSize(500, 200);
        question.setFont(font);
        question.setWrapStyleWord(true);
        question.setLineWrap(true);
        question.setOpaque(false);
        question.setEditable(false);
        question.setFocusable(false);
        question.getCaret().deinstall( question );
        //greeting.setBorder(UIManager.getBorder("JLabel"));
     
		box.add(question);
		
		// For horizontal Alignment
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, question, 30, SpringLayout.HORIZONTAL_CENTER, box);

		// For Vertical Alignment
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, question, 0, SpringLayout.VERTICAL_CENTER, box);

        box.setLayout(layout);
        box.setLocation(650, 70);
		
        JPanel buttonPanel = new JPanel();
        SpringLayout buttonLayout = new SpringLayout();
        buttonPanel.setLayout(buttonLayout);
        buttonPanel.setBounds(730,550,710,200);
        buttonPanel.setOpaque(false);
        
		BufferedImage buttonIcon = ImageIO.read(new File("assets/yes.png"));
		JButton yesButton = new JButton(new ImageIcon(buttonIcon));
		yesButton.setBorder(BorderFactory.createEmptyBorder());
		yesButton.setContentAreaFilled(false);
		yesButton.setBorderPainted(false);
		yesButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/yes_clicked.png");
		    	yesButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/yes.png");
		    	yesButton.setIcon(image);
		    }
		});
		yesButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	StartFrameController.displayStory();
		    }
		});
		BufferedImage buttonIcon1 = ImageIO.read(new File("assets/no.png"));
		JButton noButton = new JButton(new ImageIcon(buttonIcon1));
		noButton.setBorder(BorderFactory.createEmptyBorder());
		noButton.setContentAreaFilled(false);
		noButton.setBorderPainted(false);
		noButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/no_clicked.png");
		    	noButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/no.png");
		    	noButton.setIcon(image);
		    }
		});
		noButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/*not working when mouselistener code was added*/
		        try {
					main.setContentPane(new ThemePanel(main));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		    }
		});

		buttonLayout.putConstraint(SpringLayout.WEST, noButton, 20, SpringLayout.EAST, yesButton);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        
		this.add(sarah);
		this.add(box);
		this.add(buttonPanel);
		validate();
	}
}
