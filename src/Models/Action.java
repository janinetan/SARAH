package Models;

import java.util.ArrayList;

public class Action extends Event {
	public static final String TABLE_ACTION = "action";
	
	public static final String COL_ID = "id";
	public static final String COL_ACTIVITYNAME = "activty_name";
	public static final String COL_NAME = "name";
	public static final String COL_OBJECT = "object_category";
	public static final String COL_DURATION = "duration";
	public static final String COL_EPISODEID = "episode_id";
	
	private int id;
	private String activityName;
	private String name;
	private String objectCategory;
	private String duration;
	private String episodeId;
	private ArrayList<Integer> precondition;
	private ArrayList<String> locations;
	private ArrayList<String> motivation;
	private ArrayList<Integer> postcondition;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEpisodeId() {
		return episodeId;
	}
	public void setEpisodeId(String episodeId) {
		this.episodeId = episodeId;
	}
	
	
}
