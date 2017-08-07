package bank.junit.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.domain.AccountUser;

@SuppressWarnings("unused")
public class BankTest {

	@Test
	public void createDeleteUsersTest () {
		System.out.println("createDeleteUsersTest");
		BankDAO dao = new BankDAOImpl();
		
		AccountUser u1 = new AccountUser("n","p"); 
		dao.createAccountUser(u1);
		dao.initAccountUser(u1);
		
		AccountUser u2 = new AccountUser("nem","pes"); 
		dao.createAccountUser(u2);
		dao.initAccountUser(u2);
		
		AccountUser u3 = new AccountUser("nnmmee","password"); 
		dao.createAccountUser(u3);
		dao.initAccountUser(u3);
		
		dao.deleteAccountUser(u3.getId());
		
		int count = 0;
		count = (dao.readAllAccountUser().size()  );
		assertEquals(count,2);
	}
	
}
