package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Episode;
import Models.EpisodeGoal;

public class EpisodeGoalDAO {
	private Connection con;
	
	public EpisodeGoalDAO(){
		con = DBConnection.getConnection();
	}
	
	public EpisodeGoal getEpisodeGoalById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + EpisodeGoal.TABLE_EPISODEGOAL + 
														" WHERE " + EpisodeGoal.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				EpisodeGoal episodeGoal = new EpisodeGoal();
				episodeGoal.setId(rs.getInt(episodeGoal.COL_ID));
				episodeGoal.setName(rs.getString(episodeGoal.COL_NAME));
				return episodeGoal;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
