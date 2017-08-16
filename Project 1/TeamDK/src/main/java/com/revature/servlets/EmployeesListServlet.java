package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.utilities.ConnectionUtil;

/**
 * Servlet implementation class EmployeesListServlet
 */
public class EmployeesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PreparedStatement prep = null;
		ResultSet rs = null;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();



		try (Connection conn = ConnectionUtil.getConection()) {

			prep = conn.prepareStatement("SELECT * FROM  Web_Users WHERE UR_ID=2");

			out.print("<table width = 50% border = 3>");
			out.print("<caption> Here are the Employees</caption>");

			rs = prep.executeQuery();

			ResultSetMetaData md = rs.getMetaData();
			int totalResults = md.getColumnCount();
			out.print("<tr>");
			for (int i = 1; i <= totalResults; i++) {

				out.print("<th>" + md.getColumnTypeName(i) + "</th>");
			}
			out.print("</tr>");

			while (rs.next()) {
				out.print("<tr><td>" + rs.getInt(1) + "<tr><td>"+rs.getDouble(2)+"<tr><td>"+rs.getString(3)+"<tr><td>"+rs.getBlob(4)+"<tr><td>"+rs.getString(5)+"<tr><td>"+rs.getInt(6)+"<tr><td>"+rs.getInt(7)+"<tr><td>"+rs.getInt(8)+"<tr><td>"+rs.getInt(9)+"<tr><td>"+rs.getInt(10));

			}
			
			out.print("</table>");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			out.close();
		}
	}

}
