package domain;

import java.util.ArrayList;

public class BusinessRule {
	private String name;
	private String generatedCode;
	private int generatedCounter;
	
	private BusinessRuleType type;
	private BRGUser theBRGUser;	
	
	private ArrayList<Event> allEvents;
	private ArrayList<ErrorMessage> allErrorMessages;
	private ArrayList<Value> allValues;
	private ArrayList<Operator> allOperators;
	private ArrayList<Table> allTables;
	
	
	public BusinessRule(String n) {
		name = n;
		allErrorMessages = new ArrayList<ErrorMessage>();
		allValues = new ArrayList<Value>();
		allOperators = new ArrayList<Operator>();
		allTables = new ArrayList<Table>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public int getGeneratedCounter() {
		return generatedCounter;
	}

	public void setGeneratedCounter(int generatedCounter) {
		this.generatedCounter = generatedCounter;
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

	public ArrayList<Event> getAllEvents() {
		return allEvents;
	}

	public void setAllEvents(ArrayList<Event> allEvents) {
		this.allEvents = allEvents;
	}
	
	public void addEvent(Event e) {
		allEvents.add(e);
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

	public ArrayList<Value> getAllValues() {
		return allValues;
	}

	public void setAllValues(ArrayList<Value> allValues) {
		this.allValues = allValues;
	}
	
	public void addValue(Value v) {
		allValues.add(v);
	}

	public ArrayList<Operator> getAllOperators() {
		return allOperators;
	}

	public void setAllOperators(ArrayList<Operator> allOperators) {
		this.allOperators = allOperators;
	}
	
	public void addOperator(Operator o) {
		allOperators.add(o);
	}

	public ArrayList<Table> getAllTables() {
		return allTables;
	}

	public void setAllTables(ArrayList<Table> allTables) {
		this.allTables = allTables;
	}
	
	public void addTable(Table t) {
		allTables.add(t);
	}
	
	public void generateCode(){
		
	}
	
}
