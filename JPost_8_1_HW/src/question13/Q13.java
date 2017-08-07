package question13;

public class Q13 {
	/*
	 * Question 13: Display the triangle on the console as follows using any
	 * type of loop. Do NOT use a simple group of print statements to
	 * accomplish this.
	 * 0
	 * 10
     * 101
	 * 0101
	 */
	public static void main(String[] args) {
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y <= x; y++) {
				System.out.print(((x*(x+1)/2)+y)%2);
			}
			System.out.println();
		}
	}
}
