package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Object;

public class ObjectDAO {
	private Connection con;
	
	public ObjectDAO(){
		con = DBConnection.getConnection();
	}
	
	public Object getObjectByName(String name){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Object.TABLE_OBJECT + 
														" WHERE " + Object.COL_NAME + " = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Object object = new Object();
				object.setId(rs.getInt(Object.COL_ID));
				object.setName(rs.getString(Object.COL_NAME));
				object.setImageFilePath(rs.getString(Object.COL_IMAGEFILEPATH));
				return object;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getObjectById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Object.TABLE_OBJECT + 
														" WHERE " + Object.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Object object = new Object();
				object.setId(rs.getInt(Object.COL_ID));
				object.setName(rs.getString(Object.COL_NAME));
				object.setImageFilePath(rs.getString(Object.COL_IMAGEFILEPATH));
				return object;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
