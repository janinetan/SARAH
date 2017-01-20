package Models;

public class EpisodeSet {
	public static final String TABLE_EPISODESET = "episode_set";
	
	public static final String COL_ID = "id";
	public static final String COL_SICKENESSID = "sicknessId";
	public static final String COL_EPISODEID = "episodeId";
	
	private int id;
	private int sicknessId;
	private int episodeId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSicknessId() {
		return sicknessId;
	}
	public void setSicknessId(int sicknessId) {
		this.sicknessId = sicknessId;
	}
	public int getEpisodeId() {
		return episodeId;
	}
	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}
	
	
}
