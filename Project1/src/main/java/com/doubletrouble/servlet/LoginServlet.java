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
		
		User u = new User();
			try {
				u = dao.login(username, password);
			} catch (SQLException e) {
				u = new User();
				u.setRole("none");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}		
		if(u.getRole().equals("Manager")) {
			RequestDispatcher rs = req.getRequestDispatcher("managerHomepage.html");
			 HttpSession session = req.getSession(true);
	         session.setAttribute("user", u.getId());
	         
			rs.forward(req, resp);
		} else if(u.getRole().equals("Employee")) {
			RequestDispatcher rs = req.getRequestDispatcher("employeeHomepage.html");
			 HttpSession session = req.getSession(true);
	         session.setAttribute("user", u.getId());
			rs.forward(req, resp);
		} else {
			out.println("<h1>Username or password invalid</h1>");
			RequestDispatcher rs = req.getRequestDispatcher("index.html");
			rs.include(req, resp);
			rs.forward(req, resp);
		}
		
	}
	
}
