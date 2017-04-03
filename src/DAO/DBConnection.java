package DAO;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private String url, username, password;
	private static Connection connection;
	
	public DBConnection()
	{
		this.username = "root";
		this.password = "sasukeandlen";
		this.url = "jdbc:mysql://localhost:3306/sarah_kb";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username,
					password);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

	public static Connection getConnection()
	{
		if (connection == null){
			new DBConnection();
			return connection;
		}
		else{
			return connection;
		}
	}

}