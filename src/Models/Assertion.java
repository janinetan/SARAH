package Models;

public class Assertion {
	public static final String TABLE_ASSERTION = "assertion";
	
	public static final String COL_ID = "id";
	public static final String COL_TAG = "tag";
	public static final String COL_CONCEPT1 = "concept1";
	public static final String COL_CONCEPT2 = "concept2";
	
	public static final String TAG_HASPROPERTY = "hasProperty";
	public static final String TAG_IS = "is";
	public static final String TAG_OPPOSITEOF = "oppositeOf";
	
	private int id;
	private String tag;
	private String concept1;
	private String concept2;
	
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
	public String getConcept1() {
		return concept1;
	}
	public void setConcept1(String concept1) {
		this.concept1 = concept1;
	}
	public String getConcept2() {
		return concept2;
	}
	public void setConcept2(String concept2) {
		this.concept2 = concept2;
	}
}
