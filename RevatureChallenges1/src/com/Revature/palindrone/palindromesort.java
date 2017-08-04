package com.Revature.palindrone;

import java.util.ArrayList;

public class palindromesort {
	public static ArrayList<String> seperate(ArrayList<String> entries){
		ArrayList returned = new ArrayList();
		for(String temp : entries){
			boolean palin = true;
			for(int i=0; i< temp.length();i++){
				char a,b;
				a = temp.charAt(i);
				b = temp.charAt(temp.length()-1-i);
				if(a!=b){
					palin = false;;
				}
			}
			if(palin==true){
				returned.add(temp);
			}
		}
		
		return returned;
	}
}
