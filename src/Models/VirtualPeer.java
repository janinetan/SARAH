package Models;

public class VirtualPeer {
	public static final String TABLE_VIRTUALPEER = "virtual_peer";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_EMOTIONALSTATE = "emotionalState";
	public static final String COL_ISSICK = "isSick";
	public static final String COL_IMAGEFILEPATH = "imageFilePath";
	
	private int id;
	private String name;
	private String emotionalState;
	private boolean isSick;
	private String imageFilePath;
	
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
	public String getEmotionalState() {
		return emotionalState;
	}
	public void setEmotionalState(String emotionalState) {
		this.emotionalState = emotionalState;
	}
	public boolean isSick() {
		return isSick;
	}
	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	
	
}
