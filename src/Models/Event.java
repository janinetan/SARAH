package Models;

public class Event {
	public static final String TABLE_EVENT = "event";
	
	public static final String COL_ID = "id";
	public static final String COL_EVENTTYPE = "type";
	
	private int id;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventType() {
		return type;
	}
	public void setEventType(String type) {
		this.type = type;
	}
	
	
}
