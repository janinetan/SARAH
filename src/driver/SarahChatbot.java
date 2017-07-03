package driver;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

public class SarahChatbot {

	public static void main (String[] args){
		String s = "What do you think! user ? Liam.should rest";
		s = getVerdict(s);
		System.out.println("Verdict: "+s);
	}
	
	public static final String VERDICT_GOOD = "positive";
	public static final String VERDICT_BAD = "negative";
//	public static final String VERDICT_NEUTRAL = "I have no answer for that.";
	
	public static String getVerdict (String input){
//		String path = "C:/Users/Bianca/Documents/GitHub/SARAH/sarah-bot";
		String path = "C:/Users/Raisa/projects/SARAH/sarah-bot";
		Bot bot = new Bot("sarah-bot", path);
		Chat chat = new Chat(bot);
		
		input = input.replaceAll("\\?", " 999 ");
		input = input.replaceAll("[,!\\.]", " ");
		
		input = chat.multisentenceRespond(input);
		
		if (input.contains("V>")){
			String[] output = input.split("V>");
			return output[1];	
		}
		
		return input;
	}
}