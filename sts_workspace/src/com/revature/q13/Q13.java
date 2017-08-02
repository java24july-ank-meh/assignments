package com.revature.q13;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q13 {

	public static void main(String[] args) {
		triangle(4);
	}
	
	public static void triangle(int levels) {
		
		Deque<Integer> d = new ArrayDeque<>();
		for(int i=0; i<levels; i++) {
			int to_append = -1;
			if(i%4==0 || i%4==3) {to_append=0;}
			else {to_append = 1;}
			
			if(i%2==0) {d.addLast(to_append);}
			else {d.addFirst(to_append);}
			
			for(Integer j : d) {
				System.out.print(j + " ");
			}
			System.out.println("\n");
		}
	}
}
