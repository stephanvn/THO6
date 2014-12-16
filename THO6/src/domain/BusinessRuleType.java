package domain;

public class BusinessRuleType {
	
	private String name;
	private String code;
	private String description;
	private String templateCode;
	
	public BusinessRuleType(String n,String c,String d,String t) {
		name = n;
		code = c;
		description = d;
		templateCode = t;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
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

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	
	

}
