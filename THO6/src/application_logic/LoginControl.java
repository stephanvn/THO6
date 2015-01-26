package application_logic;

import domain.definition.DAOFactorySetup;
import userinterface.LoginFrame;

public class LoginControl {
	
	private DAOFactorySetup DAOFactorySetupRef = DAOFactorySetup.getInstance();
	
	public static void main(String[] args) {
		new LoginControl();
	}
	
	public LoginControl() {
		new LoginFrame(this);
	}

	public boolean checkUser(String username,String password) {
		boolean b = false;		
		//change oracle for other db type
		if(DAOFactorySetupRef.selectUser(username, password, "oracle")) {
			BusinessRuleControl brc = BusinessRuleControl.getInstance();
			brc.setUsername(username);
			brc.fillDomainFromDatabase();
			b = true;
		}
		return b;
	}

}
