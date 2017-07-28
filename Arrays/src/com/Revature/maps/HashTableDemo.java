package com.Revature.maps;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashTableDemo {

	public static void main(String[] args) {
		Hashtable<String, Double> balance = new Hashtable<>();
		
		balance.put("John", new Double(100.50));
		balance.put("Arya", new Double(23.45));
		balance.put("Joffrey", new Double(573.40));
		balance.put("Tyrion", new Double(493.298));
		
		//random access
		System.out.println(balance.get("Arya"));
		
		Enumeration names;
		String str;
		
		//using key set
		names = balance.keys();
		while(names.hasMoreElements()){
			str = (String) names.nextElement();
			System.out.println(str + ": " + balance.get(str));
		}
	}

}
