package Models;

import java.util.ArrayList;

public class Action extends Event {
	public static final String TABLE_ACTION = "action";
	
	public static final String COL_ID = "id";
	public static final String COL_ACTIVITYNAME = "activity_name";
	public static final String COL_OBJECT = "object_category";
	public static final String COL_DURATION = "duration";
	
	private int id;
	private String activityName;
	private String objectCategory;
	private String duration;
	private ArrayList<Integer> precondition;
	private ArrayList<String> locations;
	private ArrayList<String> motivation;
	private ArrayList<Integer> postcondition;
	private ArrayList<Object> objectList;
	private Object chosenObject;
	private ArrayList<String> symptomList;
	
	public ArrayList<String> getSymptomList() {
		return symptomList;
	}
	public void setSymptomList(ArrayList<String> symptomList) {
		this.symptomList = symptomList;
	}
	public ArrayList<Integer> getPrecondition() {
		return precondition;
	}
	public void setPrecondition(ArrayList<Integer> precondition) {
		this.precondition = precondition;
	}
	public ArrayList<String> getLocations() {
		return locations;
	}
	public void setLocations(ArrayList<String> locations) {
		this.locations = locations;
	}
	public ArrayList<String> getMotivation() {
		return motivation;
	}
	public void setMotivation(ArrayList<String> motivation) {
		this.motivation = motivation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getObjectCategory() {
		return objectCategory;
	}
	public void setObjectCategory(String objectCategory) {
		this.objectCategory = objectCategory;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public ArrayList<Integer> getPostcondition() {
		return postcondition;
	}
	public void setPostcondition(ArrayList<Integer> postconditions){
		this.postcondition = postconditions;
	}
	public ArrayList<Object> getObectList() {
		return objectList;
	}
	public void setObjectList(ArrayList<Object> objectList) {
		this.objectList = objectList;
	}
	public Object getChosenObject() {
		return chosenObject;
	}
	public void setChosenObject(Object chosenObject) {
		this.chosenObject = chosenObject;
	}
	
	@Override
	public String toString() {
		String actionText = "activity name: " + this.activityName + "\n";
		actionText += "chosen object: " + this.chosenObject.getName(); 
		return actionText;
	}
}
