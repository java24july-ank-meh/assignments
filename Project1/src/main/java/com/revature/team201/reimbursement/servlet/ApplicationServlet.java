package com.revature.team201.reimbursement.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationServlet extends HttpServlet {

	private static final long serialVersionUID = 5401717489601836023L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ApplicationDispatcher dispatcher = new ApplicationDispatcher();
		
		dispatcher.dispatch(req.getRequestURI(), req, resp);
		
	}

}