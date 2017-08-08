package com.revature.q5;

public class Substring {
	public static String substring(String str, int idx) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < idx; i++) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
}
