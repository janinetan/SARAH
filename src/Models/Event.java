package Models;

public class Event {
	public static final String TABLE_EVENT = "event";
	
	public static final String COL_ID = "id";
	public static final String COL_VIRTUALAGENTID = "virtualAgentId";
	public static final String COL_EVENTTYPE = "eventType";
	
	private int id;
	private int virtualAgentId;
	private String eventType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVirtualAgentId() {
		return virtualAgentId;
	}
	public void setVirtualAgentId(int virtualAgentId) {
		this.virtualAgentId = virtualAgentId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
}
