package com.revature.dao;

import java.util.List;

import com.revature.domain.Accounts;

public interface NormalUserFace {

	public void createAccount();
	public void viewAccount(int a_id);
	public List<Accounts> viewAllAccounts(int u_ID);
	public void deleteAccount(int a_id);
	public void deleteAllAcounts(int u_ID);
	public void depositMoney(int aId, String f_Name, float amt);
	public void withdrawtMoney(int aId, String f_Name, float amt);
}
