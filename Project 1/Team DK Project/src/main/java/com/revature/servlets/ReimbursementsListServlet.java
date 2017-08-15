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
		ReimbursementDao rD = new ReimbursementDaoImpl();
<<<<<<< HEAD:Project 1/Team DK Project/src/main/java/com/revature/servlets/ReimbursementsView.java
		List<Reimbursement> mr =  new ArrayList<>();
		mr.addAll(rD.readAllReimb());
		String json = new Gson().toJson(mr.cus);
		
		System.out.println(json);
		
        response.setContentType("application/json");
        response.getWriter().write(json);
=======
        List<Reimbursement> i = rD.readAllReimb();
        
        System.out.println(i.toString());
        
		String json = new Gson().toJson(i);
        
		System.out.println("json string -- "+ json);
        
		response.setContentType("application/json");
		response.getWriter().write(json);

        System.out.println(json);
>>>>>>> ab79f61d2b553ebdf794da7c75ffb6f5943b3d31:Project 1/Team DK Project/src/main/java/com/revature/servlets/ReimbursementsListServlet.java
     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD:Project 1/Team DK Project/src/main/java/com/revature/servlets/ReimbursementsView.java
		ReimbursementDao rD = new ReimbursementDaoImpl();
		List<Reimbursement> mr =  new ArrayList<>();
		mr.addAll(rD.readAllReimb());
		String json = new Gson().toJson(mr.toString());
		json = json.replace("[", "").replace("]", "");
		System.out.println(json);
		
        response.setContentType("application/json");
        response.getWriter().write(json);
=======
		// TODO Auto-generated method stub
		//old get one reimbursement and put into json
		/*Reimbursement message = rd.readReimb(100000);
		System.out.println(message.toString());
		String json = new Gson().toJson(message);
		System.out.println("json string -- "+ json);
		response.setContentType("application/json");
		response.getWriter().write(json);*/
>>>>>>> ab79f61d2b553ebdf794da7c75ffb6f5943b3d31:Project 1/Team DK Project/src/main/java/com/revature/servlets/ReimbursementsListServlet.java
	}

}
