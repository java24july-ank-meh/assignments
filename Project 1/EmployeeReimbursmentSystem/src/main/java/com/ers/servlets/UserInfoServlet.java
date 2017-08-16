package com.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.ers.dao.DAO;
import com.ers.dao.DAOImplementation;
import com.ers.exceptions.UnsuccessfulConnectionException;
import com.ers.main.ERSUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/*
 * Servlet that retrieves user information from the database.
 */
public class UserInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DAO dao = new DAOImplementation();
		ERSUser userObj = null;
		try {
			userObj = dao.viewEmployeeInfo(session.getAttribute("username").toString(), session.getAttribute("password").toString());
		} catch (UnsuccessfulConnectionException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("username", userObj.getUsername());
		jsonObj.put("firstname", userObj.getFirstname());
		jsonObj.put("lastname", userObj.getLastname());
		jsonObj.put("email", userObj.getEmail());
		if (userObj.getUserRole() == 1) {
			jsonObj.put("role", "Manager");
		} else {
			jsonObj.put("role", "Employee");
		}
		
		PrintWriter out = response.getWriter();
		out.write(jsonObj.toString());
		
	}
}
