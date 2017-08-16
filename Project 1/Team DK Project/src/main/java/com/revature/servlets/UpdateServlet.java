package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.domain.Reimbursement;
import com.revature.utilities.ConnectionUtil;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String amount = request.getParameter("amount");
		String description = request.getParameter("description");
		String author = request.getParameter("author");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		
		PreparedStatement pStmt1 = null;
		ResultSet rs = null;

		Reimbursement reimburse = new Reimbursement();
		
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		
		reimburse.setAmount(Double.parseDouble(amount));
		reimburse.setDescription(description);
		reimburse.setSubmitted(timestamp);
		reimburse.setAuthorID(Integer.parseInt(author));
		reimburse.setType(Integer.parseInt(type));
		reimburse.setStatus(Integer.parseInt(status));
		
		
		try (Connection conn = ConnectionUtil.getConection()) {

			String sql1 = "Insert into Reimbursements (R_Amount, R_Description, R_Submitted, U_ID_Author, RT_Type, RT_Status) Values(?, ?, ?, ?, ?, ?)";

			pStmt1 = conn.prepareStatement(sql1);
			
			pStmt1.setDouble(1, reimburse.getAmount());
			pStmt1.setInt(2, reimburse.getAuthorID());
			pStmt1.setString(3, reimburse.getDescription());
			pStmt1.setInt(4, reimburse.getStatus());
			pStmt1.setTimestamp(5, reimburse.getSubmitted());
			pStmt1.setInt(6, reimburse.getType());
			
			
			pStmt1.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pStmt1 != null) {
				try {
					pStmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		out.println("File updated!");
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
