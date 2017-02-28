package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Symptom;


public class SymptomDAO {
	private Connection con;
	
	public SymptomDAO(){
		con = DBConnection.getConnection();
	}
	
	public ArrayList<Symptom> getSymptomBySicknessId(int id){
		ArrayList<Symptom> symptoms = new ArrayList<Symptom>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Symptom.TABLE_SYMPTOM + 
														" WHERE " + Symptom.COL_SICKNESSID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Symptom symptom = new Symptom();
				symptom.setId(rs.getInt(Symptom.COL_ID));
				symptom.setName(rs.getString(Symptom.COL_NAME));
				symptom.setSicknessId(rs.getInt(Symptom.COL_SICKNESSID));
				symptoms.add(symptom);
			}
			return symptoms;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
