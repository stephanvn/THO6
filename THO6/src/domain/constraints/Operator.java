package domain.constraints;

public class Operator {
	
	private int ID;
	private String type;

	public Operator(int ID,String type) {
		this.ID = ID;
		this.type = type;
	}
	
	public Operator() {
		
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
