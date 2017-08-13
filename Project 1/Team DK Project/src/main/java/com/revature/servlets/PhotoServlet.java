package com.revature.servlets;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.revature.dao.ExtraDao;
import com.revature.dao.ExtraDaoImpl;

import oracle.sql.BLOB;

/**
 * Servlet implementation class PhototHelper
 */
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhotoServlet() {
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
				ExtraDao eD = new ExtraDaoImpl();

				int rid = 100000;//get id sent with blob, currently idk

		//		for (Part part : request.getParts()) {
		// File data
		//			InputStream is = part.getInputStream();
		// Write to file
					eD.uploadReciept(request.getInputStream(), rid);

		//		}

//		BufferedReader reader = request.getReader();
//
//		StringBuilder sb = new StringBuilder();
//		String line = reader.readLine();
//		while(line != null) {
//			sb.append(line + "\n");
//			line = reader.readLine();
//		}
//
//		reader.close();
//
//		System.out.println("SB: "+sb);
		
		
		
		
		//				doGet(request, response);
	}

}
