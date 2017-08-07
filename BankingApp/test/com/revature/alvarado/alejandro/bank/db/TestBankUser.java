package com.revature.alvarado.alejandro.bank.db;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.alvarado.alejandro.bank.BankUser;

public class TestBankUser {

	private static BankUserDAOImpl userDAO;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		userDAO = new BankUserDAOImpl();
	}
	
	@Test
	public void userMustBeCreated() {
		
		String name = "TESTUSER1";
		String password = "password";
		
		BankUser user = new BankUser(0, name, false, password);
		
		userDAO.createBankUser(user);
		
		List<BankUser> users = userDAO.getAllBankUsers();
		
		Boolean found = false;
		for (BankUser u : users) {
			if (u.getName().equals(name) && u.getPassword().equals(password)) {
				found = true;
			}
		}
		assertTrue(found);
				
	}
	
	@Test
	public void userMustBeDeleted() {
		
		String name = "TESTUSER2";
		String password = "password";
		
		BankUser user = new BankUser(0, name, false, password);
		
		userDAO.createBankUser(user);
		
		List<BankUser> users = userDAO.getAllBankUsers();
		
		Integer userId = 0;
		for (BankUser u: users) {
			if (u.getName().equals(name) && u.getPassword().equals(password)) {
				userId = u.getUserId();
			}
		}
		
		userDAO.deleteBankUser(userId);
		
		users = userDAO.getAllBankUsers();
		
		Boolean success = true;
		for (BankUser u: users) {
			if (u.getName().equals(name) && u.getPassword().equals(password)) {
				success = false;
			}
		}
		
		assertTrue(success);
		
	}
	
	@Test
	public void userMustBeUpdated() {
		String name = "TESTUSER3";
		String password = "password";
		
		BankUser user = new BankUser(0, name, false, password);
		
		userDAO.createBankUser(user);
		
		List<BankUser> users = userDAO.getAllBankUsers();
		
		Integer userId = 0;
		for (BankUser u : users) {
			if (u.getName().equals(name) && u.getPassword().equals(password)) {
				userId = u.getUserId();
			}
		}
		
		userDAO.updateBankUser(new BankUser(userId, name + "changed", false, password));
		
		
		users = userDAO.getAllBankUsers();
		
		Boolean success = false;
		for (BankUser u : users) {
			if (u.getName().equals(name+"changed") && u.getPassword().equals(password)) {
				success = true;
			}
		}
		
		assertTrue(success);
		
	}
	

}
