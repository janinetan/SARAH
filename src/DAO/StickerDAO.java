package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.BodyPart;
import Models.Sticker;

public class StickerDAO {
	private Connection con;
	
	public StickerDAO(){
		con = DBConnection.getConnection();
	}
	
	public Sticker getStickerByName(String name){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Sticker.TABLE_STICKER + 
														" WHERE " + Sticker.COL_NAME + " = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Sticker action = new Sticker();
				action.setEventId(rs.getInt(Sticker.COL_EVENTID));
				action.setName(rs.getString(Sticker.COL_NAME));
				action.setObjectId(rs.getInt(Sticker.COL_OBJECTID));
				action.setDuration(rs.getInt(Sticker.COL_DURATION));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Sticker getStickerByEventId(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Sticker.TABLE_STICKER + 
														" WHERE " + Sticker.COL_EVENTID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Sticker action = new Sticker();
				action.setEventId(rs.getInt(Sticker.COL_EVENTID));
				action.setName(rs.getString(Sticker.COL_NAME));
				action.setObjectId(rs.getInt(Sticker.COL_OBJECTID));
				action.setDuration(rs.getInt(Sticker.COL_DURATION));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
