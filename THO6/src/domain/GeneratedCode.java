package domain;

public class GeneratedCode {
	private String generatedCode;

	public GeneratedCode() {
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	public void generateCode(BusinessRule BR) {		
		StringBuilder output = new StringBuilder();
		output.append("create or replace trigger " + BR.getName());
		output.append("before");
		
		for (int i = 0; i < BR.getAllEvents().size(); i++) {
			if (i != BR.getAllEvents().size()) {
				output.append(BR.getAllEvents().get(i).getType() + "or");
			} else {
				output.append(BR.getAllEvents().get(i).getType());
			}
		}

		output.append("on " + BR.getAllTables().get(0).getName() + "."
				+ BR.getAllTables().get(0).getAllColumns().get(0).getName());
		
		output.append("for each row");
		output.append("declare");
		output.append("tl_oper varchar2(3);");
		output.append("tl_error_stack varchar2(4000);");
		output.append("begin");
		
		GenerateCodeChain chain1 = new AttributeRangeRule();
		GenerateCodeChain chain99 = new ErrorTypeRule();
		chain1.setNextChain(chain99);
		
		chain1.getCode(BR.getType().getName());
		output.append(chain1.output);
				
		String s = output.toString();
		setGeneratedCode(s);
	}

}
