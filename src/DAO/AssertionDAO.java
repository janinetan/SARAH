package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
				assertion.setConcept1(rs.getString(Assertion.COL_CONCEPT1));
				assertion.setConcept2(rs.getString(Assertion.COL_CONCEPT2));
				return assertion;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getHealthAssertions(String status){
		ArrayList<Integer> assertionsId = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Assertion.TABLE_ASSERTION + 
														" WHERE " + Assertion.COL_TAG + " = 'is' " +
														" AND " + Assertion.COL_CONCEPT2 + " = ? ");
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				PreparedStatement ps2 = con.prepareStatement("SELECT * FROM " + Assertion.TABLE_ASSERTION + 
										" WHERE " + Assertion.COL_TAG + " = 'hasProperty' " +
										" AND " + Assertion.COL_CONCEPT2 + " = ? ");
				ps2.setString(1, rs.getString(Assertion.COL_CONCEPT1));
				ResultSet rs2 = ps2.executeQuery();
				
				if (rs2.next()){
					Assertion assertion = new Assertion();
					assertion.setId(rs2.getInt(Assertion.COL_ID));
					assertion.setTag(rs2.getString(Assertion.COL_TAG));
					assertion.setConcept1(rs2.getString(Assertion.COL_CONCEPT1));
					assertion.setConcept2(rs2.getString(Assertion.COL_CONCEPT2));
					assertionsId.add(assertion.getId());
				}
			}
			return assertionsId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getOppsotiteAssertion(int id){
		try {
			PreparedStatement ps = con.prepareStatement(			
			" SELECT id " +
			" FROM assertion " + 
			" WHERE concept2 = (SELECT( " +
			    " CASE " +
			       " WHEN concept1 = (SELECT concept2 FROM assertion WHERE id = ?) THEN concept2 " +
			       " WHEN concept2 = (SELECT concept2 FROM assertion WHERE id = ?) THEN concept1 " +
			    " END) AS property "  +
				" FROM assertion "  +
				" WHERE (concept1 = (SELECT concept2 "  + 
								   " FROM assertion " + 
			                       " WHERE id = ?) OR " +
						" concept2 = (SELECT concept2 FROM assertion WHERE id = ?) " +
					   " ) " +
				" AND tag = 'oppositeOf')  AND tag = 'hasProperty' ");
				
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setInt(3, id);
			ps.setInt(4, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
