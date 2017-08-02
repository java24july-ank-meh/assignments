package q7;

import java.util.*;

public class nameComparator implements Comparator{  
	public int compare(Object o1,Object o2){  
		employee e1=(employee)o1;  
		employee e2=(employee)o2;  
		  
		return e1.name.compareTo(e2.name);  
		}  
		}  