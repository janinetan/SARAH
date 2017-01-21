package DAO;

import java.sql.Connection;

public class SicknessDAO {
	private Connection con;
	
	public SicknessDAO(){
		con = DBConnection.getConnection();
	}
}
