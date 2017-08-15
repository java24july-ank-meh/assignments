package com.revature.servlets;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.dao.*;
import com.revature.domain.*;

/**
 * Servlet implementation class ReimbursementsView
 */
public class ReimbursementsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementsView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReimbursementDao rD = new ReimbursementDaoImpl();
        List<Reimbursement> i = rD.readAllReimb();
        System.out.println(i.toString());
        String json = new Gson().toJson(i);
        System.out.println(json);

     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		ReimbursementDao rD = new ReimbursementDaoImpl();
//        Reimbursement r1 = rD.readReimb(100000);
//              
//        r1.setReceipt(null);
//        
//        System.out.println(r1.toString());
//        String json = new Gson().toJson(r1);
//        
//        System.out.println("json string-- "+json);
//        response.setContentType("application/json");
//        response.getWriter().write(json);
	}

}
