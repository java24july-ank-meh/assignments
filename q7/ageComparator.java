package q7;

import java.util.Comparator;

public class ageComparator implements Comparator {
	public int compare(Object o1,Object o2){  
		employee e1=(employee)o1;  
		employee e2=(employee)o2;  
		  
		if(e1.age==e2.age)  
		return 0;  
		else if(e1.age>e2.age)  
		return 1;  
		else  
		return -1;  
		}  
}
