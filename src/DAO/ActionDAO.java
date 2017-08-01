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
import Models.VirtualPeer;

public class ActionDAO {
	private Connection con;
	
	public ActionDAO() {
		con = DBConnection.getConnection();
	}
	
	public ArrayList<Integer> getFirstAction(ArrayList<Integer> goodAssertions, String location, ArrayList<Integer> badAssertions){
		ArrayList<Integer> actionIdsList = new ArrayList<Integer>();
		ArrayList<Integer> postCondition = new ArrayList<Integer>();
		ArrayList<Integer> finalList = new ArrayList<Integer>();
		String number = "";
		for (int i = 0; i < goodAssertions.size(); i++){
			number += goodAssertions.get(i);
			if (i != goodAssertions.size() - 1)
				number += ",";
		}
		
		try {
			PreparedStatement ps = con.prepareStatement( "SELECT k.id "
					+ "FROM (SELECT a.id as id "
					+ "FROM   action a LEFT   JOIN precondition p ON p.action_id = a.id  "
					+ "WHERE  p.action_id IS NULL "
					+ "UNION "
					+ "SELECT action_id as id "
					+ "FROM sarah_kb.precondition "
					+ "WHERE assertion_id IN("+ number +")) k, location  "
					+ "WHERE k.id = location.action_id AND location = ? ") ;
				
			ps.setString(1, location);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				actionIdsList.add(rs.getInt("id"));
			}
			
			for(int i = 0; i < actionIdsList.size(); i++){
				postCondition = getAllPostCondition(actionIdsList.get(i));
				postCondition.retainAll(badAssertions);
				if(!postCondition.isEmpty()){
					finalList.add(actionIdsList.get(i));
				}
			   
			}
			
			System.out.println("FINAL");
			for(int i = 0; i < finalList.size(); i++){
				System.out.println(finalList.get(i));
			}
			return finalList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getAllPostCondition(int id){
		ArrayList<Integer> actionIdsList = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT assertion_id FROM sarah_kb.postcondition WHERE action_id = ? ") ;
				
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				actionIdsList.add(rs.getInt("assertion_id"));
			}
			return actionIdsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getActionWithPrecondition(String location, int assertionId){
		ArrayList<Integer> actionIdsList = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement(" SELECT * " +
					" FROM action " +
					" WHERE id IN ( " +
					" SELECT precondition.action_id FROM precondition, location, symptom_map WHERE " +                    
					" precondition.action_id = location.action_id AND precondition.action_id = symptom_map.action_id AND location = ? AND assertion_id = ? " +
					" group by precondition.action_id)") ;
				
			ps.setString(1, location);
			ps.setInt(2, assertionId);
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
	
	public ArrayList<Integer> getReverseAction(String location, int assertionId){
		ArrayList<Integer> actionIdsList = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement(" SELECT * " +
					" FROM action " +
					" WHERE id IN ( " +
					" SELECT precondition.action_id FROM precondition, location WHERE " +                    
					" precondition.action_id = location.action_id AND location = ? AND assertion_id = ? " +
					" group by precondition.action_id)") ;
				
			ps.setString(1, location);
			ps.setInt(2, assertionId);
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
		ArrayList<String> symptomList = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT * FROM action where id = ?" ) ;
				
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			Action action = new Action();
			if (rs.next()){
				action.setId(rs.getInt(Action.COL_ID));
				action.setActivityName(rs.getString(Action.COL_ACTIVITYNAME));
				action.setObjectCategory(rs.getString(Action.COL_OBJECT));
				action.setPrecondition(getAllPrecondition(action.getId()));
				action.setMotivation(getMotivations(action.getActivityName()));
				action.setPostcondition(getAllPostcondition(action.getId()));
				action.setObjectList(getObjects(action.getObjectCategory()));
			}
			
			ps = con.prepareStatement( " SELECT * FROM symptom_map where action_id = ?" ) ;
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()){
				symptomList.add(rs.getString("symptom"));
			}
			action.setSymptomList(symptomList);
			return action;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Object> getObjects (String category){
		System.out.println(category);
		ArrayList<Object> objectList = new ArrayList<Object>();
		try {
			PreparedStatement ps = con.prepareStatement( " SELECT * FROM object WHERE category = ? OR name = ?" ) ;
			ps.setString(1, category);
			ps.setString(2, category);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Object object = new Object();
				object.setId(rs.getInt("id"));
				object.setCategory(rs.getString("category"));
				object.setName(rs.getString("name"));
				object.setVerb(rs.getString("verb"));
				if(rs.getString("filename") == null){
					object.setFilename("fruits");
				}else{
					object.setFilename(rs.getString("filename"));
				}
				object.setConnector1(rs.getString("connector1"));
				object.setConnector2(rs.getString("connector2"));
				object.setConnector3(rs.getString("connector3"));
				object.setConnector4(rs.getString("connector4"));
				object.setConnector5(rs.getString("connector5"));
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
	
	public String getConnectorGivenName(String name){
		ArrayList<Integer> postconditions = new ArrayList<Integer>();
		try {
			PreparedStatement ps = con.prepareStatement( "SELECT connector3 FROM object WHERE name = ?" ) ;
				
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				return rs.getString("connector3");
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}