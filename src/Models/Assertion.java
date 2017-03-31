package Models;

public class Assertion {
	public static final String TABLE_ASSERTION = "assertion";
	
	public static final String COL_ID = "id";
	public static final String COL_TAG = "tag";
	public static final String COL_VALUE = "value";
	
	private int id;
	private String tag;
	private String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
