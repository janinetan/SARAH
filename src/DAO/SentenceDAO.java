package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Sentence;



public class SentenceDAO {
	private Connection con;
	
	public SentenceDAO(){
		con = DBConnection.getConnection();
	}
	
	public Sentence getSentenceByTag(String tag){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Sentence.TABLE_SENTENCE + 
														" WHERE " + Sentence.COL_TAG + " = ?");
			ps.setString(1, tag);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Sentence sentence = new Sentence();
				sentence.setMessage(rs.getString(Sentence.COL_MESSAGE));
				sentence.setTag(rs.getString(Sentence.COL_TAG));
				return sentence;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
