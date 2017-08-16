package com.revature.ers.servlets;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.revature.ers.beans.*;
import com.revature.ers.dao.*;
import com.google.gson.*;

public class GetPendingRequestsServlet extends HttpServlet{
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
			List<Request> requests = null;
			
			if(user.getRole() == UserRole.EMPLYEE) {
				requests = service.getPendingRequests(user.getId());
			} else if(user.getRole() == UserRole.MANAGER) {
				requests = service.getPendingRequests();
			}
			
			if(requests != null) {
				out.write("{\"success\":true, \"requests\": " + gson.toJson(requests)  + "}");
			} else {
				out.write("{\"success\":false,\"message\":\"Could not get requests\" }");
			}
		} else {
			out.write("{\"success\":false,\"message\":\"Not logged in\"}");
		}
		
		out.close();
	}
}
