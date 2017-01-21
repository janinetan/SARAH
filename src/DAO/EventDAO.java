package DAO;

import java.sql.Connection;

public class EventDAO {
	private Connection con;
	
	public EventDAO(){
		con = DBConnection.getConnection();
	}
	
}
