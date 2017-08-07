package com.roberto.homework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.*;
import java.util.*;

public class Homework {

	Scanner in = new Scanner(System.in);

	public int[] bubbleSort() {
		int[] arr = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		System.out.println("Array before sort: " + "\n" + Arrays.toString(arr));
		int temp = 0;// create a temporary variable to store biggest value

		for (int i = 0; i < arr.length - 1; i++) {// the nested for loop will iterate 10 times
			for (int j = 1; j < (arr.length - i); j++) {// and will compare every number from index 0 to the last
														// one(which will be decreasing by one)
				if (arr[j - 1] > arr[j]) { // compares if the left number is greater than the right number and if it's
											// true, then flips them

					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}

			System.out.println("Iteration " + (i + 1) + " :" + Arrays.toString(arr));// lets you see the number of
																						// iterations and the current
																						// value
		}
		return arr;
	}

	public void fibonacci() {
		int s = 25;
		if (s >= 0) {
			int[] x = new int[s];
			for (int n = 0; n < s; n++) {
				if (n == 0) {
					x[0] = 0;
				} else if (n == 1) {
					x[1] = n;
				} else {
					x[n] = x[n - 1] + x[n - 2];
				}
				System.out.println(n + "th term: " + x[n]);
			}
		}
	}

	public void reverseString() {

		System.out.println("Please enter a string to reverse:");
		String str1 = in.nextLine();

		StringBuilder sb = new StringBuilder();
		for (int i = str1.length() - 1; i >= 0; i--) {
			sb.append(str1.charAt(i));
		}

		System.out.println(sb.toString());
	}

	public void computeNFactorial() {
		System.out.println("Please enter a number to get the factorial:");
		int n = in.nextInt(), fact = 0;

		for (int i = 1; i <= n; i++) {
			fact = fact * i;
		}

		System.out.println("Factorial of: " + n + " is: " + fact);
	}

	public void getSubstring() {
		System.out.println("Please enter a string:");
		String str1 = in.nextLine();
		System.out.println("Enter an integer: ");
		int idx = in.nextInt();

		StringBuilder sb = new StringBuilder();
		if (idx < 1) {
			System.out.println("Please enter a higher number");
			idx = in.nextInt();
		} else {
			for (int i = 0; i < idx - 1; i++) {
				sb.append(str1.charAt(i));
			}
			System.out.println("Your substring is: " + sb.toString());
		}

	}

	public void isEven() {
		System.out.println("Enter a number and I will tell you if it's even: ");

		int number = in.nextInt();
		int modulus = number % 2;

		if (modulus == 1) {
			System.out.println(number + " is not even.");
		} else if (modulus == 0) {
			System.out.println(number + " is even!");
		} else {
			System.out.println("some kind of error occured.");
		}
	}

	public void isPrime() {
		System.out.println("Getting the prime numbers from 1-100");

		ArrayList<Integer> n = new ArrayList<Integer>();
		for (int i = 1; i <= 100; i++) {
			n.add(i);
		}

		for (int i = 0; i < n.size(); i++) {
			getPrime(i);
		}
	}

	public void getPrime(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {

			} else {
				System.out.println(num + " is prime.");
			}
		}
	}

	public void minNum() {
		int a, b;
		System.out.println("Enter a:");
		a = in.nextInt();
		System.out.println("Enter b:");
		b = in.nextInt();

		int minVal = (a < b) ? a : b;
		System.out.println("the smallest is " + minVal);
	}

	public void evenNum() {
		int[] arr = new int[100];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}

		for (int i : arr) {
			try {
				if (arr[i + 1] % 2 == 0) {
					System.out.println(i);
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println(arr[99]);
				break;
			}

		}

	}

	public void printTriangle() {
		String zero = "0", one = "1";
		System.out.println("How big do you want the triangle: ");
		int n = in.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (i % 2 == 1) {
				sb.append(one);
			} else if (i % 2 == 0) {
				sb.append(zero);
			}

			System.out.println(sb.toString());
		}

	}

	public void switchCase() {
		System.out.println("Please choose one option:");
		System.out.println("1: Find square root of n:");
		System.out.println("2: Display today's date: ");
		System.out.println("3: Split 'I am learning Core Java' and store it in an array.");
		System.out.println("0: to exit to main menu.");
		
		int choice = in.nextInt();
		
		switch(choice){
			case 1:
				System.out.println("enter a number to get the square root:");
				int n = in.nextInt();
				double result = Math.sqrt(n);
				System.out.println("the square root is: " + result);
				break;
				
			case 2:
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println(dateFormat.format(date));
				break;
				
			case 3:
				String str = "I am learning Core Java";
				String[] strArr = new String[str.length()];
				
				for(int i = 0; i < str.length(); i++) {
					strArr[i] = Character.toString(str.charAt(i));
				}
				System.out.println(Arrays.toString(strArr));
				break;
			
			case 0:
				break;
		}
	}

	public void getInterest() {
		System.out.println("Please enter principal, rate and time: ");
		double principal = in.nextDouble();
		double rate = in.nextDouble();
		double time = in.nextDouble();
		
		double interest = principal*rate*time;
		System.out.println("The interest is: " + interest);
	}

	public void arrayListMani() {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for(int i = 1; i < 11; i++) {
			intList.add(i);
		}
		System.out.println("Populated ArrayList: " + Arrays.toString(intList.toArray()));
		int addition = 0;
		int subtraction = 0;
		for(int i = 0; i < intList.size(); i++) {
			if(intList.get(i)%2 == 0) {
				addition += intList.get(i);
			}
			else if(intList.get(i)%2 == 1) {
				subtraction -= intList.get(i);
			}
			else if(intList.get(i) == 3 || intList.get(i) == 5 || intList.get(i) == 7) {
				intList.remove(i);
			}
		}
		
		System.out.println("Addition is: " + addition);
		System.out.println("Subtraction is: " + subtraction);
		System.out.println("List after removing primes: " + Arrays.toString(intList.toArray()));
		
	}
	
	public void getInfoFile() {
		String name, age, state, temp[];
		
		try {
			
			in = new Scanner(new FileReader("src/Data.txt"));
			
			String[] tempStorage = new String[4];
			
			while(in.hasNext()) {
				for(int i = 0; i < tempStorage.length; i++) {
					tempStorage[i] = in.nextLine();
				}
			}
			
			
			temp = new String[4];
			temp = tempStorage[0].split(":");
			
			name = temp[0] +" "+ temp[1];
			age = temp[2];
			state = temp[3];
			
			
			System.out.println("Name: " + name);
			System.out.println("Age: " + age);
			System.out.println("State: " + state);
			
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
