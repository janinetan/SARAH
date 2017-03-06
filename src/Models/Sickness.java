package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Sickness {
	public static final String TABLE_SICKNESS = "sickness";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_SYMPTOMSID = "symptomsId";
	public static final String COL_CAUSESID = "causesId";
	
	private int id;
	private String name;
	private ArrayList<Integer> symptomsId;
	private ArrayList<Integer> causesId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSymptomsId(String symptomsId){
		this.symptomsId = new ArrayList<Integer>();
		String[] temp = symptomsId.split(",");
		for (String tempString : temp){
			this.symptomsId.add(Integer.parseInt(tempString));
		}
	}
	
	public void setCausesId(String causesId){
		this.causesId = new ArrayList<Integer>();
		String[] temp = causesId.split(",");
		for (String tempString : temp){
			this.causesId.add(Integer.parseInt(tempString));
		}
	}
	
	public ArrayList<Integer> getSymptomsId() {
		return symptomsId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String sicknessString = "";
		sicknessString += "id: " + this.id + "\n";
		sicknessString += "name: " + this.name + "\n";
		sicknessString += "symptomsId: " + this.symptomsId + "\n";
		sicknessString += "causesId: " + this.causesId + "\n";
		return sicknessString;
	}
}
