package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Photo;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.servlets.sout;
import com.revature.utilities.ConnectionUtil;

public class MainDaoTesting {

	public static void main(String[] args) {

		ReimbursementDaoImpl rD = new ReimbursementDaoImpl();
<<<<<<< HEAD
		
		
		List<Reimbursement> mr =  new ArrayList<>();
		
		
		
//		mr.addAll(rD.readAllReimb());
//		JsonArray jArray = new JsonArray();
//		jArray.add(rD.readAllReimb());
		String json = new Gson().toJson(rD.readReimb(100000).toString());
//		json = json.replace("[", "").replace("]", "");
		System.out.println(json);
=======
//        Reimbursement i = rD.readmyReimburse();
//        System.out.println(i.toString());
//        String json = new Gson().toJson(i);
//        System.out.println(json);
>>>>>>> ab79f61d2b553ebdf794da7c75ffb6f5943b3d31

	}
}
