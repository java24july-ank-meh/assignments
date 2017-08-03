package com.revature.q18;

public class Child extends Parent{

	@Override
	public boolean containsUppercase(String input) {
		
		for(int i=0; i<input.length(); i++) {
			if(Character.isUpperCase(input.charAt(i))) {return true;}
		}
		
		return false;
	}

	@Override
	public String toUpper(String input) {
		
		StringBuilder s = new StringBuilder();
		
		for(int i=0; i<input.length(); i++) {
			char current = input.charAt(i);
			if(Character.isLowerCase(current)) {current = Character.toUpperCase(current);}
			s.append(current);
		}
		
		return s.toString();
	}

	@Override
	public int toIntPlus10(String input) {
		return Integer.parseInt(input) + 10;
	}

}
