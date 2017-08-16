package com.revature.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.domain.Photo;
import com.revature.domain.Reimbursement;
import com.revature.domain.User;
import com.revature.utilities.ConnectionUtil;

public class MainDaoTesting {

	public static void main(String[] args) {
		
		Reimbursement reimburse = new Reimbursement();
		StringBuilder st = new StringBuilder();
		String amount = "5";
		
		reimburse.setAmount(Double.parseDouble(amount));
		System.out.println(reimburse.getAmount());
		
			
	
		
	}
	
//	public static JsonArray createJsonArrayList(List<Reimbursement> list) {
//		JsonArray jArray = (JsonArray) Json.createArrayBuilder();
//		
//		for(Reimbursement rm1: list) {
//			jArray.add(
//					(JsonValue) Json.createObjectBuilder()
//					.add("",rm1.getrID() )
//					.add("amount", rm1.getAmount())
//					.add("description", rm1.getDescription())
//					.add("submit", (JsonValue) rm1.getSubmitted())
//					.add("resolved", (JsonValue) rm1.getResolved())
//					.add("author", rm1.getAuthorID())
//					.add("resolver", rm1.getResolverID())
//					.add("type", rm1.getType())
//					.add("status", rm1.getStatus())
//					);	
//		}
//	((JsonArrayBuilder) jArray).build();
//	
//		
//		
//		
//		return jArray;
//		
//		
//		
//	}
}
