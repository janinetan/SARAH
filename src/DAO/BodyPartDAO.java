package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.BodyPart;
import Models.Prevention;

public class BodyPartDAO {
	private Connection con;
	
	public BodyPartDAO() {
		con = DBConnection.getConnection();
	}
	
	public ArrayList<BodyPart> getBodyPartsBySicknessId(int id){
		ArrayList<BodyPart> bodyPartsList = new ArrayList<BodyPart>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + BodyPart.TABLE_BODYPART + 
														" WHERE " + BodyPart.COL_SICKNESSID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				BodyPart bodyPart = new BodyPart();
				bodyPart.setDescription(rs.getString(BodyPart.COL_DESCRIPTION));
				bodyPart.setId(rs.getInt(BodyPart.COL_ID));
				bodyPart.setName(rs.getString(BodyPart.COL_NAME));
				bodyPart.setSicknessId(rs.getInt(BodyPart.COL_SICKNESSID));
				bodyPart.setUse(rs.getString(BodyPart.COL_USE));
				bodyPartsList.add(bodyPart);
			}
			return bodyPartsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public BodyPart getBodyPartByName(String name){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + BodyPart.TABLE_BODYPART + 
														" WHERE " + BodyPart.COL_NAME + " = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				BodyPart bodyPart = new BodyPart();
				bodyPart.setDescription(rs.getString(BodyPart.COL_DESCRIPTION));
				bodyPart.setId(rs.getInt(BodyPart.COL_ID));
				bodyPart.setName(rs.getString(BodyPart.COL_NAME));
				bodyPart.setSicknessId(rs.getInt(BodyPart.COL_SICKNESSID));
				return bodyPart;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//exact description for this one change if necessary
	public BodyPart getBodyPartByDesciption(String description){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + BodyPart.TABLE_BODYPART + 
														" WHERE " + BodyPart.COL_DESCRIPTION + " = ?");
			ps.setString(1, description);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				BodyPart bodyPart = new BodyPart();
				bodyPart.setDescription(rs.getString(BodyPart.COL_DESCRIPTION));
				bodyPart.setId(rs.getInt(BodyPart.COL_ID));
				bodyPart.setName(rs.getString(BodyPart.COL_NAME));
				bodyPart.setSicknessId(rs.getInt(BodyPart.COL_SICKNESSID));
				return bodyPart;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
