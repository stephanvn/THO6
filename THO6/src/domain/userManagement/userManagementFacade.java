package domain.userManagement;

public class userManagementFacade {
	
	private BRGUser theUser;
	
	public userManagementFacade(String name) {
		theUser = new BRGUser(name);
	}

}
