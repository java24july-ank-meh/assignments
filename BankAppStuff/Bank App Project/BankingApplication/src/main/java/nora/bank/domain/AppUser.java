package nora.bank.domain;

/**
 * 
 * @author Nora Duckett
 * This class is just designed to be a singleton version of User. It stores the information of the user
 * that is using the application so that the information can be accessed from anywhere in the app
 * without too much hassle for me while implementing things.
 *
 */
public class AppUser {
	private static User user;
	
	public AppUser() {
		if(user == null) {
			user = new User();
		}
	}
	
	public User getUser() {
		return user;
	}
	
}
