package Models;

public class Sickness {
	public static final String TABLE_SICKNESS = "sickness";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_DEFINITION = "definition";
	public static final String COL_SYMPTOMS = "symptoms";
	public static final String COL_CAUSES = "causes";
	public static final String COL_TREATMENT = "treatment";
	public static final String COL_PREVENTION = "prevention";
	public static final String COL_BODYPARTID = "bodyPartId";
	
	private int id;
	private String name;
	private String definition;
	private String symptoms;
	private String causes;
	private String treatment;
	private String prevention;
	private int bodyPartId;
	
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
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getCauses() {
		return causes;
	}
	public void setCauses(String causes) {
		this.causes = causes;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getPrevention() {
		return prevention;
	}
	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}
	public int getBodyPartId() {
		return bodyPartId;
	}
	public void setBodyPartId(int bodyPartId) {
		this.bodyPartId = bodyPartId;
	}
	
	
}
