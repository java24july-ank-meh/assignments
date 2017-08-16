package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;
//import com.revature.dao.UserDao;
//import com.revature.dao.UserDaoImpl;
//import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class UserById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserById() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//	String userID = request.getParameter("user");
//	System.out.println("User id: "+userID);
//	
//	userID = "100000";
//	System.out.println("User id: "+userID);
//
//	UserDao uD = new UserDaoImpl();
//    User personData = uD.readUser(Integer.parseInt(userID));
//    System.out.println(personData.toString());
//    String json = new Gson().toJson(personData);
//    System.out.println("json string-- "+json);
//    response.setContentType("application/json");
//    response.getWriter().write(json);
	
	
	
	
	
	
	
	
			throws ServletException, IOException {
		PreparedStatement prep = null;
		ResultSet rs = null;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("user_id");
		int user_id = Integer.parseInt(id);

		try (Connection conn = ConnectionUtil.getConection()) {

			prep = conn.prepareStatement("SELECT * FROM WEB_USERS WHERE USER_ID = ?");
			prep.setInt(1, user_id);

			out.print("<table width = 50% border = 3>");
			out.print("<caption> Here are the Results</caption>");

			rs = prep.executeQuery();

			ResultSetMetaData md = rs.getMetaData();
			int totalResults = md.getColumnCount();
			out.print("<tr>");
			for (int i = 1; i <= totalResults; i++) {

				out.print("<th>" + md.getColumnTypeName(i) + "</th>");
			}
			out.print("</tr>");

			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "<tr><td>"+rs.getString(2)+"<tr><td>"+rs.getString(3)+"<tr><td>"+rs.getString(4)+"<tr><td>"+rs.getString(5)+"<tr><td>"+rs.getString(6)+"<tr><td>"+rs.getString(7)+"<tr><td>"+rs.getInt(8));

			}
			
			out.print("</table>");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			out.close();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("user");
		System.out.println("User id: "+userID);
		
		userID = "100000";
		System.out.println("User id: "+userID);
	
		UserDao uD = new UserDaoImpl();
        User personData = uD.readUser(Integer.parseInt(userID));
        System.out.println(personData.toString());
        String json = new Gson().toJson(personData);
        System.out.println("json string-- "+json);
        response.setContentType("application/json");
        response.getWriter().write(json);
		doGet(request, response);
	}

}
