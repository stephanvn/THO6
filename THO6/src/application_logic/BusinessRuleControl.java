package application_logic;

import java.io.File;
import java.util.ArrayList;

import domain.defenition.BusinessRule;
import domain.defenition.BusinessRuleType;
import domain.defenition.DAOFactorySetup;

public class BusinessRuleControl {
	
	private ArrayList<BusinessRule> allBusinessRules = new ArrayList<BusinessRule>();
	private ArrayList<BusinessRuleType> containingTypes = new ArrayList<BusinessRuleType>();
	private static BusinessRuleControl instance = null;
	private DAOFactorySetup DAOfactorySetup = DAOFactorySetup.getInstance();
	
	public static synchronized BusinessRuleControl getInstance(){
		if (instance == null){
			instance = new BusinessRuleControl();
		}
		return instance;
	}

	public ArrayList<BusinessRule> getAllBusinessRules() {
		return allBusinessRules;
	}

	public void setAllBusinessRules(ArrayList<BusinessRule> allBusinessRules) {
		this.allBusinessRules = allBusinessRules;
	}
	
	public void addBusinessRule(BusinessRule b) {
		allBusinessRules.add(b);
		BusinessRuleType t = b.getType();
		if(!hasType(t)) {
			containingTypes.add(t);
		}
	}
	
	public String[] getAllBusinessRulesBySearch(String t) {
		String[] array = new String[allBusinessRules.size()];
		int counter = 0;
		for(BusinessRule b : allBusinessRules) {
			if(b.getName().toUpperCase().contains(t.toUpperCase())){
				array[counter] = b.getName();
				counter++;
			}
		}
		return array;
	}
	
	public String[] getAllBusinessRulesByType(String t) {
		String[] array = new String[allBusinessRules.size()];
		int counter = 0;
		for(BusinessRule b : allBusinessRules) {
			if(b.getType().getName().equals(t)){
				array[counter] = b.getName();
				counter++;
			}
		}
		return array;
	}
	
	public BusinessRule searchBusinessRule(String s) {
		BusinessRule b = null;
		for(BusinessRule br : allBusinessRules) {
			if(br.getName().equals(s)) {
				b = br;
			}
		}
		return b;
	}
	
	private boolean hasType(BusinessRuleType type) {
		boolean b = false;
		for(BusinessRuleType t : containingTypes) {
			if(t.getName().equals(type.getName())) {
				b = true;
			}
		}
		return b;
	}
	
	public void generate(ArrayList<String> list,File file) {
		for(String s : list) {
			BusinessRule b = searchBusinessRule(s);
			b.generateCode();
		}
	}
	
	public String[] getAllBusinessRulesTypeString() {
		String[] array = new String[containingTypes.size()];
		int counter = 0;
		for(BusinessRuleType t : containingTypes) {
			array[counter] = t.getName();
			counter++;
		}
		return array;
	}

	
}
