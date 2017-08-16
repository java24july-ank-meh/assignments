package com.revature.ers.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ers.beans.User;
import com.revature.ers.beans.UserRole;
import com.revature.ers.dao.DAO;
import com.revature.ers.dao.DataService;

public class GetReceipt extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected DAO service = new DataService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)  
			throws ServletException, IOException {
		ServletOutputStream out=res.getOutputStream();
		res.setContentType("image/*");
		
		HttpSession session=req.getSession(false);
		User user = (User)session.getAttribute("user");
		
		try {
			if(user != null) {
				int req_id = Integer.parseInt(req.getParameter("req_id"));
				if(user.getRole() == UserRole.MANAGER) {
					service.getReceipt(req_id, out);
				} else {
					service.getReceipt(user.getId(), req_id, out);
				}
			}
		} catch(Exception e) {
		}
		
		out.close();
	}
}
