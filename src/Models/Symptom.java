package Models;

public class Symptom {
	public static final String TABLE_SYMPTOM = "symptom";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_SICKNESSID = "sicknessId";
	
	private int id;
	private String name;
	private int sicknessId;
	
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
	public int getSicknessId() {
		return sicknessId;
	}
	public void setSicknessId(int sicknessId) {
		this.sicknessId = sicknessId;
	}
	
	
}
