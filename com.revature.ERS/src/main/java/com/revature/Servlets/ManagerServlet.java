package com.revature.Servlets;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.revature.DAO.DAOFuncImp;
import com.revature.ERS.Employee;


public class ManagerServlet extends HttpServlet{

	private static final long serialVersionUID = 2L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		int currentId = (int) session.getAttribute("userId");
		
		DAOFuncImp dao = new DAOFuncImp();
		String role = dao.readUserRole(currentId);
		
		System.out.println("current session: " + currentId + " " + role );
		//secure page so unwanted people can't see it
		if(currentId == 0 || currentId < 0) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/index.html");
			dispatch.forward(request, response);
		}
		
		if(role.equals("Manager") || role.equals("manager")) {
			
		}
		else if(role.equals("Employee") || role.equals("employee")) {
			
		}
		
		List<Employee> employees = dao.readAllEmp();
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = null;
		
		response.setContentType("application/json;charset=UTF-8");
		
		for(Employee li : employees) {
			
			json = gson.toJson(li);
			out.println(json);
			System.out.println(json);
		
		}
		
		out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
