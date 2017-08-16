package com.revature.servlets;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;


import com.revature.dao.*;
import com.revature.domain.*;

/**
 * Servlet implementation class ReimbursementsView
 */
public class ReimbursementsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String author = request.getParameter("U_ID_AUTHOR");

		
		
		ReimbursementDao rD = new ReimbursementDaoImpl();
		Reimbursement myreimburse =  rD.readReimb(100000);
		
		System.out.println(myreimburse);
		

     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// TODO Auto-generated method stub
		//old get one reimbursement and put into json
		/*Reimbursement message = rd.readReimb(100000);
		System.out.println(message.toString());
		String json = new Gson().toJson(message);
		System.out.println("json string -- "+ json);
		response.setContentType("application/json");
		response.getWriter().write(json);*/
	}

}