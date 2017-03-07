package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.EpisodeGoal;
import Models.EpisodeSet;

public class EpisodeSetDAO {
	private Connection con;
	
	public EpisodeSetDAO(){
		con = DBConnection.getConnection();
	}
	
	public EpisodeSet getRandomEpisodeSet(){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + EpisodeSet.TABLE_EPISODESET +
															" ORDER BY RAND() " + 
															" LIMIT 1 "	);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				EpisodeSet episodeSet = new EpisodeSet();
				episodeSet.setId(rs.getInt(EpisodeSet.COL_ID));
				episodeSet.setEpisodesId(rs.getString(EpisodeSet.COL_EPISODESID));
				return episodeSet;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
