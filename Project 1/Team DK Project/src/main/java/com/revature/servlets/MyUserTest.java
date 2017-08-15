package com.revature.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Reimbursement;

public class MyUserTest extends HttpServlet{

	private static final long serialVersionUID = 1L;
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		String reimAuthor = request.getParameter("author");
//		String managementId = request.getParameter("managementId");
//		String type = request.getParameter("type");
//		String status = request.getParameter("status");
//		
//		int rA = Integer.parseInt(reimAuthor);
//		int m = Integer.parseInt(managementId);
//		int t = Integer.parseInt(type);
//		int s = Integer.parseInt(status);
		
		ReimbursementDao rd = new ReimbursementDaoImpl();
		Reimbursement message = rd.readReimb(100000);
		System.out.println(message.toString());
		String json = new Gson().toJson(message);
		System.out.println("json string -- "+ json);
		response.setContentType("application/json");
		response.getWriter().write(json);
		
		
	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ReimbursementDao rd = new ReimbursementDaoImpl();
		Reimbursement message = rd.readReimb(100000);
		System.out.println(message.toString());
		String json = new Gson().toJson(message);
		System.out.println("json string -- "+ json);
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}

}
