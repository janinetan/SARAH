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
	private String filename;
	private String connector1;
	private String connector2;
	private String connector3;
	private String connector4;
	private String connector5;
	
	public int getId() {
		return id;
	}
	public String getConnector1() {
		return connector1;
	}
	public void setConnector1(String connector1) {
		this.connector1 = connector1;
	}
	public String getConnector2() {
		return connector2;
	}
	public void setConnector2(String connector2) {
		this.connector2 = connector2;
	}
	public String getConnector3() {
		return connector3;
	}
	public void setConnector3(String connector3) {
		this.connector3 = connector3;
	}
	public String getConnector4() {
		return connector4;
	}
	public void setConnector4(String connector4) {
		this.connector4 = connector4;
	}
	public String getConnector5() {
		return connector5;
	}
	public void setConnector5(String connector5) {
		this.connector5 = connector5;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
