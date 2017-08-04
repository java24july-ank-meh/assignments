package com.Revature.inheritor;

public class Stringmethodq18 extends Stringq18 {

	@Override
	public String changetoupper(String input) {
		return input.toUpperCase();
	}

	@Override
	public boolean checkupper(String input) {
		for (char e: input.toCharArray()){
			if(Character.isUpperCase(e)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void add10(String input) {
		System.out.println((Integer.parseInt(input)+10));
	}

}
