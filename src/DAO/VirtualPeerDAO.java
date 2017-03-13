package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Semantic;
import Models.VirtualPeer;

public class VirtualPeerDAO {
	private Connection con;
	
	public VirtualPeerDAO(){
		con = DBConnection.getConnection();
	}
	
	public ArrayList<VirtualPeer> getAllVirtualPeers(){
		ArrayList<VirtualPeer> vpList = new ArrayList<VirtualPeer>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + VirtualPeer.TABLE_VIRTUALPEER );
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				VirtualPeer peer = new VirtualPeer();
				peer.setEmotionalState(rs.getString(VirtualPeer.COL_EMOTIONALSTATE));
				peer.setId(rs.getInt(VirtualPeer.COL_ID));
				peer.setImageFilePath(rs.getString(VirtualPeer.COL_IMAGEFILEPATH));
				peer.setName(rs.getString(VirtualPeer.COL_NAME));
				peer.setSick(rs.getBoolean(VirtualPeer.COL_ISSICK));
				vpList.add(peer);
			}
			return vpList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
