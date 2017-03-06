package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Cause;

public class CauseDAO {
	private Connection con;
	
	public CauseDAO(){
		con = DBConnection.getConnection();
	}
	
	public Cause getCauseById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Cause.TABLE_CAUSE + 
														" WHERE " + Cause.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Cause cause = new Cause();
				cause.setId(rs.getInt(Cause.COL_ID));
				cause.setName(rs.getString(Cause.COL_NAME));
				return cause;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
