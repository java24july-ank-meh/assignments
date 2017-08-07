package com.revature.domain;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.dao.DataService;
import com.revature.exceptions.ExistingUserException;
import com.revature.exceptions.UserNotFoundException;

public class UserTest {
	public static DataService service = new DataService();
	
	@Test
	public void creatingUser() throws ExistingUserException, UserNotFoundException{
		User user = new User("testing user 123", "password123");
		service.createUser(user);
		assertNotEquals(user.getId(),-1);
		service.deleteUser(user);
	}

}
