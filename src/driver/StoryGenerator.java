package driver;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.EpisodeDAO;
import DAO.EpisodeSetDAO;
import DAO.EventDAO;
import DAO.MessageDAO;
import DAO.SentenceDAO;
import DAO.SicknessDAO;
import DAO.SymptomDAO;
import DAO.VirtualPeerDAO;
import Models.Episode;
import Models.EpisodeSet;
import Models.Event;
import Models.Message;
import Models.Sentence;
import Models.Sickness;
import Models.Symptom;
import Models.VirtualPeer;

public class StoryGenerator {
	Scanner sc = new Scanner (System.in);
	
	private ArrayList<VirtualPeer> vpList;
	private int storyRuling;
	private VirtualPeer curVP;
	
	public StoryGenerator(){
		// theme selection (red spots, fever, mosquito bites, tummy ache, colds, sneezing)
		this.vpList = (new VirtualPeerDAO()).getAllVirtualPeers();
		this.setCurVP(VirtualPeer.VP_SARAH);
		
		// temp
		storyRuling = Event.RULING_GOOD;
	}
	
	public void tellStory() {
		System.out.println("Enter symptom: ");
		String symptomInput = sc.nextLine();
		
		// gets random sickness with symptom chosen
		System.out.println("****************** chose sickness ******************");
		Symptom selectedSymptom = (new SymptomDAO()).getRandomSicknessIdWithSymptom(symptomInput);
		int sicknessId = selectedSymptom.getSicknessId();
		Sickness sickness = (new SicknessDAO()).getSicknessWithId(sicknessId);
		System.out.println(sickness);
		
		// gets random episode set
		System.out.println("****************** chose story template ******************");
		EpisodeSet episodeSet = (new EpisodeSetDAO()).getRandomEpisodeSet();
		System.out.println(episodeSet);
		
		// story get episodes
		System.out.println("****************** run story episodes ******************");
		ArrayList<Integer> episodesId = episodeSet.getEpisodesId();
		for (int tempEpisodeId: episodesId){
			Episode episode = (new EpisodeDAO()).getEpisodeById(tempEpisodeId);
			ArrayList<Integer> eventsId = episode.getEventsId();
			
			for (int tempEventId: eventsId){
				Event event = (new EventDAO()).getEventById(tempEventId);
				
				if (storyRuling == event.getRuling() || event.getRuling() == 0){
					if (event.getType() == Event.TYPE_MESSAGE){
						Message message = (new MessageDAO()).getMessageById(event.getId());
						ArrayList<String> sentenceTags = message.getSentenceTags();
						String m = "";
						for (String tempSentenceTag: sentenceTags){
							Sentence sentence = (new SentenceDAO()).getSentenceByTag(tempSentenceTag);
							m += sentence.getMessage() + " ";
						}
						System.out.println(this.curVP.getName() + ": " + m);
						this.roundRobinVP();
					}
					else if (event.getType() == Event.TYPE_ACTION){
						
					}
					String dump = sc.nextLine();
				}
			}
			if (episode.getDiscourseActId() != 0)
				this.roundRobinVP();
			// ask for input
			
			// analyze input
			
			// recognize discourse acts
			
			// change storyRuling based on user input
//			if (episode.getDiscourseActId() != 0)
//				this.setCurVP(VirtualPeer.VP_SARAH);
		}
	}
	
	public void setCurVP(int idVP) {
		this.curVP = this.vpList.get(idVP - 1);
	}
	
	public void roundRobinVP(){
		int indexVP = (this.vpList.indexOf(curVP) + 1) % this.vpList.size();
		this.curVP = this.vpList.get(indexVP); 
	}
	
}
