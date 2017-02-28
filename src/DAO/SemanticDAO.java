package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Semantic;

public class SemanticDAO {
	private Connection con;
	
	public SemanticDAO(){
		con =  DBConnection.getConnection();
	}
	
	public Semantic getSemanticByConcept1(String concept){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Semantic.TABLE_SEMANTIC + 
														" WHERE " + Semantic.COL_CONCEPT1 + " = ?");
			ps.setString(1, concept);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Semantic semantic = new Semantic();
				semantic.setConcept1(rs.getString(Semantic.COL_CONCEPT1));
				semantic.setConcept2(rs.getInt(Semantic.COL_CONCEPT2));
				semantic.setId(rs.getInt(Semantic.COL_ID));
				semantic.setRuling(rs.getInt(Semantic.COL_RULING));
				return semantic;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
