package domain.generatedCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.stringtemplate.v4.ST;

import domain.definition.BusinessRule;

public class TemplateHelper {

	private ST defaultTemplate, attributeRangeRule, attributeCompareRule,
			attributeListRule;
	private BusinessRule theBusinessRule;

	public TemplateHelper(BusinessRule theBusinessRule) {
		this.theBusinessRule = theBusinessRule;
	}

	public String getDefaultCode() {
		try {
			Path pathAbsolute = Paths
					.get("/THO6/templates/defaultTemplate.txt");
			Path pathBase = Paths.get("/THO6");
			Path pathRelative = pathBase.relativize(pathAbsolute);

			defaultTemplate = new ST(new String(Files.readAllBytes(Paths
					.get(pathRelative.toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addAttributeToTemplate(defaultTemplate, "brName",
				theBusinessRule.getName());
		addAttributeToTemplate(defaultTemplate, "targetTable",
				theBusinessRule.getTargetTableName());
		return defaultTemplate.render();
	}

	public String getAttributeRangeRuleTemplate() {
		Path pathAbsolute = Paths.get("/THO6/templates/attributeRangeRule.txt");
		Path pathBase = Paths.get("/THO6");
		Path pathRelative = pathBase.relativize(pathAbsolute);

		try {
			attributeRangeRule = new ST(new String(Files.readAllBytes(Paths
					.get(pathRelative.toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addAttributeToTemplate(attributeRangeRule, "eventsStringAbb",
				theBusinessRule.getConstrainsFacade().getEventsStringAbb());
		addAttributeToTemplate(attributeRangeRule, "column", theBusinessRule
				.getAllTables().get(0).getTargetColumn());
		addAttributeToTemplate(attributeRangeRule, "value", theBusinessRule
				.getConstrainsFacade().getAllValues().get(0).getComparable());
		addAttributeToTemplate(attributeRangeRule, "value2", theBusinessRule
				.getConstrainsFacade().getAllValues().get(1).getComparable());
		addAttributeToTemplate(attributeRangeRule, "errorMessage",
				theBusinessRule.getAllErrorMessages().get(0).getMessage());
		return attributeRangeRule.render();
	}

	public String getAttributeCompareRuleTemplate() {
		Path pathAbsolute = Paths
				.get("/THO6/templates/attributeCompareRule.txt");
		Path pathBase = Paths.get("/THO6");
		Path pathRelative = pathBase.relativize(pathAbsolute);

		try {
			attributeCompareRule = new ST(new String(Files.readAllBytes(Paths
					.get(pathRelative.toUri()))), '$', '$');
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

	public String getAttributeListRuleTemplate(){
		Path pathAbsolute = Paths.get("/THO6/templates/attributeListRule.txt");
		Path pathBase = Paths.get("/THO6");
		Path pathRelative = pathBase.relativize(pathAbsolute);

		try {
			attributeListRule = new ST(new String(Files.readAllBytes(Paths
					.get(pathRelative.toUri()))), '$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addAttributeToTemplate(attributeListRule, "eventsStringAbb",
				theBusinessRule.getConstrainsFacade().getEventsStringAbb());
		addAttributeToTemplate(attributeListRule, "column",
				theBusinessRule.getAllTables().get(0).getAllColumns().get(0).getName());
		addAttributeToTemplate(attributeListRule, "values", theBusinessRule.getConstrainsFacade().getAllValuesAsString());
		return attributeListRule.render();
	}

	public void addAttributeToTemplate(ST template, String attributeName,
			String attributeValue) {
		try {
			template.remove(attributeName);
		} catch (Exception e) {
		}
		template.add(attributeName, attributeValue);
	}
}
