package data;

import java.util.ArrayList;

import domain.definition.BusinessRule;

public interface DAOFactory {
	ArrayList<BusinessRule> getAllBusinessRulesFromDatabase();
}
