package revature.takeHome7;

import java.util.Comparator;

public class DepartmentComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getDpt().compareTo(o2.getDpt());
	}

}
