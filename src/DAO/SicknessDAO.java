package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Sickness;

public class SicknessDAO {
	private Connection con;
	
	public SicknessDAO(){
		con = DBConnection.getConnection();
	}
	
	public Sickness getSicknessWithId( int id ){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Sickness.TABLE_SICKNESS +
															" WHERE " + Sickness.COL_ID + " = ?" );
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Sickness sickness = new Sickness();
				sickness.setId(rs.getInt(Sickness.COL_ID));
				sickness.setName(rs.getString(Sickness.COL_NAME));
				return sickness;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
				sickness.setName(rs.getString(Sickness.COL_NAME));
				return sickness;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
