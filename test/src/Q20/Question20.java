package Q20;

import java.io.*;


/*
 * Q20 Create a notepad file called Data.txt and write a program that would read from 
 * the file and print it out in desired format
 */
public class Question20 {

	public static void main(String[] args) {
		String line=null;
		
		try{
			FileReader fr = new FileReader("src/Q20/Data.txt");
			BufferedReader br = new BufferedReader(fr);
			while((line = br.readLine())!=null) {
				String temp1[] = line.split(":");
				String temp2="Name:";
				for(int i=0;i<temp1.length;i++) {
					if(i<2)
						temp2+=temp1[i]+ " ";
					else if(i==2) {
						System.out.println(temp2);
						System.out.println("Age:"+temp1[i]+ " years");
						temp2="Location:";
					}
					else if(i>2 && i<temp1.length) {
						temp2+=temp1[i]+" ";
						if(i==temp1.length-1)
							System.out.println(temp2);
					}
				}
				System.out.println();
			}
			br.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
