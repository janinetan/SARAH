package Models;

public class BodyPart {
	public static final String TABLE_BODYPART = "body_part";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_DESCRIPTION = "description";
	
	private int id;
	private String name;
	private String description;
	

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
