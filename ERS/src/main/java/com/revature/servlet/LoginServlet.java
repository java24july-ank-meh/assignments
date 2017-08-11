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
			out.println(currUser.toString());
			req.getSession().setAttribute("user", currUser);
			req.getRequestDispatcher("/employeehome.html").forward(req, resp);
		} catch (InvalidLoginException e) {
			//display error on page
			req.getSession().setAttribute("errorMessage", e.getMessage());

			 //getServletContext().getRequestDispatcher("/loginpage.html").forward(req, resp);
			resp.sendRedirect("loginpage.html");
			out.println("<p>"+ e.getMessage() +"</p>");
		}
	}
}
