package com.revature.q10;

public class Q10 {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Proper usage: java program <int> <int>");
			System.exit(0);
		}
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		System.out.println("Min is " + min(x, y));
	}
	
	public static int min(int x, int y) {
		return x <= y ? x : y;
	}
}
