package domain;

public class Row extends TargetEntity {
	
	private int number;
	
	public Row(int n) {
		number = n;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

}
