package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;

/**
 * Servlet implementation class ManagerRedirect
 */
public class ManagerRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerRedirect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in manager redirect");;

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
		sRole =	(int) session.getAttribute("roleID");


		String link = "";
		String next = "";

		if(sId != -1 && sRole==1) {	
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

			User u = new User();
			u.setUserName(sUN);
			u.setFirstName(sFName);
			u.setLastName(sLName);
			String json = new Gson().toJson(u);
			
			System.out.println(json);
			
			if(link.equals("home")) {
				System.out.println("home");
				rd = context.getRequestDispatcher("/manager/ManHomepage.html");			
			}else if(link.equals("info")) {
				System.out.println("info");
				rd = context.getRequestDispatcher("/manager/ManInfo.html");
			}else if(link.equals("employees")) {
				System.out.println("employees");
				rd = context.getRequestDispatcher("/manager/MViewAllEmps.html");
			}else if(link.equals("reimbursments")) {
				System.out.println("reimbursments");
				rd = context.getRequestDispatcher("/manager/MViewAllRequests.html");
			}else if(link.equals("myreimbursements")) {
				System.out.println("myreimbursements");
				rd = context.getRequestDispatcher("/manager/MyReimbs.html");
				User u2 = new User();
				u2.setuID(sId);
				String json01 = new Gson().toJson(u2);
				
				List<Reimbursement> reimbs = new ReimbursementDaoImpl().readReimbByUser(u2,"author");
				String json02 = new Gson().toJson(reimbs);
				
				System.out.println("json of info: ");
				System.out.println(json01+"//\\"+json02);
		}else if(link.equals("empreimbursements")) {
				System.out.println("empreimbursements");
				String emp = request.getParameter("employee");
				System.out.println("employee "+emp);
				rd = context.getRequestDispatcher("/manager/MViewEmpRequests.html");
				
				User u2 = new User();
				u2.setuID(Integer.parseInt(emp));
				String json11 = new Gson().toJson(u2);
				
				List<Reimbursement> reimbs = new ReimbursementDaoImpl().readReimbByUser(u2,"author");
				String json12 = new Gson().toJson(reimbs);
				
				System.out.println("json of info: ");			
				System.out.println(json11+"//\\"+json12);
			}else if(link.equals("employee")) {
				String emp = request.getParameter("employee");
				System.out.println("employee "+emp);
				rd = context.getRequestDispatcher("/manager/MViewEmp.html");
				
				User u2 = new User();
				u2.setuID(Integer.parseInt(emp));
				String json21 = new Gson().toJson(u2);
				
				List<Reimbursement> reimbs = new ReimbursementDaoImpl().readReimbByUser(u2,"author");
				String json22 = new Gson().toJson(reimbs);
				
				System.out.println("json of info: ");
				System.out.println(json21+"//\\"+json22);
			}
		
			System.out.println("json of user: "+json);
			
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
