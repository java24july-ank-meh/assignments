package MyTests;

import java.sql.ResultSet;

import org.junit.Test;

import Main.BankConnections;
import Main.BankMethods;
import Main.SuperUser;
import Main.User;

public class MyTest {

	
//	@Test
//	public final void testMyConnection() throws Exception {
//		BankConnections.getconnection();
//	}
//	
//	@Test
//	public final void testDeposit() {
//		BankMethods methods = new BankMethods();
//		methods.makeDeposit(1, 50);
//	}
//	
//	@Test 
//	public final void testWithdraw() {
//		BankMethods bank = new BankMethods();
//		bank.makeWithdraw(2, 50);
//	}
//	
//	@Test
//	public final void testMethod() {
//		BankMethods bank  = new BankMethods();
//		bank.createAccount();
//	}
	
//	@Test 
//	public final void testUserandPass() {
//		SuperUser sup = new SuperUser();
//		sup.login();
//	}
//	
	@Test
	public final void testCreate() {
		
		User user = new User(10,"john", "Mackie", 10, "jhnn", "password","CHECKING",100);
		SuperUser sup = new SuperUser();
		sup.creatBankAccount(user);
	
	}
}
