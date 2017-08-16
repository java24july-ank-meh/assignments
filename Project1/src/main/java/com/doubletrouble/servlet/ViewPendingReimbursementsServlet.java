package com.doubletrouble.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doubletrouble.dao.CHERSDao;
import com.doubletrouble.dao.CHERSDaoImpl;
import com.doubletrouble.domain.Reimbursements;
import com.google.gson.Gson;

public class ViewPendingReimbursementsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
	 CHERSDao dao = new CHERSDaoImpl();
	 List<Reimbursements> rbs = new ArrayList<Reimbursements>();
	 try {
	rbs =	dao.viewPending();
	} catch (SQLException e) {
		e.printStackTrace();
	}

		
	//Create GSON object, convert arraylist to JSON
	Gson gson = new Gson();
	String rJSON = gson.toJson(rbs);
	
	//Set up response body for json
	resp.setContentType("application/json");
	resp.setCharacterEncoding("UTF-8");
	
	//send response in json format
	PrintWriter out = resp.getWriter();
	out.print(rJSON);
	}
}
