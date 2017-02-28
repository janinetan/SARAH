package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Action;
import Models.DiscourseAct;

public class DiscourseActDAO {
	private Connection con;
	
	public DiscourseActDAO(){
		con = DBConnection.getConnection();
	}
	
	public DiscourseAct getDiscourseActByName(String name){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + DiscourseAct.TABLE_DISCOURSEACT + 
														" WHERE " + DiscourseAct.COL_NAME + " = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				DiscourseAct discourseAct = new DiscourseAct();
				discourseAct.setId(rs.getInt(DiscourseAct.COL_ID));
				discourseAct.setName(rs.getString(DiscourseAct.COL_NAME));
				discourseAct.setKeyword(rs.getString(DiscourseAct.COL_KEYWORD));
				return discourseAct;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public DiscourseAct getDiscourseActById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + DiscourseAct.TABLE_DISCOURSEACT + 
														" WHERE " + DiscourseAct.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				DiscourseAct discourseAct = new DiscourseAct();
				discourseAct.setId(rs.getInt(DiscourseAct.COL_ID));
				discourseAct.setName(rs.getString(DiscourseAct.COL_NAME));
				discourseAct.setKeyword(rs.getString(DiscourseAct.COL_KEYWORD));
				return discourseAct;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public DiscourseAct getDiscourseActByKeyword(String keyword){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + DiscourseAct.TABLE_DISCOURSEACT + 
														" WHERE " + DiscourseAct.COL_KEYWORD + " = ?");
			ps.setString(1, keyword);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				DiscourseAct discourseAct = new DiscourseAct();
				discourseAct.setId(rs.getInt(DiscourseAct.COL_ID));
				discourseAct.setName(rs.getString(DiscourseAct.COL_NAME));
				discourseAct.setKeyword(rs.getString(DiscourseAct.COL_KEYWORD));
				return discourseAct;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
