package DAO;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private String url, username, password;
	private static Connection connection;
	
	public DBConnection()
	{
		System.out.println("DB INSTANTIATION CALLED");
		this.username = "root";
		this.password = "abc123";
		this.url = "jdbc:mysql://localhost:3306/sarah_kb";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, username,
					password);
			System.out.println("CONNECTED");
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