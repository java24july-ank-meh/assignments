package com.doubletrouble.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doubletrouble.dao.ERSDao;
import com.doubletrouble.dao.ERSDaoImpl;
import com.doubletrouble.domain.User;
import com.google.gson.Gson;

public class ViewUserInfoServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		
		 HttpSession session = req.getSession();
		 int id = (int) session.getAttribute("user");
		 ERSDao dao = new ERSDaoImpl();
		 User u = new User();
		 try {
		u =	dao.viewUserInfo(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		//Create GSON object, convert arraylist to JSON
		Gson gson = new Gson();
		String rJSON = gson.toJson(u);
		
		
		
		//Set up response body for json
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		//send response in json format
		PrintWriter out = resp.getWriter();
		out.print(rJSON);
		
	
	}
}
