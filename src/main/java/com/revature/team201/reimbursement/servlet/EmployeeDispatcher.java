package com.revature.team201.reimbursement.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.team201.reimbursement.db.EmployeeDAOImpl;
import com.revature.team201.reimbursement.db.ManagerDAOImpl;
import com.revature.team201.reimbursement.users.Employee;
import com.google.gson.Gson;

public class EmployeeDispatcher implements Dispatcher {

	private Pattern mainPattern = Pattern.compile("^/ReimbursementApplication/api/v1/employees([?/].*)?");
	private Pattern singleEmployee = Pattern.compile("^/([0-9]*)$");
	
	@Override
	public void dispatch(String uri, HttpServletRequest req, HttpServletResponse resp) {
		
			
			Matcher mainPatternMatcher = mainPattern.matcher(uri);
			if(mainPatternMatcher.matches()) {
				Matcher singleEmployeeMatcher = singleEmployee.matcher(uri);
				if (uri.isEmpty()) {
					// Sends back a Json object of all employees in an arrayList
					String gson = getAllEmployees();
					
					//Set up response body for json
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					
					//send response in json format
					PrintWriter out;
					try {
						out = resp.getWriter();
						out.write(gson);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (singleEmployeeMatcher.matches()) {
					// We need to send back a single employee
					Integer empId = Integer.parseInt(singleEmployeeMatcher.group(1));
					String gson = getEmployee(empId);
					
					//Set up response body for json
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					
					//send response in json format
					PrintWriter out;
					try {
						out = resp.getWriter();
						out.write(gson);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {}
			} else {
				// none of the routes matched, must send a 404
				try {
					req.getRequestDispatcher("WEB-INF/404.html").forward(req, resp);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
	}

	private String getEmployee(Integer empId) {
		Employee employee;
		EmployeeDAOImpl dao;
		String rJSON = null;
		//Get the employee object
		dao = new EmployeeDAOImpl();
		employee = dao.getEmployeeInformation(empId);
		
		//Create GSON object, convert ArrayList to JSON
		Gson gson = new Gson();
		rJSON = gson.toJson(employee);
		return rJSON;
	}

	private String getAllEmployees() {
		ArrayList<Employee> list;
		ManagerDAOImpl dao;
		String rJSON = null;
		//Create an ArrayList of all employees using the dao
		dao = new ManagerDAOImpl();
		list = (ArrayList<Employee>) dao.getAllEmployees();
		
		//Create GSON object, convert ArrayList to JSON
		Gson gson = new Gson();
		rJSON = gson.toJson(list);
		return rJSON; 
	}
}
