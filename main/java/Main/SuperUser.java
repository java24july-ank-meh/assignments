package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Main.BankConnections;
import Main.User;

public class SuperUser implements SuperInterface {
	static User bUsers = new User();
	private final String passName = "superuser";
	private final String supPass = "password";
	List<User> myList = new ArrayList<>();

	public void login() {
		String superUname;
		String superPass;
		int decision;
		String yesOrno = "y";

		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your username: \n");
		superUname = input.nextLine();
		System.out.println("Please enter a password: \n");
		superPass = input.nextLine();
		while (!(superUname.equals(passName) && superPass.equals(supPass))) {
			System.out.println("Incorrect username/password!\n\nTry again\n");
			System.out.println("Please enter your username");
			superUname = input.nextLine();
			System.out.println("Please enter a password");
			superPass = input.nextLine();

		}
		System.out.println("You are a super user!");

		System.out.println("Select your options");
		System.out.println("1)View Accounts \n2)Create Account \n3)Update account \n4)Delete");
		decision = input.nextInt();

		switch (decision) {

		case 1:
			getAllAccounts();

			break;

		case 2:
			creatBankAccount(createdUser());

			break;

		case 3:
			break;

		case 4:
			System.out.println("Would you like to delete a record (a) or all tables (b)?");
			String delete = input.nextLine();

			if (delete.equalsIgnoreCase("a")) {
				System.out.println("What is the id you would like to delete?");
				int deleteid = input.nextInt();
				deleteBankAccount(deleteid);

			} else if (delete.equals("b")) {
				System.out.println("What is the id you would like to delete?");
				int deleteid = input.nextInt();
				deleteBankAccount(deleteid);
			}
			break;

		default:
		}

	}

	public User createdUser() {
		Scanner input = new Scanner(System.in);
		User bUsers = new User();
		System.out.println("Enter in a id");
		bUsers.setId(input.nextInt());

		System.out.println("First Name: ");
		bUsers.setFirstName(input.nextLine());
		input.nextLine();
		System.out.println("Last Name:");
		bUsers.setLastName(input.nextLine());
		System.out.println("Username: ");
		bUsers.setUsername(input.nextLine());
		System.out.println("Password: ");
		bUsers.setPassword(input.nextLine());

		System.out.println("Type of Account -(CHECKING/SAVING):");
		bUsers.setBankType(input.nextLine());
		input.nextLine();
		bUsers.setBalance(0);

		myList.add(bUsers);

		return bUsers;
	}

	@Override
	public void creatBankAccount(User b) {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter in a id");
		bUsers.setId(input.nextInt());

		System.out.println("First Name: ");
		bUsers.setFirstName(input.nextLine());
		input.nextLine();
		System.out.println("Last Name:");
		bUsers.setLastName(input.nextLine());
		System.out.println("Username: ");
		bUsers.setUsername(input.nextLine());
		System.out.println("Password: ");
		bUsers.setPassword(input.nextLine());

		System.out.println("Type of Account -(CHECKING/SAVING):");
		bUsers.setBankType(input.nextLine());
		input.nextLine();
		bUsers.setBalance(0);

		myList.add(bUsers);
		try (Connection conn = BankConnections.getconnection()) {

			String sql = "INSERT INTO BANK_INFORMATION(ACCOUNT_TYPE, ACCOUNT_BALANCE, USER_ID) VALUES('"
					+ bUsers.getFirstName() + "','" + bUsers.getLastName() + "','" + bUsers.getUsername() + "','"
					+ bUsers.getPassword() + "')";

			String sql2 = "INSERT INTO BANK_INFORMATION(ACCOUNT_TYPE, ACCOUNT_BALANCE, USER_ID) VALUES ("
					+ bUsers.getBankType() + ",'" + bUsers.getBalance() + "'," + bUsers.getBalance() + ")";

			Statement statement = conn.createStatement();

			int affectedRow1 = statement.executeUpdate(sql);
			int affectedRow2 = statement.executeUpdate(sql2);
			int totalRows = affectedRow1 + affectedRow2;
			System.out.println(totalRows + " row created");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void readBankAccount(int id) {

		PreparedStatement prep = null;
		ResultSet result = null;
		try (Connection conn = BankConnections.getconnection()) {
			String sql = "SELECT * FROM USERINFORMATION WHERE USER_ID =?";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, id);

			result = prep.executeQuery();
			while (result.next()) {
				int userId = result.getInt("USER_ID");
				String firstName = result.getString("FIRSTNAME");
				String lastName = result.getString("LASTNAME");

				System.out.println("USER_ID " + userId);
				System.out.println("FIRSTNAME " + firstName);
				System.out.println("LASTNAME " + lastName);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("You did not get the connection");
		} finally {
			if (prep != null) {
				try {
					prep.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				if (result != null) {
					result.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateBankAccount(User b) {

		PreparedStatement prep = null;

	}

	@Override
	public void deleteBankAccount(int id) {
		PreparedStatement statement = null;
		try (Connection conn = BankConnections.getconnection()) {
			String sql = "DELETE FROM USER_INFORMATION WHERE USER_ID =?";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeQuery();
			int rowsdeleted = statement.executeUpdate();
			System.out.println(rowsdeleted + " rows deleted");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void dropTables(String tablename) {
		PreparedStatement prep = null;
		ResultSet rs = null;
		try (Connection conn = BankConnections.getconnection()) {
			String sql = "DROP TABLE ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, tablename);
			rs = prep.executeQuery();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("That is a incorrect table");
		} finally {
			if (prep != null) {
				try {
					prep.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Connection not closed");
				}
			}

		}

	}

	public List<User> getAllAccounts() {
		PreparedStatement mPreparedStatement = null;

		List<User> mAccounts = new ArrayList<>();

		try {
			Connection conn = BankConnections.getconnection();
			String sql = "SELECT * FROM USER_INFORMATION";
			mPreparedStatement = conn.prepareStatement(sql);
			ResultSet mResultSet = mPreparedStatement.executeQuery();
			while (mResultSet.next()) {
				int userId = mResultSet.getInt("USER_ID");
				String firstName = mResultSet.getString("FIRSTNAME");
				String lastName = mResultSet.getString("LASTNAME");
				String username = mResultSet.getString("USERNAME");
				String password = mResultSet.getString("PASSWORDS");
				User bankAccount = new User(userId, firstName, lastName, username, password);
				mAccounts.add(bankAccount);

				System.out.println(bankAccount.toString());
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

		return mAccounts;
	}

}
