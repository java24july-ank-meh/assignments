package Main;

import java.sql.*;
import java.util.*;

public class BankMethods implements MyInterface {
	Scanner input = new Scanner(System.in);
	List<User> list = new ArrayList<>();
	private int increment = 1;

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
	public User createAccount() {

		int decision;
		
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

		user.setId(increment);
		user.setBankId(increment);
		// user.setBankId(bankid.randomUUID().toString());

		list.add(user);
		
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

		
		return user;
	}

	@Override
	public String logout() {
		// TODO Auto-generated method stub
		return null;
	}

}
