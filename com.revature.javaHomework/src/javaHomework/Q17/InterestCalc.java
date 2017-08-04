package javaHomework.Q17;

import java.util.Scanner;

public class InterestCalc {

	public static void main(String[] args) {
		
		//make scanner object
		Scanner scan = new Scanner(System.in);
		
		//take in input from user
		System.out.println("Please enter principle: ");
		float principle = scan.nextFloat();
		
		System.out.println("Please enter rate: ");
		float rate = scan.nextFloat();
		
		System.out.println("Please enter time: ");
		float time = scan.nextFloat();
		
		float interest = principle*rate*time;
		
		System.out.println("Interest is: " + interest);
		
		scan.close();
	}

}
