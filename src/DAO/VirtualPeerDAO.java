package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Semantic;
import Models.VirtualPeer;

public class VirtualPeerDAO {
	private Connection con;
	
	public VirtualPeerDAO(){
		con = DBConnection.getConnection();
	}
	
	public VirtualPeer getVirtualPeerByName(String name){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + VirtualPeer.TABLE_VIRTUALPEER + 
														" WHERE " + VirtualPeer.COL_NAME + " = ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				VirtualPeer Peer = new VirtualPeer();
				Peer.setEmotionalState(rs.getString(VirtualPeer.COL_EMOTIONALSTATE));
				Peer.setId(rs.getInt(VirtualPeer.COL_ID));
				Peer.setImageFilePath(rs.getString(VirtualPeer.COL_IMAGEFILEPATH));
				Peer.setName(rs.getString(VirtualPeer.COL_NAME));
				Peer.setSick(rs.getBoolean(VirtualPeer.COL_ISSICK));
				return Peer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
