package Q14;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

public class Question14 {
	
	/*
	 * Q14 Write a program that demonstrates the switch case
	 */
	public static void switchDemo(int n) {
		Scanner sc = new Scanner(System.in);
		switch(n) {
		case 1:
			System.out.print("Enter a number and find out it's square root: ");
			double num=sc.nextDouble();
			System.out.println("The square root of "+ num + " is: "+ Math.sqrt(num));
			break;
		case 2:
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			System.out.println(dateFormat.format(cal.getTime()));
			break;
		case 3:
			String str = "I am learning Core Java";
			String[] arr=str.split(" ");
			System.out.println(Arrays.toString(arr));
			break;
			default: System.out.println("YOU DIDN'T ENTER A NUMBER FROM 1-3!");
		}	
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.) Find the square root of a number!");
		System.out.println("2.) Display today's date");
		System.out.println("3.) Will split the string \"I am learning core Java\" and store it in an array");
		System.out.println();
		System.out.print("Enter a number from 1-3: ");
		int o=sc.nextInt();
		switchDemo(o);
		

	}

}
