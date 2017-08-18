package com.revature.Servlets;

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
import com.revature.DAO.DAOFuncImp;
import com.revature.ERS.Employee;

public class ER_EmployeeInfoServlet extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int currentId = (int) session.getAttribute("userId");
		
		DAOFuncImp dao = new DAOFuncImp();
		String role = dao.readUserRole(currentId);
		
		Employee e = new Employee();
		List<Employee> empList = new ArrayList<>();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		String json = null;
		
		
		//secure page so unwanted people can't see it
		if(currentId == 0 || currentId < 0) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/index.html");
			dispatch.forward(request, response);
		}
		
		if(role.equals("Manager") || role.equals("manager")) {//this returns a json that holds all the employees
			
			empList = dao.readAllEmp();
			
			response.setContentType("application/json");
			
			for(Employee li : empList) {
				json = gson.toJson(li);
				out.println(json);
			}
		}
		else if(role.equals("Employee") || role.equals("employee")) {//this returns the employee making the request
			List<String> strArr = dao.readEmployee(currentId);
			json = gson.toJson(strArr);
			out.println(json);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
}
