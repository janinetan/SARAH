package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Action;
import Models.Dialogue;

public class DialogueDAO {
	private Connection con;
	
	public DialogueDAO(){
		con = DBConnection.getConnection();
	}
	
	public Dialogue getDialogueByEventId(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Dialogue.TABLE_DIALOGUE + 
														" WHERE " + Dialogue.COL_EVENTID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Dialogue dialogue = new Dialogue();
				dialogue.setEventId(rs.getInt(Dialogue.COL_EVENTID));
				dialogue.setMessage(rs.getString(Dialogue.COL_MESSAGE));
				return dialogue;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
