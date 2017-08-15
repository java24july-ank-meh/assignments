package com.revature.servlets;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64InputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.revature.dao.ExtraDao;
import com.revature.dao.ExtraDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.domain.Reimbursement;

import oracle.sql.BLOB;

/**
 * Servlet implementation class PhototHelper
 */
public class PhotoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PhotoViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// BufferedReader reader = request.getReader();
		// StringBuilder sb = new StringBuilder();
		// String line = reader.readLine();
		// while(line != null) {
		// sb.append(line + "\n");
		// line = reader.readLine();
		// }
		//
		// reader.close();
		//
		// System.out.println("SB: "+sb);
		// //in case for multiple sets of objects, i think
		// String params = sb.toString();
		// String[] _params = params.split("&");
		// for (String param : _params) {
		// System.out.println("params(POST)-->" + param);
		// }
		// //^ use the above to get corrrect id then exchange that id for the 100000
		// number placement
		// ReimbursementDao rD = new ReimbursementDaoImpl();
		// ExtraDao eD = new ExtraDaoImpl();
		// //
		// Reimbursement r1 = rD.readReimb(100000);
		//
		// JsonObject obj = new JsonObject();
		// byte[] recBytes = eD.downloadReceipt(r1.getrID());
		// System.out.println(recBytes);
		// String recBase64 = Base64.getEncoder().encodeToString(recBytes);
		// // String recBase64 =
		// org.apache.commons.codec.binary.Base64.encodeBase64String(recBytes);
		// // DatatypeConverter.printBase64Binary(recBytes);
		// System.out.println("rec: "+recBase64);
		// obj.addProperty("receipt", recBase64);
		//
		// response.setContentType("application/json");
		// response.getWriter().write(obj.toString());

		/* displaying photo */
		System.out.println("getting photo as inputstream");
		try {

			int rid = 100000;
			ExtraDao eD = new ExtraDaoImpl();
			// get blob
//			Blob bImage = eD.downloadReceiptB(rid);
//			InputStream is = bImage.getBinaryStream();
			
			byte[] myBytes = eD.downloadReceipt(rid);
	    
//			InputStream is = new ByteArrayInputStream(myBytes);
	        response.setContentType("image/gif");
			OutputStream os = response.getOutputStream();
			os.write(myBytes);
			os.flush();
			os.close();
			
//			BufferedImage bufImage = ImageIO.read(is);
//			OutputStream os = response.getOutputStream();
//			ImageIO.write(bufImage, "bmp", os);

			// create byte array
//			byte[] bytesImage = bImage.getBytes(1, (int) bImage.length());// new byte[len];
//			OutputStream os = response.getOutputStream();
//			os.write(bytesImage);
//			os.close();
			
			//
			// System.out.println(is.read(bytesImage));
			// is.close();
			
			/*==========================================================*/
			
//			byte[] myBytes = eD.downloadReceipt(rid);
//			int len = myBytes.length;
			// set response
//			response.setContentType("image/jpeg");
//			response.setContentLength(len);
//			OutputStream os = response.getOutputStream();
//			os.write(myBytes);
//			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @javax.servlet.annotation.MultipartConfig
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doGet(request, response);
	}

}
