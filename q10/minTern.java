package q10;

import java.util.Scanner;

public class minTern {
	 public static void main(String[] args) {
		 Scanner input = new Scanner(System.in);
		 
		 System.out.println("Enter the first value");
		 int x = input.nextInt();
		 System.out.println("Enter the second value");
		 int y = input.nextInt();
		 
				 
				 Object minVal = x < y ? x : y; //if x < y, x is min, else y is min 
				 
				 System.out.println("The minimum of the two numbers is " + minVal);
	 }

}
