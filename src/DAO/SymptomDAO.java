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
	
	public Symptom getRandomSicknessIdWithSymptom( String name ){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Symptom.TABLE_SYMPTOM +
															" WHERE " + Symptom.COL_NAME + " = ? " + 
															" ORDER BY RAND() " + 
															" LIMIT 1 ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				Symptom symptom = new Symptom();
				symptom.setId(rs.getInt(Symptom.COL_ID));
				symptom.setName(rs.getString(Symptom.COL_NAME));
				symptom.setSicknessId(rs.getInt(Symptom.COL_SICKNESSID));
				return symptom;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getSymptomsBySicknessId(int id){
		ArrayList<String> symptomsList = new ArrayList<String>();
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
				symptomsList.add(symptom.getName());
			}
			return symptomsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getAllSicknessIdWithSymptom( String name ){
		ArrayList<Integer> sicknessIdList=  new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT sicknessId FROM " + Symptom.TABLE_SYMPTOM +
															" WHERE " + Symptom.COL_NAME + " = ? ");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				sicknessIdList.add(rs.getInt(Symptom.COL_SICKNESSID));
			}
			return sicknessIdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
