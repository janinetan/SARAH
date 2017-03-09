package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Event;


public class EventDAO {
	private Connection con;
	
	public EventDAO(){
		con = DBConnection.getConnection();
	}
	
	public Event getEventById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Event.TABLE_EVENT + 
														" WHERE " + Event.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Event event = new Event();
				event.setId(rs.getInt(Event.COL_ID));
//				event.setEventType(rs.getString(Event.COL_EVENTTYPE));
				return event;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
