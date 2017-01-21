package DAO;

import java.sql.Connection;

public class VirtualPeerDAO {
	private Connection con;
	
	public VirtualPeerDAO(){
		con = DBConnection.getConnection();
	}
}
