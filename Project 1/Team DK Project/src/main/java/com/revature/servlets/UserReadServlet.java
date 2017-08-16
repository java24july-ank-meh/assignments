package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;

/**
 * Servlet implementation class UserReadServlet
 */
public class UserReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID = request.getParameter("user");
		System.out.println("User id: "+userID);
		
		userID = "100000";
		System.out.println("User id: "+userID);
		UserDao uD = new UserDaoImpl();
        User personData = uD.readUser(Integer.parseInt(userID));
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
		String userID = request.getParameter("user");
		System.out.println("User id: "+userID);
		
		userID = "100000";
		System.out.println("User id: "+userID);
		
		UserDao uD = new UserDaoImpl();
        User personData = uD.readUser(Integer.parseInt(userID));
        System.out.println(personData.toString());
        String json = new Gson().toJson(personData);
        System.out.println("json string-- "+json);
        response.setContentType("application/json");
        response.getWriter().write(json);
	}

}
