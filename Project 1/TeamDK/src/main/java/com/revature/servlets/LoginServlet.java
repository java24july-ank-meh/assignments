package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.SiteService;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;

/**
 * Servlet implementation class LoginServlet
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
		// TODO Auto-generated method stub
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
			session.setAttribute("link", "home");
			if(u.isManager()) {
//				response.sendRedirect("http://localhost:9080/ReimbursementSite/manhomepage.html");
				
//				request.getRequestDispatcher("/manager/ManHomepage.html").forward(request, response);

				/*
				 * to another servlet
				 */
//				ServletContext context= getServletContext();
//				RequestDispatcher rd= context.getRequestDispatcher("/manager");

//				rd.forward(request, response);
				//or
				response.setHeader("link", "home");
				
				System.out.println("header link: "+response.getHeader("link"));
				
				
				response.sendRedirect("/TeamDK/manager");
				
				
//				request.getRequestDispatcher("man").forward(request, response);

			} else {
				response.sendRedirect("http://localhost:9080/ReimbursementSite/emphomepage.html");
			
			}

		}else {
			System.out.println("not valid");
			out.print("Sorry, username or password error!");
			request.getRequestDispatcher("index.html").include(request, response);
		}

		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//does logout part...
		
	}

}
