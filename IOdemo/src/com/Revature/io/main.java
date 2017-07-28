package com.Revature.io;

import java.io.*;

public class main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = null;
		PrintWriter out = null;
		
		try{
			in = new BufferedReader(new FileReader("new.txt"));
			out = new PrintWriter(new FileWriter("out.txt"));
			String s;
			while((s = in.readLine()) != null ){
				out.println(s);
			}
		}finally{
			if(in != null){
				in.close();
			}
			if(out != null){
				
				out.close();
			}
		}
	}

}
