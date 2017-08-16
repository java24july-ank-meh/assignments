package com.revature.ers.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.revature.ers.beans.*;
import com.revature.ers.dao.*;
import com.google.gson.*;

public class GetEmployees extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected DAO service = new DataService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)  
			throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		
		Gson gson = new Gson();
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession(false);
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			List<User> employees = null;
			if(user.getRole() == UserRole.MANAGER) {
				employees = service.getEmployees();
				if(employees != null) {
					out.write("{\"success\":true, \"employees\": " + gson.toJson(employees)  + "}");
				} else {
					out.write("{\"success\":false,\"message\":\"Could not get employees}");
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
