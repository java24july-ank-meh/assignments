package com.Revature.Comparator;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();
		Person p1 = new Person(3, "Ned", 31);
		Person p2 = new Person(1, "John", 21);
		Person p3 = new Person(4, "Rob", 18);
		
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		
		Collections.sort(persons, new IDComparator());
		for(Person p : persons){ //for each loop
			System.out.println(p);
		}
	}
}
