package domain;

public class AttributeRangeRule extends GenerateCodeChain {

	private GenerateCodeChain nextInChain;

	@Override
	public void setNextChain(GenerateCodeChain nextChain) {
		// TODO Auto-generated method stub
		nextInChain = nextChain;
	}

	@Override
	public void getCode(String brt) {
		// TODO Auto-generated method stub
		if(brt.equals(this.getClass().getSimpleName())){
			output = "\tif inserting\n\tthen\n\t\tl_oper := 'INS';\n\telsif updating\n\tthen\n\t\tl_oper := 'UPD';\n\telsif deleting\n\tthen\n\t\tl_oper := 'DEL';\n\tend if;\n";
			output += "declare\n";
			output += "\tl_passed boolean := true;\n";
			output += "begin\n";
			output += "\tif l_oper in ('INS','UPD')\n";
			output += "\tthen\n";
			//output += "\tl_passed := :new" + rule.getAllTables().get(0).getAllColumns().get(0).getName() + " between " + rule.getAllValues().get(0).getComparable() + " and " + rule.getAllValues().get(1).getComparable() + "\n";";
		}
		
		else{
			nextInChain.getCode(brt);
		}
	}
}
