package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;

/**
 * Servlet implementation class UserReadServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (int)session.getAttribute("user");

		System.out.println("User id: "+userID);
		
//		userID = 100000;
		System.out.println("User id: "+userID);
	
		UserDao uD = new UserDaoImpl();
        User personData = uD.readUser(userID);
        System.out.println(personData.toString());
        String json = new Gson().toJson(personData);
        System.out.println("json string-- "+json);
        response.setContentType("application/json");
        response.getWriter().write(json);
     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String f = request.getParameter("firstname");
		String m = request.getParameter("middleinitial");
		String l = request.getParameter("lastname");
		String e = request.getParameter("email");
		String un = request.getParameter("username");
		String p = request.getParameter("password");
		
		User u = new User();
		u.setFirstName(f);
		u.setMiddleInitial(m);
		u.setLastName(l);
		u.setEmail(e);
		u.setUserName(un);
		u.setPassword(p);
		
		new UserDaoImpl().updateUser2(u);
		System.out.println("updated.");
		out.println("updated.");
	}

}
