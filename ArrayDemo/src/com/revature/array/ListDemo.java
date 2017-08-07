package com.revature.array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListDemo {
	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		arrayList.add(5);
		arrayList.add(5);
		arrayList.add(10);
		arrayList.add(3);
		System.out.println("ArrayList = " + arrayList);
		System.out.println("ArrayList[2] = " + arrayList.get(2));
		
		filter(arrayList);
		System.out.println("Filtered: " + arrayList);
		
		//LinkedList
		List<Integer> listLinkedList = new LinkedList<>();
		Deque<Integer> dequeLinkedList = new LinkedList<>();
		Queue<Integer> queueLinkedList = new LinkedList<>();
		LinkedList<Integer> normalLinkeList = new LinkedList<>();
		
		
		
	}
	
	static void filter(Collection<Integer> c) {
		for(Iterator<Integer> it = c.iterator(); it.hasNext();) {
			if(it.next() > 5) {
				it.remove();
			}
		}
	}
	
	
	
	
	
}
