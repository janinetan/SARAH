package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Message;


public class MessageDAO {
	private Connection con;
	
	public MessageDAO(){
		con = DBConnection.getConnection();
	}
	
	public Message getMessageById(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Message.TABLE_MESSAGE + 
														" WHERE " + Message.COL_EVENTID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Message message = new Message();
				message.setEventID(rs.getInt(Message.COL_EVENTID));
				message.setMessage(rs.getString(Message.COL_MESSAGE));
				return message;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
