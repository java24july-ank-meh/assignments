package com.doubletrouble.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.User;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req,
		HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		ERSDao dao = new ERSDaoImpl();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User u = null;
		try {
			u = dao.login(username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("Username or password invalid");
			RequestDispatcher rs = req.getRequestDispatcher("index.html");
			rs.include(req, resp);
		}
		
		System.out.println("login");
		if(u.getRole().equals("Manager")) {
			System.out.println(u.getRole());
			RequestDispatcher rs = req.getRequestDispatcher("managerHomepage.html");
			rs.forward(req, resp);
		}
		System.out.println("fail");
		
		if(u.getRole() == "Employee") {
			RequestDispatcher rs = req.getRequestDispatcher("employeeHomepage.html");
			rs.forward(req, resp);
		}
		
	}
	
}
