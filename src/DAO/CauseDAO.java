package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Cause;
import Models.EpisodeSet;

public class CauseDAO {
	private Connection con;
	
	public CauseDAO(){
		con = DBConnection.getConnection();
	}
	
	public ArrayList<Cause> getCauseBySicknessId(int id){
		ArrayList<Cause> causes = new ArrayList<Cause>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Cause.TABLE_CAUSE + 
														" WHERE " + Cause.COL_SICKNESSID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Cause cause = new Cause();
				cause.setId(rs.getInt(Cause.COL_ID));
				cause.setName(rs.getString(Cause.COL_NAME));
				cause.setSicknessId(rs.getInt(Cause.COL_SICKNESSID));
				causes.add(cause);
			}
			return causes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
