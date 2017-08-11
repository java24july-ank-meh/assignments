package com.revature.dao;

import org.junit.Test;

import static org.junit.Assert.*;

import com.revature.domain.User;
import com.revature.exception.InvalidLoginException;

public class ERSDAOImplTest {
	@Test
	public final void successfulLoginReturnsValidUser() throws InvalidLoginException {
		ERSDAO dao = new ERSDAOImpl();
		User u = dao.empLogin("dummy", "dummy");
		System.out.println(u.toString());
		assertEquals("dummy", u.getUsername());
	}
}
