package com.revature.team201.reimbursement.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.team201.reimbursement.db.EmployeeDAOImpl;
import com.revature.team201.reimbursement.db.ManagerDAOImpl;
import com.revature.team201.reimbursement.db.UserDAOImpl;
import com.revature.team201.reimbursement.users.Reimbursement;
import com.revature.team201.reimbursement.users.Status;
import com.revature.team201.reimbursement.util.ConnectionUtil;


@MultipartConfig
public class ReimbursementServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2150167044724512145L;

	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	private ManagerDAOImpl managerDAO = new ManagerDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("in the reimbursement doget");
		ReimbursementDispatcher dispatcher = null;
		dispatcher = new ReimbursementDispatcher();
		
		dispatcher.dispatch(req.getRequestURI(), req, resp);
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("rId"));
		Boolean denyOrApprove = Boolean.parseBoolean(req.getParameter("accepted"));
				
		Status status = denyOrApprove ? Status.APPROVED : Status.DENIED;
		
		managerDAO.updateStatusOnReimbursement(id, status);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		try (PrintWriter writer = resp.getWriter()){
			writer.println("{success: true}");
			writer.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Need to upload a new reimbursement to the server
		System.out.println("In the do post of reimbursement");
		
		
		// Gather all the parameters from the request
		Integer employeeId = Integer.parseInt(req.getParameter("employeeId"));
		Double amount = Double.parseDouble(req.getParameter("amount"));
		String description = req.getParameter("description");
		String type = req.getParameter("type");
				
		// Get the image
		Part part = req.getPart("receipt");
		
		Thread thread = new Thread(new submitImage(employeeId, amount, description, type, part));
		thread.start();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.println("{success: true}");
		
		writer.close();
		
	}
	
	private class submitImage implements Runnable {
		
		private Integer employeeId;
		private Double amount;
		private String description;
		private String type;
		private Part imagePart;
		
		public submitImage(Integer employeeId, Double amount, String description, String type, Part imagePart) {
			this.employeeId = employeeId;
			this.amount = amount;
			this.description = description;
			this.type = type;
			this.imagePart = imagePart;
			
		}

		@Override
		public void run() {
			BufferedImage image = null;
			try (InputStream imageInputStream  = imagePart.getInputStream()){
				image = ImageIO.read(imageInputStream);
			} catch (IOException e) {
				// If an an exception occurs, still fine. Image can be null
			}
			
					
			Reimbursement reimbursement = new Reimbursement(amount, description, image, employeeId, type);
			employeeDAO.submitReimbursement(reimbursement);
			
		}	
	}
	

}
