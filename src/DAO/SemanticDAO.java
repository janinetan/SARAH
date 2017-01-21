package DAO;

import java.sql.Connection;

public class SemanticDAO {
	private Connection con;
	
	public SemanticDAO(){
		con =  DBConnection.getConnection();
	}
}
