package domain.definition;

import java.util.ArrayList;

import data.DAOFactory;
import data.DAOFactoryOracle;
import data.DAOTargetFactory;
import data.DAOTargetFactoryOracleImpl;

public class DAOFactorySetup {

	private DAOFactory factory;
	private DAOTargetFactory targetFactory;
	
	private static DAOFactorySetup instance = null;

	public static synchronized DAOFactorySetup getInstance() {
		if (instance == null) {
			instance = new DAOFactorySetup();
		}
		return instance;
	}

	public static DAOFactory getDAOFactory(String databaseType) {
		if (databaseType.equalsIgnoreCase("oracle")) {
			return new DAOFactoryOracle();
		}
		return null;
	}
	
	public static DAOTargetFactory getDAOTargetFactory(String targetDatabaseType){
		if (targetDatabaseType.equalsIgnoreCase("oracleTarget")){
			return new DAOTargetFactoryOracleImpl();
		}
		return null;
	}
	
	public void executeTrigger(String targetDatabaseType, String trigger){
		targetFactory = getDAOTargetFactory(targetDatabaseType);
		targetFactory.executeTrigger(trigger);
	}

	// Fills domain with all businessRules from database
	public ArrayList<BusinessRule> getAllBusinessRulesFromDatabase(String username) {
		return factory.getAllBusinessRulesFromDatabase(username);
	}
	
	public boolean selectUser(String username,String password, String db) {
		factory = getDAOFactory(db);
		return factory.selectUser(username,password);
	}

}
