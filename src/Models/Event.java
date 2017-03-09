package Models;

public class Event {
	public static final String TABLE_EVENT = "event";
	
	public static final String COL_ID = "id";
	public static final String COL_RULING = "ruling";
	public static final String COL_EVENTTYPE = "type";
	
	private int id;
	private boolean isGood;
	private boolean isMessage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getIsGood (){
		return isGood;
	}
	public void setIsGood(int isGood) {
		this.isGood = 0 == isGood;
	}
//	public boolean getIsMessage(){
//		return isMessage;
//	}
//	public void setIsMessage{
//		this.isMessage = 
//	}
}
