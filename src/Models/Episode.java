package Models;

public class Episode {
	public static final String TABLE_EPISODE = "episode";
	
	public static final String COL_ID = "id";
	public static final String COL_EPISODEGOALID = "episodeGoalId";
	public static final String COL_EVENTID = "eventId";
	
	private int id;
	private int episodeGoalId;
	private String eventId;
	
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
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
}
