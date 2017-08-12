package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

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
		String[] str = req.getReader().readLine().split(":");
		try {
			double amount = Double.parseDouble(str[0]);
			String description = str[1];
			Date submitted = Date.valueOf(LocalDate.now());
			int author = ((User) req.getSession().getAttribute("user")).getId();
			int type = Integer.parseInt(str[2]);
			int status = 1;
	
			if (empdao.submitReimb(
				new Reimbursement(0, amount, description, null, submitted, null, author, 0, type, status))) {
				//req.getRequestDispatcher("/employeehome.html").forward(req, resp);
				out.write("Request submitted. Hooplah!");
			} else {
				out.write	("ahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			}
		}catch (NumberFormatException|NullPointerException e) {
			//output invalid fields stufferonis
			out.write("whoopsiedaisie");
		} 
	}
}
