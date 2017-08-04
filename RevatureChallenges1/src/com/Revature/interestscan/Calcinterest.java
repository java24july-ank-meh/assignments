package com.Revature.interestscan;

import java.util.Scanner;

public class Calcinterest {
	public int Calcint(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Principal: ");
		int p = in.nextInt();
		System.out.println("Enter Rate: ");
		int r = in.nextInt();
		System.out.println("Enter Time: ");
		int t = in.nextInt();
		return p*r*t;
	}
}
