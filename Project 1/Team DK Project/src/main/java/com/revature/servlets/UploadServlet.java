package com.revature.servlets;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.dao.ExtraDao;
import com.revature.dao.ExtraDaoImpl;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
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
		InputStream isFile = null;
		int rid = -1;// get id sent with blob, currently idk

//	    String reimbID = request.getParameter("rid"); 
//	    System.out.println(reimbID);
		
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		
		System.out.println(isMultiPart);
	    /*to handle file upload
	     * 	create disk factory
	     * 	configure repo
	     * 	create file upload handler
	     *  parse request
	     * 
	     * 
	     */
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		
//		int yourMaxMemorySize = 1000;
		// Set factory constraints
//		fileFactory.setSizeThreshold(yourMaxMemorySize);
		
//		ServletContext servletContext = this.getServletConfig().getServletContext();
//		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//		fileFactory.setRepository(repository);

		ServletFileUpload upload = new ServletFileUpload(fileFactory);

		try {
			
			List<FileItem> items = upload.parseRequest(request);
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();	//create iterator for items
			//couldnt i also just a for each loop?
			
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    String itemValue = item.getString();
		    	System.out.println("item value: "+itemValue);
			    
			    if(itemValue.contains("data")) {
			    	System.out.println("data");
			    	 rid = 100002;//for now
			    	byte[] itemBytes = item.get();
			    	eD.uploadReceipt(new ByteArrayInputStream(itemBytes), rid);
			    	System.out.println("data");
			    }else if (item.isFormField()) {
			    
			        String itemName = item.getFieldName();
			    	System.out.println("item field name: "+itemName);
			    	
			    	
			    	if(itemName.equals("rID")) {
			    		rid = Integer.parseInt(itemValue);
			    	}
			

			    } else {
			    	String filename = item.getName();
			    	System.out.println("item file name: "+filename);
			        String contentType = item.getContentType();
			        System.out.println("content type: "+contentType);
			    	long sizeInBytes = item.getSize();
			    	System.out.println("byte size: "+sizeInBytes);
			    	
			    	isFile = item.getInputStream();
			    	
			    	//or get  byte array then push to a stream
//			    	byte[] fileData = item.get();
			    	
			    }
			}
			
			
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(isFile != null) {//&& id != -1
				 rid = 100000;//for now
				eD.uploadReceipt(isFile, rid);
				isFile.close();
			}
			
		}

		
//	    Part filePart = request.getPart("file"); 
//        InputStream pImage = filePart.getInputStream();
//        int rid = 100002;// get id sent with blob, currently idk
//		eD.uploadReceipt(pImage, rid);

		/*
		if(request.getContentType().equals("multipart/form-data")) {
			ExtraDao eD = new ExtraDaoImpl();

			int rid = 100002;// get id sent with blob, currently idk



			Part pRID = request.getPart("rid");
			System.out.print(pRID.toString());

			Part pImage = request.getPart("file");
			System.out.println();
			
			eD.uploadReceipt(pImage.getInputStream(), rid);
		}
		*/
		//		 PrintWriter out = response.getWriter();
		// below gets user info i guess
		// Part pReimb = request.getPart("reimb_id");
		// Scanner scanner = new Scanner(pReimb.getInputStream());
		// String sRID = scanner.next();
		// System.out.println("rid is " + sRID);


		//		 Part pImage = request.getPart("file");
		//		 InputStream isImage = pImage.getInputStream();
		//		 eD.uploadReceipt(isImage, rid);
		//		 System.out.println("Photo Added");

		//		doGet(request, response);

	}

}
