package domain.generatedCode;

import domain.defenition.BusinessRule;



public class GeneratedCode {
	private String GeneratedCode;

	public GeneratedCode() {
	}

	public String getGeneratedCode() {
		return GeneratedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		GeneratedCode = generatedCode;
	}

	public void generateCode(BusinessRule theBusinessRule){
		TemplateHelper th = new TemplateHelper(theBusinessRule);
		String defaultCode = th.getDefaultCode();
		String typeSpecificCode = null;
		
		if(theBusinessRule.getType().getName().equals("Attribute Range Rule")){
			typeSpecificCode = th.getAttributeRangeRuleTemplate();
		}
		
		if(typeSpecificCode != null){
			GeneratedCode = defaultCode + typeSpecificCode;
		}
	}
}
