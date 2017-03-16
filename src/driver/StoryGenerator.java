package driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import DAO.BodyPartDAO;
import DAO.CauseDAO;
import DAO.EpisodeDAO;
import DAO.EpisodeSetDAO;
import DAO.EventDAO;
import DAO.MessageDAO;
import DAO.PreventionDAO;
import DAO.SentenceDAO;
import DAO.SicknessDAO;
import DAO.SymptomDAO;
import DAO.TreatmentDAO;
import DAO.VirtualPeerDAO;
import Models.BodyPart;
import Models.Episode;
import Models.EpisodeSet;
import Models.Event;
import Models.Message;
import Models.Sentence;
import Models.Sickness;
import Models.Symptom;
import Models.VirtualPeer;
import View.StartFrame;
import View.StoryPanel;

public class StoryGenerator {
	Scanner sc = new Scanner (System.in);
	
	private ArrayList<VirtualPeer> vpList;
	private int storyRuling;
	private VirtualPeer curVP;
	private Sickness storyTheme;
	private ArrayList<String> causesList;
	private ArrayList<String> preventionsList;
	private ArrayList<String> symptomsList;
	private ArrayList<String> treatmentsList;
	private ArrayList<BodyPart> bodyPartsList;
	private EpisodeSet storyTemplate;
	private String lastQuestion;
	private String lastSentenceTag;
	private StoryPanel p;
	private StartFrame f;
	public StoryGenerator() throws IOException{
		f = new StartFrame(this);
		f.setVisible(true);
		// theme selection (red spots, fever, mosquito bites, tummy ache, colds, sneezing)
		this.vpList = (new VirtualPeerDAO()).getAllVirtualPeers();
		this.setCurVP(VirtualPeer.VP_SARAH);
		this.causesList = new ArrayList<String>();
		this.preventionsList = new ArrayList<String>();
		this.symptomsList = new ArrayList<String>();
		this.treatmentsList = new ArrayList<String>();
		this.bodyPartsList = new ArrayList<BodyPart>();
		// temp
		storyRuling = Event.RULING_GOOD;
	}
	
	public void tellStory(String symptomInput, StoryPanel p) {
		this.p = p;
/*		System.out.println("Enter symptom: ");
		String symptomInput = sc.nextLine();*/
		
//		symptomInput is the theme chosen by user in themepanel
		selectStoryTheme(symptomInput);
		setStoryTemplate();
		
		
		// story get episodes
		System.out.println("****************** run story episodes ******************");
		ArrayList<Integer> episodesId = storyTemplate.getEpisodesId();
		for (int tempEpisodeId: episodesId){
			Episode episode = (new EpisodeDAO()).getEpisodeById(tempEpisodeId);
			ArrayList<Integer> eventsId = episode.getEventsId();
			this.lastQuestion = "";
			
			for (int tempEventId: eventsId){
				Event event = (new EventDAO()).getEventById(tempEventId);
				if (storyRuling == event.getRuling() || event.getRuling() == 0){
					playEvent(event);
					//String dump = sc.nextLine();
				}
			}
			String input = sc.nextLine();
			String verdict = SarahChatbot.getVerdict(this.lastQuestion + input);
			translateVerdict(verdict);
			System.out.println(verdict);
			
			while (this.storyRuling == Event.RULING_NEUTRAL){
				Sentence sentence = (new SentenceDAO()).getSentenceByTag(this.lastSentenceTag);
//				call method in UI to display message (use this.curVP.getName() for VP name, m for message)
				p.displayMessage(sentence.getMessage(), this.curVP.getName());
				System.out.println(this.curVP.getName() + ": " + sentence.getMessage());
				input = sc.nextLine();
				verdict = SarahChatbot.getVerdict(this.lastQuestion + input);
				translateVerdict(verdict);
			}
			
			System.out.println("DONE");
			
			// recognize discourse acts
			
			// change storyRuling based on user input
		}
	}

