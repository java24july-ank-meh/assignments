package com.revature.ers.main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.imageio.ImageIO;

import com.revature.ers.beans.*;
import com.revature.ers.dao.*;

public class Main {

	public static void main(String[] args) throws IOException {
		DAO service = new DataService();
		User tai = service.getUser("tai");
		User javier = service.verifyUser("javier","password");
		User mehrab = service.getUser("mehrab");
		System.out.println(service.createUser("hello", "world", "firstname", "lastname", "email", UserRole.MANAGER));
		
		
		List<Request> javierReqs = service.getPendingRequests(javier.getId());
		List<Request> javierReqs2 = service.getResolvedRequests(javier.getId());
		//System.out.println(service.createRequest("description", null, javier.getId(), RequestType.RELOCATION));
		//System.out.println(service.denyRequest(javierReqs.get(0),mehrab));
		
		System.out.println(mehrab);
		System.out.println(tai);
		System.out.println(javier);
		System.out.println(javierReqs);
		System.out.println(javierReqs2);
		System.out.println(service.getPendingRequests());
		System.out.println(service.getResolvedRequests());
		
		List<Request> taiReqs = service.getPendingRequests(tai.getId());
		System.out.println(taiReqs);
		
		//File file = new File("image.png");
        //FileOutputStream out = new FileOutputStream(file);
		//service.getReceipt(1, 64, out);
		//out.close();
	}

}
