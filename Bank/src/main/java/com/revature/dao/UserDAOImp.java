package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImp implements UserDAO
{

	public void createUser(User user)
	{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		User returnUser = null;

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			String username = user.getUsername();

			String password = user.getPassword();

			final String SQL = "INSERT INTO BANKUSER (BANKUSER.USERNAME, BANKUSER.PASSWORD) VALUES( ?, ?)";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());

			pstmt.executeQuery();

			// int numberOfRowsAffected = pstmt.executeUpdate(SQL);

			System.out.println("USER CREATED");

		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public User readUser(User user)
	{
		System.out.println("Logging in...");
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		User returnUser = null;

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			final String SQL = "SELECT * FROM BANKUSER WHERE USERNAME = ?";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				String username = resultSet.getString("USERNAME");
				String password = resultSet.getString("PASSWORD");
				int userId = resultSet.getInt("USER_ID");

				returnUser = new User(userId, username, password);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
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
		return returnUser;

	}

	public User updateUser(User user, User newUser)
	{
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		User updatedUser = null;

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			final String SQL = "UPDATE BANKUSER SET USERNAME=? ,PASSWORD=? WHERE USERNAME=?";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, user.getUsername());

			pstmt.executeUpdate();

			// //resultSet = pstmt.executeUpdate();
			//
			// while (resultSet.next())
			// {
			// String username = resultSet.getString("USERNAME");
			// String password = resultSet.getString("PASSWORD");
			//
			// System.out.println("User UPDATED");
			//
			// updatedUser = new User(username, password);
			// }
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
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
		return updatedUser;

	}

	public void deleteAllUsers()
	{
		PreparedStatement pstmt = null;

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			final String SQL = "DELETE FROM BANKUSER";
			pstmt = connection.prepareStatement(SQL);

			pstmt.executeQuery();
			System.out.println("USERS DELETED");
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
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
		}
	}

	@Override
	public ArrayList<User> readAllUsers()
	{
		System.out.println("Retrieving Users...");
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		ArrayList<User> userList = new ArrayList<User>();

		try (Connection connection = ConnectionUtil.getConnectionFromProperties())
		{
			final String SQL = "SELECT * FROM BANKUSER";
			pstmt = connection.prepareStatement(SQL);
			resultSet = pstmt.executeQuery();

			while (resultSet.next())
			{
				String username = resultSet.getString("USERNAME");
				String password = resultSet.getString("PASSWORD");

				userList.add(new User(username, password));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
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
		return userList;

	}

}
