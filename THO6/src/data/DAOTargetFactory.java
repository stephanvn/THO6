package data;

import java.sql.Connection;
import java.util.ArrayList;

public interface DAOTargetFactory {
	public Connection getConnection();
	public void closeConnection();
	public void executeTrigger(ArrayList<String> trigger);
}
