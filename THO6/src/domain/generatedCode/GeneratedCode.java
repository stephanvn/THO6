package domain.generatedCode;

import domain.definition.BusinessRule;



public class GeneratedCode {
	private String generatedCode;
	private TemplateHelper th;

	public GeneratedCode() {
		th = new TemplateHelper();
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public String generateCode(BusinessRule theBusinessRule){
		String defaultCode = th.getDefaultCode(theBusinessRule);
		String typeSpecificCode = null;
		
		if(theBusinessRule.getType().getName().equals("Attribute Range Rule")){
			typeSpecificCode = th.getAttributeRangeRuleTemplate(theBusinessRule);
		}
		
		if(theBusinessRule.getType().getName().equals("Attribute Compare Rule")){
			typeSpecificCode = th.getAttributeCompareRuleTemplate(theBusinessRule);
		}
		
		if(theBusinessRule.getType().getName().equals("Attribute List Rule")){
			typeSpecificCode = th.getAttributeListRuleTemplate(theBusinessRule);
		}
		
		if(theBusinessRule.getType().getName().equals("Modify Rule")){
			//typeSpecificCode = th.
		}
		
		if(typeSpecificCode != null){
			generatedCode = defaultCode + typeSpecificCode;
		}
		if(typeSpecificCode == null){
			generatedCode = defaultCode;
		}
		return generatedCode;
	}
}
