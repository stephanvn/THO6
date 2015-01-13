package domain.constraints;

import java.util.ArrayList;

public class ConstraintsFacade {
	
	private ArrayList<Event> allEvents;
	private ArrayList<Operator> allOperators;
	private ArrayList<Value> allValues;
	private String name;
	
	public ConstraintsFacade(String name) {
		allEvents = new ArrayList<Event>();
		allOperators = new ArrayList<Operator>();
		allValues = new ArrayList<Value>();
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
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

}
