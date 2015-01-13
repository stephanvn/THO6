package domain.constraints;

import java.util.ArrayList;

public class ConstraintsFacade {
	
	private ArrayList<Event> allEvents;
	private ArrayList<Operator> allOperators;
	private ArrayList<Value> allValues;
	
	public ConstraintsFacade() {
		allEvents = new ArrayList<Event>();
		allOperators = new ArrayList<Operator>();
		allValues = new ArrayList<Value>();
	}

}
