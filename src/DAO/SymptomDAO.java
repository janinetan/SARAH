package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Symptom;
import Models.Symptom;


public class SymptomDAO {
	private Connection con;
	
	public SymptomDAO(){
		con = DBConnection.getConnection();
	}
	
	public Symptom getSymptomById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Symptom.TABLE_SYMPTOM + 
														" WHERE " + Symptom.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Symptom symptom = new Symptom();
				symptom.setId(rs.getInt(Symptom.COL_ID));
				symptom.setName(rs.getString(Symptom.COL_NAME));
				return symptom;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
