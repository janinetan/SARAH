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
import View.StoryPanel2;
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
	
	private boolean doneCauseEvent = false;
	private int causeEventCount = 0;
	private ArrayList<String> pastAction;
	private Random randomGenerator = new Random();
	private Action curAction;
	private Action reverse;
	
	private int successionCtr;
	
	private int actionCtr;
	private String username;
	
	
	public StoryGenerator2(){
		username = WelcomePanel.getPlayerName();
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
		
		this.mappingActionSymptom = new ArrayList<String>();
		
		// temp
		storyRuling = Event.RULING_GOOD;
		
		curStoryEpisodeIndex = 0;
		curStoryEventIndex = 0;
		eventsId = new ArrayList<Integer>();
		pastAction = new ArrayList<String>();
	
		actionCtr = 0;
		successionCtr = 0;
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
//		System.out.print("; curStoryEventIndex = "+curStoryEventIndex);
//		System.out.println(";curEpGoal = "+episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
//		System.out.println("; actionCtr = "+actionCtr);
		
		//display end screen
		if ( curStoryEpisodeIndex == episodesList.size() && curStoryEventIndex == this.eventsId.size() ){
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
				if (episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 10 || 
						episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 11){
					
//					boolean eleven = false;
//					if (episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 11){
//						eleven = true;
//					}
					
					System.out.println("ENTERRRRRR");
					System.out.println("cur ep goal = "+episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
					System.out.println("cur episode index = "+curStoryEpisodeIndex);
					
//					System.out.println("BEFORE: mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
//					
//					int i = 0;
//					for (Episode e : episodesList){
//						System.out.print(", ep (" +i+ ") = "+e.getEpisodeGoalId());
//						i++;
//					}
					
//					System.out.println();
					
					episodesList.remove(episodesList.get(curStoryEpisodeIndex));
					
//					System.out.println("AFTER: mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
//					
//					i = 0;
//					for (Episode e : episodesList){
//						System.out.print(", ep (" +i+ ") = "+e.getEpisodeGoalId());
//						i++;
//					}
					
					curStoryEpisodeIndex--;
					System.out.println("cur episode index = "+curStoryEpisodeIndex);
//					if (eleven && storyRuling == Event.RULING_GOOD){
//						curStoryEpisodeIndex++;
//						System.out.println(",,,,,,,,,,,,,,,,,, entered ELEVEN");
//						
//						int i = 0;
//						for (Episode ep : episodesList){
//							System.out.print(", ep (" +i+ ") = "+ep.getEpisodeGoalId());
//							i++;
//						}
//						
//						System.out.println();
//						
//						System.out.println("--- curStoryEpisodeIndex = "+curStoryEpisodeIndex);
//						System.out.println("--- curEpGoal = "+episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
//						
//						Episode e = (new EpisodeDAO()).getEpisodeById(13); 
//						episodesList.add(curStoryEpisodeIndex, e);
//						curStoryEventIndex = 0;
//						
//						System.out.println("added 13: mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
//						
//						int ii = 0;
//						for (Episode ep : episodesList){
//							System.out.print(", ep (" +ii+ ") = "+ep.getEpisodeGoalId());
//							ii++;
//						}
//						
//						System.out.println();
//						
//						System.out.println("--- curStoryEpisodeIndex = "+curStoryEpisodeIndex);
//						System.out.println("--- curEpGoal = "+episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId());
//					}
					 
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
	//				if(!curAction.getSymptomList().isEmpty())
	//					System.out.println(">>>> symptomsList of curAction = " + curAction.getSymptomList().get(0));
					
					String tempAction = a.getActivityName() + " :: " + a.getChosenObject().getName() + " :: " + a.getChosenObject().getVerb();
					pastAction.add(tempAction);
					mappingActionSymptom.add(tempAction);
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
					
	//				System.out.println(">>>> curAction activity = " + curAction.getChosenObject().getVerb());
	//				if(!curAction.getSymptomList().isEmpty())
	//					System.out.println(">>>> symptomsList of curAction = " + curAction.getSymptomList().get(0));
					
					String tempAction = a.getActivityName() + " :: " + a.getChosenObject().getName() + " :: " + a.getChosenObject().getVerb();
					pastAction.add(tempAction);
					mappingActionSymptom.add(tempAction);
					
					System.out.println("GETTING REVERSE ACTION");
					ArrayList<Integer> reverseActions = (new ActionDAO()).getReverseAction("park", (new AssertionDAO()).getOppsotiteAssertion(curAction.getPrecondition().get(0)));
					if(reverseActions.size() != 0){
						int index = randomGenerator.nextInt(reverseActions.size());		
						reverse = (new ActionDAO()).setActionDetails(reverseActions.get(index));
						index = randomGenerator.nextInt(reverse.getObectList().size());
						reverse.setChosenObject(reverse.getObectList().get(index));
						System.out.println("REVERSE : " + reverse.getActivityName());
						curAction.setReverseActions(reverse);
					}
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
			
			//9 - doActivity && if curStoryEventIndex is second to the last or last	
			if( this.episode.getEpisodeGoalId() == 9 && ( curStoryEventIndex == this.eventsId.size() - 1) && StartFrameController.getFramePanel() instanceof StoryPanel2){
				
				System.out.println("pasok dito!");
				System.out.println("episode goal = "+episode.getEpisodeGoalId());
				System.out.println("event index = "+curStoryEventIndex);
				System.out.println("events size = "+(eventsId.size()-1));

//				RAISA REMOVED ACTION PANEL, TO BE FIXED :)
//				StartFrameController.displayAction(curAction.getChosenObject().getFilename());
				StartFrameController.displayAction("Liam", curAction.getChosenObject().getVerb() + " " + curAction.getChosenObject().getName() ,curAction.getChosenObject().getFilename(), curAction.getRuling());
				System.out.println("COOPERATION PLETH");
				
				if (curStoryEventIndex == this.eventsId.size() - 1){
					if (!ifLiamMeetsAssertions()){
						if(storyRuling == Event.RULING_BAD){
							if(curAction.getSymptomList().size() != 0){
								int index = randomGenerator.nextInt(curAction.getSymptomList().size());
			//					System.out.println(">>>>> randomized symptom = " + curAction.getSymptomList().get(index));
								String symTemp = curAction.getSymptomList().get(index);
								if( !symptomsList.contains(symTemp) ){
									symptomsList.add( symTemp );
									StartFrameController.addSymptom(symTemp);
								}
							}
							System.out.println("hassymptom");
							System.out.println("symptomsList size: "+symptomsList.size());
						}
					}
					//apply postcondition to liam
					System.out.println("CHANGING LIAM PROPERTY");
					
//					System.out.println("THIS = "+ curAction.getActivityName() + " " + curAction.getChosenObject().getName() + " " + curAction.getChosenObject().getVerb());
					
					if(storyRuling == Event.RULING_GOOD && curAction.getReverseActions()!= null && curStoryEventIndex == this.eventsId.size() - 1){
						System.out.println("UPDATING REVERSE");
						ArrayList<Integer> postconditions = reverse.getPostcondition();
						System.out.println("POSTCONDITION SIZE: " + postconditions.size());
						for (int postconTemp: postconditions){
							int assertionIdOpp = (new AssertionDAO()).getOppsotiteAssertion(postconTemp);
							System.out.println("TO BE SEARCH: " + assertionIdOpp);
							this.vpList.get(VirtualPeer.VP_LIAM - 1).exchangeHealthAssertion(assertionIdOpp, postconTemp);
						}
						
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
						ArrayList<Integer> postconditions = curAction.getPostcondition();
						System.out.println("curAction name = " +curAction.getActivityName());
						System.out.println("posconditions size = "+postconditions.size());
						for (int postconTemp: postconditions){
							int assertionIdOpp = (new AssertionDAO()).getOppsotiteAssertion(postconTemp);
							this.vpList.get(VirtualPeer.VP_LIAM - 1).exchangeHealthAssertion(assertionIdOpp, postconTemp);
						}
						
						if (postconditions.size() == 0){
							mappingActionSymptom.add("NONE");
						}else{
							mappingActionSymptom.add((new AssertionDAO()).getAssertionById(postconditions.get(0)).getConcept1() + " :: " + (new AssertionDAO()).getAssertionById(postconditions.get(0)).getConcept2());
						}
						
					}
					
				} //if (curStoryEventIndex == this.eventsId.size() - 1){	
				
			}
			
			
			
	//		if ( this.episode.getEpisodeGoalId() < 9 || this.episode.getEpisodeGoalId() >= 11) {
			else{
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
						m = polishMessage(m);
						StartFrameController.displayMessage(this.curVP.getName(), m, message.getIsLast(), message.getRuling());
						if (!message.getIsLast()){
							this.roundRobinVP();
						}
					}
					else if (event.getType() == Event.TYPE_ACTION){
						StartFrameController.displayAction("Liam", curAction.getChosenObject().getVerb() + " " + curAction.getChosenObject().getName(), curAction.getChosenObject().getName(), curAction.getRuling());
					}
					curStoryEventIndex++;
				}
				else{
					curStoryEventIndex++;
					playEvent();
				}
			}
				
			if(episodesList.get(curStoryEpisodeIndex).getEpisodeGoalId() == 13 && curAction.getReverseActions() == null){
				curStoryEpisodeIndex++;
			}	
			
			if(this.episode.getEpisodeGoalId() == 1 && curStoryEventIndex == 1){
				selectStoryTheme();
			}
		
		}
		
	} // end playEvent
	
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

	public boolean ifLiamMeetsAssertions(){
		
		System.out.println("called ifLiamMeetsAssertions()");
		
		ArrayList<Integer> actionAssertions = curAction.getPrecondition();
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
		String verdict = SarahChatbot.getVerdict(lastSentenceTag + " ", userInput);
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
				verdict = "Wait, we're going out of topic. Let's go back. " + polishMessage(sentence.getMessage());
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
		System.out.println("symptomsList size = " +symptomsList.size());
		
		for(String s : symptomsList)
			System.out.println("SYMPTOM: "+s);
		
		if(symptomsList.size() != 0){
			float maxPercentage = 0;
			Sickness maxSickness = new Sickness();
			ArrayList<Float> result =  new ArrayList<Float>();
			ArrayList<Integer> highest = new ArrayList<Integer>();
			
			for(int i = 1; i <= 10; i++){
				Sickness sickness = (new SicknessDAO()).getSicknessWithId(i);
				
				System.out.println("sickness = "+sickness.getName());
				
				ArrayList<String> symptoms = (new SymptomDAO()).getSymptomsBySicknessId(i);
				
				System.out.println("symptoms before retain: "+symptoms.size());
				symptoms.retainAll(symptomsList);
				System.out.println("symptoms after retain: "+symptoms.size());
				
				
				
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
				}
			}
			
			System.out.println("HIGHEST SIZE: " + highest.size());
			
			int index = randomGenerator.nextInt(highest.size());
			maxSickness = (new SicknessDAO()).getSicknessWithId(highest.get(index));
			
			System.out.println("maxSickness id = " +maxSickness.getId());
			System.out.println("maxSickness name = " +maxSickness.getName());
			
			this.storyTheme = maxSickness;
			this.causesList.addAll((new CauseDAO()).get5CausesBySicknessId(maxSickness.getId()));
			this.preventionsList.addAll((new PreventionDAO()).get5PreventionBySicknessId(maxSickness.getId()));
			this.treatmentsList.addAll((new TreatmentDAO()).get5TreatmentsBySicknessId(maxSickness.getId()));
			this.bodyPartsList.addAll((new BodyPartDAO()).getBodyPartsBySicknessId(maxSickness.getId()));
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
		message = message.replaceAll("<day>", "Saturday");
		message = message.replaceAll("<liam-status>", "sick");
		
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
		
		if(message.contains("connector")){
			if(reverse != null)
				message = message.replaceAll("<reverseAction-connector>", reverse.getChosenObject().getConnector());
			message = message.replaceAll("<curAction-connector>", this.curAction.getChosenObject().getConnector());
		}
		
		if(message.contains("connectorNarration")){
			if(reverse != null)
			message = message.replaceAll("<reverseAction-connectorNarration>", reverse.getChosenObject().getConnectorNarration());
			message = message.replaceAll("<curAction-connectorNarration>", this.curAction.getChosenObject().getConnectorNarration());
		}
		
		
		if(message.contains("prevAction")){
			String tempPrevAction = "";
			
			for(int i = mappingActionSymptom.size()-1; i >= 0; i-=2){
				if(!mappingActionSymptom.get(i-1).equalsIgnoreCase("NONE")){
					tempPrevAction = mappingActionSymptom.get(i-2);
					break;
				}
			}
			
			message = message.replaceAll("<prevAction-verb-ing>", getNLG(tempPrevAction.split(" :: ")[2]));
			message = message.replaceAll("<prevAction-verb>", tempPrevAction.split(" :: ")[2]);
			message = message.replaceAll("<prevAction-object>", tempPrevAction.split(" :: ")[1]);
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
		XMLLexicon lexicon = new XMLLexicon("C:/Users/Bianca/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
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
			XMLLexicon lexicon = new XMLLexicon("C:/Users/Bianca/Documents/GitHub/SARAH/src/simplenlg/lexicon/default-lexicon.xml");
			WordElement word2 = lexicon.getWord(word, LexicalCategory.VERB);
			InflectedWordElement infl = new InflectedWordElement(word2);
			infl.setFeature(Feature.TENSE, Tense.PAST);
			Realiser realiser = new Realiser(lexicon);
			String past = realiser.realise(infl).getRealisation();
			return past;
		}
	
}

