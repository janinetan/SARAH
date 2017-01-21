package DAO;

import java.sql.Connection;

public class ResponseDAO {
	private Connection con;
	
	public ResponseDAO(){
		con = DBConnection.getConnection();
	}
}
