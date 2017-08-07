package nora.bank.domain;



public class User {
	public static final int NORMAL_USER = 1;
	public static final int SUPER_USER = 2;

	int userID;
	String username;
	String password;
	String firstName;
	String lastName;
	int userType;
	
	
	
	private User(int userID, String username, String password, String firstName, String lastName, int userType) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}
	
	public User(String username, String password, String firstName, String lastName) {
		this(0, username, password, firstName, lastName, User.NORMAL_USER);
	}
	
	public User() {
		this("NoName", "p4ssw0rd", "Mary", "Jane");
	}
	
	public User(User user) {
		userID = user.userID;
		username = user.username;
		password = user.password;
		firstName = user.firstName;
		lastName = user.lastName;
		userType = user.userType;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int num) {
		this.userType = num;
	}
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userType=" + userType + "]";
	}
	
	

}
