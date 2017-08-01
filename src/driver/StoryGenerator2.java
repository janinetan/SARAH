package driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
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
import Models.Assertion;
import Models.BodyPart;
import Models.Episode;
import Models.EpisodeSet;
import Models.Event;
import Models.Message;
import Models.Sentence;
import Models.Sickness;
import Models.VirtualPeer;
import View.TransitionPanel;
import View.WelcomePanel;
import simplenlg.features.Feature;
import simplenlg.features.Form;
import simplenlg.features.Tense;
import simplenlg.framework.InflectedWordElement;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.NLGFactory;
import simplenlg.framework.WordElement;
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
	
	private ArrayList<String> mappingActionSymptom;
	private Hashtable<String, String> hashtable = new Hashtable<String, String>();
	private ArrayList<String> myCompletePastActions;
	private ArrayList<Action> actionList;
	
	private ArrayList<String> pastAction;
	private Random randomGenerator = new Random();
	private Action curAction;
	private Action reverse;
	
	private int successionCtr;
	
	private int actionCtr;
	private String username;
	private Boolean hasSymptom;
	private String place;
	private ArrayList<String> actions = new ArrayList<String>();
	private List<List<String>> postCondition = new ArrayList<List<String>>();
	
	
	public StoryGenerator2(){
		username = WelcomePanel.getPlayerName();
		goodHealthAssertions = (new AssertionDAO()).getHealthAssertions("good");
		badHealthAssertions = (new AssertionDAO()).getHealthAssertions("bad");
		// theme selection (red spots, fever, mosquito bites, tummy ache, colds, sneezing)
		this.vpList = (new VirtualPeerDAO()).getAllVirtualPeers();
		// set vps to have good health conditions
		for (VirtualPeer vp: vpList){
			vp.setHealthAssertions(goodHealthAssertions);
			vp.setSick(false);
		}
		
		this.setCurVP(VirtualPeer.VP_SARAH);
		this.causesList = new ArrayList<String>();
		this.preventionsList = new ArrayList<String>();
		this.symptomsList = new ArrayList<String>();
		this.treatmentsList = new ArrayList<String>();
		this.bodyPartsList = new ArrayList<BodyPart>();
		this.episodesList = new ArrayList<Episode>();
		
		this.myCompletePastActions = new ArrayList<String>();
		this.hashtable = new Hashtable<String, String>();
		this.mappingActionSymptom = new ArrayList<String>();
		this.actionList =  new ArrayList<Action>();
		
		// temp
		storyRuling = Event.RULING_GOOD;
		
		curStoryEpisodeIndex = 0;
		curStoryEventIndex = 0;
		eventsId = new ArrayList<Integer>();
		pastAction = new ArrayList<String>();
	
		actionCtr = 0;
		successionCtr = 0;
		place = StartFrameController.getPlace();
	}
	
	public boolean getIsLiamSick(){
		return vpList.get(VirtualPeer.VP_LIAM-1).isSick();
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
//		System.out.print("event arr = "+eventsId);
//		System.out.print("; curStoryEpisodeIndex = "+curStoryEpisodeIndex);
//		//System.out.print("; cur epGoal = " +episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
		//System.out.print("; curStoryEventIndex = "+curStoryEventIndex);
//		System.out.println(";curEpGoal = "+episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
//		System.out.println("; actionCtr = "+actionCtr);
		
		System.out.println("!!! curStoryEpisodeIndex == episodesList.size()" + curStoryEpisodeIndex + ":" + episodesList.size());
		System.out.println("!!! curStoryEventIndex == this.eventsId.size()" + curStoryEventIndex + ":" + this.eventsId.size());
		//display end screen
		
		if ( curStoryEpisodeIndex == episodesList.size() && curStoryEventIndex == this.eventsId.size() ){
			System.out.println("END DISPLAYEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEED");
			StartFrameController.displayEnd();
		}else{
			//if start or if tapos na sa events to move on to the next episode
			if ( eventsId.isEmpty() || curStoryEventIndex == this.eventsId.size()){
				this.episode = episodesList.get(curStoryEpisodeIndex);
				if ( this.episode.getDiscourseActId() != 10){
					this.eventsId = episode.getEventsId(); // return arraylist of events
				}
				
				//12,8,9,8,11,9,8,9
				System.out.println(".......");
				if(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 1){
					if(symptomsList.isEmpty()){
						episodesList.subList(curStoryEpisodeIndex, episodesList.size()).clear();
						Episode episode = (new EpisodeDAO()).getEpisodeById(14);
						episodesList.add(episode);
						curStoryEventIndex = 0;
						
						this.episode = episodesList.get(curStoryEpisodeIndex);
						if ( this.episode.getDiscourseActId() != 10){
							this.eventsId = episode.getEventsId(); // return arraylist of events
						}
					}
					else{
//						StartFrameController.displayTransition();
						vpList.get(VirtualPeer.VP_LIAM-1).setSick(true);
						curAction = (new ActionDAO()).setActionDetails(40);
						int index = randomGenerator.nextInt(curAction.getObectList().size());
						curAction.setChosenObject(curAction.getObectList().get(index));  
					}                   
				}
				
				
				if (episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 10 || 
						episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 11){
					
					System.out.println("ENTERRRRRR");
					System.out.println("cur ep goal = "+episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
					System.out.println("cur episode index = "+curStoryEpisodeIndex);
					
					episodesList.remove(episodesList.get(curStoryEpisodeIndex));
					

					curStoryEpisodeIndex--;
					System.out.println("cur episode index = "+curStoryEpisodeIndex);
				}
				
				
				if (episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 9 && actionCtr < 4){
					curStoryEpisodeIndex-=2;
				} else {
					System.out.println("PUMASOK SIYA DITO BESSSS");
	//				if(!(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 8 && checkIfLiamHasAllGoodAssertions()))
//						if(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() !=13)
							curStoryEpisodeIndex++;
					System.out.println("***Symptoms list size = "+symptomsList.size());
					System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
					for(String x : mappingActionSymptom)
						System.out.println("mapping: "+x);
	//				if(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 1){
	//					for(int i = 0; i < symptomsList.size(); i++){
	//						System.out.println("SYMPTOMS : "+ symptomsList.get(i));
	//					}
	//				}
				}
				
				curStoryEventIndex = 0;	
			}
			
			// 8 - invite to do activity and eventindex is 0 
			if ( this.episode.getEpisodeGoalId() == 8 && curStoryEventIndex == 0){
				if ( checkIfLiamHasAllGoodAssertions() ){
					System.out.println("((((( entered liam has all good assertions )))))");
					ArrayList<Integer> possibleActionIds = (new ActionDAO()).getFirstAction(goodHealthAssertions, place, badHealthAssertions);
					
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
	//				if(!curAction.getSymptomList().isEmpty())
	//					System.out.println(">>>> symptomsList of curAction = " + curAction.getSymptomList().get(0));
					System.out.println("H");
					
					String tempAction = a.getActivityName() + " :: " + a.getChosenObject().getName() + " :: " + a.getChosenObject().getVerb();
					
					pastAction.add(tempAction);
					
					ArrayList<Integer> post = curAction.getPostcondition();
					actions.add(tempAction);
					postCondition.add(getStringFormatPostCondition(post));
					
					mappingActionSymptom.add(tempAction);
				}
				else {
					System.out.println("((((( entered liam has bad assertions )))))");
					ArrayList<Integer> result = new ArrayList<Integer>(badHealthAssertions);
					ArrayList<Integer> liamHealthAssertions = this.vpList.get(VirtualPeer.VP_LIAM - 1).getHealthAssertions();
				    result.retainAll(liamHealthAssertions);
				    
				    System.out.println(badHealthAssertions + " " +  liamHealthAssertions + "mew: " + result);
				    int rightop = (new AssertionDAO()).getOppsotiteAssertion(result.get(randomGenerator.nextInt(result.size())));
					ArrayList<Integer> possibleActionIds = (new ActionDAO()).getActionWithPrecondition(place, rightop);
					
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
					
	//				System.out.println(">>>> curAction activity = " + curAction.getChosenObject().getVerb());
	//				if(!curAction.getSymptomList().isEmpty())
	//					System.out.println(">>>> symptomsList of curAction = " + curAction.getSymptomList().get(0));
					
					String tempAction = a.getActivityName() + " :: " + a.getChosenObject().getName() + " :: " + a.getChosenObject().getVerb();
					pastAction.add(tempAction);
					
					System.out.println("HAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
					ArrayList<Integer> post = curAction.getPostcondition();
					actions.add(tempAction);
					postCondition.add(getStringFormatPostCondition(post));
					
					mappingActionSymptom.add(tempAction);
					
					System.out.println("GETTING REVERSE ACTION");
					ArrayList<Integer> reverseActions = (new ActionDAO()).getReverseAction(place, (new AssertionDAO()).getOppsotiteAssertion(curAction.getPrecondition().get(0)));
					if(reverseActions.size() != 0){
						int index = randomGenerator.nextInt(reverseActions.size());		
						reverse = (new ActionDAO()).setActionDetails(reverseActions.get(index));
						index = randomGenerator.nextInt(reverse.getObectList().size());
						reverse.setChosenObject(reverse.getObectList().get(index));
						System.out.println("REVERSE : " + reverse.getActivityName());
						curAction.setReverseActions(reverse);
					}
					
					actionList.add(curAction);
					String revActionDetails = reverse.getChosenObject().getVerb() + "::" + reverse.getChosenObject().getName();     
				}   String curActionDetails = curAction.getChosenObject().getVerb() + "::" + curAction.getChosenObject().getName(); 
				
				actionCtr++;	
				
				System.out.println(ifLiamMeetsAssertions(curAction));
				//dito na suggest ni Sarah na I think you should rest
				if (!ifLiamMeetsAssertions(curAction)){
					Episode episode = (new EpisodeDAO()).getEpisodeById(10); 
					episodesList.add(curStoryEpisodeIndex, episode);
					episode = (new EpisodeDAO()).getEpisodeById(11);	
					episodesList.add(curStoryEpisodeIndex+1, episode);
					curStoryEventIndex = 0;
				}
			}
			
			

			Event event = (new EventDAO()).getEventById(eventsId.get(curStoryEventIndex));
			
			
			this.lastQuestion = "";
		
//			System.out.println("!!!!!!!!!!!!!!! event ruling = "+event.getRuling());
//			System.out.println("!!!!!!!!!!!!!!! story ruling = "+storyRuling);
				
			// start StoryRuling is good
			// if storyRuling is same as event ruling or event ruling is equal to neutral
			if (event == null){
				String duration = "A few days later";
				StartFrameController.displayTransition(duration);
				curStoryEventIndex++;
			}
			else if (storyRuling == event.getRuling() || event.getRuling() == Event.RULING_NEUTRAL){
				if (event.getType() == Event.TYPE_MESSAGE){
					Message message = (new MessageDAO()).getMessageById(event.getId());
					message.setRuling(event.getRuling());
					if (curStoryEventIndex == eventsId.size()-1 && episode.getDiscourseActId() != 0)
						message.setIsLast(true);
					ArrayList<String> sentenceTags = message.getSentenceTags();
					String m = "";
					for (String tempSentenceTag: sentenceTags){
						System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: "+tempSentenceTag);
						Sentence sentence = (new SentenceDAO()).getSentenceByTag(tempSentenceTag);
						m += sentence.getMessage() + " ";
						this.lastSentenceTag = tempSentenceTag;
						this.lastQuestion = sentence.getMessage();
					}
					m = polishMessage(m);
					StartFrameController.displayMessage(this.curVP.getName(), m, message.getIsLast(), message.getRuling());
					if (!message.getIsLast()){
						this.roundRobinVP();
					}
				}
				else if (event.getType() == Event.TYPE_ACTION){
					Message message = (new MessageDAO()).getMessageById(event.getId());
					if (curStoryEventIndex == eventsId.size()-1 && episode.getDiscourseActId() != 0)
						message.setIsLast(true);
					ArrayList<String> sentenceTags = message.getSentenceTags();
					String m = "";
					for (String tempSentenceTag: sentenceTags){
						Sentence sentence = (new SentenceDAO()).getSentenceByTag(tempSentenceTag);
						m += sentence.getMessage() + " ";
						this.lastSentenceTag = tempSentenceTag;
						this.lastQuestion = sentence.getMessage();
					}
					
					boolean isReverseAct = m.contains("reverseAction");
					if (m.contains("reverseAction")){
						doAction( reverse , true, polishMessage(m));
					}
					else if (m.contains("curAction")){
						doAction( curAction, false, polishMessage(m));
					}
					m = polishMessage(m);
					this.roundRobinVP();
				}
				curStoryEventIndex++;
			}
			else{
				curStoryEventIndex++;
				
				// narrate activity para sa reverse
				// narrate action para sa current action
				playEvent();
			}
				
			if(curStoryEpisodeIndex != episodesList.size()){
				if(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 13 && curAction.getReverseActions() == null){
					curStoryEpisodeIndex++;
				}
				
				if(this.episode.getEpisodeGoalId() == 1 && curStoryEventIndex == 1){
					selectStoryTheme();
				}
			}
		
		}
		
	} // end playEvent
	
	private void doAction( Action a , boolean isReverseAct, String m){
		
		// check if precondition is met
		// if not, add symptom
		// implicate postconditions
		// add to whatever mappingActionSymptom
		
		System.out.println("ACTUALLY DOING ACTION FINALLY");
		
		// THIS IS STEP 1&2 YAY
		if (!isReverseAct && !ifLiamMeetsAssertions(a)){
			if(storyRuling == Event.RULING_BAD){
				if(curAction.getSymptomList().size() != 0){
					int index = randomGenerator.nextInt(curAction.getSymptomList().size());
					String symTemp = curAction.getSymptomList().get(index);
					if( !symptomsList.contains(symTemp) ){
						symptomsList.add( symTemp );
						//mappingActionSymptom.add()
						StartFrameController.addSymptom(symTemp);
					}
				}
			}
		}
		
		// THIS IS STEP 3
		ArrayList<Integer> postconditions = a.getPostcondition();
		for (int postconTemp: postconditions){
			int assertionIdOpp = (new AssertionDAO()).getOppsotiteAssertion(postconTemp);
			System.out.println("TO BE SEARCH: " + assertionIdOpp);
			this.vpList.get(VirtualPeer.VP_LIAM - 1).exchangeHealthAssertion(assertionIdOpp, postconTemp);
			Assertion assertion = (new AssertionDAO()).getAssertionById(assertionIdOpp); 
			hashtable.put(assertion.getConcept1(), a.getChosenObject().getVerb() + " :: " + a.getChosenObject().getName());
		}
		
		ArrayList<String> toUpdateLiamBadAssertions = new ArrayList<String>();
		ArrayList<Integer> liamAssertions = (ArrayList<Integer>) this.vpList.get(VirtualPeer.VP_LIAM - 1).getHealthAssertions().clone();
		liamAssertions.retainAll(badHealthAssertions);
		for (int tempA: liamAssertions){
			Assertion assertion = (new AssertionDAO()).getAssertionById(tempA);
			toUpdateLiamBadAssertions.add(assertion.getConcept1() + " " + assertion.getConcept2());
		}
		StartFrameController.updateHealthAssertion(toUpdateLiamBadAssertions);
		
		// THIS IS STEP 4
		if(isReverseAct){
			String revActionDetails = reverse.getChosenObject().getVerb() + "::" + reverse.getChosenObject().getName();
			String curActionDetails = curAction.getChosenObject().getVerb() + "::" + curAction.getChosenObject().getName();
			
			myCompletePastActions.add(revActionDetails + "::" + curActionDetails);
			System.out.println("UPDATING REVERSE");
			mappingActionSymptom.add(mappingActionSymptom.size()-1, reverse.getActivityName() + " :: " + reverse.getChosenObject().getName() + " :: " + reverse.getChosenObject().getVerb());
			mappingActionSymptom.add(mappingActionSymptom.size()-1, (new AssertionDAO()).getAssertionById(postconditions.get(0)).getConcept1() + " :: " + (new AssertionDAO()).getAssertionById(postconditions.get(0)).getConcept2());
			
			if (curAction.getPostcondition().size() == 0){
				mappingActionSymptom.add("NONE");
			}else{
				mappingActionSymptom.add((new AssertionDAO()).getAssertionById(curAction.getPostcondition().get(0)).getConcept1() + " :: " + (new AssertionDAO()).getAssertionById(curAction.getPostcondition().get(0)).getConcept2());
			}
		}
		else{
			System.out.println("UPDATING");
			if (postconditions.size() == 0){
				mappingActionSymptom.add("NONE");
			}else{
				mappingActionSymptom.add((new AssertionDAO()).getAssertionById(postconditions.get(0)).getConcept1() + " :: " + (new AssertionDAO()).getAssertionById(postconditions.get(0)).getConcept2());
			}
		}
		StartFrameController.displayAction("Liam", a.getChosenObject().getVerb() + " " + a.getChosenObject().getName(), a.getChosenObject().getFilename(), a.getRuling(), m);
	}
	
	private boolean checkIfLiamHasAllGoodAssertions() {
		ArrayList<Integer> liamHealthAssertions = this.vpList.get(VirtualPeer.VP_LIAM - 1).getHealthAssertions();
//		System.out.println("Liam's health assertions = " +liamHealthAssertions);
//		System.out.println("Good health assertions = " +goodHealthAssertions);
		for (int assertionTemp: goodHealthAssertions){
			if (liamHealthAssertions.indexOf(assertionTemp) == -1)
				return false;
		}
		return true;
	}

	public boolean ifLiamMeetsAssertions( Action a ){
		
		System.out.println("called ifLiamMeetsAssertions()");
		
		ArrayList<Integer> actionAssertions = a.getPrecondition();
		ArrayList<Integer> liamHealthAssertions = this.vpList.get(VirtualPeer.VP_LIAM - 1).getHealthAssertions();
		
		for (int tempAssertionIndex : actionAssertions ){
			if (liamHealthAssertions.indexOf(tempAssertionIndex) == -1)
				return false;
		}
		return true;
	}
	
	public boolean checkActionAcceptable(Action a){
		String check = a.getActivityName() + " :: " + a.getChosenObject().getName() + " :: " + a.getChosenObject().getVerb();
		
		
		System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
		System.out.println("checking if past = "+check);
		for(String x: pastAction)
			System.out.println("past action: "+x);
		
		if (pastAction.contains(check)){
			System.out.println("ACTION ALREADY DONE");
			return false;
		}
		System.out.println("ACTION OKAY");
		return true;
	}

	public void getVerdict(String userInput) throws IOException {
		System.out.println("{{{{{-BES-}}}}}");
		String verdict = SarahChatbot.getVerdict(lastSentenceTag, userInput, successionCtr);
		System.out.println("lastSentenceTag = "+lastSentenceTag);
		System.out.println(verdict);
		
		if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_BAD)){
			this.storyRuling = Event.RULING_BAD;
			StartFrameController.playEvent();
		}
		else if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_GOOD)){
			this.storyRuling = Event.RULING_GOOD;
						
			StartFrameController.playEvent();
		}
		else{
			this.storyRuling = Event.RULING_NEUTRAL;
			Sentence sentence;
			
			if(verdict.contains("T:")){
				System.out.println("********** StoryGenerator getVerdict if contains T:");
				String[] output = verdict.split("T:");
				sentence = (new SentenceDAO()).getSentenceByTag(output[1]);
				verdict = polishMessage(sentence.getMessage());
			}else{
				sentence = (new SentenceDAO()).getSentenceByTag(this.lastSentenceTag);
			}
			
			successionCtr++;
			
			if(successionCtr == 3){
				successionCtr = 0;
				verdict += " Wait, we're going out of topic. Let's go back. " + polishMessage(sentence.getMessage());
			}
			
			StartFrameController.displayMessage(this.curVP.getName(), verdict, true, storyRuling);
		}
