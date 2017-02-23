import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Paint;
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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ThemePanel extends JPanel{
	
	public ThemePanel(JFrame main) throws IOException 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		Font font = new Font("Comic Sans MS", Font.PLAIN, 60);
		
		ImagePanel box = new ImagePanel("assets/cloud.png");
		SpringLayout dialogLayout = new SpringLayout();

        JTextArea greeting = new JTextArea("What do you want to learn about?");
        greeting.setSize(700, 200);
        greeting.setFont(font);
        greeting.setWrapStyleWord(true);
        greeting.setLineWrap(true);
        greeting.setOpaque(false);
        greeting.setEditable(false);
        greeting.setFocusable(false);
        greeting.getCaret().deinstall( greeting );
        greeting.setBorder(UIManager.getBorder("JLabel"));
     
		box.add(greeting);
		
		// For horizontal Alignment
		dialogLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, greeting, 0, SpringLayout.HORIZONTAL_CENTER, box);

		// For Vertical Alignment
		dialogLayout.putConstraint(SpringLayout.VERTICAL_CENTER, greeting, 0, SpringLayout.VERTICAL_CENTER, box);

        box.setLayout(dialogLayout);
        box.setLocation(0, 20);
        
        JPanel themes = new JPanel();
        SpringLayout themesLayout = new SpringLayout();
        themes.setBounds(170, 400, 1300, 500);
        //themes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        themes.setOpaque(false);

		themes.setLayout(themesLayout);
        
        BufferedImage buttonIcon1 = ImageIO.read(new File("assets/sick1.png"));
		JButton sickButton1 = new JButton(new ImageIcon(buttonIcon1));
		sickButton1.setBorder(BorderFactory.createEmptyBorder());
		sickButton1.setContentAreaFilled(false);
		sickButton1.setBorderPainted(false);
		sickButton1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	/*ImageIcon image = new ImageIcon("assets/highlight.png");
		    	JLabel label = new JLabel();
		        label.setIcon(image);
		        themes.add(label);
		        themesLayout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, themes);*/
		    	/*Border b =  BorderFactory.createDashedBorder(Color.BLACK, 5, 4, 2, true);
		    	sickButton1.setBorderPainted(true);
		    	sickButton1.setBorder(b);*/
		    	ImageIcon image = new ImageIcon("assets/sick1_clicked1.png");
		    	sickButton1.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/sick1.png");
		    	sickButton1.setIcon(image);
		    }
		});
		
		BufferedImage buttonIcon2 = ImageIO.read(new File("assets/sick2.png"));
		JButton sickButton2 = new JButton(new ImageIcon(buttonIcon2));
		sickButton2.setBorder(BorderFactory.createEmptyBorder());
		sickButton2.setContentAreaFilled(false);
		sickButton2.setBorderPainted(false);
		sickButton2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/sick2.png");
		    	sickButton2.setIcon(image);
		    }
		});
		
		
		BufferedImage buttonIcon3 = ImageIO.read(new File("assets/sick3.png"));
		JButton sickButton3 = new JButton(new ImageIcon(buttonIcon3));
		sickButton3.setBorder(BorderFactory.createEmptyBorder());
		sickButton3.setContentAreaFilled(false);
		sickButton3.setBorderPainted(false);
		sickButton3.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/sick3.png");
		    	sickButton3.setIcon(image);
		    }
		});
		
		
		BufferedImage buttonIcon4 = ImageIO.read(new File("assets/sick4.png"));
		JButton sickButton4 = new JButton(new ImageIcon(buttonIcon4));
		sickButton4.setBorder(BorderFactory.createEmptyBorder());
		sickButton4.setContentAreaFilled(false);
		sickButton4.setBorderPainted(false);
		sickButton4.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/sick4.png");
		    	sickButton4.setIcon(image);
		    }
		});
		
		
		BufferedImage buttonIcon5 = ImageIO.read(new File("assets/sick5.png"));
		JButton sickButton5 = new JButton(new ImageIcon(buttonIcon5));
		sickButton5.setBorder(BorderFactory.createEmptyBorder());
		sickButton5.setContentAreaFilled(false);
		sickButton5.setBorderPainted(false);
		sickButton5.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/sick5.png");
		    	sickButton5.setIcon(image);
		    }
		});
		
		
		BufferedImage buttonIcon6 = ImageIO.read(new File("assets/sick6.png"));
		JButton sickButton6 = new JButton(new ImageIcon(buttonIcon6));
		sickButton6.setBorder(BorderFactory.createEmptyBorder());
		sickButton6.setContentAreaFilled(false);
		sickButton6.setBorderPainted(false);
		sickButton6.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	/*ImageIcon image = new ImageIcon("assets/highlight.png");
		    	JLabel label = new JLabel();
		        label.setIcon(image);
		        themes.add(label);
		        themesLayout.putConstraint(SpringLayout.WEST, sickButton6, 0, SpringLayout.WEST, label);*/
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/sick6.png");
		    	sickButton6.setIcon(image);
		    }
		});
		
		
		themes.add(sickButton1);
		themes.add(sickButton2);
		themes.add(sickButton3);
		themes.add(sickButton4);
		themes.add(sickButton5);
		themes.add(sickButton6);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton1, 0, SpringLayout.WEST,themes);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton2, 60, SpringLayout.EAST, sickButton1);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton3, 60, SpringLayout.EAST, sickButton2);
		
		themesLayout.putConstraint(SpringLayout.NORTH, sickButton4, 30, SpringLayout.SOUTH, sickButton1);
		themesLayout.putConstraint(SpringLayout.NORTH, sickButton5, 30, SpringLayout.SOUTH, sickButton2);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton5, 60, SpringLayout.EAST, sickButton4);
		themesLayout.putConstraint(SpringLayout.NORTH, sickButton6, 30, SpringLayout.SOUTH, sickButton3);
		themesLayout.putConstraint(SpringLayout.WEST, sickButton6, 60, SpringLayout.EAST, sickButton5);
        BufferedImage nextButtonIcon = ImageIO.read(new File("assets/next_button.png"));
		JButton nextButton = new JButton(new ImageIcon(nextButtonIcon));
		nextButton.setBorder(BorderFactory.createEmptyBorder());
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setBounds(1000,780,500,200);
		nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/next_button_clicked.png");
		    	nextButton.setIcon(image);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ImageIcon image = new ImageIcon("assets/next_button.png");
		    	nextButton.setIcon(image);
		    }
//		    public void mouseClicked(java.awt.event.MouseEvent evt)
//		    {
//		    	ImageIcon image = new ImageIcon("assets/start_button_clicked.png");
//		        startButton.setIcon(image);
//		    }
		    
		});
		nextButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	/*not working when mouselistener code was added*/
		        try {
					main.setContentPane(new TutorialPromptPanel(main));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		    }
		});
        
		this.add(box);
		this.add(themes);
		this.add(nextButton);
		validate();
		
	}
}
