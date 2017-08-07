package nora.bank.dao;

import java.util.List;

import nora.bank.domain.BankAccount;
import nora.bank.domain.User;

public interface BankDAO {

	//basic logging functions
	public boolean logUserIn(String username, String password);
	public void createUser(User u);
	public boolean isUsernameTaken(String username);
	
	//basic user option functions
	public List<BankAccount> getUserAccountsAndBalances(String username);
	public void createBankAccount(int userID);
	public void withdraw(int accountID, int amt);
	public void deposit(int accountID, int amt);
	public void deleteBankAccount(int accountID);
	
	//Super user option functions
	public List<User> getAllUsers();
	public void deleteUser(int accountID);
	public void editExistingUser(User user);
}
