package Models;

public class Object {
	public static final String TABLE_OBJECT = "object";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_CATEGORY = "category";
	public static final String COL_VERB = "verb";
	
	private int id;
	private String name;
	private String category;
	private String verb;
	private String filename;
	private String connector;
	private String connectorNarration;
	
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getVerb() {
		return verb;
	}
	public void setVerb(String verb) {
		this.verb = verb;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getConnectorNarration() {
		return connectorNarration;
	}
	public void setConnectorNarration(String connectorNarration) {
		this.connectorNarration = connectorNarration;
	}
	
}
