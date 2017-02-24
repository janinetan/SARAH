import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class WelcomePanel extends JPanel{
	
	public WelcomePanel(JFrame main) throws IOException 
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(0,0,StartFrame.frameWidth,StartFrame.frameHeight);
		setBackground(new Color(197,229,240));
		setLayout(null);
		
		JLabel welcome = new JLabel("Hello there! What's your name?");
		Font font = new Font("Comic Sans MS", Font.PLAIN, 60);
		welcome.setFont(font);
		welcome.setBounds(100,-50,1000,300);
		
		CustomTextField name = new CustomTextField();
		name.setPlaceholder("Type Name Here");
		name.setOpaque(false);
		name.setBorder(new EmptyBorder(5, 5, 5, 5));
		//name.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		name.setFont(font);
		name.setBounds(100, 200, 1200, 100);
		
		JLabel line = new JLabel("________________________________");
		line.setBounds(100,230,1300,100);
		line.setFont(font);
		
		ImagePanel liam = new ImagePanel("assets/liam_flipped.png");
        liam.setLocation(-40, 400);
        
        /*ImagePanel box = new ImagePanel("assets/liam_dialog_box.png");
        box.setLayout(new BorderLayout());
        box.setLocation(0, 400);
        
        JTextArea greeting = new JTextArea("Hi, I’m Liam. Let’s play together!!");
        greeting.setSize(900, 400);
        greeting.setFont(font);
        greeting.setWrapStyleWord(true);
        greeting.setLineWrap(true);
        greeting.setOpaque(false);
        greeting.setEditable(false);
        greeting.setFocusable(false);
        greeting.setBorder(UIManager.getBorder("JLabel"));
     
		box.add(greeting, BorderLayout.EAST);*/
		
		ImagePanel box = new ImagePanel("assets/dialog_box.png");
		SpringLayout layout = new SpringLayout();

        JTextArea greeting = new JTextArea("Hi, I’m Liam. Let’s play together!!");
        greeting.setSize(500, 200);
        greeting.setFont(font);
        greeting.setWrapStyleWord(true);
        greeting.setLineWrap(true);
        greeting.setOpaque(false);
        greeting.setEditable(false);
        greeting.setFocusable(false);
        greeting.getCaret().deinstall( greeting );
        //greeting.setBorder(UIManager.getBorder("JLabel"));
     
		box.add(greeting);
		
		// For horizontal Alignment
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, greeting, 30, SpringLayout.HORIZONTAL_CENTER, box);

		// For Vertical Alignment
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, greeting, 0, SpringLayout.VERTICAL_CENTER, box);

        box.setLayout(layout);
        box.setLocation(570, 400);
		
		BufferedImage buttonIcon = ImageIO.read(new File("assets/next_button.png"));
		JButton nextButton = new JButton(new ImageIcon(buttonIcon));
		nextButton.setBorder(BorderFactory.createEmptyBorder());
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setBounds(1150,780,500,200);
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
		    	ImageIcon image = new ImageIcon("assets/next_button_clicked.png");
		    	nextButton.setIcon(image); 
		        try {
					main.setContentPane(new ThemePanel(main));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		    }
		});
        
		this.add(welcome);
		this.add(name);
		this.add(line);
		this.add(liam);
		this.add(box);
		this.add(nextButton);
		validate();
	}
}
