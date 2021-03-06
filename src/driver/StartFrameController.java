package driver;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import View.AboutPanel;
import View.ActionPanel;
import View.EndStoryPanel;
import View.InteractionPanel;
import View.LocationPanel;
import View.StartFrame;
import View.StartMenuPanelTest;
import View.StoryPanel2;
import View.TransitionPanel;
import View.TutorialEndStoryPanel;
import View.TutorialInteractionPanel;
import View.TutorialPanel;
import View.WelcomePanel;

public class StartFrameController implements IController {

	private static StartFrame frame;
	private static StoryGenerator2 storyGenerator;
	private static StoryPanel2 storyPanel;
	private static String theme;
	private static MyFilewriter logger;
	private static boolean logOn = false;
	private int counter =1;
	static JPanel tPanel;
	
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
	
	public static StartFrame getFrame(){
		return frame;
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
	
	public static void displayAction(String vp, String action, String actionFilename, int eventRuling, String msg){
		logMessage(msg);
		System.out.println(vp + " is doing " + action + " with image path " + actionFilename);
		JPanel curPanel = frame.getCurPanel();
		if (curPanel instanceof StoryPanel2){
			String icnImagepath = (new StoryWorldManager()).getIconImagepath(actionFilename);
			frame.changePanel(new ActionPanel ((new StoryWorldManager()).getLocationBg(theme), (new StoryWorldManager()).getVPImagepath(vp, eventRuling), icnImagepath, msg));
			storyPanel.addActivity(icnImagepath, action);
		}
		else{
			((ActionPanel)curPanel).setContent((new StoryWorldManager()).getVPImagepath(vp, eventRuling), (new StoryWorldManager()).getIconImagepath(actionFilename), msg);
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
		String msg = "Yey! You have now completed the story. I hope you learned a lot! Do you want to hear another story?";
		try {
			frame.changePanel(new EndStoryPanel(frame,(new StoryWorldManager()).getLocationBg(theme), msg));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void displayTransition(String text){
		try {
			frame.changePanel(new TransitionPanel(frame, text));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void displayAbout(){
		frame.changePanel(new AboutPanel());
	}
	
	public static boolean getIsLiamSick(){
		return storyGenerator.getIsLiamSick();
	}
	
	public static void displayTutorialPanel()
	{
		try {
			frame.changePanel(new TutorialPanel(frame));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void updateHealthAssertion(ArrayList<String> liamBadHealthAssertions){
		storyPanel.clearHealth();
		for (String tempString: liamBadHealthAssertions){
			storyPanel.addLiamHealthStatus((new StoryWorldManager()).getIconImagepath(tempString), tempString);
		}
	}
	
	public static void addSymptom(String symptomName){
		String icnImagepath = (new StoryWorldManager()).getIconImagepath(symptomName);
//		storyPanel.addSymptom(icnImagepath, symptomName);
	}
	
//	public static void sendUserResponse(String userInput){
//		try {
//			logMessage(WelcomePanel.getPlayerName() + ": " + userInput);
//			storyGenerator.getVerdict(userInput);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void sendUserResponse(String userInput){
		try {
			logMessage(WelcomePanel.getPlayerName() + ": " + userInput);
			
			String[] beforeCheck = userInput.split(" ");
			int s = beforeCheck.length;
			String afterCheck = "";
			String temp = "";
			for (int i = 0; i < s; i++){
				SpellChecker.initialize();
				temp = SpellChecker.checkSpelling(beforeCheck[i]);
				if (temp.equalsIgnoreCase("NO SUGGESTION")){
					afterCheck += beforeCheck[i] + " ";
				} else{
					afterCheck += temp + " ";
				}
			}
			System.out.println("aftercheck: "+afterCheck);
			storyGenerator.getVerdict(afterCheck);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void nextTutorialsPage(){
		JPanel curPanel = frame.getCurPanel();
		
		if(curPanel instanceof TutorialPanel)
			tPanel = curPanel;
		String msg="";
		if (((TutorialPanel)tPanel).nextBg() ==2 ){
			msg = "Once you reach this screen, you have finished the story. Do you want to proceed to the story now?";
			try {
				frame.changePanel(new TutorialEndStoryPanel(frame,(new StoryWorldManager()).getLocationBg("park"), msg));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}/*
		else if (((TutorialPanel)tPanel).nextBg() ==1)
		{
			msg = "In this screen, Sarah will ask you a question and you should type your answer in the text box below. Once you are done, click the blue button on the lower right." ;
			try {
				frame.changePanel(new TutorialInteractionPanel(frame,"sarah",msg,(new StoryWorldManager()).getLocationBg("park")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}*/
	}
	
	public static void logMessage(String text){
		if (logOn){
			logger.writeToFile(text);
		}
	}
	
	public static JPanel getFramePanel(){
		return frame.getCurPanel();
	}
	
	public static String getPlace(){
		return theme;
	}
	public void resetTutorialCounter(){
		counter = 1;
	}
}
