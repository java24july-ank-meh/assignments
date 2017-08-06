package com.revature.assignments;

public class Question18 extends Question18Abstract{

	public static void main(String[] args) {
		Question18 test = new Question18();
		test.CheckUP("does this have an upper caHIddense?");
		test.MakeUP("does this have an upper caHIddense?");
		test.PlusTen("20");
	}
	
	@Override
	public void CheckUP(String str) {
		//char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'J', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		for(int i = 0; i < str.length(); i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				System.out.println(true);
				break;
			}
			else if(i == str.length()-1){
				System.out.println(false);
			}
		}
	}

	@Override
	public void MakeUP(String str) {
		str = str.toUpperCase();
		System.out.println(str);
		
	}

	@Override
	public void PlusTen(String str) {
		System.out.println(str);
		int value = Integer.valueOf(str);
		System.out.println(value);
		value = value + 10;
		str = Integer.toString(value);
		System.out.println(str);
		
	}

}
