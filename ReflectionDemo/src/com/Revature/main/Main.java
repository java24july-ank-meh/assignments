package com.Revature.main;

import com.Revature.bean.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		/*
		Person bill = new Person();
		bill.setName("Bill");
		bill.setAge(30);
		bill.setSex('M');
		
		Class c = Class.forName("com.Revature.bean.Person");
		Field[] fields = c.getDeclaredFields();
		for(Field f : fields){
			System.out.println(f.getName());
		}
		
		Person joe = (Person) c.newInstance();
		joe.setName("Joe");
		System.out.println(joe.getName());
		
		Field age = c.getDeclaredField("age");
		age.setAccessible(true);
		age.set(bill, -100);
		System.out.println(bill.getAge());
		*/
		
		Person p = new Person("Name", 22, 'M');
		writeObject("src/Person", p);
		readObject("src/Person");
	}
	static void writeObject(String filename, Object obj){
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
			oos.writeObject(obj);
			System.out.println(obj);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	static void readObject(String filename){
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
			Object obj = ois.readObject();
			System.out.println(obj);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
