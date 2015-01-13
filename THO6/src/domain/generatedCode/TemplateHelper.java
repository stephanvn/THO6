package domain.generatedCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.stringtemplate.v4.ST;

import domain.defenition.BusinessRule;

public class TemplateHelper {

	private ST defaultTemplate, attributeRangeRule;
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
				theBusinessRule.getEventsStringAbb());
		addAttributeToTemplate(attributeRangeRule, "column", theBusinessRule
				.getAllTables().get(0).getTargetColumn());
		addAttributeToTemplate(attributeRangeRule, "operator", theBusinessRule
				.getAllOperators().get(0).getType());
		addAttributeToTemplate(attributeRangeRule, "value", theBusinessRule
				.getAllValues().get(0).getComparable());
		addAttributeToTemplate(attributeRangeRule, "errorMessage",
				theBusinessRule.getAllErrorMessages().get(0).getMessage());
		return attributeRangeRule.render();
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
