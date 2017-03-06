package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.BodyPart;

public class BodyPartDAO {
	private Connection con;
	
	public BodyPartDAO() {
		con = DBConnection.getConnection();
	}
	
	public BodyPart getBodyPartById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + BodyPart.TABLE_BODYPART + 
														" WHERE " + BodyPart.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				BodyPart bodyPart = new BodyPart();
				bodyPart.setDescription(rs.getString(BodyPart.COL_DESCRIPTION));
				bodyPart.setId(rs.getInt(BodyPart.COL_ID));
				bodyPart.setName(rs.getString(BodyPart.COL_NAME));
				return bodyPart;
			}
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
				return bodyPart;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
