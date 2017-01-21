package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Object;
import Models.Response;

public class ResponseDAO {
	private Connection con;
	
	public ResponseDAO(){
		con = DBConnection.getConnection();
	}
	
	public Response getResponseByDiscouseActId(int id){
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Response.TABLE_RESPONSE + 
														" WHERE " + Response.COL_DISCOURSEACTID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Response response = new Response();
				response.setDiscourseActId(rs.getInt(Response.COL_DISCOURSEACTID));
				response.setId(rs.getInt(Response.COL_ID));
				response.setMessage(rs.getString(Response.COL_MESSAGE));
				response.setResponseType(rs.getString(Response.COL_RESPONSETYPE));
				response.setVirtualAgentId(rs.getInt(Response.COL_VIRTUALAGENTID));
				return response;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
