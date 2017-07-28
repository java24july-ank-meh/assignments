package com.Revature.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		arrayList.add(5);
		arrayList.add(5);
		arrayList.add(10);
		arrayList.add(3);
		System.out.println("arraylist = " + arrayList);
		System.out.println("arraylist[2] = " + arrayList.get(2));
		
		List<Integer> listLinkedList = new LinkedList<>();
	}

}
