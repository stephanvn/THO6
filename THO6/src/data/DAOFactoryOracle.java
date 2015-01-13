package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOFactoryOracle implements DAOFactory {
	
	private Connection connection;
	private String URL = "jdbc:oracle:thin:@ondora01.hu.nl:8521:cursus01";
	private String Username = "tho6_2014_2a_team4";
	private String Password = "tho6_2014_2a_team4";

	public DAOFactoryOracle() {
		connect();			
		
		//Testing example		
		try {
			Statement stmt = connection.createStatement();
			String sql = "SELECT table_name from all_tables where owner = 'THO6_2014_2A_TEAM4'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String res = rs.getString("table_name");
				System.out.println(res);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		close();
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection(URL, Username, Password);
		}
		catch(SQLException e) {
			System.out.println("Connection failed!");
		}
		
		if (connection != null) {
			System.out.println("Connected with Oracle DB: " + Username);
		} else {
			System.out.println("Failed to make connection with Oracle DB: " + Username + "!");
		}
	}
	
	public void close() {
		try {
			connection.close();
			
			if (connection.isClosed()) {
				System.out.println("Closed connection with Oracle DB: " + Username);
			} else {
				System.out.println("Failed to close connection with Oracle DB: " + Username + "!");
			}
		}
		catch(SQLException e) {
			System.out.println("Can't close connection!");
		}		
	}

}
