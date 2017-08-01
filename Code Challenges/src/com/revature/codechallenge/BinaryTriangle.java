package com.revature.codechallenge;

public class BinaryTriangle {
	public static void main(String[] args) {
		boolean flip = false;
		
		for (int i = 1; i <= 4; ++i) {
			for (int z = 1; z <= i; ++z) {
				String out;
				if (flip)
					out = "1 ";
				else
					out = "0 ";
				flip = !flip;
				System.out.print(out);
			}
			System.out.println("");
		}
	}
}
