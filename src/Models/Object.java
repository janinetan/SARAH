package Models;

public class Object {
	public static final String TABLE_OBJECT = "object";
	
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_IMAGEFILEPATH = "imageFilePath";
	
	private int id;
	private String name;
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
	public String getImageFilePath() {
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
	
	
}
