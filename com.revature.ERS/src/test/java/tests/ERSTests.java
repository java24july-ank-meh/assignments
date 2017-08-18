package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.revature.DAO.DAOFuncImp;
import com.revature.ERS.Employee;
import com.revature.ERS.Reimbursement;

@SuppressWarnings("unused")
public class ERSTests {

	public static void main(String[] args) {
		DAOFuncImp dao = new DAOFuncImp();
		Employee e = new Employee();
		 e.setU_username("roberto");
		 e.setU_password("roberto");
		 e.setU_firstname("roberto");
		 e.setU_lastname("alvarez");
		 e.setU_email("roberto@revature.com");
		 e.setUr_role("Employee");
		 
		 dao.createEmployee(e);
		 
	}
	
	
	@Test
	public void employeeSpawnTest() {
		System.out.println("\n#TEST# add, delete, and readAll employee/s");

		DAOFuncImp dao = new DAOFuncImp();
		// Create two random strings to act as unique passwords and usernames for the
		// test employees
		Random rndm = new Random();
		String s1 = Integer.toString(rndm.nextInt());
		String s2 = Integer.toString(rndm.nextInt());

		// create two employees and add them to database
		Employee e1 = new Employee(s1, "gr33n", "Kermit", "The Frog", s1 + "@muppet.me", "employee");
		dao.createEmployee(e1);
		dao.commit();

		Employee e2 = new Employee(s2, "peepis", "beepis", "meepisio", s2 + "@beepis.beepis", "employee");
		dao.createEmployee(e2);
		dao.commit();

		// save a count of readAllEmp
		int initialCount = dao.readAllEmp().size();

		// delete kermit and take another count of readAllEmp
		dao.deleteEmployee(e1.getId());
		dao.commit();
		int finalCount = dao.readAllEmp().size();

		assertEquals(finalCount, initialCount - 1);
	}

	@Test
	public void employeeUpdateTest() {
		System.out.println("\n#TEST# update employee");

		DAOFuncImp dao = new DAOFuncImp();
		// Create random strings to act as a unique password and username for the test
		// employee
		Random rndm = new Random();
		String s1 = Integer.toString(rndm.nextInt());

		// create employee and add to database
		Employee e1 = new Employee(s1, "snowcrash", "Hiro", "Protagonist", s1 + "@metaverse.com", "employee");
		dao.createEmployee(e1);
		dao.commit();

		// make a new employee object where employee changes their username and email
		Employee e2 = new Employee(s1 + "<3Geuegle", "snowcrash", "Hiro", "Protagonist", s1 + "@gmail.com", "employee");

		// save a count of readAllEmp
		int initialCount = dao.readAllEmp().size();

		// update Hiro and take another count of readAllEmp
		int id = e1.getId();
		dao.updateEmployee(e2, id);
		dao.commit();
		List<Employee> employees = dao.readAllEmp();
		int finalCount = dao.readAllEmp().size();
		boolean exists = false;

		for (Employee e : employees) {
			if (e.getId() == id) {
				exists = true;
				assertEquals(e.getU_username(), (s1 + "<3Geuegle"));
			}
		}

		assertEquals(exists, true);
		assertEquals(finalCount, initialCount);
	}

	@Test
	public void goodLoginTest() {
		System.out.println("\n#TEST# correct login test");

		DAOFuncImp dao = new DAOFuncImp();
		List<Employee> employees = dao.readAllEmp();

		for (Employee e : employees) {

			String uname = e.getU_username();
			String pass = e.getU_password();

			assertEquals(e.getId(), dao.userLogin(uname, pass));
		}
	}

	@Test
	public void badLoginTest() {
		System.out.println("\n#TEST# incorrect login test");

		DAOFuncImp dao = new DAOFuncImp();
		List<Employee> employees = dao.readAllEmp();

		for (Employee e : employees) {

			String uname = e.getU_username();
			String pass = e.getU_password();

			assertEquals(0, dao.userLogin(uname, pass + "garbage"));
		}
	}

	@Test
	public void saveReimbursment() {
		System.out.println("\n#TEST# save and read reimbursment");

		Random rndm = new Random();
		DAOFuncImp dao = new DAOFuncImp();
		List<Employee> employees = dao.readAllEmp();
		List<Reimbursement> rmbsts = new ArrayList<Reimbursement>();
		int initialCount = dao.readReimbursement().size();

		for (Employee e : employees) {
			Reimbursement r = new Reimbursement(rndm.nextDouble(), e.getId(), 0, "type", "Pending", "ilikmonies");
			rmbsts.add(r);
			dao.saveReimbursement(e.getId(), r);
			dao.commit();
		}

		int finalCountAll = dao.readReimbursement().size();
		int finalCountPend = dao.readAllPendingRmbs().size();

		assertEquals(rmbsts.size() + initialCount, finalCountAll);
		assertEquals(finalCountPend, finalCountAll);
	}

	@Test
	public void imageToByteArrTest() {
		System.out.println("\n#TEST# converting an image to a byte[] and back...test");

		DAOFuncImp dao = new DAOFuncImp();
		File f = new File("C:\\Users\\SkiiNet\\Pictures\\dale.jpg");

		BufferedImage bi;

		try {
			bi = dao.byteArrToImage(dao.imageToByteArr(f));
			ImageIO.write(bi, "jpg", new File("C:\\Users\\SkiiNet\\Pictures\\", "test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void receiptTest(){
		System.out.println("\n#TEST# uploading and retrieving image file(receipt) to/from database");
		
		DAOFuncImp dao = new DAOFuncImp();
		File f = new File("C:\\Users\\SkiiNet\\Pictures\\dale.jpg");
		
		BufferedImage bi;
		boolean probablyWorked = false;
		
		try {
			 byte[] bytes = dao.imageToByteArr(f);
				Random rndm = new Random();
				List<Employee> employees = dao.readAllEmp();
				List<Reimbursement> rmbsts = new ArrayList<Reimbursement>() ;
				int initialCount = dao.readReimbursement().size();
				
				for(Employee e: employees) {
					Reimbursement r = new Reimbursement(rndm.nextDouble(), e.getId(), 0, "type", "Pending","IHaveImage?");
					rmbsts.add( r);
					dao.saveReimbursementWithImage(e.getId(),r,bytes);
					dao.commit();
					break;
				}	
			 
				for(Reimbursement r : dao.readReimbursement()) {
					if(r.get_blob() != null && r.get_blobBytes()!= null) {
						bi = dao.byteArrToImage( r.get_blobBytes()   );
						ImageIO.write(bi, "jpg", new File("C:\\Users\\SkiiNet\\Pictures\\","success!.jpg"));
						probablyWorked = true;
					}
				}
			 
			 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			assertEquals(true, probablyWorked);
		}
		
	}
}
