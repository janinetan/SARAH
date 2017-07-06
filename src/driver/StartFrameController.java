package driver;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JPanel;

import View.ActionPanel;
import View.EndStoryPanel;
import View.InteractionPanel;
import View.LocationPanel;
import View.StartFrame;
import View.StartMenuPanelTest;
import View.StoryPanel;
import View.StoryPanel2;
import View.ThemePanel;
import View.WelcomePanel;

public class StartFrameController implements IController {

	private static StartFrame frame;
	private static StoryGenerator2 storyGenerator;
	private static String theme;
	
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
			frame.changePanel(new StartMenuPanelTest());
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
			frame.changePanel(new LocationPanel(frame));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void displayStory(String bgFilePath){
		frame.changePanel(new StoryPanel2(bgFilePath));
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
		StartFrameController.theme = theme;
		storyGenerator = new StoryGenerator2();
		storyGenerator.setUpStory();
		displayStory((new StoryWorldManager()).getLocationBg(theme));
		playEvent();
	}
	
	public static void playEvent(){
		try {
			storyGenerator.playEvent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void displayMessage(String peer, String msg, boolean isLast, int eventRuling){
		if (isLast){
			StartFrameController.displayInteractionPanel(peer, msg);
		}
		else{
			JPanel curPanel = frame.getCurPanel();
			if (! (curPanel instanceof StoryPanel2) ){
				displayStory((new StoryWorldManager()).getLocationBg(theme));
				curPanel = frame.getCurPanel();
			}
			((StoryPanel2)curPanel).displayMessage(peer, msg, (new StoryWorldManager()).getVPImagepath(peer, eventRuling));
		}
	}
	
	public static void displayAction(String vp, String act, int eventRuling){
		JPanel curPanel = frame.getCurPanel();
		System.out.println("DOING ACTIONNNNNNNNNNNNNNNNNNNNNNN0 " + act);
		
//		frame.changePanel(new ActionPanel ((new StoryWorldManager()).getLocationBg(theme), (new StoryWorldManager()).getVPImagepath("LIAM", 1), (new StoryWorldManager()).getIconImagepath("colds")));

		if (curPanel instanceof StoryPanel2){
			frame.changePanel(new ActionPanel ((new StoryWorldManager()).getLocationBg(theme), (new StoryWorldManager()).getVPImagepath(vp, eventRuling), (new StoryWorldManager()).getIconImagepath(act)));
		}
		else{
			((ActionPanel)curPanel).setContent((new StoryWorldManager()).getVPImagepath(vp, eventRuling), (new StoryWorldManager()).getIconImagepath(act));
		}
	}
	
	public static void displayInteractionPanel(String vp, String msg){
		try {
			JPanel curPanel = frame.getCurPanel();
			if (curPanel instanceof StoryPanel2)
				frame.changePanel(new InteractionPanel(frame, vp, msg, ((StoryPanel2)curPanel).getBgImagepath()));
			else
				((InteractionPanel)curPanel).setAnotherMessage(msg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void displayEnd(String theme){
		try {
			frame.changePanel(new EndStoryPanel(frame,theme));
//			frame.changePanel(new EndStoryPanel(frame,(new StoryWorldManager()).getLocationBg(StartFrameController.theme)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void sendUserResponse(String userInput){
		try {
			storyGenerator.getVerdict(userInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JPanel getFramePanel(){
		return frame.getCurPanel();
	}
}
