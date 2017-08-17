package com.revature.bank.DAO;

import com.revature.bank.Process.*;
import java.util.*;

public interface DAOFunct {

	/*these all are for admin user only*/
	public void createPerson(Person p); //this will be used for user 
	public void readPerson(int id); //this will be user for user
	public List<Person> readAll();
	public void updatePerson(Person p, int id);
	public void deletePerson(int id); 
	
	public void readBal(int id);
}
