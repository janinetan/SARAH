package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Treatment;


public class TreatmentDAO {
	private Connection con;
	
	public TreatmentDAO(){
		con = DBConnection.getConnection();
	}
	
	public ArrayList<Treatment> getTreatmentBySicknessId(int id){
		ArrayList<Treatment> treatments = new ArrayList<Treatment>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Treatment.TABLE_TREATMENT + 
														" WHERE " + Treatment.COL_SICKNESSID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Treatment treatment = new Treatment();
				treatment.setId(rs.getInt(Treatment.COL_ID));
				treatment.setName(rs.getString(Treatment.COL_NAME));
				treatment.setSicknessId(rs.getInt(Treatment.COL_SICKNESSID));
				treatments.add(treatment);
			}
			return treatments;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
