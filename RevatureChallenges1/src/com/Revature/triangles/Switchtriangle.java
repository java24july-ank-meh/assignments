package com.Revature.triangles;

public class Switchtriangle {
	public void printtri(int x){
		String output = "";
		int modu;
		for(int i=0; i<x; i++){
			modu = i%4;
			switch(modu){
			case(0): output = output + "0"; System.out.println(output); break;
			case(1): output = "1" + output; System.out.println(output); break;
			case(2): output = output + "1"; System.out.println(output); break;
			case(3): output = "0" + output; System.out.println(output); break;
			}
		}
	}
}
