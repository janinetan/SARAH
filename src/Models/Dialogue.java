package Models;

public class Dialogue {
	public static final String TABLE_DIALOGUE = "dialogue";
	
	public static final String COL_EVENTID = "eventId";
	public static final String COL_MESSAGE = "message";
	
	private int eventId;
	private String message;
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
