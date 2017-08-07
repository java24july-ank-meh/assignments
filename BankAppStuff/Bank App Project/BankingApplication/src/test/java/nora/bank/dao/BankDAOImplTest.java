package nora.bank.dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankDAOImplTest {
	
	@Test
	public final void isUsernameTakenTesting() {
		BankDAO bank = new BankDAOImpl();
		assertFalse(bank.isUsernameTaken("Me"));
		assertTrue(bank.isUsernameTaken("SlimShade"));
	}
	
}
