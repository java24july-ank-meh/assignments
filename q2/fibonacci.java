package q2;



public class fibonacci {
	 public static void main(String[] args) {
			
		
			int x = 25;
			fibonacciLoop(x);//begin fibonacci
			
		 
	 }
	 public static void fibonacciLoop(int number) {
		 System.out.print(0 + " " + 1 + " ");// print 0 and 1
		 
		int fibo1 = 1, fibo2 = 1, fibonacci = 1;
		for (int i = 3; i <= number; i++) {//fibonacci starting at 3rd number
			fibonacci = fibo1 + fibo2; // 
			fibo1 = fibo2;
			fibo2 = fibonacci;
			System.out.print(fibonacci + " ");//print rest of values

		}
		
	}
}
