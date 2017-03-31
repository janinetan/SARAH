package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Action;
import Models.BodyPart;

public class ActionDAO {
	private Connection con;
	
	public ActionDAO() {
		con = DBConnection.getConnection();
	}
	
	public Action getActionWithNoPrecondition(){
		Action action = new Action();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Action.TABLE_ACTION + 
														" WHERE " + Action.COL_ASSERTIONID + " = ?" + " ORDER BY RAND() " + 
														" LIMIT 1 ");
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				action.setId(rs.getInt(Action.COL_ID));
				action.setAssertionId(rs.getInt(Action.COL_ASSERTIONID));
				action.setName(rs.getString(Action.COL_NAME));
				action.setObject(rs.getString(Action.COL_OBJECT));
				action.setMotivation(rs.getString(Action.COL_MOTIVATION));
				action.setDuration(rs.getString(Action.COL_DURATION));
				action.setActivity(getActivitiesByActionId(rs.getInt(Action.COL_ID)));
				action.setLocation(getLocationByActionId(rs.getInt(Action.COL_ID)));
				action.setPostCondition(getPostConditionByActionId(rs.getInt(Action.COL_ID)));
				action.setReversePrecondition(getReversePreconditionByActionId(rs.getInt(Action.COL_ID)));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Action getActionByLocationandPrecondition(int assertionId, String location){
		Action action = new Action();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Action.TABLE_ACTION + 
														" WHERE " + Action.COL_ID + " IN "
														+ "(SELECT " + Action.COL_ACTIONID +" FROM "+ Action.TABLE_LOCATION +" WHERE "+ Action.COL_LOCATION +" = ?) "
														+ "AND "+ Action.COL_ASSERTIONID +" = ? AND "+ Action.COL_ID +" IN (SELECT DISTINCT " + Action.COL_ACTIONID +" FROM "+ Action.TABLE_REVERSE +") "
														+ "ORDER BY RAND() LIMIT 1 ");
			ps.setString(1, location);
			ps.setInt(2, assertionId);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				action.setId(rs.getInt(Action.COL_ID));
				action.setAssertionId(rs.getInt(Action.COL_ASSERTIONID));
				action.setName(rs.getString(Action.COL_NAME));
				action.setObject(rs.getString(Action.COL_OBJECT));
				action.setMotivation(rs.getString(Action.COL_MOTIVATION));
				action.setDuration(rs.getString(Action.COL_DURATION));
				action.setActivity(getActivitiesByActionId(rs.getInt(Action.COL_ID)));
				action.setLocation(getLocationByActionId(rs.getInt(Action.COL_ID)));
				action.setPostCondition(getPostConditionByActionId(rs.getInt(Action.COL_ID)));
				action.setReversePrecondition(getReversePreconditionByActionId(rs.getInt(Action.COL_ID)));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Action getActionById(int id){
		ArrayList<String> activityList = new ArrayList<String>();
		ArrayList<String> locationList = new ArrayList<String>();
		ArrayList<Integer> postConditionList = new ArrayList<Integer>();
		ArrayList<Integer> reversePreconditionList = new ArrayList<Integer>();
		Action action = new Action();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + Action.TABLE_ACTION + 
														" WHERE " + Action.COL_ID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()){
				action.setId(rs.getInt(Action.COL_ID));
				action.setAssertionId(rs.getInt(Action.COL_ASSERTIONID));
				action.setName(rs.getString(Action.COL_NAME));
				action.setObject(rs.getString(Action.COL_OBJECT));
				action.setMotivation(rs.getString(Action.COL_MOTIVATION));
				action.setDuration(rs.getString(Action.COL_DURATION));
				action.setActivity(getActivitiesByActionId(id));
				action.setLocation(getLocationByActionId(id));
				action.setPostCondition(getPostConditionByActionId(id));
				action.setReversePrecondition(getReversePreconditionByActionId(id));
				return action;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public ArrayList<String> getActivitiesByActionId(int id){
		ArrayList<String> activityList = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT "+ Action.COL_ACTIVITY+ " FROM " + Action.TABLE_ACTIVITY +
														" WHERE " + Action.COL_ACTIONID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				activityList.add(rs.getString("activity"));
			}
			return activityList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public ArrayList<String> getLocationByActionId(int id){
		ArrayList<String> locationList = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT "+ Action.COL_LOCATION+ " FROM " + Action.TABLE_LOCATION +
														" WHERE " + Action.COL_ACTIONID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				locationList.add(rs.getString(Action.COL_LOCATION));
			}
			return locationList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getPostConditionByActionId(int id){
		ArrayList<Integer> postConditionList = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT "+ Action.COL_ASSERTIONID + " FROM " + Action.TABLE_POSTCONDITION +
														" WHERE " + Action.COL_ACTIONID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				postConditionList.add(rs.getInt(Action.COL_ASSERTIONID));
			}
			return postConditionList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getReversePreconditionByActionId(int id){
		ArrayList<Integer> reversePreconditionList = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT "+ Action.COL_REVERSEACTION + " FROM " + Action.TABLE_REVERSE +
														" WHERE " + Action.COL_ACTIONID + " = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				reversePreconditionList.add(rs.getInt(Action.COL_REVERSEACTION));
			}
			return reversePreconditionList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}