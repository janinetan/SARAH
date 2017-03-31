package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Action;

public class SymptomMapDAO {
		private Connection con;
		
		public SymptomMapDAO() {
			con = DBConnection.getConnection();
		}
		
		public ArrayList<Integer> getAllActionsPossible(int id){
			ArrayList<Integer> actions = new ArrayList<Integer>();
			try {
				PreparedStatement ps = con.prepareStatement("SELECT action_id FROM sickness_map WHERE sickness_id = (SELECT * FROM symptom_map WHERE action_id = ? ORDER BY RAND() LIMIT 1)");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()){
					actions.add(rs.getInt("action_id"));
				}
				return actions;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;	
		}
}
