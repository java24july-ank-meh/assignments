package com.revature.codechallenge;
abstract class StringModifier {
	public abstract boolean hasUpper(String str);
	public abstract String convertToUpper(String str);
	public abstract int stringToIntPlus10(String str);
}

class StringTest extends StringModifier {

	@Override
	public boolean hasUpper(String str) {
		char[] chrstr = str.toCharArray();
		for (int i = 0; i < chrstr.length; ++i) {
			if (Character.isUpperCase(chrstr[i]))
				return true;
		}
		return false;
	}

	@Override
	public String convertToUpper(String str) {
		return str.toUpperCase();
	}

	@Override
	public int stringToIntPlus10(String str) {
		return Integer.parseInt(str) +10;
	}
	
}

public class AbstractExtending {
	public static void main(String[] args) {
		StringTest s = new StringTest();
		String str = "I have CapiTals";
		
		System.out.println(s.hasUpper(str));
		System.out.println(s.convertToUpper(str));
		System.out.println(s.stringToIntPlus10("12312313"));
	}
}
