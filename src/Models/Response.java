package Models;

public class Response {
	public static final String TABLE_RESPONSE = "response";
	
	public static final String COL_ID = "id";
	public static final String COL_DISCOURSEACTID = "discourseActId";
	public static final String COL_MESSAGE = "message";
	public static final String COL_VIRTUALAGENTID = "virtualAgentId";
	public static final String COL_RESPONSETYPE = "responseType";
	
	private int id;
	private int discourseActId;
	private String message;
	private int virtualAgentId;
	private String responseType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDiscourseActId() {
		return discourseActId;
	}
	public void setDiscourseActId(int discourseActId) {
		this.discourseActId = discourseActId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getVirtualAgentId() {
		return virtualAgentId;
	}
	public void setVirtualAgentId(int virtualAgentId) {
		this.virtualAgentId = virtualAgentId;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	
}
