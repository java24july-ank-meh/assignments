package com.revature.dao;

import java.util.List;

import com.revature.domain.Transaction;

public interface TransactionDao {

	public void createTransaction(int id);
	public void createTransaction(Transaction t);
	
	public List<Transaction> readAllTransaction();
	public List<Transaction> readAllTransaction(String s,int id);//2, one for acc, one for user
	
	public void deleteTransaction(int tid);
	public void deleteAllAccountTransaction(int aid);
	public void deleteAllUserTransaction(int uid);
	public void deleteAllTransaction();
	
}
