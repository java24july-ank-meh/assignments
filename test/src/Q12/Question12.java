package Q12;

import java.util.ArrayList;
import java.util.Arrays;

public class Question12 {
	
	/*
	 * Q12 Write a program to store numbers from 1 to 100 in an array. 
	 */
	public static ArrayList<Integer> printsEvens1_100(){
		Integer[] oneTo100 = new Integer[100];
		for(int i=0;i<100;i++)
			oneTo100[i]=i+1;
		ArrayList<Integer> all = new ArrayList<Integer>(Arrays.asList(oneTo100));
		ArrayList<Integer> evens = new ArrayList<Integer>();
		for(int i:all) 
			if(all.get(i-1).intValue()%2==0) {
				evens.add(all.get(i-1));
				System.out.println(all.get(i-1));
			}
		return evens;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> evens = printsEvens1_100();

	}

}
