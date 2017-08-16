package com.revature.ers.servlets;

import java.io.*;
import java.sql.Blob;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.revature.ers.beans.*;
import com.revature.ers.dao.*;
import com.google.gson.*;

public class ApproveRequest extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected DAO service = new DataService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)  
			throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		
		//Gson gson = new Gson();
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		HttpSession session=req.getSession(false);
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			if(user.getRole() == UserRole.MANAGER) {
				try {
					int req_id = Integer.parseInt(req.getParameter("request_id"));
					boolean success = service.approveRequest(req_id, user.getId());
					if(success) {
						out.write("{\"success\": true}");
					} else {
						out.write("{\"success\":false,\"message\":\"Bad input\"}");
					}
				} catch(Exception e) {
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
