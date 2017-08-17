package com.roberto.abstracts;

public class SubclassAbstractImp extends ParentAbstract{

	public static void main(String[] args) {
		SubclassAbstractImp sub = new SubclassAbstractImp();
		
		System.out.println(sub.lookUpperCase("i am VEry SLeepY"));
		System.out.println(sub.lookUpperCase("i am not"));
		
		System.out.println(sub.lowerToUpper("i was smol, but now im tol"));
		
		sub.stringToInt("243945");
	}

	@Override
	Boolean lookUpperCase(String str) {
		boolean myBool = false;
		for(char c: str.toCharArray()) {
			if(Character.isLetter(c) && Character.isUpperCase(c)) {
				myBool = true;
			}
			else {
				myBool = false;
			}
		}
		return myBool;
	}

	@Override
	String lowerToUpper(String str) {
		
		String newStr = str.toUpperCase();
		return newStr;
	}

	@Override
	void stringToInt(String str) {
		System.out.println(Integer.parseInt(str));
		
	}

}
