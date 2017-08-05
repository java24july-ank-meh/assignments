package com.homework.Q18;

public class Q18 extends StringManipulator{

	public static void main(String[] args) {
		//examples
		StringManipulator sm = new Q18();
		System.out.println(sm.isUpper("HELLO"));
		System.out.println(sm.isUpper("HElLO"));
		System.out.println(sm.lowerToUpper("HEllo"));
		sm.addTen("5");
	}

	@Override
	public boolean isUpper(String s) {
		if(s == s.toUpperCase())
			return true;
		return false;
	}

	@Override
	public String lowerToUpper(String s) {
		return s.toUpperCase();
	}

	@Override
	public void addTen(String s) {
		System.out.println(Integer.parseInt(s));
	}

}
