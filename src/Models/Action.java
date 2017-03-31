package Models;

import java.util.ArrayList;

public class Action {
	public static final String TABLE_ACTION = "action";
	
	public static final String COL_ID = "id";
	public static final String COL_ASSERTIONID = "assertion_id";
	public static final String COL_NAME = "name";
	public static final String COL_OBJECT = "object";
	public static final String COL_DURATION = "duration";
	public static final String COL_MOTIVATION = "motivation";
	
	public static final String TABLE_ACTIVITY = "activity";
	public static final String COL_ACTIVITY = "activity";
	public static final String COL_ACTIONID = "action_id";
	
	public static final String TABLE_LOCATION = "location";
	public static final String COL_LOCATION = "location";
	
	public static final String TABLE_POSTCONDITION = "post_condition";
	
	public static final String TABLE_REVERSE ="reverse_precondition";
	public static final String COL_REVERSEACTION = "reverse_action_id";
	
	private int id;
	private int assertionId;
	private String name;
	private String object;
	private String duration;
	private String motivation;
	private ArrayList<String> activity;
	private ArrayList<String> location;
	private ArrayList<Integer> postCondition;
	private ArrayList<Integer> reversePrecondition;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAssertionId() {
		return assertionId;
	}
	public void setAssertionId(int assertionId) {
		this.assertionId = assertionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public ArrayList<String> getActivity() {
		return activity;
	}
	public void setActivity(ArrayList<String> activity) {
		this.activity = activity;
	}
	public ArrayList<String> getLocation() {
		return location;
	}
	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}
	public ArrayList<Integer> getPostCondition() {
		return postCondition;
	}
	public void setPostCondition(ArrayList<Integer> postCondition) {
		this.postCondition = postCondition;
	}
	public ArrayList<Integer> getReversePrecondition() {
		return reversePrecondition;
	}
	public void setReversePrecondition(ArrayList<Integer> reversePrecondition) {
		this.reversePrecondition = reversePrecondition;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
}
