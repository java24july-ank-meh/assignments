package com.revature.q14;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Q14 {
	
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("proper usage: java Q14 <argument>"); System.exit(0);
		}
		
		switch(args[0]) {
		case "sqrt": System.out.println(Math.sqrt(4.0)); break;
		case "date": DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		             LocalDate l = LocalDate.now();
		             System.out.println(d.format(l)); break;
		case "split": String s = "I am learning Core Java";
		              String[] strs = s.split(" "); 
		              for (String str: strs) {System.out.println(str);}
		              break;
		default: System.out.println("chose sqrt, date, or split");
		}
	}
	
}

enum Function{
	SQRT, DATE, SPLIT
}
