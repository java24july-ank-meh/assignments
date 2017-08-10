package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		PrintWriter out = resp.getWriter();	
		User currUser;
		try {
			currUser = empdao.empLogin(username, password);
			req.getSession().setAttribute("user", currUser);
			out.println("We in here, doin servlet shizz");
		} catch (InvalidLoginException e) {
			//display error on page
			out.println("<p>" + e.getMessage() + "</p>");
		}
	}
}
