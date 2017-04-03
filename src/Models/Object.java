package Models;

public class Object {
	public static final String TABLE_OBJECT = "object";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_CATEGORY = "category";
	public static final String COL_VERB = "verb";
	
	private int id;
	private String name;
	private String category;
	private String verb;
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(String verb) {
		this.verb = verb;
	}
	
	
}
