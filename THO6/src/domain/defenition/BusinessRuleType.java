package domain.defenition;

public class BusinessRuleType {
	
	private String name;
	private String code;
	private String description;
	
	public BusinessRuleType(String n, String d) {
		name = n;
		description = d;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
