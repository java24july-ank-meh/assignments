package com.revature.alvarado.alejandro.bank.db;

import java.util.List;

import com.revature.alvarado.alejandro.bank.BankUser;

public interface BankUserDAO {
	
	public List<BankUser> getAllBankUsers();
	public BankUser signInBankUser(String name, String password) throws UserNotFoundException;
	public BankUser getBankUser(Integer userId) throws UserNotFoundException;
	public Boolean createBankUser(BankUser user);
	public Boolean updateBankUser(BankUser user);
	public Boolean deleteBankUser(Integer userId);
}
