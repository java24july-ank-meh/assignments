package com.revature.dao;

import java.util.List;

import com.revature.domain.*;

public interface BankDAO {
	public void CreateBU(BankUser bu);
	public  BankUser ReadBU(String un, String pw);
	public BankUser UpdateBU(BankUser bu, String ut1, String ut2, String ut3, String ut4);
	public void DeleteBU(BankUser bu);
	public int GetUserID(BankUser bu);
	public Accounts GetAccInfo(BankUser bu, String str);
	public int GetAccNUM(BankUser bu, String str);
	
	
	public void CreateAcc(BankUser bu);
	public Accounts ReadAcc(BankUser bu, String t);
	public void WithdrawAcc(Accounts ac, int amount);
	public void DepositAcc(Accounts ac, int amount);
	public void DeleteAcc(Accounts ac);
	public List<Accounts> ReadAllAcc(BankUser bu);
	public List<Accounts> ReadUserAcc(BankUser bu);
	public List<Accounts> ReadUserAllAcc(BankUser bu);
	public List<BankUser> ReadAllBankUsers();
 	
	
	

}
