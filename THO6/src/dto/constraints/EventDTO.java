package dto.constraints;

public class EventDTO {
	private int ID;
	private String type;
	
	public EventDTO(int iD, String type) {
		super();
		ID = iD;
		this.type = type;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
