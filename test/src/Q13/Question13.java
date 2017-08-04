package Q13;

public class Question13 {
	/*
	 * Q13 Display a triangle on the console using any type of loop.
	 */
	
	public static void displayTriangle(int n) {
		if(n<0)
			return;
		else {
			for(int i=0;i<n;i++) {
				switch(i%4) {
				case 0:
					System.out.println("0");
					break;
				case 1:
					System.out.println("1 0");
					break;
				case 2:
					System.out.println("1 0 1");
					break;
				case 3:
					System.out.println("0 1 0 1");
					break;
				}
			}
			
		}
	}
	
	public static void main(String args[]) {
		displayTriangle(4);
	}
}
