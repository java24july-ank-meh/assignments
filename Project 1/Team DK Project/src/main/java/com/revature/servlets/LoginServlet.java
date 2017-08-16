package com.revature.servlets;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
		String username =un.substring(1, un.length()-1);
		String password =pw.substring(1, pw.length()-1);

		//		request.getRequestDispatcher("info.html").include(request, response);
		System.out.println("username "+username);
		System.out.println("password "+password);

//		Enumeration e = request.getParameterNames();
//		while(e.hasMoreElements()) {
//			Object o = e.nextElement();
//			System.out.println(o.toString()+" . "+o.equals("password")+" . "+o.equals("password0"));
//		}

		boolean validate = new SiteService().validateFullLogin(username, password);

		if(validate) {
			System.out.println("valid");
			int uid = new UserDaoImpl().loginReturnID(username,password);
			User u =  new UserDaoImpl().loginReturnPartial(username,password);
	

//			if(u != null) {
				out.println("Welcome, "+u.getFirstName()+" "+u.getLastName()+"..User "+uid);
//			} else {
//				out.print("Welcome, User "+uid);
//			}

			HttpSession session = request.getSession();

			session.setAttribute("firstName", u.getFirstName());
			session.setAttribute("lastName", u.getFirstName());
			session.setAttribute("roleID", u.getRoleID());
			session.setAttribute("user", u.getuID());
			System.out.println("manager:"+u.isManager());
//			out.print("Correct loading home now");
			
			if(u.isManager()) {
				request.setAttribute("user", u.getFirstName());
				request.setAttribute("userid", u.getuID());
				RequestDispatcher rd = request.getRequestDispatcher("manhomepage.html");
				rd.forward(request, response);

//				response.sendRedirect("http://localhost:8181/ReimbursementSite/manager/homepage.html");
				
//				response.sendRedirect("http://localhost:9088/ReimbursementSite/manager/homepage.html");
			
//				request.getRequestDispatcher("manhomepage.html").include(request, response);  
			} else {
				request.getRequestDispatcher("emphomepage.html").forward(request, response);
			}

		}else {
			System.out.println("not valid");
			out.print("Sorry, username or password error!");
			request.getRequestDispatcher("login.html").include(request, response);
		}

		out.close();

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
response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
				
		String un = request.getParameter("username");
		String pw = request.getParameter("password");
		
		String username =un;//.substring(1, un.length()-1);
		String password =pw;//.substring(1, pw.length()-1);

		System.out.println("username "+username);
		System.out.println("password "+password);

		boolean validate = new SiteService().validateFullLogin(username, password);

		if(validate) {
			System.out.println("valid");
			int uid = new UserDaoImpl().loginReturnID(username,password);
			User u =  new UserDaoImpl().loginReturnPartial(username,password);
	
			out.println("Welcome, "+u.getFirstName()+" "+u.getLastName()+"..User "+uid);

			HttpSession session = request.getSession();

			session.setAttribute("firstName", u.getFirstName());
			session.setAttribute("lastName", u.getFirstName());
			session.setAttribute("roleID", u.getRoleID());
			session.setAttribute("user", u.getuID());
			
			request.setAttribute("user", u.getFirstName());
			request.setAttribute("userid", u.getuID());
			
			System.out.println("manager:"+u.isManager());
			
			if(u.isManager()==true) {
				boolean manager = true;
				String manJson = new Gson().toJson(manager);
				String unJson = new Gson().toJson(u.getUserName());
				System.out.println("mj "+manJson+", uj"+unJson);

				manJson.concat(unJson);
				System.out.println("mj "+manJson);
//				out.write("");
//				request.getRequestDispatcher("manhomepage.html").include(request, response);  
				
				response.sendRedirect("http://localhost:9080/ReimbursementSite/manhomepage.html");

//				RequestDispatcher rd = request.getRequestDispatcher("manhomepage.html");
//				rd.forward(request, response);  
			} else {
				request.getRequestDispatcher("emphomepage.html").forward(request, response);
			}

		}else {
			System.out.println("not valid");
			out.print("Sorry, username or password error!");
			request.getRequestDispatcher("login.html").include(request, response);
		}

		out.close();

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
