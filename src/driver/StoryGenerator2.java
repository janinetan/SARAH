package driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.codec.binary.StringUtils;

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
import Models.Action;
import Models.BodyPart;
import Models.Episode;
import Models.EpisodeSet;
import Models.Event;
import Models.Message;
import Models.Sentence;
import Models.Sickness;
import Models.Symptom;
import Models.VirtualPeer;

public class StoryGenerator2 {
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
	private String lastQuestion;
	private String lastSentenceTag;
	private ArrayList<Event> eventsList;
	private static int curStoryEventIndex;
	private ArrayList<Episode> episodesList;
	private static int curStoryEpisodeIndex;
	private Episode episode;
	private ArrayList<Integer> eventsId;
	
	
	public StoryGenerator2(){
		// theme selection (red spots, fever, mosquito bites, tummy ache, colds, sneezing)
		this.vpList = (new VirtualPeerDAO()).getAllVirtualPeers();
		this.setCurVP(VirtualPeer.VP_SARAH);
		this.causesList = new ArrayList<String>();
		this.preventionsList = new ArrayList<String>();
		this.symptomsList = new ArrayList<String>();
		this.treatmentsList = new ArrayList<String>();
		this.bodyPartsList = new ArrayList<BodyPart>();
		this.eventsList = new ArrayList<Event>();
		this.episodesList = new ArrayList<Episode>();
		// temp
		storyRuling = Event.RULING_GOOD;
		
		curStoryEpisodeIndex = 0;
		curStoryEventIndex = 0;
		eventsId = new ArrayList<Integer>();
	}
	
	public void setUpStory() {
		// gets random episode set
		EpisodeSet storyTemplate = (new EpisodeSetDAO()).getRandomEpisodeSet();
		
		// stores all events into a single array
		ArrayList<Integer> episodesId = storyTemplate.getEpisodesId();
		
		for (int tempEpisodeId: episodesId){
			Episode episode = (new EpisodeDAO()).getEpisodeById(tempEpisodeId);
			this.episodesList.add(episode);
		}
	}
	
	public void playEvent() throws IOException{
		if ( curStoryEpisodeIndex == episodesList.size() && curStoryEventIndex == this.eventsId.size() ){
			StartFrameController.displayEnd();
		}
		
		if ( eventsId.isEmpty() || curStoryEventIndex == this.eventsId.size() ){
			this.episode = episodesList.get(curStoryEpisodeIndex);
			if ( this.episode.getDiscourseActId() != 10){
				this.eventsId = episode.getEventsId();
			}
			curStoryEpisodeIndex++;
			curStoryEventIndex = 0;
		}
		
		if ( this.episode.getDiscourseActId() != 10) {
			Event event = (new EventDAO()).getEventById(eventsId.get(curStoryEventIndex));
			this.lastQuestion = "";
			System.out.println(event);
			System.out.println(storyRuling);
			
			if (storyRuling == event.getRuling() || event.getRuling() == Event.RULING_NEUTRAL){
				System.out.println("hi");
				if (event.getType() == Event.TYPE_MESSAGE){
					Message message = (new MessageDAO()).getMessageById(event.getId());
					message.setRuling(event.getRuling());
					if ( curStoryEventIndex == eventsId.size()-1 && episode.getDiscourseActId() != 0)
						message.setIsLast(true);
					ArrayList<String> sentenceTags = message.getSentenceTags();
					String m = "";
					for (String tempSentenceTag: sentenceTags){
						Sentence sentence = (new SentenceDAO()).getSentenceByTag(tempSentenceTag);
						m += sentence.getMessage() + " ";
						this.lastSentenceTag = tempSentenceTag;
						this.lastQuestion = sentence.getMessage();
					}
//					m = polishMessage(m);
					StartFrameController.displayMessage(this.curVP.getName(), m, message.getIsLast());
					if (!message.getIsLast()){
						this.roundRobinVP();
					}
				}
				else if (event.getType() == Event.TYPE_ACTION){
					
				}
				curStoryEventIndex++;
			}
			else{
				curStoryEventIndex++;
				playEvent();
			}
		}
		
		
	}

	public void getVerdict(String userInput) throws IOException {
		String verdict = SarahChatbot.getVerdict(this.lastQuestion + userInput);
		System.out.println(verdict);
		if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_BAD)){
			this.storyRuling = Event.RULING_BAD;
			StartFrameController.playEvent();
		}
		else if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_GOOD)){
			this.storyRuling = Event.RULING_GOOD;
			StartFrameController.playEvent();
		}
		else if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_NEUTRAL)){
			this.storyRuling = Event.RULING_NEUTRAL;
			Sentence sentence = (new SentenceDAO()).getSentenceByTag(this.lastSentenceTag);
			StartFrameController.displayMessage(this.curVP.getName(), sentence.getMessage(), true);
		}
		
	}

	public void selectStoryTheme(String symptomInput) {
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
		System.out.println("**************************************************");
		System.out.println(message);
		
		message = message.replaceAll("<user>", "dump-name");
		message = message.replaceAll("<day>", "dump-day");
		message = message.replaceAll("<liam-status>", "dump-Liam-status");
		
		message = message.replaceAll("<sickness>", this.storyTheme.getName());
		message = message.replaceAll("<body-part-affected>", this.bodyPartsList.get(0).getName());
		message = message.replaceAll("<body-part-definition>", this.bodyPartsList.get(0).getDescription());
		
//		String s = "Hello [replace] this is [replace]. We are [replace].";
//		int k = 0;
//		while (s.contains("[replace]")){
//			if (k == 0){
//				s = s.replaceFirst("\\[replace\\]", "howdy");
//				System.out.println(">> howdy >> "+s);
//			} else if (k == 1) {
//				s = s.replaceFirst("\\[replace\\]", "cowboy");
//				System.out.println(">> howdy, cowboy >> "+s);
//			}else {
//				s = s.replaceFirst("\\[replace\\]", "mate");
//				System.out.println(">> howdy, cowboy, mate >> "+s);
//			}
//			k++;
//		}
		
		int i = 0;
		while (message.contains("[symptom]")){
			System.out.println(".......... replacing symptom ..........");
			message = message.replaceFirst("\\[symptom\\]", this.symptomsList.get(i));
			i++;
		}
		
		i = 0;
		while (message.contains("[treatment]")){
			System.out.println(".......... replacing treatment ..........");
			message = message.replaceFirst("\\[treatment\\]", this.treatmentsList.get(i));
			i++;
		}
		
		i = 0;
		while (message.contains("[cause]")){
			message = message.replaceFirst("\\[cause\\]", this.causesList.get(i));
			i++;
		}
		
		i = 0;
		while (message.contains("[prevention]")){
			message = message.replaceFirst("\\[prevention\\]", this.preventionsList.get(i));
			i++;
		}  
		
		System.out.println(message);
		System.out.println("**************************************************");
		return message;
	}
}

