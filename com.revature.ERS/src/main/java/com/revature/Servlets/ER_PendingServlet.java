package com.revature.Servlets;

import java.io.IOException;
import java.util.List;
import com.revature.DAO.*;
import com.revature.ERS.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class ER_PendingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//use only to see pending reimbursements, might be used by both manager and employee
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		//get session id
		HttpSession session = request.getSession();
		int currentId = (int) session.getAttribute("userId");
		
		DAOFuncImp dao = new DAOFuncImp();
		String role = dao.readUserRole(currentId);
		
		//secure page so unwanted people can't see it
		if(currentId == 0 || currentId < 0) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/index.html");
			dispatch.forward(request, response);
		}
		
		if(role.equals("Manager") || role.equals("manager")) {
			
		}
		else if(role.equals("Employee") || role.equals("employee")) {
			
		}
		
		
		List<Reimbursement> rmbsts = dao.readAllPendingRmbs();
		
		String json = new Gson().toJson(rmbsts);
		
		 	response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}