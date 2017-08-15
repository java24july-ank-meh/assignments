package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dao.*;
import com.revature.domain.User;

/**
 * Servlet implementation class FirstServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	response.sendRedirect("Request.html");
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		request.getRequestDispatcher("info.html").include(request, response);
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
		boolean validate = new SiteService().validateFullLogin(un, pw);
		
		if(validate) {
			
			User u = new UserDaoImpl();
		}
		
		/*//gets all users and sends them to html...ummm what
		UserDao uD = new UserDaoImpl();
		ArrayList<User> allUsers = (ArrayList<User>) uD.readAllUsers();//
		System.out.println("printing all users from login servlet");
		
		for(User u:allUsers) {
			System.out.println(u.toString());
		}
		
		//convert arraylist to json, below
		Gson gson = new Gson();
		String rJSON = gson.toJson(allUsers);

		//setup response body for json
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		//send response in json format
		PrintWriter out = response.getWriter();
		out.write(rJSON);*/
//		System.out.println(allUsers);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession();
		session.invalidate();
		
		out.print("You are successfully logged out!");
		
		out.close();
	}

}
