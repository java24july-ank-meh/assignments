package com.doubletrouble.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.User;
import com.google.gson.Gson;

public class UpdateUserServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		 int id = (int) session.getAttribute("user");
		ERSDao dao = new ERSDaoImpl();
		User u = new User();
		try {
			u = dao.viewUserInfo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String fName = req.getParameter("fName");
		String lName = req.getParameter("lName");
		String email = req.getParameter("email");
		
		if(username.equals(""))
			username = u.getUsername();
		if(password.equals(""))
			password = u.getPassword();
		if(fName.equals(""))
			fName = u.getfName();
		if(lName.equals(""))
			lName = u.getlName();
		if(email.equals(""))
			email = u.getEmail();
		
		 try {
			dao.updateUser(id, username, password, fName, lName, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			dao.requestReimbursement(amnt, description, id, type_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
			RequestDispatcher rs = req.getRequestDispatcher("profile.html");
			rs.include(req, resp);
		 
	}
}
