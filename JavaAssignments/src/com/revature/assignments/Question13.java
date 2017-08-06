package com.revature.assignments;

import java.util.ArrayList;

public class Question13 {
	public static void main(String[] args) {
		PrintTree(4);
		/*ArrayList<Integer> numlist2 = new ArrayList();
		numlist2.add(5);
		numlist2.add(6);
		numlist2.add(10);
		numlist2.add(12);
		for(int cur = 0; cur < numlist2.size(); cur++) {
			System.out.print(cur+ ", ");
		}
		System.out.println();
		*/

	}
	
	public static void PrintTree(int num) {
		ArrayList<Integer> numlist = new ArrayList();
		for(int i = 0; i < num; i++) {
			if(i%4 == 0) {
				numlist.add(0);
			}else if(i%4 == 1) {
				numlist.add(0, 1);;
			}else if(i%4 == 2) {
				numlist.add(1);
			}else if(i%4 == 3) {
				numlist.add(0, 0);
			}
			for(int cur = 0; cur < numlist.size(); cur++) {
				System.out.print(numlist.get(cur)+ ", ");
			}
			System.out.println();
		}
	}
}
