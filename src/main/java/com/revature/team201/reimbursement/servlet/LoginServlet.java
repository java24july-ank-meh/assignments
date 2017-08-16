package com.revature.team201.reimbursement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import com.google.gson.Gson;
import com.revature.team201.reimbursement.db.UserDAOImpl;
import com.revature.team201.reimbursement.users.User;
import com.revature.team201.reimbursement.util.ConnectionUtil;

public class LoginServlet extends HttpServlet {

	private UserDAOImpl userDAO = new UserDAOImpl();
	
	
	public LoginServlet() throws SQLException, IOException {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		// The user is trying to sign in
		// Gather the information from the request
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		
		Gson gsonObj = new Gson();
		User user = userDAO.login(username, password);		
		
		String userAsJson = gsonObj.toJson(user);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");		
		
		PrintWriter out = resp.getWriter();
		
		out.println(userAsJson);
		
		out.close();
		
	}	
	
}
