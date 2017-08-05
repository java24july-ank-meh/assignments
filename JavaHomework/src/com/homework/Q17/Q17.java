package com.homework.Q17;

import java.util.Scanner;

public class Q17 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Please input principle value");
		double principle = sc.nextDouble();
		System.out.println("Please input rate value");
		double rate = sc.nextDouble();
		System.out.println("Please input time value");
		double time = sc.nextDouble();
		System.out.println(interest(principle, rate, time));
	}
	
	
	public static double interest(double principle, double rate, double time) {
		return principle*rate*time;
	}
}
