package format_textfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FormatTextFile {

	public static void main(String[] args) {
		
		try (BufferedReader bwriter = new BufferedReader(new FileReader("src/temp/characters.txt"))) {
			
			String line;
			while((line = bwriter.readLine()) != null) {
				String[] info = line.split(":");
				System.out.println("Name: " + info[0] + " " + info[1]);
				System.out.println("Age: " + info[2] + " years");
				System.out.println("State: " + info[3] + " State");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
