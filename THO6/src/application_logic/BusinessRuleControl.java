package application_logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import domain.definition.BusinessRule;
import domain.definition.DAOFactorySetup;

public class BusinessRuleControl {
	
	private ArrayList<BusinessRule> allBusinessRules = new ArrayList<BusinessRule>();
	private ArrayList<String> allBusinessRuleTypesString = new ArrayList<String>();
	private DAOFactorySetup DAOFactorySetupRef = DAOFactorySetup.getInstance();
	private static BusinessRuleControl instance = null;
	
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
		if(t.equals("All")) {
			for(BusinessRule b1 : allBusinessRules) {
				array[counter] = b1.getName();
				counter++;
			}
		}
		else {
			for(BusinessRule b2 : allBusinessRules) {
				if(b2.getType().getName().equals(t)){
					array[counter] = b2.getName();
					counter++;
				}
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
	
	public ArrayList<String> getAllBusinessRulesTypeString() {
		return allBusinessRuleTypesString;
	}
	
	private boolean hasType(String type) {
		boolean b = false;
		for(String t : allBusinessRuleTypesString) {
			if(t.equals(type)) {
				b = true;
			}
		}
		return b;
	}
	
	public void generate(ArrayList<String> list,File file) throws IOException {
		FileWriter fw = new FileWriter(file);
		for(String s : list) {
			BusinessRule b = searchBusinessRule(s);
			b.generateCode();
			fw.write(b.getTheGeneratedCode().getGeneratedCode());
		}
		fw.close();
	}	
	
	public void fillDomainFromDatabase(){
		//Replace "oracle" with other database, or make it an option on userInterface so user can choose
		allBusinessRules = DAOFactorySetupRef.getAllBusinessRulesFromDatabase("oracle");
		for(BusinessRule br : allBusinessRules) {
			if(!hasType(br.getType().getName())) {
				allBusinessRuleTypesString.add(br.getType().getName());
			}
		}
	}
}
