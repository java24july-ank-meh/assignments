package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.domain.User;

public class AuthenticationServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String[] str = req.getReader().readLine().split(":");
		if (str[0].equals("logout"))
			req.getSession().setAttribute("user", null);
		try {
			User u = (User) req.getSession().getAttribute("user");
			if (u.getRole() == Integer.parseInt(str[0]))
				out.write(u.getPassword() + ":" + u.getFirstname() + ":" + u.getLastname() + ":" + u.getEmail());
			else
				out.write("fail");

		} catch (NullPointerException e) {
			out.write("fail");
		}
	}
}
