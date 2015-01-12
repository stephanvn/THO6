package domain;

public class BRGUser {
	
	private String name;
	private String code;
	
	public BRGUser(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode(){
		if(code == null){
			code = name.substring(0, Math.min(name.length(), 4)).toUpperCase();
		}
		return code;
	}

}
