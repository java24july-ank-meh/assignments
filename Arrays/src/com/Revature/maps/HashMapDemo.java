package com.Revature.maps;

import java.util.HashMap;
import java.util.Set;

public class HashMapDemo {

	public static void main(String[] args) {
		HashMap<String, Double> balance = new HashMap<>();
		
		balance.put("John", new Double(100.50));
		balance.put("Arya", new Double(23.45));
		balance.put("Joffrey", new Double(573.40));
		balance.put("Tyrion", new Double(493.298));
		
		//random access
		System.out.println(balance.get("Arya"));
		
		//use key set
		Set<String> keys = balance.keySet();
		for(String key : keys){
			System.out.println(key + ": " + balance.get(key));
		}

	}

}
