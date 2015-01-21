package domain.constraints;

import java.util.LinkedList;
import java.util.List;

import data.EventDAO;
import dto.constraints.EventDTO;

public class Event {
	private static List<Event> allEvents = new LinkedList<Event>();
	
	private int ID;
	private String type;
	
	public Event(int ID,String type) {
		this.ID = ID;
		this.type = type;
		allEvents.add(this);
	}
	
	public Event(EventDTO evDTO) {
		this.ID = evDTO.getID();
		this.type = evDTO.getType();
		allEvents.add(this);
	}

	
	public static Event getEventById(int ID) {
		for (Event ev: allEvents) {
			if (ev.getID() == ID) {
				return ev;
			}
		}
		EventDAO.getEventByID(ID);
		return null;
	}
	
	public int getID() {
		return ID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
