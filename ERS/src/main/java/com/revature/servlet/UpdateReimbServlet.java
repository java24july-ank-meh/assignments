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
import com.revature.domain.Reimbursement;
import com.revature.domain.User;

public class UpdateReimbServlet extends HttpServlet {
	ERSDAO ersdao = new ERSDAOImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String[] str = req.getReader().readLine().split(":");

		ersdao.updateReimb(new Reimbursement(Date.valueOf(LocalDate.now()),
				((User) req.getSession().getAttribute("user")).getId(), Integer.parseInt(str[0])));
		out.write("success");
	}
}
