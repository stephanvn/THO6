package domain.definition;

import java.util.ArrayList;

import domain.constraints.ConstraintsFacade;
import domain.errorHandling.ErrorMessage;
import domain.generatedCode.GeneratedCode;
import domain.target.Table;
import domain.userManagement.BRGUser;

public class BusinessRule {
	private int ID;
	private String name;
	private GeneratedCode theGeneratedCode;
	private BusinessRuleType type;
	private BRGUser theBRGUser;
	private ConstraintsFacade constraintsFacade;

	private ArrayList<ErrorMessage> allErrorMessages;
	private ArrayList<Table> allTables;

	public BusinessRule(int ID) {
		this.ID = ID;
		constraintsFacade = new ConstraintsFacade();
		allErrorMessages = new ArrayList<ErrorMessage>();		
		allTables = new ArrayList<Table>();
	}
	
	public ConstraintsFacade getConstrainsFacade() {
		return constraintsFacade;
	}
	
	public int getID() {
		return ID;
	}

	public String getName() {
		if (name == null) {
			this.generateName();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BusinessRuleType getType() {
		return type;
	}

	public void setType(BusinessRuleType type) {
		this.type = type;
	}

	public BRGUser getTheBRGUser() {
		return theBRGUser;
	}

	public void setTheBRGUser(BRGUser theBRGUser) {
		this.theBRGUser = theBRGUser;
	}

	public GeneratedCode getTheGeneratedCode() {
		return theGeneratedCode;
	}

	public void setTheGeneratedCode(GeneratedCode theGeneratedCode) {
		this.theGeneratedCode = theGeneratedCode;
	}	

	public ArrayList<ErrorMessage> getAllErrorMessages() {
		return allErrorMessages;
	}

	public void setAllErrorMessages(ArrayList<ErrorMessage> allErrorMessages) {
		this.allErrorMessages = allErrorMessages;
	}

	public void addErrorMessage(ErrorMessage em) {
		allErrorMessages.add(em);
	}

	public ArrayList<Table> getAllTables() {
		return allTables;
	}

	public String getTargetTableName() {
		return allTables.get(0).getName();
	}

	public void setAllTables(ArrayList<Table> allTables) {
		this.allTables = allTables;
	}

	public void addTable(Table t) {
		allTables.add(t);
	}

	public void generateName() {
		name = "BRG_" + this.theBRGUser.getCode() + "_"
				+ this.getAllTables().get(0).getAllColumns().get(0).getCode() + "_"
				+ "CNS_"
				+ this.getType().getCode() + "_"
				+ "001";
	}

	public void generateCode() {
		GeneratedCode gc = new GeneratedCode();
		gc.generateCode(this);
		setTheGeneratedCode(gc);
	}

	@Override
	public String toString() {
		return "BusinessRule [ID=" + ID + ", name=" + name
				+ ", type=" + type.getCode()
				+ ", theBRGUser=" + theBRGUser.getName() + "]";
	}

}
