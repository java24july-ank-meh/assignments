package com.revature.bankingTests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.Account;
import com.revature.domain.BankUser;
import com.revature.domain.SuperUser;

public class DAOTest {

	@Test
	public void countTest() {
		BankUser b = new BankUser("test", "test", "test", "test");
		SuperUser su = SuperUser.createSuperUser("chris", "chris", "chris", "chris");
		
		BankDAO bankdao = new BankDAOImpl();
		int beforeInsert = bankdao.userCount();
		bankdao.createBankUser(b);
		int afterInsert = bankdao.userCount();
		
		Account a = new Account(b, 0, "CHECKING", 1);
		int beforeAccountInsert = bankdao.accountCount();
		bankdao.createAccount(b, a);
		int afterAccountInsert = bankdao.accountCount();
		
		assertEquals(beforeInsert + 1, afterInsert);
		assertEquals(beforeAccountInsert + 1, afterAccountInsert);
		
		bankdao.deleteAccount(b, a);
		int afterAccountDelete = bankdao.accountCount();
		
		assertEquals(beforeAccountInsert, afterAccountDelete);
		
		bankdao.deleteBankUser(su, b);
		int afterDelete = bankdao.userCount();
		
		assertEquals(beforeInsert, afterDelete);
		
	}
	
	@Test
	public void withdrawalDepositTest() {
		BankUser b = new BankUser("test", "test", "test", "test");
		SuperUser su = SuperUser.createSuperUser("chris", "chris", "chris", "chris");
		double initialBalance = 5.00;
		Account a = new Account(b, initialBalance, "CHECKING", 1.0);
		
		BankDAO bankdao = new BankDAOImpl();
		bankdao.createBankUser(b);
		bankdao.createAccount(b, a);
		
		double withdrawalAmt = 1.00;
		bankdao.transaction(b, a, -withdrawalAmt);
		
		double newBalance = bankdao.viewBalance(b, a);
		assertEquals(newBalance, initialBalance-withdrawalAmt, 0.001);
		
		double depositAmt = 2.00;
		bankdao.transaction(b, a, depositAmt);
		
		double newBalance2 = bankdao.viewBalance(b, a);
		assertEquals(newBalance2, newBalance+depositAmt, 0.001);
		
		bankdao.deleteAccount(b, a);
		bankdao.deleteBankUser(su, b);
	}
	
	@Test
	public void loggedInTest() {
		BankUser b = new BankUser("test", "test", "test", "test");
		SuperUser su = SuperUser.createSuperUser("chris", "chris", "chris", "chris");
		
		BankDAO bankdao = new BankDAOImpl();
		bankdao.createBankUser(b);
		bankdao.login(b);
		
		assertTrue(bankdao.isLoggedIn(b));
		
		bankdao.logout(b);
		
		assertFalse(bankdao.isLoggedIn(b));
		
		bankdao.deleteBankUser(su, b);
		
	}
}
