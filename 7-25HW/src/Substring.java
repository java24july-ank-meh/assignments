import java.util.Scanner;;

public class Substring 
{
	public static void main(String args[]) 
	{
		Scanner scanOne=new Scanner(System.in);
		System.out.println("Please enter the string");
		String input = scanOne.next();
		
		System.out.println("Please enter the index");
		Scanner scanTwo=new Scanner(System.in);
		String holder =scanTwo.next();
		int index = Integer.parseInt(holder);
		scanOne.close();
		scanTwo.close();
		/*get user input*/
		StringBuilder outPut=new StringBuilder();
		for (int x=0; x<index ;x++) 
		{
			outPut.append(input.charAt(x));
		}
		
		/*output*/
		System.out.println(outPut);
		/*output*/
	}
}
