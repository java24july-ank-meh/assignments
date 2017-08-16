package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String link = "";
		String next = "";
		
		next = request.getParameter("link");
		if(next != null) {
			System.out.println("has value");
			System.out.println(next);
			link = next;
		} else {
			System.out.println("has no value");
			HttpSession session = request.getSession();
			link = (String) session.getAttribute("link");
		}

		System.out.println("link "+link);
		
		ServletContext context= getServletContext();
		RequestDispatcher rd = null;
		
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
		}else if(link.equals("empreimbursements")) {
			System.out.println("empreimbursements");
			rd = context.getRequestDispatcher("/manager/MViewEmpRequests.html");
		}else if(link.equals("employee")) {
			System.out.println("employee");
			rd = context.getRequestDispatcher("/manager/MViewEmp.html");
		}

		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
