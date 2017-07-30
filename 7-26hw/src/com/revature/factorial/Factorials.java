package com.revature.factorial;

import java.util.Scanner;

public class Factorials {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int input=0;
		int value=1;
		Scanner scan= new Scanner(System.in);
		System.out.println("Please enter the value");
		String holder = scan.next();
		input =  Integer.parseInt(holder);
		scan.close();
		for(int x =1;x<=input;x++)
		{
			value=value*x;
		}
		System.out.println(value);
	}

}
