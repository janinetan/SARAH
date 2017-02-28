package Models;

public class Semantic {
	public static final String TABLE_SEMANTIC = "semantic";
	
	public static final String COL_ID = "id";
	public static final String COL_CONCEPT1 = "concept1";
	public static final String COL_CONCEPT2 = "concept2";
	public static final String COL_RULING = "ruling";
	
	private int id;
	private String concept1;
	private int concept2;
	private int ruling;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConcept1() {
		return concept1;
	}
	public void setConcept1(String concept1) {
		this.concept1 = concept1;
	}
	public int getConcept2() {
		return concept2;
	}
	public void setConcept2(int concept2) {
		this.concept2 = concept2;
	}
	public int getRuling() {
		return ruling;
	}
	public void setRuling(int ruling) {
		this.ruling = ruling;
	}
	
	
}
