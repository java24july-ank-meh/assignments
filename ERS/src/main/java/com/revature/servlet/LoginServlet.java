package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.*;
import com.revature.domain.*;
import com.revature.exception.InvalidLoginException;

public class LoginServlet extends HttpServlet{
	
	ERSDAO empdao = new ERSDAOImpl();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		String[] str = req.getReader().readLine().split(":");
		
		String username = str[0];
		String password = str[1];

		User currUser;
		try {
			currUser = empdao.empLogin(username, password);
			req.getSession().setAttribute("user", currUser);
			out.write("success");
		} catch (InvalidLoginException e) {
			//display error on page

			out.write(e.getMessage());
		}
	}
}
