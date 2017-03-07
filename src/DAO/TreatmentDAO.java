package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Treatment;
import Models.Treatment;


public class TreatmentDAO {
	private Connection con;
	
	public TreatmentDAO(){
		con = DBConnection.getConnection();
	}
	
	public Treatment getTreatmentById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Treatment.TABLE_TREATMENT + 
														" WHERE " + Treatment.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Treatment treatment = new Treatment();
				treatment.setId(rs.getInt(Treatment.COL_ID));
				treatment.setName(rs.getString(Treatment.COL_NAME));
				treatment.setSicknessId(rs.getInt(Treatment.COL_SICKNESSID));
				return treatment;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
