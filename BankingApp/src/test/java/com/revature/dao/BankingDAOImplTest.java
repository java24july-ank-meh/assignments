package com.revature.dao;

import org.junit.Test;

import com.revature.exception.SQLStatementFailedException;
import com.revature.exception.UsernameTakenException;
public class BankingDAOImplTest {
	
	@Test(expected = SQLStatementFailedException.class)
	public final void deletingNonExistentUserThrowsException() throws SQLStatementFailedException {
		BankingDAOImpl dao = new BankingDAOImpl();
		dao.deleteUser("lol");
	}
	
	@Test(expected = UsernameTakenException.class)
	public final void takenUsernameThrowsException() throws UsernameTakenException {
		BankingDAOImpl dao = new BankingDAOImpl();
		dao.checkUsernameAvail("art");
	}
	
	@Test(expected = SQLStatementFailedException.class)
	public final void updatingNonExistentUserThrowsException() throws SQLStatementFailedException {
		BankingDAOImpl dao = new BankingDAOImpl();
		dao.updateUser("lol", "lolol", "lolololol");
	}
}
