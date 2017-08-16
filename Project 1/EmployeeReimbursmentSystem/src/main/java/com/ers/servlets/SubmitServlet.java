package com.ers.servlets;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.ers.dao.DAO;
import com.ers.dao.DAOImplementation;
import com.ers.main.ReimbursementRequest;

/*
 * Servlet for submitting a new reimbursement request.
 */
public class SubmitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String responseString = "";
		DAO dao = new DAOImplementation();
		ReimbursementRequest rr = new ReimbursementRequest();
		HttpSession session = request.getSession();
		
		try {
			List<FileItem> ajaxItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			
			
			// Reads in each file item in the received upload
			for (FileItem item : ajaxItems) {
				
				String fieldname = item.getFieldName();
				InputStream in = item.getInputStream();
				
				switch (fieldname) {
				
				case "amount":
					StringWriter writerAmt = new StringWriter();
					IOUtils.copy(in, writerAmt, null);
					//System.out.println("writerAmt: "+writerAmt.toString());
					rr.setAmount(Double.parseDouble(writerAmt.toString()));
					writerAmt.close();
					//System.out.println("rr.amount = "+rr.getAmount());
					break;
					
				case "description":
					StringWriter writerDesc = new StringWriter();
					IOUtils.copy(in, writerDesc, null);
					rr.setDescription(writerDesc.toString());
					writerDesc.close();
					//System.out.println("rr.desc = "+rr.getDescription());
					break;
					
				case "type":
					StringWriter writerType = new StringWriter();
					IOUtils.copy(in, writerType, null);
					String typeStr = writerType.toString();
					writerType.close();
					int typeInt;
					if (typeStr.equals("Travel")) {
						typeInt = 1;
					} else if (typeStr.equals("Materials")) {
						typeInt = 2;
					} else if (typeStr.equals("Licensing or Certification")) {
						typeInt = 3;
					} else {
						typeInt = 4;
					}
					rr.setType(typeInt);
					//System.out.println("rr.type = "+rr.getType());
					break;
					
				case "imagefile":
					BufferedImage imgbuf = ImageIO.read(in);
					rr.setReceiptImg(imgbuf);
					//System.out.println("rr.receiptImg is set");
					break;
					
				default:
				}
				
				in.close();
			}
			
			// Prepares and writes to the database the ReimbursementRequest
			rr.setDateSubmitted(new Date());
			int authorId = dao.viewEmployeeInfo(session.getAttribute("username").toString(), 
					session.getAttribute("password").toString()).getUid();
			rr.setAuthorId(authorId);
			rr.setResolverId(2000000);
			rr.setStatus(1);
			
			int statusCode = dao.submitReimbursementRequest(session.getAttribute("username").toString(), 
					session.getAttribute("password").toString(), rr);
			responseString = "Complete reimbursement request upload with: "+statusCode;
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.write(responseString);
		
	}

}
