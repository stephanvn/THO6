package domain;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.stringtemplate.v4.ST;

public class TemplateHelper {

	private ST defaultTemplate, attributeRangeRule;
	private BusinessRule theBusinessRule;

	public TemplateHelper(BusinessRule theBusinessRule) {
		this.theBusinessRule = theBusinessRule;
	}

	public String getDefaultCode() {
			try {
				defaultTemplate = new ST(new String(Files.readAllBytes(Paths
						.get(TemplateHelper.class
								.getResource("defaultTemplate.txt").toURI()))),
						'$', '$');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		addAttributeToTemplate(defaultTemplate, "brName",
				theBusinessRule.getName());
		addAttributeToTemplate(defaultTemplate, "targetTable",
				theBusinessRule.getTargetTableName());
		return defaultTemplate.render();
	}

	public String getAttributeRangeRuleTemplate(){
		try {
			attributeRangeRule = new ST(new String(Files.readAllBytes(Paths
					.get(TemplateHelper.class
							.getResource("attributeRangeRule.txt").toURI()))),
					'$', '$');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addAttributeToTemplate(attributeRangeRule, "eventsStringAbb",theBusinessRule.getEventsStringAbb());
		addAttributeToTemplate(attributeRangeRule, "column",theBusinessRule.getAllTables().get(0).getTargetColumn());
		addAttributeToTemplate(attributeRangeRule, "operator",theBusinessRule.getAllOperators().get(0).getType());
		addAttributeToTemplate(attributeRangeRule, "value",theBusinessRule.getAllValues().get(0).getComparable());
		addAttributeToTemplate(attributeRangeRule, "errorMessage",theBusinessRule.getAllErrorMessages().get(0).getMessage());
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