	private void translateVerdict(String verdict) {
		if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_BAD)){
			this.storyRuling = Event.RULING_BAD;
		}
		else if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_GOOD)){
			this.storyRuling = Event.RULING_GOOD;
		}
		else if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_NEUTRAL)){
			this.storyRuling = Event.RULING_NEUTRAL;
		}
	}

	private void playEvent(Event event) {
		if (event.getType() == Event.TYPE_MESSAGE){
			Message message = (new MessageDAO()).getMessageById(event.getId());
			ArrayList<String> sentenceTags = message.getSentenceTags();
			String m = "";
			for (String tempSentenceTag: sentenceTags){
				Sentence sentence = (new SentenceDAO()).getSentenceByTag(tempSentenceTag);
				m += sentence.getMessage() + " ";
				this.lastSentenceTag = tempSentenceTag;
				this.lastQuestion = sentence.getMessage();
			}
			m = polishMessage(m);
//			call method in UI to display message (use this.curVP.getName() for VP name, m for message)
			p.displayMessage(m, this.curVP.getName());
			System.out.println(this.curVP.getName() + ": " + m);
			if (m.indexOf("?") == -1){
				this.roundRobinVP();
			}
		}
		else if (event.getType() == Event.TYPE_ACTION){
			
		}
	}

	private void setStoryTemplate() {
		// gets random episode set
		EpisodeSet episodeSet = (new EpisodeSetDAO()).getRandomEpisodeSet();
		this.storyTemplate = episodeSet;
	}

	private void selectStoryTheme(String symptomInput) {
		// gets random sickness with symptom chosen and sets it as story theme
		// sets all facts about selected story theme
		Symptom selectedSymptom = (new SymptomDAO()).getRandomSicknessIdWithSymptom(symptomInput);
		int sicknessId = selectedSymptom.getSicknessId();
		Sickness sickness = (new SicknessDAO()).getSicknessWithId(sicknessId);
		System.out.println(sickness);
		this.causesList.addAll((new CauseDAO()).get5CausesBySicknessId(sicknessId));
		this.preventionsList.addAll((new PreventionDAO()).get5PreventionBySicknessId(sicknessId));
		this.symptomsList.add(symptomInput);
		this.symptomsList.addAll((new SymptomDAO()).get4SymptomsBySicknessId(sicknessId, symptomInput));
		this.treatmentsList.addAll((new TreatmentDAO()).get5TreatmentsBySicknessId(sicknessId));
		this.bodyPartsList.addAll((new BodyPartDAO()).getBodyPartsBySicknessId(sicknessId));
		this.storyTheme = sickness;
	}
	
	public void setCurVP(int idVP) {
		this.curVP = this.vpList.get(idVP - 1);
	}
	
	public void roundRobinVP(){
		int indexVP = (this.vpList.indexOf(curVP) + 1) % this.vpList.size();
		this.curVP = this.vpList.get(indexVP); 
	}
	
	public String polishMessage (String message){
		message = message.replaceAll("<user>", "Anna");
//		message = message.replaceAll("<day>", "Anna");
//		message = message.replaceAll("<liam-status>", "Anna");
		message = message.replaceAll("<sickness>", this.storyTheme.getName());
		message = message.replaceAll("<body-part-affected>", this.bodyPartsList.get(0).getName());
		message = message.replaceAll("<body-part-definition>", this.bodyPartsList.get(0).getDescription());
		
//		fixing this tomorrow :)
//		int i = 0;
//		while (message.contains("[treatment]")){
//			message = message.replaceFirst("[treatment]", this.treatmentsList.get(i));
//			i++;
//		}
//		i = 0;
//		while (message.contains("[symptom]")){
//			System.out.println("hi");
//			message = message.replaceFirst("[symptom]", this.symptomsList.get(i));
//			i++;
//		}
//		i = 0;
//		while (message.contains("[cause]")){
//			message = message.replaceFirst("[cause]", this.causesList.get(i));
//			i++;
//		}
//		i = 0;
//		while (message.contains("[prevention]")){
//			message = message.replaceFirst("[prevention]", this.preventionsList.get(i));
//			i++;
//		}  
		return message;
	}
}
