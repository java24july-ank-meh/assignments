package com.Revature.chal1;

import java.io.IOException;
import java.util.*;

import com.Revature.EvenNums.Showeven;
import com.Revature.Float.Client;
import com.Revature.bubblesort.Bubblesort1;
import com.Revature.calcinterface.CalculatorImp;
import com.Revature.comparison.*;
import com.Revature.factorial.Factorial;
import com.Revature.fib.Fibo;
import com.Revature.fileimport.Parser;
import com.Revature.inheritor.Stringmethodq18;
import com.Revature.interestscan.Calcinterest;
import com.Revature.listintfunc.Listintfunctions;
import com.Revature.oddoreven.OddorEven;
import com.Revature.palindrone.palindromesort;
import com.Revature.primes.Printprime;
import com.Revature.reverser.ReverseString;
import com.Revature.stringcharcount.CharCount;
import com.Revature.substring.Makesubstring;
import com.Revature.switchchal.Switchcase;
import com.Revature.ternarycomp.Ternarycomparator;
import com.Revature.triangles.Switchtriangle;

public class main {

	public static void main(String[] args) throws IOException {
		ArrayList<Integer> Hundred = new ArrayList<Integer>();
		for(int i=1;i<101; i++){
			Hundred.add(i);
		}

		//Q1 Bubblesort
		Bubblesort1 q1 = new Bubblesort1();
		int[] q1a = {1,0,5,6,3,2,3,7,9,8,4};
		System.out.println("Q1:\nUnsorted: " + Arrays.toString(q1a));
		System.out.println("Calling Bubblesort...");
		q1.sort(q1a);
		System.out.println("Sorted: " + Arrays.toString(q1a));
		
		//Q2 Fib
		Fibo q2 = new Fibo();
		System.out.println("Q2:\nCalling Fib...");
		System.out.println(q2.fibprintint(25));
		
		//Q3 ReverseString
		ReverseString q3 = new ReverseString();
		System.out.println("Q3:\nReversing String...");
		System.out.println(q3.reverse("Reversing String..."));
		
		//Q4 Factorial
		Factorial q4 = new Factorial();
		System.out.println("Q4:\nCalculating Factorial of 5: " + q4.calculate(5));
		System.out.println("Calculating Factorial of 8: " + q4.calculate(8));
		
		//Q5 Substring
		Makesubstring q5 = new Makesubstring();
		System.out.println("Q5:\nSubstring of 5|TestString is: " + q5.substringer(5, "TestString"));
		
		//Q6 Odd or Even
		OddorEven q6 = new OddorEven();
		System.out.println("Q6:\n1153 is: " + q6.returnType(1153) + "\n12 is: " + q6.returnType(12));
		
		//Q7 Sort Employees
		ArrayList Q7list = new ArrayList();
		Q7list.add(new Employee("John", "Math", 31));
		Q7list.add(new Employee("Bob", "English", 27));
		Collections.sort(Q7list);
		System.out.println("Q7 Sort 2:\n" + Q7list.toString());
		Q7list.add(new Employee("John", "English", 35));
		Q7list.add(new Employee("Bob", "English", 31));
		Collections.sort(Q7list);
		System.out.println("Sort 4:\n" + Q7list.toString());
		
		//Q8 Export Palindromes
		ArrayList Q8list = new ArrayList();
		Q8list.add("karan");
		Q8list.add("madam");
		Q8list.add("tom");
		Q8list.add("civic");
		Q8list.add("radar");
		Q8list.add("sexes");
		Q8list.add("jimmy");
		Q8list.add("kayak");
		Q8list.add("john");
		Q8list.add("refer");
		Q8list.add("billy");
		Q8list.add("did");
		ArrayList Q8out = palindromesort.seperate(Q8list);
		System.out.println("Q8 Palindrome\nInput:\n" + Q8list.toString());
		System.out.println("Output\n" + Q8out.toString());
		
		//Q9 Primes.
		Printprime Q9class = new Printprime();
		System.out.println("Q9 Primes: ");
		Q9class.printout(Hundred);
		
		//Q10 Minimum
		Ternarycomparator Q10 = new Ternarycomparator();
		System.out.println("Q10 Ternary Min: \n" + Q10.findmin(5, 10));
		
		//Q11 Access Floats
		Client Q11C = new Client();
		System.out.println("Q11 Float Access: \n" + Q11C.access());
		
		//Q12 ShowEven
		Showeven Q12 = new Showeven();
		System.out.println("Q12 Show Even Numbers:");
		Q12.printeven();

		//Q13 Triangle
		System.out.println("Q13 Print Triangle:");
		Switchtriangle Q13 = new Switchtriangle();
		Q13.printtri(4);

		//Q14 SwitchCase
		Switchcase Q14 = new Switchcase();
		Q14.testswitch();
		
		//Q15 CalculatorInterface
		CalculatorImp Q15 = new CalculatorImp();
		System.out.println("Q15, Calculator\nAdd 5,7: " + Q15.add(5, 7) + "\nSubtract 9,2: " + Q15.subtract(9, 2));
		
		//Q16 CharInStrings
		CharCount Q16 = new CharCount();
		System.out.println("Q16 Charcount in input: ");
		for(int i = 0; i < args.length;i++){
			System.out.println("Input Number " + i + ", Input: " + args[i] + "\nCOUNT: " + Q16.countchar(args[i]));
		}

		//Q17 Scanner Interest
		Calcinterest Q17 = new Calcinterest();
		System.out.println("Q17 Calculate Interest.");
		int Q17B = Q17.Calcint();
		System.out.println("Result is: " + Q17B);

		//Q18 Inheritance.
		Stringmethodq18 Q18 = new Stringmethodq18();
		System.out.println("Q18 inheritance: ");
		System.out.println("Q18 Op1, Test: " + Q18.checkupper("Test"));
		System.out.println("Q18 Op2, test: " + Q18.changetoupper("test"));
		System.out.print("Q18 Op3, 22: ");
		Q18.add10("22");
		System.out.println();

		//Q19 Arraylist functions.
		ArrayList Q19list = new ArrayList();
		for(int i=1; i<=10;i++){
			Q19list.add(i);
		}
		Listintfunctions Q19 = new Listintfunctions();
		Q19.function(Q19list);
		
		//Q20 ReadData + Tokenize
		Parser q20 = new Parser();
		q20.driver();
	}

}
