package com.revature.team201.reimbursement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.team201.reimbursement.db.EmployeeDAOImpl;
import com.revature.team201.reimbursement.db.ManagerDAOImpl;
import com.revature.team201.reimbursement.users.Employee;

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 4314301339221784439L;

	public EmployeeServlet() {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		ManagerDAOImpl mDao = null;
		mDao = new ManagerDAOImpl();
		ArrayList<Employee> arr = mDao.getAllEmployees();
		Gson gsonObj = new Gson();		
		
		String userAsJson = gsonObj.toJson(arr);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");		
		
		PrintWriter out = resp.getWriter();
		
		out.println(userAsJson);
		out.close();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		Integer userId = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String fname = req.getParameter("fName");
		String lname = req.getParameter("lName");
		String email = req.getParameter("email");

		Employee employee = new Employee(userId, username, fname, lname, email);
		EmployeeDAOImpl eDao = new EmployeeDAOImpl();
		eDao.updateEmployeeInformation(employee, password);
	}
}
