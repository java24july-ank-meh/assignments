package com.revature.Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.DAO.DAOFuncImp;
import com.revature.ERS.Employee;


public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DAOFuncImp dao = new DAOFuncImp();
		
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
	
		 
				
		int sessionId = dao.userLogin(username, password);
		String user_role = dao.readUserRole(sessionId);
		
		if(sessionId < 1) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/index.html");
			dispatch.forward(request, response);
			
		}
		else {
			if(user_role.equals("Manager") || user_role.equals("manager")) {
				session.setAttribute("userId", sessionId);
				System.out.println(session.getAttribute("userId"));
				RequestDispatcher dispatch = request.getRequestDispatcher("ManagerHomepage.html");
				dispatch.forward(request, response);
			}
			else if(user_role.equals("Employee") || user_role.equals("employee")) {
				session.setAttribute("userId", sessionId);
				System.out.println(session.getAttribute("userId"));
				RequestDispatcher dispatch = request.getRequestDispatcher("EmployeeHomepage.html");
				dispatch.forward(request, response);
			}
		}
		
		
		System.out.println(sessionId + user_role);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
