package com.doubletrouble.main;

import java.io.IOException;
import java.sql.SQLException;

import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.User;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		ERSDao dao = new ERSDaoImpl();
		User u;
		
		u = dao.login("JonSnowden", "password");
		
		System.out.println(u);
	}

}