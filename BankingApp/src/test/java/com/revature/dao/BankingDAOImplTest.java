package com.revature.dao;

import java.sql.SQLException;

import org.junit.Test;

import com.revature.domain.User;
import com.revature.exception.OverdraftException;
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
	
	@Test(expected = SQLException.class)
	public final void updatingAccountWithNonExistentAccountThrowsException() throws SQLStatementFailedException, OverdraftException, SQLException {
		BankingDAOImpl dao = new BankingDAOImpl();
		dao.updateAccount("lol", 20, new User("Lol","lol",1111));
	}
}
