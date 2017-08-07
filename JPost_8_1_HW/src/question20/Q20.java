package question20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Q20 {
	/*
	 * Question 20: Create a notepad file called Data.txt and enter the following:
	 * 
	 * Mickey:Mouse:35:Arizona
	 * Hulk:Hogan:50:Virginia
	 * Roger:Rabbit:22:California
 	 * Wonder:Woman:18:Montana
 	 * 
 	 * Write a program that would read from the file and print it out
 	 * to the screen in the following format:
 	 * 
 	 * Name: Mickey Mouse
	 * Age: 35 years
	 * State: Arizona
	 */
	public static void main(String[] args) throws IOException {
		ArrayList<Person> list = new ArrayList<>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("src/question20/Data.txt"));
			String c;
			while((c = in.readLine()) != null) {
				String[] s = c.split(":");
				list.add(new Person(s[0],s[1],Integer.parseInt(s[2]),s[3]));
			}
		}
		finally {
			if(in != null)
				in.close();
		}
		for(Person p : list) {
			System.out.println("Name: " + p.getfName() + " " + p.getlName());
			System.out.println("Age: " + p.getAge() + " years");
			System.out.println("State: " + p.getState() + "\n");
		}
	}
}
