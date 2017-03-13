package Models;

import java.util.ArrayList;

public class Episode {
	public static final String TABLE_EPISODE = "episode";
	
	public static final String COL_ID = "id";
	public static final String COL_EPISODEGOALID = "episodeGoalId";
	public static final String COL_EVENTID = "eventId";
	
	private int id;
	private int episodeGoalId;
	private ArrayList<Integer> eventsId;
	private int discourseActId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getEpisodeGoalId() {
		return episodeGoalId;
	}
	
	public void setEpisodeGoalId(int episodeGoalId) {
		this.episodeGoalId = episodeGoalId;
	}
	
	public ArrayList<Integer> getEventsId() {
		return eventsId;
	}
	
	public void setEventsId(String eventId) {
		this.eventsId = new ArrayList<Integer>();
		if (eventId != null){
			String[] temp = eventId.split(",");
			for (String tempString : temp){
				try{
					int t = Integer.parseInt(tempString);
					this.eventsId.add(Integer.parseInt(tempString));
				}
				catch (NumberFormatException e){
					tempString = tempString.replaceAll("\\D+","");
					this.setDiscourseActId(Integer.parseInt(tempString));
				}
			}
		}
		
	}
	
	public void setDiscourseActId(int discourseActId) {
		this.discourseActId = discourseActId;
	}
	
	public int getDiscourseActId() {
		return discourseActId;
	}
	
	@Override
	public String toString() {
		String episodeString = "";
		episodeString += "id: " + this.id + "\n";
		episodeString += "episodeGoalId: " + this.episodeGoalId + "\n";
		episodeString += "eventId: " + this.eventsId + "\n";
		episodeString += "discourseActId: " + this.discourseActId + "\n";
		return episodeString;
	}
	
	
}
