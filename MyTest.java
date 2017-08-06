package MyTests;

import org.junit.Test;

import Main.BankConnections;
import Main.BankMethods;

public class MyTest {

	
	@Test
	public final void testMyConnection() throws Exception {
		BankConnections.getconnection();
	}
	
	@Test
	public final void testDeposit() {
		BankMethods methods = new BankMethods();
		methods.makeDeposit(1, 50);
	}
	
	@Test 
	public final void testWithdraw() {
		BankMethods bank = new BankMethods();
		bank.makeWithdraw(2, 50);
	}
	
	@Test
	public final void testMethod() {
		BankMethods bank  = new BankMethods();
		bank.createAccount();
	}
}
