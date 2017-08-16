package com.revature.ers.servlets;

import java.io.*;
import java.sql.Blob;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.revature.ers.beans.*;
import com.revature.ers.dao.*;
import com.google.gson.*;



public class CreateUser extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	protected DAO service = new DataService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)  
			throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession(false);
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			if(user.getRole() == UserRole.MANAGER) {
				try {
					String u_name=req.getParameter("username");
					String pass=req.getParameter("password");
					String f_name=req.getParameter("firstname");
					String l_name=req.getParameter("lastname");
					String eml=req.getParameter("email");
					int u_role=Integer.parseInt(req.getParameter("role"));
					boolean success=service.createUser(u_name, pass, f_name, l_name, eml, UserRole.valueOf(u_role));
					
					if(success) {
						out.write("{\"success\": true}");
					} else {
						out.write("{\"success\": false, \"message\": \"Failed to create new user. Try again.\"}");
					}
				}catch(Exception e) {
					out.write("{\"success\":false,\"message\":\"Bad input\"}");
				}
			} else {
				out.write("{\"success\":false,\"message\":\"You are not a manager\"}");
			}
		} else {
			out.write("{\"success\":false,\"message\":\"Not logged in\"}");
			}
		out.close();
	}
}
