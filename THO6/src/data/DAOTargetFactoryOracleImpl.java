package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOTargetFactoryOracleImpl implements DAOTargetFactory {

	private Connection connection;
	private String URL = "jdbc:oracle:thin:@ondora01.hu.nl:8521:cursus01";
	private String Username = "tho6_2014_2a_team4_target";
	private String Password = "tho6_2014_2a_team4_target";

	@Override
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, Username, Password);
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("Connected with Oracle DB: " + Username);
		} else {
			System.out.println("Failed to make connection with Oracle DB: "
					+ Username + "!");
		}
		return connection;
	}

	@Override
	public void closeConnection() {
		try {
			connection.close();

			if (connection.isClosed()) {
				System.out.println("Closed connection with Oracle DB: "
						+ Username);
			} else {
				System.out
						.println("Failed to close connection with Oracle DB: "
								+ Username + "!");
			}
		} catch (SQLException e) {
			System.out.println("Can't close connection!");
		}
	}

	@Override
	public void executeTrigger(String trigger) {
		// TODO Auto-generated method stub
		ExecuteTriggerDAO etDAO = new executeTriggerDAOOracleImpl(this);
		etDAO.executeTrigger(trigger);
	}
}
