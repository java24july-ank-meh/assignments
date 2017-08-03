package com.revature.codechallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataPrint {
	public static void main(String[] args) {
		try (BufferedReader bf = new BufferedReader(new FileReader("src/com/revature/codechallenge/Data.txt"))){
			printEntry(bf.readLine().split(":"));
			printEntry(bf.readLine().split(":"));
			printEntry(bf.readLine().split(":"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printEntry(String[] str) {
		System.out.println("Name: " +str[0] + " " +str[1]);
		System.out.println("Age: "+ str[2]);
		System.out.println("State: " + str[3] + " State\n");
		
	}
}
