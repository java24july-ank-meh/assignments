package com.banking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import com.banking.dao.*;
import com.banking.domain.Account;
import com.banking.domain.User;
import com.banking.exception.OverDraftException;


public class BankDaoTest {
	User test_user;
	@Ignore
	@Test
	public void createUserSucceeds() throws IOException {
		System.out.println("Currently testing createUserSucceeds");
		BankDao dao = new BankDaoImpl();
		dao.createUser("Bro", "Test", 1);
	}
	
	@Ignore
	@Test
	public void loginSucceeds() throws IOException {
		System.out.println("Currently testing login");
		User u;
		BankDao dao = new BankDaoImpl();
		test_user = dao.login("Bro", "Test");
		System.out.println(test_user.toString());
	}
	
	@Ignore
	@Test
	public void createAccountSucceeds() throws IOException {
		System.out.println("Currently testing createAccount");
		BankDao dao = new BankDaoImpl();
		Account a = new Account( 6, test_user.getUserId(), "account", 20);	
		dao.createAccount(a);
	}
	
	@Ignore
	@Test
	public void depositAmountSucceeds() throws IOException, RuntimeException, OverDraftException{
		System.out.println("Currently testing depositAmount");
		BankDao dao = new BankDaoImpl();	
		double d = dao.depositAmount(test_user.getUserId(),"account", 20);
		System.out.println("Total = " + d);
	}

	@Ignore
	@Test
	public void deleteAccountSucceeds() throws IOException {
		System.out.println("Currently testing deleteAccount");
		BankDao dao = new BankDaoImpl();	
		dao.deleteAccount(test_user.getUserId(),"account");
		}

	@Test
	public void viewAllUsersSucceeds() throws IOException {
		System.out.println("Currently testing createAccount");
		List<User> users = new ArrayList<User>();
		BankDao dao = new BankDaoImpl();
		users = dao.viewUsers();	
		System.out.println("Printing all users");
		for(User u : users) {
			System.out.println(u.toString());
		}
		System.out.println("Done printing all users");
		
	}
	
	@Ignore
	@Test
	public void updateUserSucceeds() throws IOException {
		System.out.println("Currently testing updateUserSucceeds");
		BankDao dao = new BankDaoImpl();
		dao.updateUser(test_user.getUserId(), "Test2", "Test2", 0);
	}
	
	@Ignore
	@Test
	public void deleteUserSucceeds() throws IOException {
		System.out.println("Currently testing deleteUserSucceeds");
		BankDao dao = new BankDaoImpl();
		dao.deleteUser(test_user.getUserId());
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("****Before Class****");
	}
	@AfterClass
	public static void afterClass() {
		System.out.println("----After Class----");
	}
}
