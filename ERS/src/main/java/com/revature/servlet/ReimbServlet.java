package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ERSDAO;
import com.revature.dao.ERSDAOImpl;
import com.revature.domain.*;

public class ReimbServlet extends HttpServlet{
	ERSDAO empdao = new ERSDAOImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			double amount = Double.parseDouble(req.getParameter("amount"));
			String description = req.getParameter("description");
			Date submitted = Date.valueOf(LocalDate.now());
			int author = ((User) req.getSession().getAttribute("user")).getId();
			int type = Integer.parseInt(req.getParameter("type"));
			int status = 1;
			
	
			if (empdao.submitReimb(
				new Reimbursement(0, amount, description, null, submitted, null, author, 0, type, status))) {
				//req.getRequestDispatcher("/employeehome.html").forward(req, resp);
				out.println("Request submitted. Hooplah!");
			} else {
				out.println("ahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			}
		}catch (NumberFormatException|NullPointerException e) {
			//output invalid fields stufferonis
			e.printStackTrace();
			req.getRequestDispatcher("/employeehome.html").forward(req, resp);
		} 
	}
}
