package viewElements;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

public class ItemLoggerPanel extends RoundedPanel{
	private JPanel itemLogHolder, refPanel;
	private JScrollBar horizontal;
	private BasicArrowButton west, east;
	private double itemwidth, elementwidth;
	
	public ItemLoggerPanel() {
		itemwidth = 0;
		elementwidth = 0;
		
		this.setBackground(Color.decode("#EBF2FA"));
		this.setForeground(Color.decode("#96CDFF"));
		this.setLayout(new BorderLayout());
		
		itemLogHolder = new JPanel();
		itemLogHolder.setOpaque(false);
		itemLogHolder.setLayout( new FlowLayout(FlowLayout.RIGHT, 0, 0) );
		JScrollPane scrollpane = new JScrollPane(itemLogHolder);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
		scrollpane.setBorder(null);

		this.add(scrollpane, BorderLayout.CENTER);
		horizontal = scrollpane.getHorizontalScrollBar();

        west = new BasicArrowButton(BasicArrowButton.WEST);
        west.setAction( new ActionMapAction("", horizontal, "negativeUnitIncrement") );
        west.setVisible(false);
        this.add(west, BorderLayout.WEST);

        east = new BasicArrowButton(BasicArrowButton.EAST);
        east.setAction( new ActionMapAction("", horizontal, "positiveUnitIncrement") );
        east.setVisible(false);
        this.add(east, BorderLayout.EAST);
	}
	
	public void setSize(JPanel refPanel, double widthPercentage, double heightPercentage){
		this.refPanel = refPanel;
		double h = refPanel.getPreferredSize().getHeight()*heightPercentage;		
		double w = refPanel.getPreferredSize().getWidth()*widthPercentage + 40;	
		this.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		int westw = ( new Double (west.getPreferredSize().getWidth())).intValue();
		int eastw = ( new Double (east.getPreferredSize().getWidth())).intValue();
		this.setBorder(BorderFactory.createEmptyBorder(0, westw, 5, eastw));
	}
	
	public void setTitle(String title){
		JLabel label = new JLabel(title, SwingConstants.CENTER);
		double h = this.getPreferredSize().getHeight() * 0.25;
		double w = this.getPreferredSize().getWidth();
		elementwidth = w;
		label.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		this.add(label, BorderLayout.NORTH);
	}
	
	public void addItem(String imagePath, String caption){
		
		JPanel itemHolder = new JPanel();
		double h = this.refPanel.getPreferredSize().getHeight()*0.50;		
		double w = this.refPanel.getPreferredSize().getWidth()*0.08;
		itemwidth += w;
		
		if (itemwidth > elementwidth){
			west.setVisible(true);
			east.setVisible(true);
			this.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
		}
		
		horizontal.setUnitIncrement((new Double(w)).intValue());
		itemHolder.setPreferredSize(new Dimension((new Double(w)).intValue(), (new Double(h)).intValue()));
		itemHolder.setLayout(new BorderLayout());
		int up = new Double(itemHolder.getPreferredSize().getHeight()*0.05).intValue();		
		int down = new Double(itemHolder.getPreferredSize().getHeight()*0.05).intValue();	
		int leftright = new Double(itemHolder.getPreferredSize().getWidth()*0.05).intValue();		
		itemHolder.setBorder(BorderFactory.createEmptyBorder(up, leftright, down, leftright));
		itemHolder.setOpaque(false);
		
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(imagePath));
			ImageIcon imageIcon = new ImageIcon(bufferedImage);
			h = itemHolder.getPreferredSize().getHeight()*0.75;		
			w = itemHolder.getPreferredSize().getWidth()*0.90;		
			Image image = imageIcon.getImage().getScaledInstance((new Double(w)).intValue(), (new Double(h)).intValue(), Image.SCALE_SMOOTH);
			ImagePanel imgPanel = new ImagePanel(image);
			
			JLabel label = new JLabel(caption, SwingConstants.CENTER);
			
			itemHolder.add(imgPanel, BorderLayout.NORTH);
			itemHolder.add(label, BorderLayout.SOUTH);
			
			itemLogHolder.add(itemHolder);
			itemLogHolder.repaint();
			itemLogHolder.revalidate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
