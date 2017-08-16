package com.revature.team201.reimbursement.servlet;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;
import com.revature.team201.reimbursement.db.EmployeeDAOImpl;
import com.revature.team201.reimbursement.db.ManagerDAOImpl;
import com.revature.team201.reimbursement.db.UserDAOImpl;
import com.revature.team201.reimbursement.users.Reimbursement;
import com.revature.team201.reimbursement.users.Status;

public class ReimbursementDispatcher implements Dispatcher {

	private Pattern mainPattern = Pattern.compile("^/ReimbursementApplication/api/v1/(.*)");
	private Pattern allReimbursements = Pattern.compile("^reimbursements(\\?.*)?");
	private Pattern singleReimbursement = Pattern.compile("^reimbursements/([0-9]*)$");
	
	private ManagerDAOImpl managerDAO = new ManagerDAOImpl();
	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	private UserDAOImpl userDAO = new UserDAOImpl();
	
	@Override
	public void dispatch(String uri, HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println(uri);
		
		Matcher mainPatternMatcher = mainPattern.matcher(uri);
		
		if (mainPatternMatcher.matches()) {
			// The API level matcher is good
			
			String restUri = mainPatternMatcher.group(1);
			Matcher allReimbursementMatcher = allReimbursements.matcher(restUri);
			Matcher singleReimbursementMatcher = singleReimbursement.matcher(restUri);
			
			if (allReimbursementMatcher.matches()) {
				// Get all reimbursements, maybe with parameters
				if (req.getParameter("employeeId") == null) {
					returnAllReimbursements(req, resp);
				} else {
					returnAllReimbursementsWithParameters(req, resp);
				}
		
			} else if (singleReimbursementMatcher.matches()) {
				
				// Return a single reimbursement
				Integer id = Integer.parseInt(singleReimbursementMatcher.group(1)); 
				
				returnReimbursementImage(id, req, resp);
				
			} else {
				System.out.println("No path found in reimbursements");
			}
		} else {
			System.out.println("Didn't match the main path");
		}
		

	}

	private void returnAllReimbursements(HttpServletRequest req, HttpServletResponse resp) {
		
		List<Reimbursement> reimbursements = managerDAO.getAllPendingReimbursements();
		
		Gson gsonObj = new Gson();
		String jsonStr = gsonObj.toJson(reimbursements);
	
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		try {
			PrintWriter writer = resp.getWriter();
			
			writer.print(jsonStr);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	private void returnReimbursementImage(Integer id, HttpServletRequest req, HttpServletResponse resp) {
		
		BufferedImage r = userDAO.getReimbursementImage(id);
		
		if (r == null) {
			r = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		}
					
		resp.setContentType("image/jpeg");
		
		try (OutputStream stream = resp.getOutputStream()) {
			
			ImageIO.write(r, "jpg", stream);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	private void returnAllReimbursementsWithParameters(HttpServletRequest req, HttpServletResponse resp) {
		
		Map<String, String[]> parameters = req.getParameterMap();
		
		String[] userIds = parameters.get("employeeId");
		Integer userId = Integer.parseInt(userIds[0]);
		
		
		String[] statuses = parameters.get("status");
		Status status = Status.valueOf(statuses[0].toUpperCase());
		
		if (status != null && userId != null) {
			
			List<Reimbursement> reimbursements;
			
			switch (status) {
			case PENDING:
				reimbursements = employeeDAO.getPendingReimbursements(userId);
				break;
			case DENIED:
			case APPROVED:
				reimbursements = employeeDAO.getResolvedReimbursements(userId);
				break;
			default:
				reimbursements = new ArrayList<Reimbursement>();
			}
						
			Gson gsonObj = new Gson();
			String jsonStr = gsonObj.toJson(reimbursements);
		
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			try {
				PrintWriter writer = resp.getWriter();
				
				writer.print(jsonStr);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}
