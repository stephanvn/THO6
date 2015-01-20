package domain.definition;

public class BusinessRuleType {
	
	private String name;
	private String code;
	
	public BusinessRuleType(String n) {
		name = n;
	}
	
	public BusinessRuleType(String n, String c) {
		name = n;
		code = c;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		if(code == null){
			code = name.substring(0, Math.min(name.length(), 4)).toUpperCase();
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
