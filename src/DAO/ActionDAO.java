package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Action;
import Models.BodyPart;

public class ActionDAO {
	private Connection con;
	
	public ActionDAO(){
		con = DBConnection.getConnection();
	}
	
	public Action getActionByName(String name){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Action.TABLE_ACTION + 
														" WHERE " + Action.COL_NAME + " = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Action action = new Action();
				action.setEventId(rs.getInt(Action.COL_EVENTID));
				action.setName(rs.getString(Action.COL_NAME));
				action.setObjectId(rs.getInt(Action.COL_OBJECTID));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Action getActionByEventId(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Action.TABLE_ACTION + 
														" WHERE " + Action.COL_EVENTID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Action action = new Action();
				action.setEventId(rs.getInt(Action.COL_EVENTID));
				action.setName(rs.getString(Action.COL_NAME));
				action.setObjectId(rs.getInt(Action.COL_OBJECTID));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
