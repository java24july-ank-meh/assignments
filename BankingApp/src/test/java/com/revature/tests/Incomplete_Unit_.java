package com.revature.tests;

import org.junit.Test;

import com.revature.domain.Clients;
import com.revature.util.Connect;

import com.revature.dao.*;
import com.revature.domain.*;

public class Incomplete_Unit_ {

		SuperUserFace admin = new S_User();
		NormalUserFace client = new N_User();
	@Test
	public final void testMyConnection() throws Exception{
		Connect.connectWithProps();
	}
	
	@Test
	public final void makeClient() throws Exception{
		Clients Jill = new Clients();
	}
	
	@Test
	public final void makeAccount() throws Exception{
		Accounts checking = new Accounts();
	}
	@Test
	public final void addAnAccount() throws Exception{
		//admin.addClient();
	}
	@Test
	public final void viewAClient() throws Exception{
		admin.viewClient(27);
	}
	@Test
	public final void viewNonexistentClient() throws Exception{
		admin.viewClient(3);
	}
	@Test
	public final void viewCLIENTS() throws Exception{
		System.out.println(admin.viewAllClients());
	}
}
