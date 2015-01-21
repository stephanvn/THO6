package data;

import dto.constraints.EventDTO;

public class EventDAOOracleImpl implements EventDAO {

	private DAOFactory factory;

	public EventDAOOracleImpl() {
		factory = new DAOFactoryOracle();
	}

	public EventDTO getEventByID(int ID) {
		// TODO Auto-generated method stub
			factory.getConnection();
			//statement 
			factory.closeConnection();
		return null;
	}

}
