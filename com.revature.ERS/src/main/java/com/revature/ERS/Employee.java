package com.revature.ERS;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.Random;

import javax.imageio.ImageIO;

public class Employee {

	String u_username, u_password, u_firstname, u_lastname, u_email, ur_role;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String u_username, String u_password, String u_firstname, String u_lastname, String u_email,
			String ur_role) {
		super();
		this.u_username = u_username;
		this.u_password = u_password;
		this.u_firstname = u_firstname;
		this.u_lastname = u_lastname;
		this.u_email = u_email;
		this.ur_role = ur_role;
	}

	public String getU_username() {
		return u_username;
	}

	public void setU_username(String u_username) {
		this.u_username = u_username;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getU_firstname() {
		return u_firstname;
	}

	public void setU_firstname(String u_firstname) {
		this.u_firstname = u_firstname;
	}

	public String getU_lastname() {
		return u_lastname;
	}

	public void setU_lastname(String u_lastname) {
		this.u_lastname = u_lastname;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getUr_role() {
		return ur_role;
	}

	public void setUr_role(String ur_role) {
		this.ur_role = ur_role;
	}

	@Override
	public String toString() {
		return "Employee [u_username=" + u_username + ", u_password=" + u_password + ", u_firstname=" + u_firstname
				+ ", u_lastname=" + u_lastname + ", u_email=" + u_email + ", ur_role=" + ur_role + "]";
	}
	
	public String generateRandomPass() {
		String allChar = "AbCDefGHIJKlmnOpqrSTuvwxYz012!594";
		StringBuilder pass = new StringBuilder();
		Random rndm = new Random();
		while(pass.length() < 8) {
			int index = (int) (rndm.nextInt() * allChar.length());
			pass.append(allChar.charAt(index));
		}
		String finalStr = pass.toString();
		return finalStr;
	}
	
	public byte[] getBlob(File img) {
		byte[] imgInByte = null;
		try {
			BufferedImage bi = ImageIO.read(img);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			baos.flush();
			imgInByte = baos.toByteArray();
			baos.close();
			
		}catch(IOException io) {
			System.out.println(io.getMessage());
		}
		
		return imgInByte;
	}

}
