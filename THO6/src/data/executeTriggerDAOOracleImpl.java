package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class executeTriggerDAOOracleImpl implements ExecuteTriggerDAO {

	private DAOTargetFactory targetFactory;

	public executeTriggerDAOOracleImpl(DAOTargetFactory targetFactory) {
		this.targetFactory = targetFactory;
	}

	@Override
	public void executeTrigger(String triggerCode) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = targetFactory.getConnection();

		try {
			Statement stmt = connection.createStatement();
			stmt.executeQuery(triggerCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		targetFactory.closeConnection();
	}

}
