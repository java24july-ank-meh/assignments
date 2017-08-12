package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.*;
import com.revature.domain.*;

public class EmpUpdateServlet extends HttpServlet {

	ERSDAO empdao = new ERSDAOImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String[] str = req.getReader().readLine().split(":");
		User u = (User) req.getSession().getAttribute("user");
		try {
			if (str.length == 1) {
				// uncomment when things are tied together
				out.write(u.getPassword() + ":" + u.getFirstname() + ":" + u.getLastname() + ":" + u.getEmail());

				// out.write("dummy:Dummy:Jones:dummyjones@gmail.com");
			} else {
				if (str[0] != null)
					u.setPassword(str[0]);
				if (str[1] != null)
					u.setFirstname(str[1]);
				if (str[2] != null)
					u.setLastname(str[2]);
				if (str[3] != null)
					u.setEmail(str[3]);
				if (empdao.updateEmp(u))
					out.write("Jolly good, ol' chap!");
				else
					out.write("Bad show, old boy.");
			}
		} catch (NullPointerException e) {
			out.write("fail");
		}
	}

}
