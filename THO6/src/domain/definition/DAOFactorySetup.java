package domain.definition;

import java.util.ArrayList;

import data.DAOFactory;
import data.DAOFactoryOracle;

public class DAOFactorySetup {
	
	private DAOFactory factory;
	private static DAOFactorySetup instance = null;
	
	public static synchronized DAOFactorySetup getInstance() {
		if(instance==null) {
			instance = new DAOFactorySetup();
		}
		return instance;
	}
	
	public static DAOFactory getDAOFactory(String databaseType){
		if(databaseType.equalsIgnoreCase("oracle")){
			return new DAOFactoryOracle();
		}
		return null;
	}
	
	
	
	//Fills domain with all businessRules from database
	public ArrayList<BusinessRule> getAllBusinessRulesFromDatabase(String databaseType){
		factory = getDAOFactory(databaseType);
		return factory.getAllBusinessRulesFromDatabase();
	}

}
