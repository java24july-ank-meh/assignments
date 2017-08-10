package com.revature.dao;

import org.junit.Test;

import static org.junit.Assert.*;

import com.revature.domain.User;
import com.revature.exception.InvalidLoginException;

public class EmployeeDAOImplTest {
	@Test
	public final void successfulLoginReturnsValidUser() throws InvalidLoginException {
		EmployeeDAO dao = new EmployeeDAOImpl();
		User u = dao.employeeLogin("dummy", "dummy");
		System.out.println(u.toString());
		assertEquals("dummy", u.getUsername());
	}
}