//		else if (verdict.equalsIgnoreCase(SarahChatbot.VERDICT_NEUTRAL)){
//			this.storyRuling = Event.RULING_NEUTRAL;
//			Sentence sentence = (new SentenceDAO()).getSentenceByTag(this.lastSentenceTag);
//			StartFrameController.displayMessage(this.curVP.getName(), polishMessage(sentence.getMessage()), true);
//		}
		
	}

	public void selectStoryTheme() {
		System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW: entered function selectStoryTheme");
		System.out.println("symptomsList size = " + symptomsList.size());
		List<List<String>> resultList = new ArrayList<List<String>>();
		List<List<String>> highestList = new ArrayList<List<String>>();
		
		for(String s : symptomsList)
			System.out.println("SYMPTOM: "+s);
		
		if(symptomsList.size() != 0){
			float maxPercentage = 0;
			Sickness maxSickness = new Sickness();
			ArrayList<Float> result =  new ArrayList<Float>();
			ArrayList<Integer> highest = new ArrayList<Integer>();
			ArrayList<Integer> sicknessId = new ArrayList<Integer>();
			
			
			for(int i = 1; i <= 10; i++){
				Sickness sickness = (new SicknessDAO()).getSicknessWithId(i);
				
				System.out.println("sickness = "+sickness.getName());
				
				ArrayList<String> symptoms = (new SymptomDAO()).getSymptomsBySicknessId(i);
				
				System.out.println("symptoms before retain: "+symptoms.size());
				symptoms.retainAll(symptomsList);
				System.out.println("symptoms after retain: "+symptoms.size());
				
				resultList.add(symptoms);
			
				float percentage = ((float)symptoms.size() / (float)symptomsList.size()) * 100;
				result.add(percentage);
				System.out.println("percentage = "+percentage);
				
				if (percentage > maxPercentage){
					maxPercentage = percentage;
					maxSickness = sickness;
					System.out.println("maxPercentage = "+maxPercentage);
				}
			}
			
			for(int i = 1; i <= 10; i++){
				if(maxPercentage == result.get(i-1)){
					highest.add(i);
					highestList.add(resultList.get(i-1));
				}
			}
			
			System.out.println("HIGHEST SIZE: " + highest.size());
			
			int index = randomGenerator.nextInt(highest.size());
			maxSickness = (new SicknessDAO()).getSicknessWithId(highest.get(index));
			
			System.out.println("maxSickness id = " +maxSickness.getId());
			System.out.println("maxSickness name = " +maxSickness.getName());
			
			System.out.println("SYMPTOMS BEFORE");
			for(int i = 0; i <symptomsList.size();i++){
				System.out.println(symptomsList.get(i));
			}
			
			this.storyTheme = maxSickness;
			this.causesList.addAll((new CauseDAO()).get5CausesBySicknessId(maxSickness.getId()));
			this.preventionsList.addAll((new PreventionDAO()).get5PreventionBySicknessId(maxSickness.getId()));
			this.treatmentsList.addAll((new TreatmentDAO()).get5TreatmentsBySicknessId(maxSickness.getId()));
			this.bodyPartsList.addAll((new BodyPartDAO()).getBodyPartsBySicknessId(maxSickness.getId()));
			this.symptomsList = (ArrayList<String>) highestList.get(index);
			
			System.out.println("SYMPTOMS AFTER");
			for(int i = 0; i <symptomsList.size();i++){
				System.out.println(symptomsList.get(i));
			}
		} // if symptomsList is not empty
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
		
		message = message.replaceAll("<user-cap>", username.substring(0, 1).toUpperCase() + username.substring(1));
		message = message.replaceAll("<day>", getDay());
		message = message.replaceAll("<liam-status>", "sick");
		
		if(message.contains("pastAction")){
			System.out.println("Starttttttttt");
			for (String temp: this.myCompletePastActions){
				String prevVerb = temp.split("::")[0];    
				String prevObj = temp.split("::")[1];     
				String curVerb = temp.split("::")[2];     
				String curObj = temp.split("::")[3];
				
				System.out.println(curVerb +" "+curObj);
				System.out.println(prevVerb +" "+prevObj);
				for(int i = 0; i < actionList.size(); i++){
					if(actionList.get(i).getChosenObject().getName().equals(curObj) && actionList.get(i).getChosenObject().getVerb().equals(curVerb) &&
							actionList.get(i).getReverseActions().getChosenObject().getName().equals(prevObj) && actionList.get(i).getReverseActions().getChosenObject().getVerb().equals(prevVerb))
						actionList.remove(i);
				}
			}
			System.out.println("NATIRA");
			for(int i = 0; i < actionList.size(); i++){
				message = message.replaceFirst("<pastAction-revreseVerb>", actionList.get(i).getReverseActions().getChosenObject().getVerb());
				if(actionList.get(i).getReverseActions().getChosenObject().getConnector5() == null){
					message = message.replaceFirst("<pastAction-revreseVerb-connector> ", "");
				}else{
				message = message.replaceFirst("<pastAction-revreseVerb-connector>", actionList.get(i).getReverseActions().getChosenObject().getConnector5());
				}
				message = message.replaceFirst("<pastAction-revreseName>", actionList.get(i).getReverseActions().getChosenObject().getName());
				message = message.replaceFirst("<body>", (new AssertionDAO()).getAssertionById(actionList.get(i).getReverseActions().getPrecondition().get(0)).getConcept1());
				message = message.replaceFirst("<condition>", (new AssertionDAO()).getAssertionById(actionList.get(i).getReverseActions().getPrecondition().get(0)).getConcept2());
				message = message.replaceFirst("<pastAction-curVerb>", getNLG(actionList.get(i).getChosenObject().getVerb()));
				if(actionList.get(i).getChosenObject().getConnector5() ==  null){
					message = message.replaceFirst("<pastAction-curVerb-connector> ", "");
				}
				else{
					message = message.replaceFirst("<pastAction-curVerb-connector>", actionList.get(i).getChosenObject().getConnector5());
				}
				message = message.replaceFirst("<pastAction-curName>", actionList.get(i).getChosenObject().getName());
				
				if(i != 0){
					message = message + "Also, " + "You probably got your sickness because you did not <pastAction-revreseVerb> <pastAction-revreseVerb-connector> <pastAction-revreseName> when your <body> was <condition> before <pastAction-curVerb> <pastAction-curVerb-connector> <pastAction-curName>.";
					message = message.replaceFirst("<pastAction-revreseVerb>", actionList.get(i).getReverseActions().getChosenObject().getVerb());	
						if(actionList.get(i).getReverseActions().getChosenObject().getConnector5() == null){
								message = message.replaceFirst("<pastAction-revreseVerb-connector> ", "");
							}else{
							message = message.replaceFirst("<pastAction-revreseVerb-connector>", actionList.get(i).getReverseActions().getChosenObject().getConnector5());
							}
							message = message.replaceFirst("<pastAction-revreseName>", actionList.get(i).getReverseActions().getChosenObject().getName());
							message = message.replaceFirst("<body>", (new AssertionDAO()).getAssertionById(actionList.get(i).getReverseActions().getPrecondition().get(0)).getConcept1());
							message = message.replaceFirst("<condition>", (new AssertionDAO()).getAssertionById(actionList.get(i).getReverseActions().getPrecondition().get(0)).getConcept2());
							message = message.replaceFirst("<pastAction-curVerb>", getNLG(actionList.get(i).getChosenObject().getVerb()));
							if(actionList.get(i).getChosenObject().getConnector5() ==  null){
								message = message.replaceFirst("<pastAction-curVerb-connector> ", "");
							}
							else{
								message = message.replaceFirst("<pastAction-curVerb-connector>", actionList.get(i).getChosenObject().getConnector5());
							}
							message = message.replaceFirst("<pastAction-curName>", actionList.get(i).getChosenObject().getName());
				
				}/*<pastAction-revreseVerb> <pastAction-revreseVerb-connector> <pastAction-revreseName> when your <body> was <condition> before <pastAction-curVerb> <pastAction-curVerb-connector> <pastAction-curName>.
				
				System.out.println(actionList.get(i).getReverseActions().getChosenObject().getVerb() + " " + actionList.get(i).getReverseActions().getChosenObject().getName());
				
				System.out.println("body: " + (new AssertionDAO()).getAssertionById(actionList.get(i).getReverseActions().getPrecondition().get(0)).getConcept1());
				System.out.println("conditon: " + (new AssertionDAO()).getAssertionById(actionList.get(i).getReverseActions().getPrecondition().get(0)).getConcept2());
			
				 System.out.println(actionList.get(i).getChosenObject().getVerb() + " " + actionList.get(i).getChosenObject().getName());*/
				 
			}
			
			System.out.println("ENDDDDDDDDDDDDD");
		}
		
		if(message.contains("review")){
			for (String temp: this.myCompletePastActions){
				String prevVerb = temp.split("::")[0];
				String prevObj = temp.split("::")[1];
				String curVerb = temp.split("::")[2];
				String curObj = temp.split("::")[3];
				
				if((new ActionDAO()).getConnectorGivenName(prevObj) != null){
					message = message.replaceFirst("<reviewRevAction-verb-past>", prevVerb + " " + (new ActionDAO()).getConnectorGivenName(prevObj));
				}else{
					message = message.replaceFirst("<reviewRevAction-verb-past>", prevVerb);
				}

				message = message.replaceFirst("<reviewRevAction-object>", prevObj);
				
				if((new ActionDAO()).getConnectorGivenName(curObj) != null){
					message = message.replaceFirst("<reviewCurAction-verb-ing>", getNLG(curVerb) + " " + (new ActionDAO()).getConnectorGivenName(curObj));
				}else{
					message = message.replaceFirst("<reviewCurAction-verb-ing>", getNLG(curVerb));
				}
				message = message.replaceFirst("<reviewCurAction-object>", curObj);
			}
		}
		
		if(message.contains("sickness"))
			message = message.replaceAll("<sickness>", this.storyTheme.getName());
		
		if(message.contains("body-part")){
			message = message.replaceAll("<body-part-affected>", this.bodyPartsList.get(0).getName());
			message = message.replaceAll("<body-part-affected-cap>", this.bodyPartsList.get(0).getName().substring(0, 1).toUpperCase() + this.bodyPartsList.get(0).getName().substring(1));
			message = message.replaceAll("<body-part-definition>", this.bodyPartsList.get(0).getDescription());
		}
		
		if (message.contains("curAction")){
			message = message.replaceAll("<curAction-verb-ing>", getNLG(this.curAction.getChosenObject().getVerb()));
			message = message.replaceAll("<curAction-verb-ing-cap>", getNLG(this.curAction.getChosenObject().getVerb()).substring(0, 1).toUpperCase() + getNLG(this.curAction.getChosenObject().getVerb()).substring(1));
			message = message.replaceAll("<curAction-verb>", this.curAction.getChosenObject().getVerb());
			message = message.replaceAll("<curAction-object>", this.curAction.getChosenObject().getName());
			message = message.replaceAll("<curAction-motivation>", this.curAction.getMotivation().get(new Random().nextInt(this.curAction.getMotivation().size())));
		}
		
		if(message.contains("curAction-connector")){
			if(this.curAction.getChosenObject().getConnector1() == null){
				message = message.replaceAll("<curAction-connector1> ", "");
			}else{
				message = message.replaceAll("<curAction-connector1>", this.curAction.getChosenObject().getConnector1());
			}
			
			if(this.curAction.getChosenObject().getConnector2() == null){
				message = message.replaceAll("<curAction-connector2> ", "");
			}else{
				message = message.replaceAll("<curAction-connector2>", this.curAction.getChosenObject().getConnector2());
			}
			
			if(this.curAction.getChosenObject().getConnector3() == null){
				message = message.replaceAll("<curAction-connector3> ", "");
			}else{
				message = message.replaceAll("<curAction-connector3>", this.curAction.getChosenObject().getConnector3());
			}
			
			if(this.curAction.getChosenObject().getConnector4() == null){
				message = message.replaceAll("<curAction-connector4> ", "");
			}else{
				message = message.replaceAll("<curAction-connector4>", this.curAction.getChosenObject().getConnector4());
			}
			
			if(this.curAction.getChosenObject().getConnector5() == null){
				message = message.replaceAll("<curAction-connector5> ", "");
			}else{
				message = message.replaceAll("<curAction-connector5>", this.curAction.getChosenObject().getConnector5());
			}
			
			
		}
		
		if(message.contains("reverseAction-connector")){
			if(reverse.getChosenObject().getConnector1() == null){
				message = message.replaceAll("<reverseAction-connector1> ", "");
			}else{
				message = message.replaceAll("<reverseAction-connector1>", reverse.getChosenObject().getConnector1());
			}
			
			if(reverse.getChosenObject().getConnector2() == null){
				message = message.replaceAll("<reverseAction-connector2> ", "");
			}else{
				message = message.replaceAll("<reverseAction-connector2>", reverse.getChosenObject().getConnector2());
			}
			
			if(reverse.getChosenObject().getConnector3() == null){
				message = message.replaceAll("<reverseAction-connector3> ", "");
			}else{
				message = message.replaceAll("<reverseAction-connector3>", reverse.getChosenObject().getConnector3());
			}
			
			if(reverse.getChosenObject().getConnector4() == null){
				message = message.replaceAll("<reverseAction-connector4> ", "");
			}else{
				message = message.replaceAll("<reverseAction-connector4>", reverse.getChosenObject().getConnector4());
			}
			
			if(reverse.getChosenObject().getConnector5() == null){
				message = message.replaceAll("<reverseAction-connector5> ", "");
			}else{
				message = message.replaceAll("<reverseAction-connector5>", reverse.getChosenObject().getConnector5());
			}
			
		}
		
		if(message.contains("prevAction-connector")){
			
		}
		
		if(message.contains("prevAction")){
			/*System.out.println("SIIIIIIIZE: " +postCondition.size());
			String search = (new AssertionDAO()).getAssertionById(reverse.getPostcondition().get(0)).getConcept1() + " " + (new AssertionDAO()).getAssertionById((new AssertionDAO()).getOppsotiteAssertion(curAction.getPrecondition().get(0))).getConcept2();
			
			System.out.println("TO BE SEARCHED IN POSTCONDITION: "+ search);
			String tempPrevAction = actions.get(searchAssertioninPostCondition(search));
			
			message = message.replaceAll("<prevAction-verb-ing>", getNLG(tempPrevAction.split(" :: ")[2]));
			message = message.replaceAll("<prevAction-verb>", tempPrevAction.split(" :: ")[2]);
			message = message.replaceAll("<prevAction-object>", tempPrevAction.split(" :: ")[1]);*/
			
			String tempPrevAction = "";
			
			String bodyPart = (new AssertionDAO()).getAssertionById(reverse.getPostcondition().get(0)).getConcept1();
			String actionDetails = hashtable.get(bodyPart);
			System.out.println(actionDetails);
			String prevVerb = actionDetails.split(" :: ")[0];
			String prevObj = actionDetails.split(" :: ")[1];
			System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
			for(String xxx : mappingActionSymptom)
				System.out.println("mapping xxx = "+xxx);
			System.out.println(":::::::::;; DONE NA BES");
			
//			for(int i = mappingActionSymptom.size()-1; i >= 0; i-=2){
//				if(!mappingActionSymptom.get(i-1).equalsIgnoreCase("NONE")){
//					tempPrevAction = mappingActionSymptom.get(i-2);
//					break;
//				}
//			}
			
//			message = message.replaceAll("<prevAction-verb-ing>", getNLG(tempPrevAction.split(" :: ")[2]));
//			message = message.replaceAll("<prevAction-verb>", tempPrevAction.split(" :: ")[2]);
//			message = message.replaceAll("<prevAction-object>", tempPrevAction.split(" :: ")[1]);
			
			message = message.replaceAll("<prevAction-verb-ing>", getNLG(prevVerb));
			message = message.replaceAll("<prevAction-verb>", prevVerb);
			message = message.replaceAll("<prevAction-object>", prevObj);
		}
		
		if(message.contains("reverseAction")){
			message = message.replaceAll("<reverseAction-verb-past>", getPast(reverse.getChosenObject().getVerb()));
			message = message.replaceAll("<reverseAction-verb-ing>", getNLG(reverse.getChosenObject().getVerb()));
			message = message.replaceAll("<reverseAction-verb>", reverse.getChosenObject().getVerb());
			message = message.replaceAll("<reverseAction-object>", reverse.getChosenObject().getName());
			message = message.replaceAll("<reverseAction-postCondition-property>", (new AssertionDAO()).getAssertionById(reverse.getPostcondition().get(0)).getConcept2());
		}
		
		if(message.contains("curCondition")){
			message = message.replaceAll("<curCondition-body>", (new AssertionDAO()).getAssertionById(reverse.getPostcondition().get(0)).getConcept1());
			message = message.replaceAll("<curCondition-property>", (new AssertionDAO()).getAssertionById((new AssertionDAO()).getOppsotiteAssertion(curAction.getPrecondition().get(0))).getConcept2());
		}
		
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
		while (message.contains("[treatment-cap]")){
			message = message.replaceFirst("\\[treatment\\]", this.treatmentsList.get(i).substring(0, 1).toUpperCase() + this.treatmentsList.get(i).substring(1));
			i++;
		}
		
		i = 0;
		while (message.contains("[cause]")){
			message = message.replaceFirst("\\[cause\\]", this.causesList.get(i));
			i++;
		}
		
		i = 0;
		while (message.contains("[cause-cap]")){
			System.out.println(">>>>>>>>>> cause-cap "+i);
			message = message.replaceFirst("\\[cause\\]", this.causesList.get(i).substring(0, 1).toUpperCase() + this.causesList.get(i).substring(1));
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
//		XMLLexicon lexicon = new XMLLexicon("C:/Users/Bianca/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
//		XMLLexicon lexicon = new XMLLexicon("C:/Users/Raisa/projects/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
//		XMLLexicon lexicon = new XMLLexicon("C:/Users/Janine Tan/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
		XMLLexicon lexicon = new XMLLexicon("C:/Users/Heinson/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
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
	
	public String getPast (String word){
//			XMLLexicon lexicon = new XMLLexicon("C:/Users/Bianca/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
//		XMLLexicon lexicon = new XMLLexicon("C:/Users/Raisa/projects/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
//		XMLLexicon lexicon = new XMLLexicon("C:/Users/Janine Tan/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
		XMLLexicon lexicon = new XMLLexicon("C:/Users/Heinson/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
			WordElement word2 = lexicon.getWord(word, LexicalCategory.VERB);
			InflectedWordElement infl = new InflectedWordElement(word2);
			infl.setFeature(Feature.TENSE, Tense.PAST);
			Realiser realiser = new Realiser(lexicon);
			String past = realiser.realise(infl).getRealisation();
			return past;
	}
	
	public String getDay(){
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 
		String dayOfWeek = null;
		switch (day) {
		    case Calendar.SUNDAY:
		    	dayOfWeek = "Sunday";
		    	break; 
		    	
		    case Calendar.MONDAY:
		    	dayOfWeek = "Monday";
		    	break;
		    
		    case Calendar.TUESDAY:
		    	dayOfWeek = "Tuesday";
		    	break;
	
		    case Calendar.WEDNESDAY:
		    	dayOfWeek = "Wednesday";
		    	break;
		    	
		    case Calendar.THURSDAY:
		    	dayOfWeek = "Thursday";
		    	break;
		    	
		    case Calendar.FRIDAY:
		    	dayOfWeek = "Friday";
		    	break;
		    	
		    case Calendar.SATURDAY:
		    	dayOfWeek = "Saturday";	
		    	break;
		}
		return dayOfWeek;
		
	}	
	
	public ArrayList<String> getStringFormatPostCondition(ArrayList<Integer> post){
		ArrayList<String> temp = new ArrayList<String>();
		for(int i= 0; i < post.size(); i++){
			Assertion b = (new AssertionDAO()).getAssertionById(post.get(i));
			String onepost = b.getConcept1() + " " + b.getConcept2(); 
			temp.add(onepost);
		}
		return temp;
	}
	
	
	public int searchAssertioninPostCondition(String search){
		for(int i = 0; i < postCondition.size(); i--){
			ArrayList one = (ArrayList) postCondition.get(i);
			System.out.println("ACTION NUMBER: " + i);
			System.out.println("ONE SIZE: " + i);
			if(one.contains(search))
				return i;
		}
		
		return -1;
	}
}

