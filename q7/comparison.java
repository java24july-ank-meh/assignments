package q7;

import java.util.*;
import java.io.*;
public class comparison {
	public static void main(String args[]){  
		  
		ArrayList al=new ArrayList();  
		al.add(new employee("jack","Finance",25));  
		al.add(new employee("Steve","Human Resources",21));  
		  
		System.out.println("Sorting by Name...");  
		System.out.println();  
		Collections.sort(al,new nameComparator());  
		Iterator itr=al.iterator();  
		while(itr.hasNext()){  
			employee emp=(employee)itr.next();  
		System.out.println(emp.name +" " + emp.Department + " " + emp.age);  
		}  
		System.out.println();
		System.out.println("Sorting by Department...");
		System.out.println();
		Collections.sort(al,new DepartmentComparator());  
		Iterator itr2=al.iterator();  
		while(itr2.hasNext()){  
			employee emp=(employee)itr2.next();  
		System.out.println(emp.name +" " + emp.Department + " " + emp.age);  
		}  
		System.out.println();
		System.out.println("sorting by age...");  
		System.out.println(); 
		Collections.sort(al, new ageComparator());  
		Iterator itr3=al.iterator();  
		while(itr3.hasNext()){  
			employee emp=(employee)itr3.next();  
		System.out.println(emp.name +" " + emp.Department + " " + emp.age);  
		}  
		  
		
		  
		  
		}  

}
