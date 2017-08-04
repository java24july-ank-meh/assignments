package com.Revature.substring;

public class Makesubstring {
	public String substringer(int x, String y){
		String result = "";
		
		for(int i=0; i<x; i++){
			result = result + y.charAt(i);
		}
		
		return result;
	}
}
