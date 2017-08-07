package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO
{

	@Override
	public void createAccount(Account account, int userId)
	{

		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Account returnAccount = null;

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			final String SQL = "INSERT INTO BANKACCOUNT (BANKACCOUNT.ACCOUNT_NAME, BANKACCOUNT.ACCOUNT_BALANCE, BANKACCOUNT.USER_ID) VALUES( ?, ?, ?) ";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, account.getName());
			pstmt.setDouble(2, account.getBalance());
			pstmt.setInt(3, userId);

			pstmt.executeQuery();

			System.out.println("ACCOUNT CREATED");

		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

	}

	@Override
	public ArrayList<Account> readAllAccounts(int userId)
	{
		System.out.println("Retrieving Accounts...");
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		ArrayList<Account> accountList = new ArrayList<Account>();

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			final String SQL = "SELECT * FROM BANKACCOUNT WHERE BANKACCOUNT.USER_ID = ?";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, userId);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				int accountId = resultSet.getInt("ACCOUNT_ID");
				String accountName = resultSet.getString("ACCOUNT_NAME");
				Double accountBalance = resultSet.getDouble("ACCOUNT_BALANCE");

				accountList.add(new Account(accountId, accountName, accountBalance));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		} finally
		{
			if (pstmt != null)
				try
				{
					pstmt.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			if (resultSet != null)
				try
				{
					resultSet.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return accountList;

	}

	@Override
	public Account deposit(Account account, double amount)
	{
		CallableStatement callableStatement = null;

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			String sql = "{CALL SP_DEPOSIT_ACCOUNT(?, ?)}";
			callableStatement = connection.prepareCall(sql);

			callableStatement.setInt(1, account.getAccountId());
			callableStatement.setDouble(2, amount);

			callableStatement.execute();

			System.out.println("DEPOSIT SUCCESS");

		} catch (Exception e)
		{

			e.printStackTrace();
		} finally
		{
			if (callableStatement != null)
				try
				{
					callableStatement.close();
				} catch (SQLException e)
				{
					e.printStackTrace();

				}
		}

		return null;
	}

	@Override
	public Account withdraw(Account account, double amount)
	{

		if (account.getBalance() - amount < 0)
			System.out.println("Not enough funds to withdraw");
		else
		{
			CallableStatement callableStatement = null;

			try (Connection connection = ConnectionUtil.getConnectionFromProperties())
			{
				String sql = "{CALL SP_WITHDRAW_ACCOUNT(?, ?)}";
				callableStatement = connection.prepareCall(sql);

				callableStatement.setInt(1, account.getAccountId());
				callableStatement.setDouble(2, amount);

				callableStatement.execute();

				System.out.println("WITHDRAW SUCCESS");

			} catch (Exception e)
			{

				e.printStackTrace();
			} finally
			{
				if (callableStatement != null)
					try
					{
						callableStatement.close();
					} catch (SQLException e)
					{
						e.printStackTrace();

					}
			}

		}
		return null;
	}

	@Override
	public void deleteAccount(Account account)
	{
		PreparedStatement pstmt = null;
		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			final String SQL = "DELETE FROM BANKACCOUNT WHERE ACCOUNT_ID = ?";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setInt(1, account.getAccountId());

			pstmt.executeUpdate();

			System.out.println("DELETED");

		} catch (SQLException | IOException e)
		{
			e.printStackTrace();
		}
	}

}
