package javaHomeworl.Q20;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ReadFormat {

	public static void main(String[] args) {

		String line;
		ArrayList<String[] > lines = new ArrayList<String[] >();
		
		try (
				//read data line by line
			    InputStream fStream = new FileInputStream("Data.txt");
			    InputStreamReader streamReader = new InputStreamReader(fStream, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(streamReader);
			) {
			    while ((line = br.readLine()) != null) {
			        //split line at : and add to array
			    	lines.add(line.split(":") );
			    }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		//print data
		for(String[] strArr:lines) {
			System.out.println("Name: " + strArr[0] + " " + strArr[1]);
			System.out.println("Age: " + strArr[2] + " years");
			System.out.println("State: " + strArr[3] + " State \n");
		}

	}

}
