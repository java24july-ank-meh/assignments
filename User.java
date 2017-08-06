package Main;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private int bankId;
	private String username;
	private String password;
	public double withdraw;
	public double deposit;
	public String bankType;
	double balance;

	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
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

	
	public double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	@Override
	public String toString() {

		return firstName + lastName + username + password;
	}

	public User(int id, String firstName, String lastName, int bankId, String username, String password,
			String bankType, double balance) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bankId = bankId;
		this.username = username;
		this.password = password;
		this.bankType = bankType;
		this.balance = balance;
	}

	
	
}
