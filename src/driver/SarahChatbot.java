package driver;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

public class SarahChatbot {
	
	public static final String VERDICT_GOOD = "positive";
	public static final String VERDICT_BAD = "negative";
	public static final String VERDICT_NEUTRAL = "I have no answer for that.";
	
//	public static String path = "C:/Users/Bianca/Documents/GitHub/SARAH/sarah-bot";
	public static String path = "C:/Users/Raisa/projects/SARAH/sarah-bot";
	public static Bot bot = new Bot("sarah-bot", path);
	public static Chat chat = new Chat(bot);
	public static String s = "";
	
	
	public static void main(String[] args){
		System.out.println(getVerdict("askReverseAction? ", "anuna bes"));
		System.out.println(getVerdict("askReverseAction ", "yes"));
	}
	
	public static String getVerdict (String question, String response){
		Bot bot = new Bot("sarah-bot", path);
		Chat chat = new Chat(bot);
		String s = "";
		
		question = question.replaceAll("\\?", " 999 ");
		question = question.replaceAll("[,!\\.]", " ");
		
		s = chat.multisentenceRespond(question + response);

		if (s.equals(VERDICT_NEUTRAL)){
			s = chat.multisentenceRespond(response);
		}
		
		if (s.contains("V>")){
			String[] output = s.split("V>");
			return output[1];	
		}
		
		return s;
	}
}