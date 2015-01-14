package data;

import java.sql.Connection;

public interface DAOFactory {
	
	public Connection getConnection();
	
	public void closeConnection();
	
	public Object chooseDAO(String type);

}
