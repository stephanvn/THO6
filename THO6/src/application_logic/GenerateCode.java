package application_logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import domain.defenition.BusinessRule;

public class GenerateCode {
	
	private BusinessRule rule;
	private String output;
	private File file;
	
	public GenerateCode(BusinessRule rule,File file) {
		this.rule = rule;
		this.file = file;
		generate();
	}
	
	private void generate() {
		output = "create or replace trigger ";
		if(rule.getType().getCode().equals("ARNG")) {
			output += rule.getName() + "\n";
			output += "\tbefore delete or insert or update" + "\n";			
			output += "\ton "+rule.getAllTables().get(0).getName()+"."+rule.getAllTables().get(0).getAllColumns().get(0).getName()+"\n";
			output += "\tfor each row\n";
			output += "declare\n";
			output += "\tl_oper varchar2(3);\n";
			output += "\tl_error_stack varchar2(4000);\n";
			output += "begin\n";
			output += "\tif inserting\n\tthen\n\t\tl_oper := 'INS';\n\telsif updating\n\tthen\n\t\tl_oper := 'UPD';\n\telsif deleting\n\tthen\n\t\tl_oper := 'DEL';\n\tend if;\n";
			output += "declare\n";
			output += "\tl_passed boolean := true;\n";
			output += "begin\n";
			output += "\tif l_oper in ('INS','UPD')\n";
			output += "\tthen\n";
			output += "\tl_passed := :new" + rule.getAllTables().get(0).getAllColumns().get(0).getName() + " between " + rule.getAllValues().get(0).getComparable() + " and " + rule.getAllValues().get(1).getComparable() + "\n";
			output += "\tif not l_passed\n";
			output += "\tthen\n";
			output += "\t\t[HIER DE ERRORMESSAGE]\n";
			output += "\tendif;\n";			
			output += "end;";
		}
		Writer writer = null;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath()+"\\"+rule.getName()+".txt"), "utf-8"));
		    writer.write(output);
		    System.out.println(output);
		} catch (IOException ex) {
		  System.out.println("Fout bij schrijven van "+rule.getName()+".txt");
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
		
	}

}
