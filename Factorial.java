
public class Factorial {
/* non negative integers multiplied by the descending sequence
 * of numbers
 */
	public static void main(String[] args) {
		int i; int x= 1;
		int fact= 3;  //number to find factorial from
			for(i= 1; i<= fact; i++)  {
				x= x* i;
			}
			System.out.println("The factorial of "+ fact+ " is:  "+ x);
	}

}
