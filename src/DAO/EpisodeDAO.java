package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.DiscourseAct;
import Models.Episode;

public class EpisodeDAO {
	private Connection con;
	
	public EpisodeDAO(){
		con = DBConnection.getConnection();
	}
	
	public Episode getEpisodeByEventId(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Episode.TABLE_EPISODE + 
														" WHERE " + Episode.COL_EVENTID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Episode episode = new Episode();
				episode.setEpisodeGoalId(rs.getInt(Episode.COL_EPISODEGOALID));
				episode.setEventId(rs.getString(Episode.COL_EVENTID));
				episode.setId(rs.getInt(Episode.COL_ID));
				return episode;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
