package bank;

public interface DOA 
{
	public void createUser(String username, String password) throws Exception;
	public void createAccount(int userID) throws Exception;
	public int readAccounts(int userID) throws Exception;
	public void readBalance(int accountID) throws Exception;
	public void updateAccount(int accountID, float ammount) throws Exception;
	public void updateUsername(int userID, String newName)throws Exception;
	public void updatePassword(int userID, String newPassword)throws Exception;
	public void deleteAccount(int accountID)throws Exception;
	public void deleteUser(int userID)throws Exception;
}
