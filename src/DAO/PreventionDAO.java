package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Prevention;
import Models.Prevention;


public class PreventionDAO {
	private Connection con;
	
	public PreventionDAO(){
		con = DBConnection.getConnection();
	}
	
	public Prevention getPreventionById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Prevention.TABLE_PREVENTION + 
														" WHERE " + Prevention.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Prevention prevention = new Prevention();
				prevention.setId(rs.getInt(Prevention.COL_ID));
				prevention.setName(rs.getString(Prevention.COL_NAME));
				return prevention;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
