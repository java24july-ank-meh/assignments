package com.revature.test.controllers;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.controllers.LoginController;
import com.revature.models.User;

public class LoginControllerTest
{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams()
	{
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams()
	{
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void ShouldLoginSuperUser()
	{
		String username = "superusername";
		String password = "superpassword";

		LoginController.loginUser();
		assertEquals("yes", outContent.toString());

		System.out.print("hello");
		assertEquals("hello", outContent.toString());
		// assertEquals("Incorrect username or password.", outContent.toString());

	}

}
