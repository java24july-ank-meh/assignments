package com.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.ers.dao.DAO;
import com.ers.dao.DAOImplementation;
import com.ers.exceptions.UnsuccessfulConnectionException;
import com.ers.main.ReimbursementRequest;

public class EmployeeRequestsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<ReimbursementRequest> reqArr = new ArrayList<>();
		DAO dao = new DAOImplementation();
		
		try {
			reqArr.addAll(dao.viewPendingRequests(session.getAttribute("username").toString(), 
					session.getAttribute("password").toString()));
			reqArr.addAll(dao.viewResolvedRequests(session.getAttribute("username").toString(), 
					session.getAttribute("password").toString()));
		} catch (UnsuccessfulConnectionException e) {
			e.printStackTrace();
		}
		
		/*
		List<String> jsonArr = new ArrayList<>();
		ObjectMapper objMap = new ObjectMapper();
		
		for (ReimbursementRequest rr : reqArr) {
			jsonArr.add(objMap.writeValueAsString(rr));
		}
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<jsonArr.size(); i++) {
			sb.append(jsonArr.get(i));
			if (i != jsonArr.size() - 1) {
				sb.append(",");
			}
		}
		
		out.write(sb.toString());
		*/
		List<JSONObject> jsonArr = new ArrayList<>();
		for (ReimbursementRequest rr : reqArr) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("amount", String.format("$%.2f",rr.getAmount()));
			jsonObj.put("description", rr.getDescription());
			switch (rr.getType()){
			case 1:
				jsonObj.put("type", "Travel");
				break;
			case 2:
				jsonObj.put("type", "Materials");
				break;
			case 3:
				jsonObj.put("type", "Licensing or Certification");
				break;
			case 4:
				jsonObj.put("type", "Other");
				break;
			default:
			}
			switch (rr.getStatus()) {
			case 1:
				jsonObj.put("status", "PENDING");
				break;
			case 2:
				jsonObj.put("status", "APPROVED");
				break;
			case 3:
				jsonObj.put("status", "DENIED");
				break;
			default:
			}
			jsonObj.put("rid", rr.getRid());
			
			jsonArr.add(jsonObj);
			
			
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<jsonArr.size(); i++) {
			sb.append(jsonArr.get(i));
			if (i < jsonArr.size() - 1) {
				sb.append("|");
			}
		}
		
		PrintWriter out = response.getWriter();
		out.write(sb.toString());
		
		//System.out.println(sb.toString());
		
	}

}
