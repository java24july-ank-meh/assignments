package com.ers.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.imageio.ImageIO;

import com.ers.dao.DAO;
import com.ers.dao.DAOImplementation;
import com.ers.exceptions.UnsuccessfulConnectionException;

public class ERSMain {
	
	public static void main(String args[]) {
		
		DAO dao = new DAOImplementation();
		int role = -1;
		
		try {
			role = dao.login("jschmoe", "121212");
			System.out.println("Success!: "+role);
		} catch (UnsuccessfulConnectionException e) {
			System.out.println("Fail!");
		}
		
		/*
		ReimbursementRequest req = new ReimbursementRequest();
		req.setAmount(3.50);
		req.setAuthorId(2000004);
		req.setDateSubmitted(new Date());
		req.setDescription("Give me money!");
		req.setType(4);
		
		try {
			int status = -1;
			status = dao.submitReimbursementRequest("jschmoe", "121212", req);
			System.out.println("Successful submit!");
		} catch (UnsuccessfulConnectionException e) {
			System.out.println("Failed to submit!");
		}
		*/
		
		try {
			dao.logout("jschmoe", "121212");
			System.out.println("Successfully logged out");
		} catch (UnsuccessfulConnectionException e) {
			System.out.println("Failed to logout");
		}
		
		
		/*
		BufferedImage imgbuf;
		try {
			imgbuf = ImageIO.read(new File("troll.jpg"));
			dao.uploadReceiptImage("jschmoe", "121212", imgbuf, 1000000);
		} catch (IOException ie) {
			System.out.println("IO exception in image upload");
			ie.printStackTrace();
		} catch (UnsuccessfulConnectionException e) {
			System.out.println("Unsuccessful connection in image upload");
			e.printStackTrace();
		}
		
		System.out.println("successfully uploaded image");
		
		BufferedImage outimgbuf = null;
		try {
			outimgbuf = dao.viewReceiptImage("jschmoe", "121212", 1000000);
		} catch (UnsuccessfulConnectionException e) {
			e.printStackTrace();
		}
		
		System.out.println("successfully retrieved image");
		
		try {
			ImageIO.write(outimgbuf, "png", new File("troll_output.png"));
		} catch (IOException e) {
			System.out.println("failed to write image to file");
			e.printStackTrace();
		}
		System.out.println("successfully wrote image to file");
		*/
		
	}
}
