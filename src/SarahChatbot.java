import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

public class SarahChatbot {

	public static void main (String[] args){
		String s = "What do you think! user ? Liam.should rest";
		s = getVerdict(s);
		System.out.println("Verdict: "+s);
	}
	
	public static String getVerdict (String input){
		String path = "C:/Users/Bianca/Downloads/sarah-bot";
		Bot bot = new Bot("sarah-bot", path);
		Chat chat = new Chat(bot);
		
		input = input.replaceAll("\\?", " 999 ");
		input = input.replaceAll("[!\\.]", " ");
		
		input = chat.multisentenceRespond(input);
		
		if (input.contains("V>")){
			String[] output = input.split("V>");
			return output[1];	
		}
		
		return input;	
	}
}