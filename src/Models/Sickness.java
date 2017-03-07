package Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Sickness {
	public static final String TABLE_SICKNESS = "sickness";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	
	private int id;
	private String name;
	
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
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String sicknessString = "";
		sicknessString += "id: " + this.id + "\n";
		sicknessString += "name: " + this.name + "\n";
		return sicknessString;
	}
}
