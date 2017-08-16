package com.revature.ers.main;

import com.revature.ers.beans.User;
import com.revature.ers.beans.UserRole;
import com.revature.ers.dao.DataService;

public class Main2 {

	public static void main(String[] args) {
		DataService ds = new DataService();
		/*System.out.println(ds.createUser("hello", "world", "firstname", "lastname", "email", UserRole.MANAGER));
		User user = ds.getUser("hello");
		System.out.println(user.toString());
		user.setFirstname("Goodbye");
		System.out.println(ds.updateUser(user));
		System.out.println(user.toString());
		*/
		System.out.println(ds.getEmployees());

	}

}
