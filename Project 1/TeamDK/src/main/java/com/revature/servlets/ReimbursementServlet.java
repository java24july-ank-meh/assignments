package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		int reimbID = (int)session.getAttribute("reimb");

		int reimbID = Integer.parseInt(request.getParameter("reimb"));
		
		System.out.println("Reimb id: "+reimbID);
		
//		reimbID = 100000;
//		System.out.println("Reimb id: "+reimbID);
	
		UserDao uD = new UserDaoImpl();
        User personData = uD.readUser(reimbID);
        System.out.println(personData.toString());
        String json = new Gson().toJson(personData);
        System.out.println("json string-- "+json);
        response.setContentType("application/json");
        response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String t = request.getParameter("type");
		PrintWriter out = response.getWriter();

		if(t.equals("update")) {
			String amount = request.getParameter("amount");
			String description = request.getParameter("description");
			String author = request.getParameter("author");
			String type = request.getParameter("type");
			String status = request.getParameter("status");
			
			Reimbursement r = new Reimbursement();
			r.setAmount(Double.parseDouble(amount));
			r.setDescription(description);
			r.setAuthorID(Integer.parseInt(author));
			r.setType(Integer.parseInt(type));
			r.setStatus(Integer.parseInt(status));
			r.setSubmitted(new Timestamp(System.currentTimeMillis()));
			
			new ReimbursementDaoImpl().updateReimbReg(r);
		} else if(t.equals("resolve")) {
			String amount = request.getParameter("amount");
			String description = request.getParameter("description");
			String author = request.getParameter("author");
			String type = request.getParameter("type");
			String status = request.getParameter("status");
			
			Reimbursement r = new Reimbursement();
			r.setAmount(Double.parseDouble(amount));
			r.setDescription(description);
			r.setAuthorID(Integer.parseInt(author));
			r.setType(Integer.parseInt(type));
			r.setStatus(Integer.parseInt(status));
			r.setSubmitted(new Timestamp(System.currentTimeMillis()));
			
			new ReimbursementDaoImpl().updateReimbReg(r);
		}
	
		System.out.println("updated.");
		out.println("updated.");
	}

}
