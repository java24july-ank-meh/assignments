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
		ReimbursementDao rD = new ReimbursementDaoImpl();
		List<Reimbursement> mr =  new ArrayList<>();
		mr.addAll(rD.readAllReimb());
		String json = new Gson().toJson(mr.cus);
		
		System.out.println(json);
		
        response.setContentType("application/json");
        response.getWriter().write(json);
     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReimbursementDao rD = new ReimbursementDaoImpl();
		List<Reimbursement> mr =  new ArrayList<>();
		mr.addAll(rD.readAllReimb());
		String json = new Gson().toJson(mr.toString());
		json = json.replace("[", "").replace("]", "");
		System.out.println(json);
		
        response.setContentType("application/json");
        response.getWriter().write(json);
	}

}
