package com.revature.CusDAO;

import com.revature.Customers.Customers;

public interface BankDAO {
	
	public void createCustomer(Customers cus);
	public void readAccountNumber(int number);
	public void updateCustomer(Customers cus);
	public void deleteCustomer(int id);
	// Add see account balance
	//Add withdraw and deposit store procedures.\
	public void withdraw(int take);
	public void deposit(int give);

}
