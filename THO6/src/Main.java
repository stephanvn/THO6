import userinterface.MainFrame;
import application_logic.BusinessRuleControl;


public class Main {

	public static void main(String[] args) {
		BusinessRuleControl brc = BusinessRuleControl.getInstance();
		brc.fillDomainFromDatabase();
		
		new MainFrame(brc);
		System.out.println(brc.getAllBusinessRules().get(10).getConstrainsFacade().getAllValuesAsString());
	}

}
