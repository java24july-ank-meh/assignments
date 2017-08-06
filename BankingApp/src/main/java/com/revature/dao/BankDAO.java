package com.revature.dao;

import java.util.List;

import com.revature.domain.*;

public interface BankDAO {
	public void CreateBU(BankUser bu);
	public void ReadBU(BankUser bu);
	public void UpdateBU(BankUser bu);
	public void DeleteBU(BankUser bu);
	public int GetUserID(BankUser bu);
	public Accounts GetAccInfo(BankUser bu, String str);
	public int GetAccNUM(BankUser bu, String str);
	
	
	public void CreateAcc(BankUser bu);
	public void ReadAcc(Accounts ac);
	public void WithdrawAcc(Accounts ac);
	public void DepositAcc(Accounts ac);
	public void DeleteAcc(Accounts ac);
	public List<Accounts> ReadAllAcc(BankUser bu);
	
	
	

}
