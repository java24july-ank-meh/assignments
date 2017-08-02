package simple_interest;

import java.util.Scanner;

public class SimpleInterest {

	
	public static void main(String[] args) {
		
		System.out.println("Starting Simple Interest Calculator");
		
		double principal;
		double rate;
		double time;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Principal = ");
		principal = scanner.nextDouble();
		
		
		System.out.print("Rate = ");
		rate = scanner.nextDouble();
		
		System.out.print("Time = ");
		time = scanner.nextDouble();
		
		System.out.println("Principal = " + (principal * rate * time));
		
		scanner.close();
		
	}
	
}
