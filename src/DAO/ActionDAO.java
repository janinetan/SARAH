package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Action;
import Models.Assertion;
import Models.BodyPart;
import Models.Object;

public class ActionDAO {
	private Connection con;
	
	public ActionDAO() {
		con = DBConnection.getConnection();
	}
	
	public ArrayList<Integer> getFirstAction(ArrayList<Integer> goodAssertions, String location){
		ArrayList<Integer> actionIdsList = new ArrayList<Integer>();
		String number = "";
		for (int i = 0; i < goodAssertions.size(); i++){
			number += goodAssertions.get(i);
			if (i != goodAssertions.size() - 1)
				number += ",";
		}
		
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT possible.id FROM (SELECT * FROM ( " +
					" SELECT a.id, a.activity_name, a.object_category, a.duration, p.assertion_id " +
					" FROM sarah_kb.action a " +
					" LEFT JOIN sarah_kb.precondition p " + 
					" ON p.action_id = a.id " + 
				" ) k " +
				" WHERE k.assertion_id IN ( " + number + " ) OR k.assertion_id IS NULL) possible, location, postcondition WHERE location = ? And possible.id = location.action_id AND possible.id = postcondition.action_id " +
				" GROUP BY possible.id ") ;
				
			ps.setString(1, location);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Action action = new Action();
				action.setId(rs.getInt(Action.COL_ID));
				actionIdsList.add(action.getId());
			}
			return actionIdsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Action setActionDetails(int id){
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT * FROM action where id = ?" ) ;
				
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				Action action = new Action();
				action.setId(rs.getInt(Action.COL_ID));
				action.setActivityName(rs.getString(Action.COL_ACTIVITYNAME));
				action.setObjectCategory(rs.getString(Action.COL_OBJECT));
				action.setDuration(rs.getString(Action.COL_DURATION));
				action.setPrecondition(getAllPrecondition(action.getId()));
				action.setMotivation(getMotivations(action.getActivityName()));
				action.setPostcondition(getAllPostcondition(action.getId()));
				action.setObectList(getObjects(action.getObjectCategory()));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Object> getObjects (String category){
		ArrayList<Object> objectList = new ArrayList<Object>();
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT * FROM object WHERE category = ?" ) ;
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Object object = new Object();
				object.setId(rs.getInt("id"));
				object.setCategory(rs.getString("category"));
				object.setName(rs.getString("name"));
				object.setVerb(rs.getString("verb"));
				objectList.add(object);
			}
			return objectList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getMotivations(String action){
		ArrayList<String> motivations = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT motivation FROM activity WHERE name = ?" ) ;
			ps.setString(1, action);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				motivations.add(rs.getString("motivation"));
			}
			return motivations;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getAllPrecondition(int id){
		ArrayList<Integer> preconditions = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT assertion_id FROM precondition WHERE action_id = ?" ) ;
				
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				preconditions.add(rs.getInt("assertion_id"));
			}
			return preconditions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getAllPostcondition(int id){
		ArrayList<Integer> postconditions = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT assertion_id FROM postcondition WHERE action_id = ?" ) ;
				
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				postconditions.add(rs.getInt("assertion_id"));
			}
			return postconditions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}