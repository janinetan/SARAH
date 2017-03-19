package Models;

public class Action extends Event{
	public static final String TABLE_ACTION = "action";
	
	public static final String COL_EVENTID = "eventId";
	public static final String COL_NAME = "name";
	public static final String COL_OBJECTID = "objectId";
	public static final String COL_DURATION = "duration";
	
	private int eventId;
	private String name;
	private int objectId;
	private int duration;
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	
	
}

