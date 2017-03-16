package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Cause;
import Models.Prevention;

public class CauseDAO {
	private Connection con;
	
	public CauseDAO(){
		con = DBConnection.getConnection();
	}
	
	public ArrayList<String> get5CausesBySicknessId(int id){
		ArrayList<String> causesList = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Cause.TABLE_CAUSE + 
														" WHERE " + Cause.COL_SICKNESSID + " = ?" +
														" ORDER BY RAND() " +
														" LIMIT 5 ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Cause cause = new Cause();
				cause.setId(rs.getInt(Cause.COL_ID));
				cause.setName(rs.getString(Cause.COL_NAME));
				cause.setSicknessId(rs.getInt(Cause.COL_SICKNESSID));
				causesList.add(cause.getName());
			}
			return causesList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
