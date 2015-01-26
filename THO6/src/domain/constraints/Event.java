package domain.constraints;


public class Event {
	
	private int ID;
	private String type;
	
	public Event(int ID,String type) {
		this.ID = ID;
		this.type = type;
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
