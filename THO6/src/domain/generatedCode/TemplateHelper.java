package domain.generatedCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.stringtemplate.v4.ST;

import domain.definition.BusinessRule;

public class TemplateHelper {

	private ST defaultTemplate, attributeRangeRule, attributeCompareRule,
			attributeListRule, tupleCompareRule, interEntityCompareRule,
			entityOtherRule, modifyRule;

	public TemplateHelper() {
	}

	public String getDefaultTemplate(BusinessRule theBusinessRule) {
		try {
			defaultTemplate = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("defaultTemplate").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(defaultTemplate, "brName",
				theBusinessRule.getTriggerName());
		addAttributeToTemplate(defaultTemplate, "targetTable",
				theBusinessRule.getTargetTableName());
		return defaultTemplate.render();
	}

	public String getAttributeRangeRuleTemplate(BusinessRule theBusinessRule) {
		try {
			attributeRangeRule = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("attributeRangeRule").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(attributeRangeRule, "eventsStringAbb",
				theBusinessRule.getConstrainsFacade().getEventsStringAbb());
		addAttributeToTemplate(attributeRangeRule, "column", theBusinessRule
				.getAllTables().get(0).getTargetColumn());
		addAttributeToTemplate(attributeRangeRule, "operator", theBusinessRule
				.getConstrainsFacade().getAllOperators().get(0).getType());
		addAttributeToTemplate(attributeRangeRule, "value", theBusinessRule
				.getConstrainsFacade().getAllValues().get(0).getComparable());
		addAttributeToTemplate(attributeRangeRule, "value2", theBusinessRule
				.getConstrainsFacade().getAllValues().get(1).getComparable());
		addAttributeToTemplate(attributeRangeRule, "errorMessage",
				theBusinessRule.getAllErrorMessages().get(0).getMessage());
		return attributeRangeRule.render();
	}

	public String getAttributeCompareRuleTemplate(BusinessRule theBusinessRule) {
		try {
			attributeCompareRule = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("attributeCompareRule").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(attributeCompareRule, "eventsStringAbb",
				theBusinessRule.getConstrainsFacade().getEventsStringAbb());
		addAttributeToTemplate(attributeCompareRule, "column", theBusinessRule
				.getAllTables().get(0).getTargetColumn());
		addAttributeToTemplate(attributeCompareRule, "operator",
				theBusinessRule.getConstrainsFacade().getAllOperators().get(0)
						.getType());
		addAttributeToTemplate(attributeCompareRule, "value", theBusinessRule
				.getConstrainsFacade().getAllValues().get(0).getComparable());
		addAttributeToTemplate(attributeCompareRule, "errorMessage",
				theBusinessRule.getAllErrorMessages().get(0).getMessage());
		return attributeCompareRule.render();
	}

	public String getAttributeListRuleTemplate(BusinessRule theBusinessRule) {
		try {
			attributeListRule = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("attributeListRule").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(attributeListRule, "eventsStringAbb",
				theBusinessRule.getConstrainsFacade().getEventsStringAbb());
		if (theBusinessRule.getConstrainsFacade().getAllOperators().get(0)
				.getID() == 8) {
			addAttributeToTemplate(attributeListRule, "operator", "IN");
		}
		if (theBusinessRule.getConstrainsFacade().getAllOperators().get(0)
				.getID() == 9) {
			addAttributeToTemplate(attributeListRule, "operator", "NOT IN");
		}
		addAttributeToTemplate(attributeListRule, "column", theBusinessRule
				.getAllTables().get(0).getAllColumns().get(0).getName());
		addAttributeToTemplate(attributeListRule, "values", theBusinessRule
				.getConstrainsFacade().getAllValuesAsString());
		addAttributeToTemplate(attributeListRule, "errorMessage",
				theBusinessRule.getAllErrorMessages().get(0).getMessage());
		return attributeListRule.render();
	}

	public String getTupleCompareRuleTemplate(BusinessRule theBusinessRule) {
		try {
			tupleCompareRule = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("tupleCompareRule").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(tupleCompareRule, "eventsStringAbb",
				theBusinessRule.getConstrainsFacade().getEventsStringAbb());
		addAttributeToTemplate(tupleCompareRule, "column1", theBusinessRule
				.getAllTables().get(0).getAllColumns().get(0).getName());
		addAttributeToTemplate(tupleCompareRule, "operator", theBusinessRule
				.getConstrainsFacade().getAllOperators().get(0).getType());
		addAttributeToTemplate(tupleCompareRule, "column2", theBusinessRule
				.getAllTables().get(0).getAllColumns().get(1).getName());
		addAttributeToTemplate(tupleCompareRule, "errorMessage",
				theBusinessRule.getAllErrorMessages().get(0).getMessage());
		return tupleCompareRule.render();
	}

	public String getInterEntityCompareRuleTemplate(BusinessRule theBusinessRule) {
		try {
			interEntityCompareRule = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("interEntityCompareRule").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(interEntityCompareRule, "brName",
				theBusinessRule.getTriggerName());
		addAttributeToTemplate(interEntityCompareRule, "targetTable",
				theBusinessRule.getTargetTableName());
		addAttributeToTemplate(interEntityCompareRule, "table2",
				theBusinessRule.getAllTables().get(1).getName());
		addAttributeToTemplate(interEntityCompareRule, "t2column1",
				theBusinessRule.getAllTables().get(1).getAllColumns().get(0)
						.getName());
		addAttributeToTemplate(interEntityCompareRule, "eventsStringAbb",
				theBusinessRule.getConstrainsFacade().getEventsStringAbb());
		addAttributeToTemplate(interEntityCompareRule, "table1",
				theBusinessRule.getTargetTableName());
		addAttributeToTemplate(interEntityCompareRule, "t1column2",
				theBusinessRule.getAllTables().get(0).getAllColumns().get(1)
						.getName());
		addAttributeToTemplate(interEntityCompareRule, "t2column2",
				theBusinessRule.getAllTables().get(1).getAllColumns().get(1)
						.getName());
		addAttributeToTemplate(interEntityCompareRule, "t1column1",
				theBusinessRule.getAllTables().get(0).getAllColumns().get(0)
						.getName());
		addAttributeToTemplate(interEntityCompareRule, "operator",
				theBusinessRule.getConstrainsFacade().getAllOperators().get(0)
						.getType());
		addAttributeToTemplate(interEntityCompareRule, "errorMessage",
				theBusinessRule.getAllErrorMessages().get(0).getMessage());
		return interEntityCompareRule.render();
	}

	public String getEntityOtherRuleTemplate(BusinessRule theBusinessRule) {
		try {
			entityOtherRule = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("attributeRangeRule").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(entityOtherRule, "code", theBusinessRule
				.getAllErrorMessages().get(0).getMessage());
		return entityOtherRule.render();
	}

	public String getModifyRuleTemplate(BusinessRule theBusinessRule) {
		try {
			modifyRule = new ST(new String(Files.readAllBytes(Paths
					.get(getPath("modifyRule").toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addAttributeToTemplate(modifyRule, "brName",
				theBusinessRule.getTriggerName());
		addAttributeToTemplate(modifyRule, "targetTable",
				theBusinessRule.getTargetTableName());
		addAttributeToTemplate(modifyRule, "eventsStringAbb", theBusinessRule
				.getConstrainsFacade().getEventsStringAbb());
		addAttributeToTemplate(modifyRule, "table2", theBusinessRule
				.getAllTables().get(1).getName());
		addAttributeToTemplate(modifyRule, "t2column1", theBusinessRule
				.getAllTables().get(1).getAllColumns().get(0).getName());
		addAttributeToTemplate(modifyRule, "table1", theBusinessRule
				.getAllTables().get(0).getName());
		addAttributeToTemplate(modifyRule, "t2column2", theBusinessRule
				.getAllTables().get(1).getAllColumns().get(1).getName());
		addAttributeToTemplate(modifyRule, "t1column1", theBusinessRule
				.getAllTables().get(0).getAllColumns().get(0).getName());
		if (theBusinessRule.getConstrainsFacade().getAllOperators().get(0)
				.getID() == 8) {
			addAttributeToTemplate(modifyRule, "operator", "IN");
		}
		if (theBusinessRule.getConstrainsFacade().getAllOperators().get(0)
				.getID() == 9) {
			addAttributeToTemplate(modifyRule, "operator", "NOT IN");
		}
		addAttributeToTemplate(modifyRule, "values", theBusinessRule
				.getConstrainsFacade().getAllValuesAsString());
		addAttributeToTemplate(modifyRule, "errorMessage", theBusinessRule
				.getAllErrorMessages().get(0).getMessage());
		return modifyRule.render();
	}

	public void addAttributeToTemplate(ST template, String attributeName,
			String attributeValue) {
		try {
			template.remove(attributeName);
		} catch (Exception e) {
		}
		template.add(attributeName, attributeValue);
	}

	public Path getPath(String type) {
		Path pathAbsolute = Paths.get("/THO6/templates/" + type + ".txt");
		Path pathBase = Paths.get("/THO6");
		Path pathRelative = pathBase.relativize(pathAbsolute);
		return pathRelative;
	}
}
