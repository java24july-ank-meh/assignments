package com.homework.Q20;

import java.io.*;

public class Q20 {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("src/Data.txt");
		BufferedReader br = new BufferedReader(fr);
		String s;
		String[] stringArray = new String[4];
		
		while((s = br.readLine()) != null) {
			stringArray = s.split(":");
			System.out.println("Name: " + stringArray[0] +" "+ stringArray[1]);
			System.out.println("Age: " + stringArray[2]);
			System.out.println("State: " + stringArray[3]);
			System.out.println();
			//stringArray = new String[4];
		}
		br.close();
	}

}
