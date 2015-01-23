package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	
	private DAOFactory factory;
	private String username;
	private String password;
	
	public LoginDAO(DAOFactory factory,String username,String password) {
		this.factory = factory;
		this.username = username;
		this.password = password;
	}
	
	public boolean selectUser() {
		Connection connection = factory.getConnection();
		boolean b = false;
		
		try {
			Statement stmt1 = connection.createStatement();			

			String sql1 = "select name,password from brguser where name = '" + this.username + "' and password = '" + this.password + "'";
			
			ResultSet rs1 = stmt1.executeQuery(sql1);

			while(rs1.next()){
				b = true;
			}
			
			rs1.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		factory.closeConnection();
		
		return b;
	}

}
