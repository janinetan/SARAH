package Models;

import java.util.ArrayList;

public class Message extends Event {
	public static final String TABLE_MESSAGE = "message";
	
	public static final String COL_EVENTID = "eventId";
	public static final String COL_MESSAGE = "message";
	
	private int eventID;
	private String message;
	private ArrayList<String> sentenceTags;
	
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
		this.setSentenceTags();
	}
	public ArrayList<String> getSentenceTags() {
		return sentenceTags;
	}
	public void setSentenceTags(){
		this.sentenceTags = new ArrayList<String>();
		if (this.message != null){
			String[] temp = this.message.split(" \\+ ");
			for (String tempString : temp){
				this.sentenceTags.add(tempString);
			}
		} 
	}
	
	@Override
	public String toString() {
		String messageString = "";
		messageString += "id: " + this.eventID + "\n";
		messageString += "message: " + this.message + "\n";
		return messageString;
	}
}
