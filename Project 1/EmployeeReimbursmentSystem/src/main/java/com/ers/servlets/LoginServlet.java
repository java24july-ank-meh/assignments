package com.ers.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.dao.DAO;
import com.ers.dao.DAOImplementation;
import com.ers.exceptions.UnsuccessfulConnectionException;

/*
 * Servlet used for logging into the ERS system. Tests for successful connection
 * to the database, and then saves login info in a Session object.
 */
public class LoginServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = null;
		
		
		DAO dao = new DAOImplementation();
		int statusCode = -1;
		
		// Parse request data
		StringBuffer sb = new StringBuffer();
		String line = "";
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      sb.append(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] loginInfo = sb.toString().split(","); // [0] = username, [1] = password
		
		try {
			
			session = request.getSession();
			session.setAttribute("username", loginInfo[0]);
			session.setAttribute("password", loginInfo[1]);
			statusCode = dao.login(session.getAttribute("username").toString(), session.getAttribute("password").toString());
			
			if (statusCode == 1) {
				response.sendRedirect(response.encodeRedirectURL("ManHome.html"));
			} else if (statusCode == 2) {
				response.sendRedirect(response.encodeRedirectURL("EmpHome.html"));
			}
			
		} catch (UnsuccessfulConnectionException e) {
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.write("Invalid username and password! "/*+session.getAttribute("username").toString()
					+" "+session.getAttribute("password").toString()*/);
			e.printStackTrace();
		}
		
		
	}
	
}
