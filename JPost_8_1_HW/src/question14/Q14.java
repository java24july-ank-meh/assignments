package question14;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Q14 {
	/*
	 * Question 14: Write a program that demonstrates the switch case.
	 * Implement the following functionalities in the cases:
	 * Case 1: Find the square root of a number using Math class method.
	 * Case 2: Display today's date.
	 * Case 3: Split the following string and store it in a string array.
	 * 			"I am learning Core Java"
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String option;
		boolean flag = true;
		while (flag) {
			System.out.println("\nOptions 1-3. Anything else exits.");
			option = sc.next();
			switch(option) {
			case "1":	{
				System.out.println("Square root of 19 = " + Math.sqrt(19));
				break;
			}
				
			case "2":	{
				LocalDate localDate = LocalDate.now();
		        System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
				break;
			}
				
			case "3":	{
				String string = "I am learning Core Java";
				String[] s = string.split(" ");
				System.out.println(Arrays.toString(s));
				break;
			}
				
			default:	flag = false;
			}
		}
		sc.close();
	}
}
