package driver;
import java.io.IOException;
import java.util.List;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
//import org.languagetool.JLanguageTool;
//import org.languagetool.language.AmericanEnglish;
//import org.languagetool.rules.RuleMatch;

public class SarahChatbot {
	
	public static final String VERDICT_GOOD = "positive";
	public static final String VERDICT_BAD = "negative";
	public static final String VERDICT_NEUTRAL = "I have no answer for that.";
	
//	public static String path = "C:/Users/Raisa/projects/SARAH/sarah-bot";
	public static String path = "C:/Users/Heinson/Documents/GitHub/SARAH/sarah-bot";
//	public static String path = "C:/Users/Janine Tan/Documents/GitHub/SARAH/sarah-bot";
//	public static String path = "C:/Users/Bianca/Documents/GitHub/SARAH/sarah-bot";
	public static Bot bot = new Bot("sarah-bot", path);
	public static Chat chat = new Chat(bot);
	public static String s = "";
	
	
	public static void main(String[] args) throws IOException{
//		System.out.println(getVerdict("", "shut up"));
//		System.out.println(getVerdict("askReverseAction? ", "of course"));
		
//		JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
//		// comment in to use statistical ngram data:
//		//langTool.activateLanguageModelRules(new File("/data/google-ngram-data"));
////		System.out.println("tapos bes");
//		List<RuleMatch> matches = langTool.check("upp");
//		System.out.println("here na bes");
//		for (RuleMatch match : matches) {
////			System.out.println("kalma bes");
//		  System.out.println("Potential error at characters " +
//		      match.getFromPos() + "-" + match.getToPos() + ": " +
//		      match.getMessage());
////		  System.out.println("konti nalang bes");
//		  System.out.println("Suggested correction(s): " +
//		      match.getSuggestedReplacements());
////		  System.out.println("malapit na talaga bes");
//		}
////		System.out.println("tapos na bes");
	}
	
	public static String getVerdict (String question, String response, int successionCtr){
		
//		question = question.replaceAll("\\?", " 999 ");
		question = question.replaceAll("[,!\\.]", " ");
		
		System.out.println("LELELOLZ = "+successionCtr);
		
		// set topic
		if(successionCtr == 0){
//			System.out.println("ASKING AIML ON: " + "000 " + response);
			s = chat.multisentenceRespond("000 " + response);
			if (s.equals("OKIES")){
				System.out.println("ASKING AIML ON: " + question + " 999");
				s = chat.multisentenceRespond(question + " 999"); // set topic
			}
		}
		else {
			s = chat.multisentenceRespond(" others 999"); // set topic
		}
		
		System.out.println("ASKING AIML ON: " + response);
		s = chat.multisentenceRespond(response);
		
//		if (s.equals(VERDICT_NEUTRAL)){
//			System.out.println("********** SarahChatbot getVerdict if verdict_neutral");
//			s = chat.multisentenceRespond(response);
//		}
		
		if (s.contains("V:")){
			System.out.println("********** SarahChatbot getVerdict if contains V:");
			String[] output = s.split("V:");
			return output[1];	
		}
		
		return s;
	}
}