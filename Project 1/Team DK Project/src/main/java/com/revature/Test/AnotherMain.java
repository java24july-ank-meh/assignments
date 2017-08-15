package com.revature.Test;

import com.google.gson.*;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.User;

public class AnotherMain {

	public static void main(String[] args) {

		ReimbursementDaoImpl rD = new ReimbursementDaoImpl();
        String makeJson = rD.readAllReimb().toString();
        makeJson = makeJson.replace("[", "").replace("]", "");
        System.out.println(makeJson);   
        String json = new Gson().toJson(makeJson);      
        System.out.println(json);
		
		
		
	
		
		
	
//		UserDao uD = new UserDaoImpl();
//        User personData = uD.readUser(100000);
//        System.out.println(personData.toString());
//        String json = new Gson().toJson(personData);
//        System.out.println("json string-- "+json);
	}

}
