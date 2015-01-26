package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class executeTriggerDAOOracleImpl implements ExecuteTriggerDAO {

	private DAOTargetFactory targetFactory;

	public executeTriggerDAOOracleImpl(DAOTargetFactory targetFactory) {
		this.targetFactory = targetFactory;
	}

	@Override
	public void executeTrigger(ArrayList<String> triggerCode) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = targetFactory.getConnection();
		for (String s : triggerCode) {
			try {
				Statement stmt = connection.createStatement();
				stmt.executeQuery(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		targetFactory.closeConnection();
	}

}
