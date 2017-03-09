package Models;

import java.util.ArrayList;

public class EpisodeSet {
	public static final String TABLE_EPISODESET = "episode_set";
	
	public static final String COL_ID = "id";
	public static final String COL_EPISODESID = "episodesId";
	
	private int id;
	private ArrayList<Integer> episodesId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setEpisodesId(String episodesId){
		this.episodesId = new ArrayList<Integer>();
		if (episodesId != null){
			String[] temp = episodesId.split(",");
			for (String tempString : temp){
				this.episodesId.add(Integer.parseInt(tempString));
			}
		}
	}
	
	public ArrayList<Integer> getEpisodesId() {
		return episodesId;
	}
	
	@Override
	public String toString() {
		String episodeSetString = "";
		episodeSetString += "id: " + this.id + "\n";
		episodeSetString += "episodesId: " + this.episodesId + "\n";
		return episodeSetString;
	}
	
	
}
