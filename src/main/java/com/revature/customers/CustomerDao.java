package com.revature.customers;

import java.util.ArrayList;

public interface CustomerDao {
	public void updateAccount(Integer id, double balance);
	public ArrayList<Account> getAccounts(Integer id);
	public void addAccount(Integer uid, String type);
	public void closeAccount(Integer id);
}
