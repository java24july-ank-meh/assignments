package com.revature.CusDAO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.ConnectionB.ConnectingBank;
import com.revature.Customers.Customers;

public class CusDAOImpl implements BankDAO {

	@Override
	public void createCustomer(Customers cus) {
		// Opening a new connection
		try (Connection conn = ConnectingBank.getConnectionProp()) {
			// Storing class fields as future arguments
			String first = cus.getFirstName();
			String last = cus.getLastName();
			String user = cus.getUsername();
			char pass = cus.getPassword();
			int ages = cus.getAge();
			String phone = cus.getPhoneNumber();
			String emails = cus.getEmail();
			// Declare sql statement + argument statement
			String sql = "INSERT INTO CUSTOMER(CUSTOMER_FIRSTNAME, CUSTOMER_LASTNAME, CUSTOMER_USERNAME, CUSTOMER_PASSWORD, CUSTOMER_AGE, CUSTOMER_PHONE,"
					+ "CUSTOMER_EMAIL) VALUES('" + first + "' ," + last + "' ," + user + "' ," + pass + "' ," + ages
					+ "' ," + phone + "' ," + emails + ")";
			// Create the statement, as part of the connection
			Statement stmt = conn.createStatement();
			// Execute the statement
			int nRowsAffected = stmt.executeUpdate(sql);
			System.out.println(nRowsAffected + " row created");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException a) {
			a.printStackTrace();
		}

	}

	@Override
	//Have it where you ask for a customer id in order to see their account balance
	public void readAccountNumber(int number) {
		PreparedStatement pstmt = null;
		// what is going to be stored
		ResultSet rs = null;
		try (Connection conn = ConnectingBank.getConnectionProp()) {
			String sql = "SELECT * FROM ACCOUNTS WHERE CUSTOMER_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String accountNumber = rs.getString("ACCOUNT_NUMBER");
				String accountBalance = rs.getString("ACCOUNT_BALANCE");

				System.out.println("Account Number: " + accountNumber);
				System.out.println("Account Balance: " + accountBalance );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException a) {
			a.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void updateCustomer(Customers cus) {

	}

	@Override
	public void deleteCustomer(int id) {
			PreparedStatement pstmt = null;
			// what is going to be stored
			ResultSet rs = null;
			try (Connection conn = ConnectingBank.getConnectionProp()) {
				String sql = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException a) {
				a.printStackTrace();
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		

	}
	@Override
	public void withdraw(int take) {
				CallableStatement cs = null;
				try (Connection conn = ConnectingBank.getConnectionProp()) {
					
					String sql = "{CALL PR_WITHDRAW(?)}";
					cs = conn.prepareCall(sql);
					cs.setInt(1, take);
					Boolean result = cs.execute();
					if (result) {
						System.out.println("Withdraw sucessfull");
					} else {
						System.out.println("Withdraw failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (cs != null) {
						try {
							cs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
	@Override
	public void deposit(int give) {
				CallableStatement cs = null;
				try (Connection conn = ConnectingBank.getConnectionProp()) {
					String sql = "{CALL PR_DEPOSIT(?}";
					cs = conn.prepareCall(sql);
					cs.setInt(1, give);
					Boolean result = cs.execute();
					if (result) {
						System.out.println("Deposit Successful");
					} else {
						System.out.println("Deposit Failed");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (cs != null) {
						try {
							cs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}

}
