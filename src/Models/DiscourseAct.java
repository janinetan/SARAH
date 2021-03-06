package Models;

public class DiscourseAct {
	public static final String TABLE_DISCOURSEACT = "discourse_act";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_KEYWORD = "keyword";
	
	private int id;
	private String name;
	private String keyword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
