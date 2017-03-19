package driver;

import java.awt.EventQueue;
import java.io.IOException;

import View.EndStoryPanel;
import View.InteractionPanel;
import View.StartFrame;
import View.StartMenuPanel;
import View.StoryPanel;
import View.ThemePanel;
import View.WelcomePanel;

public class StartFrameController implements IController {

	private static StartFrame frame;
	private static StoryGenerator storyGenerator;
	
	public StartFrameController(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void displayStartMenu(){
		try {
			frame.changePanel(new StartMenuPanel());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void displayWelcome(){
		try {
			frame.changePanel(new WelcomePanel());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void displayTheme(){
		try {
			frame.changePanel(new ThemePanel(frame));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void displayStory(){
		try {
			frame.changePanel(new StoryPanel());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void getControl(IController prevController) {
		// TODO Auto-generated method stub
		prevController.dispose();
		frame.setVisible(true);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		frame.setVisible(false);
	}

	public static void displayStartStory(String theme) {
		storyGenerator = new StoryGenerator();
		storyGenerator.selectStoryTheme(theme);
		storyGenerator.setUpStory();
		playEvent();
	}
	
	public static void playEvent(){
		storyGenerator.playEvent();
	}
	
	public static void displayMessage(String peer, String msg, boolean isLast){
		if (isLast){
			StartFrameController.displayInteractionPanel(peer, msg);
		}
		else{
			displayStory();
			((StoryPanel)frame.getCurPanel()).displayMessage(peer, msg);
		}
	}
	
	public static void displayInteractionPanel(String vp, String msg){
		try {
			frame.changePanel(new InteractionPanel(frame, vp, msg));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void displayEnd(){
		try {
			frame.changePanel(new EndStoryPanel(frame));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void sendUserResponse(String userInput){
		storyGenerator.getVerdict(userInput);
	}
}