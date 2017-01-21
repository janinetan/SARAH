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
	
	public ArrayList<EpisodeSet> getEpisodeSetBySicknessId(int id){
		ArrayList<EpisodeSet> EpisodeSets = new ArrayList<EpisodeSet>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + EpisodeSet.TABLE_EPISODESET + 
														" WHERE " + EpisodeSet.COL_SICKENESSID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				EpisodeSet episodeSet = new EpisodeSet();
				episodeSet.setEpisodeId(rs.getInt(EpisodeSet.COL_EPISODEID));
				episodeSet.setId(rs.getInt(EpisodeSet.COL_ID));
				episodeSet.setSicknessId(rs.getInt(EpisodeSet.COL_SICKENESSID));
				EpisodeSets.add(episodeSet);
			}
			return EpisodeSets;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
