package com.revature.q11.a;

import java.lang.reflect.Field;

import com.revature.q11.b.Person;

public class Main {

	public static void main(String[] args) {
		/*
		 * Not sure what to make of the question. Obviously, if we make the two float fields
		 * public, we can access them outside of the package. we could also access them from 
		 * outside the package if we make them protected and write a class that extends the 
		 * class that contains the float values we're interested in. I'm gonna try reflection
		 * here.
		 * 
		 */
		
		Class personClassObject = null;
		try{personClassObject = Class.forName("com.revature.q11.b.Person");}
		catch(ClassNotFoundException e) {System.out.println(e.getMessage()); System.exit(0);}
		
		Field[] fields = personClassObject.getDeclaredFields();
		for(int i=0; i<fields.length; i++) {
			System.out.println(fields[i].getName() );
		}
		
		try{
			Person p = (Person)personClassObject.newInstance();
			p.setAge(3.4f);
			p.setBalance(300.93f);
			
			for(Field f : fields) {
				Field current = personClassObject.getDeclaredField(f.getName());
				current.setAccessible(true);
				System.out.println(current.getFloat(p));
			}
		}
		catch(Exception e) {System.out.println(e.getMessage()); System.exit(0);}
	}
}
