package com.revature.ers.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.revature.ers.dao.*;

public class LogoutServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	protected DAO service = new DataService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)  
			throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		
		HttpSession session=req.getSession();  
		session.invalidate();
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		out.write("{\"success\":true}");
		
		out.close();
	}
}
