package com.revature.ers.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.revature.ers.beans.*;
import com.revature.ers.dao.*;
import com.google.gson.*;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected DAO service = new DataService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)  
			throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		User user = service.verifyUser(username, password);
		
		Gson gson = new Gson();
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		
		if(user != null) {
			HttpSession session=req.getSession(true); 
			session.setAttribute("user",user);
			out.write("{\"success\":true, \"user\": " + gson.toJson(user)  + "}");
		} else {
			out.write("{\"success\":false,\"message\":\"Incorrect username/password. Try again.\"}");
		}
		
		out.close();
	}
}
