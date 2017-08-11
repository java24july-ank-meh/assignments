package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.ExtraDao;
import com.revature.dao.ExtraDaoImpl;

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

class Photo {

	private int photoID;//same as reimbuursment id
	private String title;//title of img brought in
	private String desc;//reimbursement desc
	private byte[] photoData;//binary/byte data of image

	//-----------------------------
	
	public Photo() {

	}


	public Photo(int photoID, String title, String desc, byte[] photoData) {
		super();
		this.photoID = photoID;
		this.title = title;
		this.desc = desc;
		this.photoData = photoData;
	}
	
	//--------------------------
	
	public void updatePhoto() {
		ExtraDao eD = new ExtraDaoImpl();
		
		
	}
	/*
	 *  SqlParameter photo=new SqlParameter("reciept", SqlDbType.VarBinary);
   photo.Value=p.PhotoData;
	 * 
	 * 
	 * 
	 */
	//------------------------------------------------------------------
	
	public int getPhotoID() {
		return photoID;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public byte[] getPhotoData() {
		return photoData;
	}

	public void setPhotoData(byte[] photoData) {
		this.photoData = photoData;
	}

}

