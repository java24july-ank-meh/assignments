package com.homework.Q13;

public class Q13 {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 4; ++i) {
			if (i % 2 == 0) {
				sb.insert(0, 0 + " ");
			} else {
				sb.insert(0, 1 + " ");
			}
			System.out.println(sb.toString());
		}

	}

}
