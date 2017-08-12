package com.revature.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.domain.User;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nameV = request.getParameter("name");
		System.out.println("name: "+nameV);
		Map<String, String[]> map = request.getParameterMap();
		
		System.out.println("Printing map: \n"+map);
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//UserDao uD = new UserDao
//		List<User> allUsers = new ArrayList<>();//UserDao uD = new USerDAoImpl(); and so allUSers=uD.readAllUsers()
//
//		//convert arraylist to json, below
//		Gson gson = new Gson();
//		String rJSON = gson.toJson(allUsers);
//
//		//setup response body for json
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		
//		//send response in json format
//		PrintWriter out = response.getWriter();
//		out.write(rJSON);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);\
//		ServletInputStream in = request.getInputStream();
//		String s = "";
//		while((s = in.) != null) {
//		}
		
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = reader.readLine();
		while(line != null) {
			sb.append(line + "\n");
			line = reader.readLine();
		}
		
		reader.close();
		
		System.out.println("SB: "+sb);
		//in case for multiple sets of objects, i think
	    String params = sb.toString();
	    String[] _params = params.split("&");
	    for (String param : _params) {
	      System.out.println("params(POST)-->" + param);
	    }
		
	}

}
