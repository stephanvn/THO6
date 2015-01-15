package domain.target;

public class Column {
	
	private String name;
	private String code;
	private int position;
	
	public Column(String n,int position) {
		name = n;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPosition() {
		return position;
	}

	public String getCode(){
		if(code == null){
			code = name.substring(0, Math.min(name.length(), 3)).toUpperCase();
		}
		return code;
	}
	

}
