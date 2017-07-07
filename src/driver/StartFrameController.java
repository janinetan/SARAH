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
import View.StoryPanel2;
import View.WelcomePanel;

public class StartFrameController implements IController {

	private static StartFrame frame;
	private static StoryGenerator2 storyGenerator;
	private static StoryPanel2 storyPanel;
	private static String theme;
	private static MyFilewriter logger;
	private static boolean logOn = false;
	
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
	
	@Override
	public void getControl(IController prevController) {
		// TODO Auto-generated method stub
		prevController.dispose();
		frame.setVisible(true);
	}

	@Override
	public void dispose() {
		frame.setVisible(false);
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
			e1.printStackTrace();
		}
	}
	
	public static void displayTheme(){
		try {
			frame.changePanel(new LocationPanel(frame));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static void displayStartStory(String theme) {
		StartFrameController.theme = theme;
		storyGenerator = new StoryGenerator2();
		storyGenerator.setUpStory();
		storyPanel = new StoryPanel2((new StoryWorldManager()).getLocationBg(theme));
		if (logOn){
			if (logger != null)
				logger.closeFile();
			logger = new MyFilewriter(WelcomePanel.getPlayerName());
		}
		playEvent();
	}
	
	public static void playEvent(){
		try {
			storyGenerator.playEvent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void displayMessage(String peer, String msg, boolean isLast, int eventRuling){
		if (isLast){
			StartFrameController.displayInteractionPanel(peer, msg);
		}
		else{
			logMessage(peer + ": " + msg);
			JPanel curPanel = frame.getCurPanel();
			if (! (curPanel instanceof StoryPanel2) ){
				frame.changePanel(storyPanel);
				curPanel = frame.getCurPanel();
			}
			((StoryPanel2)curPanel).displayMessage(peer, msg, (new StoryWorldManager()).getVPImagepath(peer, eventRuling));
		}
	}
	
	public static void displayAction(String vp, String action, String actionFilename, int eventRuling){
		logMessage(vp + " " + action);
		System.out.println(vp + " is doing " + action + " with image path " + actionFilename);
		JPanel curPanel = frame.getCurPanel();
		if (curPanel instanceof StoryPanel2){
			String icnImagepath = (new StoryWorldManager()).getIconImagepath(actionFilename);
			frame.changePanel(new ActionPanel ((new StoryWorldManager()).getLocationBg(theme), (new StoryWorldManager()).getVPImagepath(vp, eventRuling), icnImagepath));
			storyPanel.addActivity(icnImagepath, action);
		}
		else{
			((ActionPanel)curPanel).setContent((new StoryWorldManager()).getVPImagepath(vp, eventRuling), (new StoryWorldManager()).getIconImagepath(actionFilename));
		}
	}
	
	public static void displayInteractionPanel(String vp, String msg){
		try {
			logMessage(vp + ": " + msg);
			JPanel curPanel = frame.getCurPanel();
			if (curPanel instanceof StoryPanel2)
				frame.changePanel(new InteractionPanel(frame, vp, msg, ((StoryPanel2)curPanel).getBgImagepath()));
			else
				((InteractionPanel)curPanel).setAnotherMessage(msg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void displayEnd(){
		try {
			frame.changePanel(new EndStoryPanel(frame,(new StoryWorldManager()).getLocationBg(theme)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void addSymptom(String symptomName){
		String icnImagepath = (new StoryWorldManager()).getIconImagepath(symptomName);
		storyPanel.addSymptom(icnImagepath, symptomName);
	}
	
	public static void sendUserResponse(String userInput){
		try {
			logMessage(WelcomePanel.getPlayerName() + ": " + userInput);
			storyGenerator.getVerdict(userInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logMessage(String text){
		if (logOn){
			logger.writeToFile(text);
		}
	}
	
	public static JPanel getFramePanel(){
		return frame.getCurPanel();
	}
}
