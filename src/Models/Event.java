package Models;

public class Event {
	public static final String TABLE_EVENT = "event";
	
	public static final String COL_ID = "id";
	public static final String COL_RULING = "ruling";
	public static final String COL_EVENTTYPE = "type";
	
	public static final int RULING_BAD = 1;
	public static final int RULING_GOOD = 2;
	
	public static final int TYPE_ACTION = 0;
	public static final int TYPE_MESSAGE = 1;
	
	private int id;
	private int ruling;
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRuling() {
		return ruling;
	}
	public void setRuling(int ruling) {
		this.ruling = ruling;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		String eventString = "";
		eventString += "id: " + this.id + "\n";
		eventString += "ruling: " + this.ruling + "\n";
		eventString += "type: " + this.type + "\n";
		return eventString;
	}
}
