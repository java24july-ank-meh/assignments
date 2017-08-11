package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;

import com.revature.domain.Photo;
import com.revature.utilities.ConnectionUtil;

public class MainDaoTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Photo p = new Photo();
//		p.setPhotoID();
//		p.updatePhotoD();
		try {
//			ConnectionUtil.getConectionProperties();
			ConnectionUtil.getConection();
		} catch (/*IOException |*/  SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
