package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.domain.User;

/**
 * Servlet implementation class ManagerRedirect
 */
public class EmployeeRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeRedirect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in employee redirect");;

		String link = "";
		String next = "";

		HttpSession session = request.getSession();
		int sId = -1;
		sId = (int) session.getAttribute("user");
		String sFName = "";
		sFName = (String) session.getAttribute("firstName");
		String sLName = "";
		sFName = (String) session.getAttribute("lastName");
		String sUN = "";
		sFName = (String) session.getAttribute("userName");
		int sRole = -1;
		sRole = (int) session.getAttribute("roleID");

		if(sId != -1 && sRole==2) {
			next = request.getParameter("link");
			if(next != null) {
				System.out.println("has value");
				System.out.println(next);
				link = next;
			} else {
				System.out.println("has no value");

				link = (String) session.getAttribute("link");
			}

			System.out.println("link "+link);

			ServletContext context= getServletContext();
			RequestDispatcher rd = null;

			if(link.equals("home")) {
				System.out.println("home");
				rd = context.getRequestDispatcher("/employe/EmpHomepage.html");
			}else if(link.equals("info")) {
				System.out.println("info");
				rd = context.getRequestDispatcher("/employe/EmpInfo.html");
			}else if(link.equals("submit")) {
				System.out.println("submit");
				rd = context.getRequestDispatcher("/employe/EmpSubmit.html");
			}else if(link.equals("reimbursments")) {
				System.out.println("reimbursments");
				rd = context.getRequestDispatcher("/employe/EmpReimbs.html");
			}else if(link.equals("photo")) {
				System.out.println("photo");
				rd = context.getRequestDispatcher("/employee/EmpPhoto.html");
			}
			
			User u = new User();
			u.setUserName(sUN);
			String json = new Gson().toJson(u);
			
			response.getWriter().write(json);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
