package domain.defenition;

import data.DAOFactory;
import data.DAOFactoryOracle;

public class DAOFactorySetup {
	
	private static DAOFactorySetup instance = null;
	private DAOFactory factory;
	
	public static synchronized DAOFactorySetup getInstance() {
		if(instance==null) {
			instance = new DAOFactorySetup();
		}
		return instance;
	}
	
	public DAOFactorySetup() {
		factory = new DAOFactoryOracle();
	}

}
