package Models;

public class Sentence {
	public static final String TABLE_SENTENCE = "sentence";
	
	public static final String COL_TAG = "tag";
	public static final String COL_MESSAGE = "message";
	
	private String tag;
	private String message;
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
