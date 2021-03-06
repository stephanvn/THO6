package data;

import java.sql.Connection;
import java.util.ArrayList;

import domain.definition.BusinessRule;

public interface DAOFactory {
	ArrayList<BusinessRule> getAllBusinessRulesFromDatabase(String u);
	public Connection getConnection();
	public void closeConnection();
	public boolean selectUser(String u, String p);
	public void updateToolModified(ArrayList<Integer> ruleID);
}

