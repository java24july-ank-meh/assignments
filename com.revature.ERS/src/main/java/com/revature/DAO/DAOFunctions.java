package com.revature.DAO;


import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.List;

import com.revature.ERS.Employee;
import com.revature.ERS.Reimbursement;

public interface DAOFunctions {

	public List readEmployee(int id); //returns a list with one employee (used list for easier access)
	public void createEmployee(Employee e);//inserts a new employee with values from employee class
	public void deleteEmployee(int id);//deletes an employee from the database given the employee id
	public void updateEmployee(Employee e, int id); //updates an employee from the database getting new info from the employee class and the id 
	public int userLogin(String user, String pass); //lets a user or manager to log in into the program
	public List<Employee> readAllEmp();//returns a list of all the employees 
	public void saveReimbursement(int id, Reimbursement r);//lets employee to create a new reimbursement without image
	public void saveReimbursementWithImage(int id, Reimbursement r, byte[] blobArr);//lets a user create a reimbursement with an image 
	public void reimbursementStatus(int r_id, int rs_id, String status);//lets manager to cancel or approve a reimbursement
	public List<Reimbursement> readPendingReimbursements(int u_id, int r_id);//lets manager to read all the pending reimbursements 
	public List<Reimbursement> readResolvedReimbursements(int u_id, int r_id);//lets manager read all the approved reimbursements
	public List<Reimbursement> readReimbursement();//returns a list of all the reimbursements in the database
	public BufferedImage readBlob(int r_id, byte[] blob);//returns an image from the database(might be used for displaying an image in the webpage
	
}
