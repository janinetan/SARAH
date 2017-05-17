package driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.codec.binary.StringUtils;

import DAO.ActionDAO;
import DAO.AssertionDAO;
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
import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.XMLLexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

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
	private static int curStoryEventIndex;
	private ArrayList<Episode> episodesList;
	private static int curStoryEpisodeIndex;
	private Episode episode;
	private ArrayList<Integer> eventsId;
	private ArrayList<Integer> goodHealthAssertions;
	private ArrayList<Integer> badHealthAssertions;
	
	private boolean doneCauseEvent = false;
	private int causeEventCount = 0;
	private ArrayList<String> pastAction;
	private Random randomGenerator = new Random();
	private Action curAction;
	private Action reverse;
	
	private int actionCtr;
	
	public StoryGenerator2(){
		goodHealthAssertions = (new AssertionDAO()).getHealthAssertions("good");
		badHealthAssertions = (new AssertionDAO()).getHealthAssertions("bad");
		// theme selection (red spots, fever, mosquito bites, tummy ache, colds, sneezing)
		this.vpList = (new VirtualPeerDAO()).getAllVirtualPeers();
		// set vps to have good health conditions
		for (VirtualPeer vp: vpList){
			vp.setHealthAssertions(goodHealthAssertions);
		}
		this.setCurVP(VirtualPeer.VP_SARAH);
		this.causesList = new ArrayList<String>();
		this.preventionsList = new ArrayList<String>();
		this.symptomsList = new ArrayList<String>();
		this.treatmentsList = new ArrayList<String>();
		this.bodyPartsList = new ArrayList<BodyPart>();
		this.episodesList = new ArrayList<Episode>();
		// temp
		storyRuling = Event.RULING_GOOD;
		
		curStoryEpisodeIndex = 0;
		curStoryEventIndex = 0;
		eventsId = new ArrayList<Integer>();
		pastAction = new ArrayList<String>();
	
		actionCtr = 0;
	}
	
	public void setUpStory() {
		// gets random episode set (1,8,2,3,4,5,6,7)
		EpisodeSet storyTemplate = (new EpisodeSetDAO()).getRandomEpisodeSet();
		
		// stores all events into a single array
		ArrayList<Integer> episodesId = storyTemplate.getEpisodesId();
		
		//episode = 1 row in episode table
		for (int tempEpisodeId: episodesId){
			Episode episode = (new EpisodeDAO()).getEpisodeById(tempEpisodeId);
			this.episodesList.add(episode);
		}
	}
	
	public void playEvent() throws IOException{
		System.out.println("***********************************");
		System.out.print("event arr = "+eventsId);
		System.out.print("; curStoryEpisodeIndex = "+curStoryEpisodeIndex);
		System.out.print("; cur epGoal = " +episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
		System.out.print("; curStoryEventIndex = "+curStoryEventIndex);
		System.out.println("; actionCtr = "+actionCtr);
		
		//display end screen
		if ( curStoryEpisodeIndex == episodesList.size() && curStoryEventIndex == this.eventsId.size() ){
			StartFrameController.displayEnd();
		}
		
		// TODO: if episodeGoal == 11 && ruling is good, insert reverse episode after epgoal 11, else do nothing
		// TODO: remove reverse episode after its execution
		
		//if start or if tapos na sa events to move on to the next episode
		if ( eventsId.isEmpty() || curStoryEventIndex == this.eventsId.size() ){
			this.episode = episodesList.get(curStoryEpisodeIndex);
			if ( this.episode.getDiscourseActId() != 10){
				this.eventsId = episode.getEventsId(); // return arraylist of events
			}
			
			//12,8,9,8,11,9,8,9
			System.out.println(".......");
			if (episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 10 || 
					episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 11){
				System.out.println("ENTERRRRRR");
				System.out.println("cur ep goal = "+episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
				System.out.println("cur episode index = "+curStoryEpisodeIndex);
				int i = 0;
				for (Episode e : episodesList){
					System.out.println("ep (" +i+ ") = "+e.getEpisodeGoalId());
					i++;
				}
				episodesList.remove(episodesList.get(curStoryEpisodeIndex));
				i = 0;
				for (Episode e : episodesList){
					System.out.println("ep (" +i+ ") = "+e.getEpisodeGoalId());
					i++;
				}
				curStoryEpisodeIndex--;
				System.out.println("cur episode index = "+curStoryEpisodeIndex);
			}
			
			if (episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 9 && actionCtr < 4){
				curStoryEpisodeIndex--;
			} else {
//				if(!(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 8 && checkIfLiamHasAllGoodAssertions()))
					curStoryEpisodeIndex++;
				System.out.println("Symptoms list size = "+symptomsList.size());
				if(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 1){
					for(int i = 0; i < symptomsList.size(); i++){
						System.out.println("SYMPTOMS : "+ symptomsList.get(i));
					}
				}
			}
			
			curStoryEventIndex = 0;	
		}
		
		// 8 - invite to do activity and eventindex is 0 
		if ( this.episode.getEpisodeGoalId() == 8 && curStoryEventIndex == 0){
			if ( checkIfLiamHasAllGoodAssertions() ){
				System.out.println("((((( entered liam has all good assertions )))))");
				ArrayList<Integer> possibleActionIds = (new ActionDAO()).getFirstAction(goodHealthAssertions, "park");
				
				Action a;
				do {
					int index = randomGenerator.nextInt(possibleActionIds.size());
					System.out.println("rand index possible actionIds = "+index);
					a = (new ActionDAO()).setActionDetails(possibleActionIds.get(index));
					index = randomGenerator.nextInt(a.getObectList().size());
					a.setChosenObject(a.getObectList().get(index));
				} while (! checkActionAcceptable(a));
				curAction = a;
				
				System.out.println(">>>> curAction activity = " + curAction.getChosenObject().getVerb());
				if(!curAction.getSymptomList().isEmpty())
					System.out.println(">>>> symptomsList of curAction = " + curAction.getSymptomList().get(0));
				
				pastAction.add(a.getActivityName() + " " + a.getChosenObject().getName() + " " + a.getChosenObject().getVerb());
			}
			else {
				System.out.println("((((( entered liam has bad assertions )))))");
				ArrayList<Integer> result = new ArrayList<Integer>(badHealthAssertions);
				ArrayList<Integer> liamHealthAssertions = this.vpList.get(VirtualPeer.VP_LIAM - 1).getHealthAssertions();
			    result.retainAll(liamHealthAssertions);
			    
			    System.out.println(badHealthAssertions + " " +  liamHealthAssertions + "mew: " + result);
			    int rightop = (new AssertionDAO()).getOppsotiteAssertion(result.get(randomGenerator.nextInt(result.size())));
				ArrayList<Integer> possibleActionIds = (new ActionDAO()).getActionWithPrecondition("park", rightop);
				
				Action a;
				do {
					System.out.println(">>>>>>>> possibleActionIds.size() = "+possibleActionIds.size());
					int index = randomGenerator.nextInt(possibleActionIds.size());
					System.out.println("rand index possible actionIds = "+index);
					a = (new ActionDAO()).setActionDetails(possibleActionIds.get(index));
					System.out.println("debugging" +a.getObectList().size());
					index = randomGenerator.nextInt(a.getObectList().size());
					a.setChosenObject(a.getObectList().get(index));
				} while (! checkActionAcceptable(a));
				curAction = a;
				
				System.out.println(">>>> curAction activity = " + curAction.getChosenObject().getVerb());
				if(!curAction.getSymptomList().isEmpty())
					System.out.println(">>>> symptomsList of curAction = " + curAction.getSymptomList().get(0));
				
				pastAction.add(a.getActivityName() + " " + a.getChosenObject().getName() + " " + a.getChosenObject().getVerb());
				
				System.out.println("GETTING REVERSE ACTION");
				ArrayList<Integer> reverseActions = (new ActionDAO()).getReverseAction("park", (new AssertionDAO()).getOppsotiteAssertion(curAction.getPrecondition().get(0)));
				int index = randomGenerator.nextInt(reverseActions.size());
				reverse= (new ActionDAO()).setActionDetails(reverseActions.get(index));
				index = randomGenerator.nextInt(reverse.getObectList().size());
				reverse.setChosenObject(reverse.getObectList().get(index));
				System.out.println("REVERSE : " + reverse.getActivityName());
			}
			
			actionCtr++;	
			
			System.out.println(ifLiamMeetsAssertions());
			//dito na suggest ni Sarah na I think you should rest
			if (!ifLiamMeetsAssertions()){
				Episode episode = (new EpisodeDAO()).getEpisodeById(10); 
				episodesList.add(curStoryEpisodeIndex, episode);
				episode = (new EpisodeDAO()).getEpisodeById(11);	
				episodesList.add(curStoryEpisodeIndex+1, episode);
				curStoryEventIndex = 0;
			}
		}
		
//		if ( this.episode.getEpisodeGoalId() < 9 || this.episode.getEpisodeGoalId() >= 11) {
			Event event = (new EventDAO()).getEventById(eventsId.get(curStoryEventIndex));
			this.lastQuestion = "";
			
	
			System.out.println("!!!!!!!!!!!!!!! event ruling = "+event.getRuling());
			System.out.println("!!!!!!!!!!!!!!! story ruling = "+storyRuling);
			
			//start StoryRuling is good
			//if storyRuling is same as event ruling or event ruling is equal to neutral
			if (storyRuling == event.getRuling() || event.getRuling() == Event.RULING_NEUTRAL){
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
					m = polishMessage(m);
					StartFrameController.displayMessage(this.curVP.getName(), m, message.getIsLast());
					if (!message.getIsLast()){
						this.roundRobinVP();
					}
				}
				curStoryEventIndex++;
			}
			else{
				curStoryEventIndex++;
				//recursion
				playEvent();
			}
//		}
			
		//9 - doActivity && if curStoryEventIndex is second to the last or last	
		if( this.episode.getEpisodeGoalId() == 9 && ( curStoryEventIndex == this.eventsId.size() - 1 || 
				curStoryEventIndex == this.eventsId.size() ) ){
			
			StartFrameController.displayAction(curAction.getChosenObject().getFilename());
			
			if (curStoryEventIndex == this.eventsId.size() - 1){
				if (!ifLiamMeetsAssertions()){
					if(curAction.getSymptomList().size() != 0){
						int index = randomGenerator.nextInt(curAction.getSymptomList().size());
	//					System.out.println(">>>>> randomized symptom = " + curAction.getSymptomList().get(index));
						if(!symptomsList.contains( curAction.getSymptomList().get(index)))
							symptomsList.add( curAction.getSymptomList().get(index));
					}
					System.out.println("hassymptom");
				}
				//apply postcondition to liam
				System.out.println("CHANGING LIAM PROPERTY");
				
				/*if(storyRuling == Event.RULING_GOOD){
					System.out.println("GETTING REVERSE ACTION");
					ArrayList<Integer> reverseActions = (new ActionDAO()).getReverseAction("park", (new AssertionDAO()).getOppsotiteAssertion(curAction.getPrecondition().get(0)));
					int index = randomGenerator.nextInt(reverseActions.size());
					Action reverse= (new ActionDAO()).setActionDetails(reverseActions.get(index));
					ArrayList<Integer> postconditions = reverse.getPostcondition();
					for (int postconTemp: postconditions){
						int assertionIdOpp = (new AssertionDAO()).getOppsotiteAssertion(postconTemp);
						this.vpList.get(VirtualPeer.VP_LIAM - 1).exchangeHealthAssertion(assertionIdOpp, postconTemp);
					}
				}*/
			
				ArrayList<Integer> postconditions = curAction.getPostcondition();
				for (int postconTemp: postconditions){
					int assertionIdOpp = (new AssertionDAO()).getOppsotiteAssertion(postconTemp);
					this.vpList.get(VirtualPeer.VP_LIAM - 1).exchangeHealthAssertion(assertionIdOpp, postconTemp);
				}
				if(checkIfLiamHasAllGoodAssertions()){
					curStoryEpisodeIndex--;
					curStoryEventIndex = 0;	
				}
				
			}
//			actionCtr++;	
		}	
		
		/*if(storyRuling == Event.RULING_GOOD && this.episode.getEpisodeGoalId() == 11){
			System.out.println("CHANGING 2");
			ArrayList<Integer> postconditions = reverse.getPostcondition();
			for (int postconTemp: postconditions){
				int assertionIdOpp = (new AssertionDAO()).getOppsotiteAssertion(postconTemp);
				this.vpList.get(VirtualPeer.VP_LIAM - 1).exchangeHealthAssertion(assertionIdOpp, postconTemp);
			}
		}*/
		
	}
	
	private boolean checkIfLiamHasAllGoodAssertions() {
		ArrayList<Integer> liamHealthAssertions = this.vpList.get(VirtualPeer.VP_LIAM - 1).getHealthAssertions();
		System.out.println("Liam's health assertions = " +liamHealthAssertions);
		System.out.println("Good health assertions = " +goodHealthAssertions);
		for (int assertionTemp: goodHealthAssertions){
			if (liamHealthAssertions.indexOf(assertionTemp) == -1)
				return false;
		}
		return true;
	}

	public boolean ifLiamMeetsAssertions(){
		ArrayList<Integer> actionAssertions = curAction.getPrecondition();
		ArrayList<Integer> liamHealthAssertions = this.vpList.get(VirtualPeer.VP_LIAM - 1).getHealthAssertions();
		for (int tempAssertionIndex : actionAssertions ){
			if (liamHealthAssertions.indexOf(tempAssertionIndex) == -1)
				return false;
		}
		return true;
	}
	
	public boolean checkActionAcceptable(Action a){
		String check = a.getActivityName() + " " + a.getChosenObject().getName() + " " + a.getChosenObject().getVerb();
		if (pastAction.contains(check))
			return false;
		return true;
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

	public void selectStoryTheme(String location, String symptomInput) {
		// gets random sickness with symptom chosen and sets it as story theme
		// sets all facts about selected story theme
		Symptom selectedSymptom = (new SymptomDAO()).getRandomSicknessIdWithSymptom(symptomInput);
		int sicknessId = selectedSymptom.getSicknessId();
		Sickness sickness = (new SicknessDAO()).getSicknessWithId(sicknessId);
		System.out.println(sickness);
		this.causesList.addAll((new CauseDAO()).get5CausesBySicknessId(sicknessId));
		this.preventionsList.addAll((new PreventionDAO()).get5PreventionBySicknessId(sicknessId));
//		this.symptomsList.add(symptomInput);
//		this.symptomsList.addAll((new SymptomDAO()).get4SymptomsBySicknessId(sicknessId, symptomInput));
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
		System.out.println("mssg = " +message);
		
		message = message.replaceAll("<user>", "Anna");
		message = message.replaceAll("<day>", "Saturday");
		message = message.replaceAll("<liam-status>", "sick");
		
		message = message.replaceAll("<sickness>", this.storyTheme.getName());
		message = message.replaceAll("<body-part-affected>", this.bodyPartsList.get(0).getName());
		message = message.replaceAll("<body-part-definition>", this.bodyPartsList.get(0).getDescription());
		if (message.contains("curAction")){
			message = message.replaceAll("<curAction-verb-ing>", getNLG(this.curAction.getChosenObject().getVerb()));
			message = message.replaceAll("<curAction-verb>", this.curAction.getChosenObject().getVerb());
			message = message.replaceAll("<curAction-object>", this.curAction.getChosenObject().getName());
			message = message.replaceAll("<curAction-motivation>", this.curAction.getMotivation().get(new Random().nextInt(this.curAction.getMotivation().size())));
		}
		/* NO VALUES FOR 'REPLACEMENT' YET */
		if(message.contains("prevAction")){
			message = message.replaceAll("<prevAction-verb-ing>", pastAction.get(pastAction.size()-2).split(" ")[2]);
			message = message.replaceAll("<prevAction-verb>", pastAction.get(pastAction.size()-2).split(" ")[2]);
			message = message.replaceAll("<prevAction-object>", pastAction.get(pastAction.size()-2).split(" ")[1]);
		}
////		
		if(message.contains("reverseAction")){
		message = message.replaceAll("<reverseAction-verb>", reverse.getChosenObject().getVerb());
		message = message.replaceAll("<reverseAction-object>", reverse.getChosenObject().getName());
		message = message.replaceAll("<reverseAction-postCondition-property>", "BASTA");
		}
//		
//		message = message.replaceAll("<curCondition-body>", replacement);
//		message = message.replaceAll("<curCondition-property>", replacement);
		
		
		int i = 0;
		while (message.contains("[symptom]")){
			message = message.replaceFirst("\\[symptom\\]", this.symptomsList.get(i));
			i++;
		}
		
		i = 0;
		while (message.contains("[treatment]")){
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
		
		return message;
	}
	public String getNLG (String word){
	XMLLexicon lexicon = new XMLLexicon("C:/Users/Heinson/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
	//	XMLLexicon lexicon = new XMLLexicon("C:/Users/Raisa/projects/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
		NLGFactory phraseFactory = new NLGFactory(lexicon);
		VPPhraseSpec live = phraseFactory.createVerbPhrase(word);
		SPhraseSpec clause = phraseFactory.createClause();
		clause.setVerbPhrase(live);
		clause.setFeature(Feature.FORM, Form.GERUND);
		Realiser realizer = new Realiser(lexicon);
		clause.setFeature(Feature.FORM, Form.GERUND);
		String gerund = realizer.realise(clause).getRealisation();
		System.out.println(gerund);
		return gerund;
	}
}

