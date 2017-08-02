package switch_statement;

import java.util.Date;

public class SwitchStatement {
	
	public static void main(String[] args) {

		int num = 1;
		
		switch(num) {
		case 1: Math.sqrt(100);
			break;
		case 2: System.out.println(new Date());
			break;
		case 3: "I am learning Core Java".split(" ");
			break;
		default: System.out.println("Default case");
		}
		
	}

}
