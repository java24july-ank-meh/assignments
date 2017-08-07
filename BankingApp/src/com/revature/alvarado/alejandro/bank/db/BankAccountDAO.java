package com.revature.alvarado.alejandro.bank.db;

import java.util.List;

import com.revature.alvarado.alejandro.bank.BankAccount;
import com.revature.alvarado.alejandro.bank.NotEnoughFundsException;

public interface BankAccountDAO {

	public List<BankAccount> getAllBankAccountsForUser(int userId);
	public Boolean createBankAccount(Integer userId);
	public Boolean deleteBankAccount(Integer userId, Integer accountId) 
			throws AccountNotFoundException;
	public Integer updateBankAccount(Integer userId, Integer bankId, Integer amount) 
			throws AccountNotFoundException, NotEnoughFundsException;
	
}
