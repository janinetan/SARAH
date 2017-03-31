package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Assertion;
import Models.BodyPart;

public class AssertionDAO {
	private Connection con;
	
	public AssertionDAO() {
		con = DBConnection.getConnection();
	}
	
	public Assertion getAssertionById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Assertion.TABLE_ASSERTION + 
														" WHERE " + Assertion.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Assertion assertion = new Assertion();
				assertion.setId(rs.getInt(Assertion.COL_ID));
				assertion.setTag(rs.getString(Assertion.COL_TAG));
				assertion.setValue(rs.getString(Assertion.COL_VALUE));
				return assertion;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
