package Main;

import java.sql.*;
import java.util.*;

public class RegularUser implements MyInterface {
	Scanner input = new Scanner(System.in);
	User user = new User();
	private int increment = 1;
	static List<User> list = new ArrayList<>();
	
	
	
	@Override
	public double makeWithdraw(int id, double amt) {

		CallableStatement callableStatement = null;
		try (Connection conn = BankConnections.getconnection()) {
			String sql = "{CALL ACCOUNT_WITHDRAWAL(?,?)}";
			callableStatement = conn.prepareCall(sql);
			callableStatement.setInt(1, id);
			callableStatement.setDouble(2, amt);
			System.out.println("You withdrew: $" + amt + "\nThank you!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return amt;

	}

	@Override
	public double makeDeposit(int id, double amt) {

		CallableStatement callableStatement = null;

		try (Connection conn = BankConnections.getconnection()) {
			String sql = "{CALL ACCOUNT_DEPOSITE(?,?) }";
			callableStatement = conn.prepareCall(sql);

			callableStatement.setInt(1, id);
			callableStatement.setDouble(2, amt);

			System.out.println("You deposited: $" + amt + "\nThank you!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to make a deposite");
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return amt;
	}

	@Override
	public void login() {

		int decision =0;
		String yesOrno = "n";
		User user = new User();

		// UUID bankid = null;

		System.out.println("enter firstname");
		user.setFirstName(input.nextLine());

		System.out.println("enter lastname");
		user.setLastName(input.nextLine());

		System.out.println("enter username");
		user.setUsername(input.nextLine());

		while (list.contains(user.getUsername())) {

			System.out.println("please choose another username, enter again");
			user.setUsername(input.nextLine());

		}
		System.out.println("enter password");
		user.setPassword(input.nextLine());

	
		// user.setBankId(bankid.randomUUID().toString());

		list.add(user);
		
		do {
		System.out.println("Menu Options: \n`1` Withdraw \n`2`Deposite ");
		decision = input.nextInt();
		
		
		switch(decision) {
		
		case 1: 
			double withdraw;
			System.out.println("How much would you like to withdraw?");
				withdraw = input.nextDouble();
				makeWithdraw(user.getId(),withdraw);
				
		case 2: 
			double deposit;
			System.out.println("How much would you like to deposit?");
			deposit = input.nextDouble();
			makeDeposit(user.getId(), deposit);
		}

		increment++;

		System.out.println("\nReturn to menu ? \n(Y/N)\n");
		yesOrno = input.nextLine();
		
	} while(yesOrno.equalsIgnoreCase("y"));
	}
	
	public static List<User> viewMyAccount(int id) {
		PreparedStatement mPreparedStatement = null;
		try {
			Connection conn = BankConnections.getconnection();
			String sql = "SELECT USER_ID, FIRSTNAME, LASTNAME FROM USERINFORMATION WHERE ID =?";
			mPreparedStatement = conn.prepareStatement(sql);
			ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				int userId = mResultSet.getInt("USER_ID");
				String firstName = mResultSet.getString("FIRSTNAME");
				String lastName = mResultSet.getString("LASTNAME");
				String username = mResultSet.getString("USERNAME");
				String password = mResultSet.getString("PASSWORD");
				
				User bankAccount = new User(userId, firstName, lastName,username, password);
				
				list.add(bankAccount);

			}
			mResultSet.close();
		} catch (Exception e) {
			System.out.println("Faild connection");
			e.printStackTrace();

		} finally {
			if (mPreparedStatement != null) {
				try {
					mPreparedStatement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;

			}
	@Override
	public String logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
