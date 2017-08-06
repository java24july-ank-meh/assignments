package com.revature.domain;

import java.util.ArrayList;
import java.util.List;

public class AccountTypes {
	
	private static List<String> allTypes = new ArrayList<>();
	
	public static void addType(String newType) {
		allTypes.add(newType);
	}
	
	public static List<String> getAllTypes(){
		return allTypes;
	}
}
