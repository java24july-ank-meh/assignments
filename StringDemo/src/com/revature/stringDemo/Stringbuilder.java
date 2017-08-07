package com.revature.stringDemo;

public class Stringbuilder {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for(char current = 'a';current<='z';current++) {
			sb.append(current);
		}
		System.out.println(sb);
	}
}
