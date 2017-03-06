package Models;

public class Symptom {
	public static final String TABLE_SYMPTOM = "symptom";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	
	private int id;
	private String name;
	
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
	
	
}
