package data;

import java.sql.Connection;
import java.util.ArrayList;

import domain.definition.BusinessRule;

public interface DAOFactory {
	ArrayList<BusinessRule> getAllBusinessRulesFromDatabase();
	public Connection getConnection();
	public void closeConnection();
}
