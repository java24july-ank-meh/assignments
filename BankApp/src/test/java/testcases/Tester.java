package testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;
import com.revature.customers.*;
import com.revature.users.*;

public class Tester {
	@Before
	public void beforeMethod() {
		System.out.println("****Executing method****");
	}
	
	@After
	public void afterMethod() {
		System.out.println("****Method complete****\n");
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("****Tests Running****\n");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("****Tests done****\n");
	}
	@Test
	public void newCustomerParametersDefaultToNull(){
		System.out.println("Currently testing new Customer info.");
		User tester = new Customer();
		Integer id = tester.getUserId();
		String name = tester.getUsername();
		assertEquals("New customer should have nulled id.", null, id);
		assertEquals("New customer should have nulled name.", null, name);
	}
	
	@Test
	public void getAccountsWillReturnEmptyIfUserIdIsInvalid() {
		System.out.println("Getting accounts with invalid id info.");
		CustomerDaoImpl tester = new CustomerDaoImpl();
		ArrayList<Account> a = tester.getAccounts(123);
		assertEquals("Invalid customer id should return empty ArrayList.", new ArrayList<Account>(), a);
	}
	
	@Test
	public void getUserReturnsNullWithInvalidCredentials() {
		System.out.println("Getting user with invalid password.");
		UserDao tester = new UserDaoImpl();
		User a = tester.getUser("josh", "pass");
		assertEquals("Invalid password should return null.", null, a);
		System.out.println("Getting user with invalid username.");
		User b = tester.getUser("thomas", "password");
		assertEquals("Invalid password should return null.", null, b);
	}
	
	@Test
	public void checkCustomerReturnsNullWithInvalidUsername() {
		System.out.println("Checking for user with invalid username.");
		UserDao tester = new UserDaoImpl();
		User a = tester.checkUser("Thomas");
		assertEquals("Invalid username should return null.", null, a);
	}
	
	

}
