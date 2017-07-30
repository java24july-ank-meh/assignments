
import java.util.Scanner;;

public class Reverse 
{
	public static void main(String args[]) 
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("Please enter the string");
		String input = scan.next();
		StringBuilder readOut= new StringBuilder();
		scan.close();
		/*get user input*/
		for(int x =input.length()-1; x>-1; x--) 
		{
			readOut.append(input.charAt(x));
		}		
		/*output*/
		System.out.println(readOut);
		/*output*/
	}
}
