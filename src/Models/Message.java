package Models;

public class Message {
	public static final String TABLE_MESSAGE = "message";
	
	public static final String COL_EVENTID = "id";
	public static final String COL_MESSAGE = "message";
	
	private int eventID;
	private String message;
	
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
	}
	
	
}
