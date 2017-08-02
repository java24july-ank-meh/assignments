package revature.takeHome14;

import java.lang.Math;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SwitchDemo {
	public static void main(String[] args){
		casePractice(1);
		casePractice(3);
		casePractice(2);
	}
	
	public static void casePractice(int x){ 
		//int caseNum = x%3;
		//String answer;
		switch(x%3) {
		case 0:	System.out.println(Math.sqrt(49));
				break;
		case 1: System.out.println(LocalDateTime.now());
				break;
		case 2: String ex = "I am learning Core Java";
				String[] test = ex.split(" ");
				System.out.println(Arrays.toString(test));
				break;
		}
	}
}
