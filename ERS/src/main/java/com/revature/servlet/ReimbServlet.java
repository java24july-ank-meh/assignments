package com.revature.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.revature.dao.ERSDAO;
import com.revature.dao.ERSDAOImpl;
import com.revature.domain.*;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class ReimbServlet extends HttpServlet{
	ERSDAO ersdao = new ERSDAOImpl();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		//String[] str = req.getReader().readLine().split(",");
		System.out.println(req.getParameter("amount"));
		try {
			double amount = Double.parseDouble(req.getParameter("amount"));//str[0]);
			String description = req.getParameter("description");//str[1];
			//System.out.println(req.getParameter("fileinput"));
			//OutputStream fileout= file.setBinaryStream(1);
			//fileout.write
			
			Part filePart = req.getPart("fileinput");
			System.out.println(filePart.getName() + "kds");// Retrieves <input type="file" name="file">
		    //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		    InputStream fileContent = filePart.getInputStream();
			Date submitted = Date.valueOf(LocalDate.now());
			int author = ((User) req.getSession().getAttribute("user")).getId();
			int type = Integer.parseInt(req.getParameter("type"));//str[2]);
			int status = 1;
	
			if (ersdao.submitReimb(
				new Reimbursement(0, amount, description, null, submitted, null, author, 0, type, status), fileContent)) {
				//req.getRequestDispatcher("/employeehome.html").forward(req, resp);
				out.write("Request submitted. Hooplah!");
			} else {
				out.write	("ahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
			}
		}catch (NumberFormatException|NullPointerException e) {
			//output invalid fields stufferonis
			out.write("whoopsiedaisie");
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		User u =((User) req.getSession().getAttribute("user"));
		u.setPendReimbs(ersdao.viewUserReimb(u, 1));
		u.setResReimbs(ersdao.viewUserReimb(u, 2));
		Gson gson = new Gson();
		String rJSON = gson.toJson(u);
		
		//Set up response body for json
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		//send response in json format
		out.write(rJSON);
	}
}
