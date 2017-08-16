package com.revature.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.revature.dao.*;

/**
 * Servlet implementation class PhotoServlet
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
		System.out.println("getting photo as inputstream");

			int rid = 100000;
			ExtraDao eD = new ExtraDaoImpl();
			
			byte[] myBytes = eD.downloadReceipt(rid);
	    
	        response.setContentType("image/gif");
			OutputStream os = response.getOutputStream();
			os.write(myBytes);
			os.flush();
			os.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ExtraDao eD = new ExtraDaoImpl();
		InputStream isFile = null;
		int rid = -1;// get id sent with blob, currently idk

	    String reimbID = request.getParameter("rid"); 
	    System.out.println("reimbid: "+reimbID);
		
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		
		System.out.println(isMultiPart);
	    /*to handle file upload
	     * 	create disk factory
	     * 	configure repo
	     * 	create file upload handler
	     *  parse request
	     */
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(fileFactory);

		try {
			
			List<FileItem> items = upload.parseRequest(request);
			
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();	//create iterator for items
			
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
			e.printStackTrace();
		} finally {
			if(isFile != null) {//&& id != -1
				 rid = 100000;//for now
				eD.uploadReceipt(isFile, rid);
				isFile.close();
			}
			
		}
		
	}

}
