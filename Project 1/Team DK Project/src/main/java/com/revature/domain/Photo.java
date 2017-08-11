package com.revature.domain;

import com.revature.dao.ExtraDao;
import com.revature.dao.ExtraDaoImpl;

public class Photo {

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
	
	public void updatePhotoD() {
		ExtraDao eD = new ExtraDaoImpl();
		eD.addPhoto(photoID, photoData);
	}
	public void updatePhotoB() {
		ExtraDao eD = new ExtraDaoImpl();
		eD.addPhoto(photoID, photoData);
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
