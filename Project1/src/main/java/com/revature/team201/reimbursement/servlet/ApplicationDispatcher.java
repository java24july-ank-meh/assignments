package com.revature.team201.reimbursement.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationDispatcher implements Dispatcher {

	private Pattern mainPattern = Pattern.compile("^/ReimbursementApplication/app");
	
	@Override
	public void dispatch(String uri, HttpServletRequest req, HttpServletResponse resp) {
				
		Matcher mainMatcher = mainPattern.matcher(uri);
		
		if (mainMatcher.matches()) {
			displayMainPage(req, resp);
		} 
	}

	private void displayMainPage(HttpServletRequest req, HttpServletResponse resp) {
		try {
			// Deliver the index.html page when no path is provided
			req.getRequestDispatcher("/index.html").forward(req, resp);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
