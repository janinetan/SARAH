package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Sickness;

public class SicknessDAO {
	private Connection con;
	
	public SicknessDAO(){
		con = DBConnection.getConnection();
	}
	
	public Sickness getSicknessByName(String name){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Sickness.TABLE_SICKNESS + 
														" WHERE " + Sickness.COL_NAME + " = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Sickness sickness = new Sickness();
				sickness.setId(rs.getInt(Sickness.COL_ID));
				sickness.setBodyPartId(rs.getInt(Sickness.COL_BODYPARTID));
				sickness.setCauses(rs.getString(Sickness.COL_CAUSES));
				sickness.setDefinition(rs.getString(Sickness.COL_DEFINITION));
				sickness.setName(rs.getString(Sickness.COL_NAME));
				sickness.setPrevention(rs.getString(Sickness.COL_PREVENTION));
				sickness.setSymptoms(rs.getString(Sickness.COL_SYMPTOMS));
				sickness.setTreatment(rs.getString(Sickness.COL_TREATMENT));
				return sickness;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
