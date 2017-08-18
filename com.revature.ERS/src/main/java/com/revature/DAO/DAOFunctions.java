package com.revature.DAO;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.revature.ERS.Employee;
import com.revature.ERS.Reimbursement;

public interface DAOFunctions {
	
	public void commit();
	public List<String> readEmployee(int id);	//returns a list with one employee (used list for easier access)
	public void createEmployee(Employee e);		//inserts a new employee with values from employee class
	public void deleteEmployee(int id);			//deletes an employee from the database given the employee id
	public void updateEmployee(Employee e, int id); //updates an employee from the database getting new info from the employee class and the id 
	public int userLogin(String user, String pass); //lets a user or manager to log in into the program
	public List<Employee> readAllEmp();				//returns a list of all the employees 
	public void saveReimbursement(int id, Reimbursement r);//lets employee to create a new reimbursement without image
	public void saveReimbursementWithImage(int id, Reimbursement r, byte[] blobArr);//lets a user create a reimbursement with an image 
	public void reimbursementStatus(int r_id, int rs_id, String status);//lets manager to cancel or approve a reimbursement
	public List<Reimbursement> readAllPendingRmbs();	//lets manager to read all the pending reimbursements 
	public List<Reimbursement> readAllResolvedRmbs();	//lets manager read all the approved reimbursements
	public List<Reimbursement> readPendingRmbs(int u_id);	//lets employee read all their pending reimbursements 
	public List<Reimbursement> readResolvedRmbs(int u_id);	//lets employee read all their approved reimbursements
	public List<Reimbursement> readReimbursement();		//returns a list of all the reimbursements in the database
	public List<Reimbursement> readEmployeeRequests(int u_id); //lets manager view a list of all requests from a given employee
	public String readUserRole(int id);
	public BufferedImage byteArrToImage(byte[] bytearray) throws IOException;
	public byte[] imageToByteArr(File f) throws IOException;
	
	
}
