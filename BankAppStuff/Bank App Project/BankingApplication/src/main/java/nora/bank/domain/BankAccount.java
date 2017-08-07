package nora.bank.domain;

public class BankAccount {
	private int accountID;
	private int accountHolderID;
	private double balance;

	public BankAccount(int accountID, int accountHolderID, double balance) {
		this.accountID = accountID;
		this.accountHolderID = accountHolderID;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Bank Account # " + accountID + " has a balance of $" + balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountID() {
		return accountID;
	}

	public int getAccountHolderID() {
		return accountHolderID;
	}

	
}
