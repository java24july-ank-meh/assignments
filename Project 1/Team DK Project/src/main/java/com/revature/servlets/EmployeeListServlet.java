package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;

/**
 * Servlet implementation class EmployeeInfoServlet
 */
public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String uri = request.getRequestURI();
		
		if(uri.endsWith("/empInfoPage")) {
			gotToEmpInfoPage(request, response);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}
	
	private void gotToEmpInfoPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/Employee_Information.html";
		
        request.getRequestDispatcher(url).forward(request,response);
		
	}
	
	private void getEmpInfo(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		int eid = Integer.parseInt(request.getParameter("eid"));
		
		UserDao uD = new UserDaoImpl();
		User u = uD.readUser(eid);
		
	}

}
