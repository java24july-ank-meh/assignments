package com.Revature.fileimport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	List<String> list = new ArrayList<String>();
	String[] currentread;
	
	public void driver() throws IOException{
		readdata();
		tokenize();
	}
	
	private void tokenize() {
		for(String temp : list){
			currentread = temp.split(":");
			System.out.println("Name: " + currentread[0] + " " + currentread[1]);
			System.out.println("Age: " + currentread[2] + " years");
			System.out.println("State: " + currentread[3] + " State");
			System.out.println();
		}
	}

	public void readdata() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("Data.txt"));
		String str;
		
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] stringArr = list.toArray(new String[0]);
	}
}
