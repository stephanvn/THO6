package data;

import java.sql.Connection;

public interface DAOTargetFactory {
	public Connection getConnection();
	public void closeConnection();
	public void executeTrigger(String trigger);
}
