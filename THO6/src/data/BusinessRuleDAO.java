package data;

import java.util.ArrayList;

import domain.definition.BusinessRule;

public interface BusinessRuleDAO {
	
	public ArrayList<BusinessRule> selectBusinessRules(String username);
	public void modified(ArrayList<Integer> ruleID);
}
