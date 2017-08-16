package com.ers.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.dao.DAO;
import com.ers.dao.DAOImplementation;
import com.ers.exceptions.UnsuccessfulConnectionException;
import com.ers.main.ERSUser;

/*
 * Servlet invoked on initial web page load of either the
 * employee home page or the manager home page.
 */
public class GreetingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		// Uses username and password from session to retrieve full name of the user
		DAO dao = new DAOImplementation();
		ERSUser userObj;
		try {
			userObj = dao.viewEmployeeInfo(session.getAttribute("username").toString(), 
						session.getAttribute("password").toString());
		} catch (UnsuccessfulConnectionException e) {
			e.printStackTrace();
			out.write("<Error!>");
			return;
		}
		String fullname = userObj.getFirstname()+" "+userObj.getLastname();
		
		out.write(fullname);
		
		
	}

}
