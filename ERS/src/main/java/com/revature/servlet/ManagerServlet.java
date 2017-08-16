package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.ERSDAO;
import com.revature.dao.ERSDAOImpl;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;

public class ManagerServlet extends HttpServlet {
	ERSDAO ersdao = new ERSDAOImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		ArrayList<User> users = ersdao.viewAllEmps();
		
		for (User u : users) {
			u.setPendReimbs(ersdao.viewUserReimb(u, 1));
		}
		Gson gson = new Gson();
		String rJSON = gson.toJson(users);
		// Set up response body for json
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		// send response in json format
		out.write(rJSON);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		String[] str = req.getReader().readLine().split(",");
		
		ersdao.updateReimb(new Reimbursement(Integer.parseInt(str[1]), Date.valueOf(LocalDate.now()),
				((User) req.getSession().getAttribute("user")).getId(), Integer.parseInt(str[0])));
	}

}
