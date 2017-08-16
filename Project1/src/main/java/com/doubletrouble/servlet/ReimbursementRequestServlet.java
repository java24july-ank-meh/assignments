package com.doubletrouble.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.User;
import com.google.gson.Gson;

public class ReimbursementRequestServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		
		String amount = req.getParameter("amount");
		String description = req.getParameter("description");
		String type = req.getParameter("type");
		double amnt = Double.parseDouble(amount);
		int type_id = 0;
		if(type.equals("vacation"))
				type_id = 1;
		if(type.equals("damages"))
			type_id = 2;
		if(type.equals("gas"))
			type_id = 3;	
		
		System.out.println(amnt);
		System.out.println(description);
		System.out.println(type);
		ERSDao dao = new ERSDaoImpl();
		HttpSession session = req.getSession();
		 int id = (int) session.getAttribute("user");
		 
		try {
			dao.requestReimbursement(amnt, description, id, type_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				RequestDispatcher rs = req.getRequestDispatcher("submitRequest.html");
				rs.include(req, resp);
			
	}
}
