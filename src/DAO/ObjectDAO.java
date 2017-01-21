package DAO;

import java.sql.Connection;

public class ObjectDAO {
	private Connection con;
	
	public ObjectDAO(){
		con = DBConnection.getConnection();
	}
}
