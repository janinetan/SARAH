package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Prevention;
import Models.Treatment;
import Models.Prevention;


public class PreventionDAO {
	private Connection con;
	
	public PreventionDAO(){
		con = DBConnection.getConnection();
	}
	
	public ArrayList<String> get5PreventionBySicknessId(int id){
		ArrayList<String> preventionsList = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Prevention.TABLE_PREVENTION + 
														" WHERE " + Prevention.COL_SICKNESSID + " = ?" +
														" ORDER BY RAND() " +
														" LIMIT 5 ");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Prevention prevention = new Prevention();
				prevention.setId(rs.getInt(Prevention.COL_ID));
				prevention.setName(rs.getString(Prevention.COL_NAME));
				prevention.setSicknessId(rs.getInt(Prevention.COL_SICKNESSID));
				preventionsList.add(prevention.getName());
			}	
			return preventionsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
