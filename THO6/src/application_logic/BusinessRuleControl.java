package application_logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import userinterface.MainFrame;
import domain.definition.BusinessRule;
import domain.definition.DAOFactorySetup;

public class BusinessRuleControl {
	
	private ArrayList<BusinessRule> allBusinessRules = new ArrayList<BusinessRule>();
	private ArrayList<String> allBusinessRuleTypesString = new ArrayList<String>();
	private DAOFactorySetup DAOFactorySetupRef = DAOFactorySetup.getInstance();
	private static BusinessRuleControl instance = null;
	private String username;
	
	public static synchronized BusinessRuleControl getInstance(){
		if (instance == null){
			instance = new BusinessRuleControl();
		}
		return instance;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public ArrayList<String> getAllBusinessRulesBySearch(String t) {
		ArrayList<String> array = new ArrayList<String>();
		for(BusinessRule b : allBusinessRules) {
			if(b.getName().toUpperCase().contains(t.toUpperCase())){
				array.add(b.getName());
			}
		}
		return array;
	}
	
	public ArrayList<String> getAllBusinessRulesByType(String t) {
		ArrayList<String> array = new ArrayList<String>();
		if(t.equals("All")) {
			for(BusinessRule b1 : allBusinessRules) {
				array.add(b1.getName());
			}
		}
		else {
			for(BusinessRule b2 : allBusinessRules) {
				if(b2.getType().getName().equals(t)){
					array.add(b2.getName());
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
		Calendar now = Calendar.getInstance();
		FileWriter fw = null; 
		String fileName = file+"\\triggers_" + now.getTimeInMillis() +".txt";
		fw = new FileWriter(fileName);
		ArrayList<String>generatedCodes = new ArrayList<String>();
		ArrayList<Integer>generatedCodesID = new ArrayList<Integer>();
		for(String s : list) {
			BusinessRule b = searchBusinessRule(s);
			try {
				b.generateCode();
				fw.write(b.getTheGeneratedCode().getGeneratedCode());
				generatedCodes.add(b.getTheGeneratedCode().getGeneratedCode());
				generatedCodesID.add(b.getID());
				//Replace "oracleTarget" with other database for other target
			} catch(Exception e) {e.printStackTrace();}			
		}
		fw.close();		
		
		File savedFile = new File(fileName);
		if(savedFile.length()==0) {
			savedFile.delete();
		}
		DAOFactorySetupRef.executeTrigger("oracleTarget", generatedCodes);
		DAOFactorySetupRef.updateToolModified(generatedCodesID);
	}	
	
	public void fillDomainFromDatabase(){
		allBusinessRuleTypesString.clear();
		//Replace "oracle" with other database, or make it an option on userInterface so user can choose
		allBusinessRules = DAOFactorySetupRef.getAllBusinessRulesFromDatabase(username);
		if(allBusinessRules!=null) {
			allBusinessRuleTypesString.add("All");
			for(BusinessRule br : allBusinessRules) {
				if(!hasType(br.getType().getName())) {
					allBusinessRuleTypesString.add(br.getType().getName());
				}
			}
			//Sort on alphabet
			List<String> subList = allBusinessRuleTypesString.subList(1, allBusinessRuleTypesString.size());
			Collections.sort(subList);
		}
		new MainFrame(this);
	}
}
