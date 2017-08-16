package com.revature.ers.servlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.revature.ers.beans.*;
import com.revature.ers.dao.*;

@MultipartConfig
public class SubmitRequest extends HttpServlet{
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
			try {
				double amount = Double.parseDouble(req.getParameter("amount"));
				String description = req.getParameter("description");
				InputStream receipt = req.getPart("receipt").getInputStream();
				RequestType type = RequestType.valueOf(Integer.parseInt(req.getParameter("type")));
				boolean success = service.createRequest(amount,description, receipt, user.getId(), type);
				if(success) {
					out.write("{\"success\": true}");
				} else {
					out.write("{\"success\": false, \"message\": \"Failed to submit the reimbursement request. Try again.\"}");
				}
			} catch(NumberFormatException e) {
				out.write("{\"success\":false,\"message\":\"Invalid input\"}");
			}
		} else {
			out.write("{\"success\":false,\"message\":\"Not logged in\"}");
		}
		
		out.close();
	}
}
