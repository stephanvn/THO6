package domain;

import java.util.ArrayList;

public class BusinessRule {
	private String name;
	private GeneratedCode theGeneratedCode;
	private BusinessRuleType type;
	private BRGUser theBRGUser;

	private ArrayList<Event> allEvents;
	private ArrayList<ErrorMessage> allErrorMessages;
	private ArrayList<Value> allValues;
	private ArrayList<Operator> allOperators;
	private ArrayList<Table> allTables;

	public BusinessRule() {
		allErrorMessages = new ArrayList<ErrorMessage>();
		allValues = new ArrayList<Value>();
		allOperators = new ArrayList<Operator>();
		allTables = new ArrayList<Table>();
		allEvents = new ArrayList<Event>();
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

	public ArrayList<Event> getAllEvents() {
		return allEvents;
	}

	public String getEventsStringAbb() {
		String s = "";
		for (int i = 0; i + 1 < allEvents.size(); i++) {
			s += "'"
					+ allEvents.get(i).getType()
							.substring(0, Math.min(name.length(), 3))
							.toUpperCase() + "',";
		}
		s += "'"
				+ allEvents.get(allEvents.size() - 1).getType()
						.substring(0, Math.min(name.length(), 3)).toUpperCase()
				+ "'";
		return s;
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
				+ this.getAllTables().get(0).getAllColumns().get(0).getCode()
				+ "_" + this.getType().getCode();
	}

	public void generateCode() {
		GeneratedCode gc = new GeneratedCode();
		gc.generateCode(this);
		setTheGeneratedCode(gc);
	}

}
