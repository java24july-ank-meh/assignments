package bank;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;



import bank.connectionUtil;

public class DAOImp implements DOA
{
	
	private class account
	{
		public account(int id, float balance2) {
			// TODO Auto-generated constructor stub
		}
		int accountID;
		float balance;
	}

	@Override
	public void createUser(String username, String password) throws Exception 
	{
		// TODO Auto-generated method stub
				try(Connection conn = connectionUtil.getConnProp())
				{
					String sql = "insert into users(username, password) values('" +username+", '" +password+")";
					Statement s = conn.createStatement();
					s.executeUpdate(sql);
					System.out.println(username + "added");
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
	}

	@Override
	public void createAccount(int userID) throws Exception
	{
		// TODO Auto-generated method stub
		try(Connection conn = connectionUtil.getConnProp())
		{
			String sql = "insert into accounts(userID, balance) values('" +userID+", '" +0+")";
			Statement s = conn.createStatement();
			s.executeUpdate(sql);
			System.out.println("account created");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int readAccounts(int userID) throws Exception 
	{
		System.console().flush();
		System.out.println("Please select account");
		PreparedStatement pS = null;
		List<account> caves = new ArrayList<>();
		try
		{
			Connection conn = connectionUtil.getConnProp();
            String sql = "SELECT * FROM account where userID = " + userID ;
            pS = conn.prepareStatement(sql);
            ResultSet rs = pS.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("acountID");
                float balance = rs.getInt("balance");
                
                account c = new account(id,balance);
                caves.add(c);
            }
            rs.close();
        } 
		catch(SQLException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            if (pS!=null) 
            {
                try {pS.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
        for(int i = 0; i < caves.size(); i++)
        {
        	System.out.println(i + ") Account number:" + caves.get(i).accountID);
        }
        return caves.size();
	}

	@Override
	public void readBalance(int accountID) throws Exception
	{
		// TODO Auto-generated method stub
		System.console().flush();
		System.out.println("Account balance: ");
		PreparedStatement pS = null;
		List<account> caves = new ArrayList<>();
		try
		{
			Connection conn = connectionUtil.getConnProp();
            String sql = "SELECT * FROM account where accountID = " + accountID ;
            pS = conn.prepareStatement(sql);
            ResultSet rs = pS.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("acountID");
                float balance = rs.getInt("balance");
                
                account c = new account(id,balance);
                caves.add(c);
            }
            rs.close();
        } 
		catch(SQLException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            if (pS!=null) 
            {
                try {pS.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
        	System.out.println(caves.get(0).balance);

	}

	@Override
	public void updateAccount(int accountID, float ammount) throws Exception 
	{
		System.console().flush();
		System.out.println("Please select account");
		PreparedStatement pS = null;
		List<account> caves = new ArrayList<>();
		try
		{
			Connection conn = connectionUtil.getConnProp();
            String sql = "SELECT * FROM account where accountID = " + accountID ;
            pS = conn.prepareStatement(sql);
            ResultSet rs = pS.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("acountID");
                float balance = rs.getInt("balance");
                
                account c = new account(id,balance);
                caves.add(c);
            }
            rs.close();
        } 
		catch(SQLException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            if (pS!=null) 
            {
                try {pS.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
        
		float newTotal = ammount + caves.get(0).balance;
		
		if(newTotal >= 0)
		{
			try
			{
				Connection conn = connectionUtil.getConnProp();
	            String sql = "update balance account set balance = " + newTotal + "where accountID = " + accountID ;
	            pS = conn.prepareStatement(sql);
	            pS.executeQuery();
	        }
			catch(SQLException e) 
			{
	            e.printStackTrace();
	        } 
			finally 
			{
	            if (pS!=null) 
	            {
	                try {pS.close();} 
	                catch(SQLException e) {e.printStackTrace();}
	            }
	        }
		}
		else 
		{
			System.out.println("Insuffient funds");
			return;
		}
		
	}


	@Override
	public void deleteAccount(int accountID) throws Exception
	{
		System.console().flush();
		System.out.println("Please select account");
		PreparedStatement pS = null;
		List<account> caves = new ArrayList<>();
		try
		{
			Connection conn = connectionUtil.getConnProp();
            String sql = "SELECT * FROM account where accountID = " + accountID ;
            pS = conn.prepareStatement(sql);
            ResultSet rs = pS.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("acountID");
                float balance = rs.getInt("balance");
                
                account c = new account(id,balance);
                caves.add(c);
            }
            rs.close();
        } 
		catch(SQLException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            if (pS!=null) 
            {
                try {pS.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
        
		
		
		if(caves.get(0).balance == 0)
		{
			try
			{
				Connection conn = connectionUtil.getConnProp();
	            String sql = "delete from accounts where accountID = " + accountID ;
	            pS = conn.prepareStatement(sql);
	            pS.executeQuery();
	        }
			catch(SQLException e) 
			{
	            e.printStackTrace();
	        } 
			finally 
			{
	            if (pS!=null) 
	            {
	                try {pS.close();} 
	                catch(SQLException e) {e.printStackTrace();}
	            }
	        }
		}
		else 
		{
			System.out.println("Funds present in account, unable to delete");
			return;
		}
	}

	@Override
	public void deleteUser(int userID) throws Exception
	{
		// TODO Auto-generated method stub
		System.console().flush();
		PreparedStatement pS = null;
		List<account> caves = new ArrayList<>();
		try
		{
			Connection conn = connectionUtil.getConnProp();
            String sql = "SELECT * FROM users where userID = " + userID ;
            pS = conn.prepareStatement(sql);
            ResultSet rs = pS.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("acountID");
                float balance = rs.getInt("balance");
                
                account c = new account(id,balance);
                caves.add(c);
            }
            rs.close();
        } 
		catch(SQLException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            if (pS!=null) 
            {
                try {pS.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
        
		
		
		
		
			try
			{
				Connection conn = connectionUtil.getConnProp();
	            String sql = "delete from users where userID = " + userID ;
	            pS = conn.prepareStatement(sql);
	            pS.executeQuery();
	        }
			catch(SQLException e) 
			{
	            e.printStackTrace();
	        } 
			finally 
			{
	            if (pS!=null) 
	            {
	                try {pS.close();} 
	                catch(SQLException e) {e.printStackTrace();}
	            }
	        }
	}

	@Override
	public void updateUsername(int userID, String newName) throws Exception 
	{
		System.console().flush();
		PreparedStatement pS = null;
		
		try
		{
			Connection conn = connectionUtil.getConnProp();
            String sql = "SELECT * FROM users where accountID = " + userID ;
            pS = conn.prepareStatement(sql);
            ResultSet rs = pS.executeQuery();
            
            sql = "update users account set username = " + newName + "where accountID = " + userID ;
            pS.close();
            pS = conn.prepareStatement(sql);
	        pS.executeQuery();
            
            rs.close();
        } 
		catch(SQLException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            if (pS!=null) 
            {
                try {pS.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
	}

		
		
	

	@Override
	public void updatePassword(int userID, String newPassword) throws Exception 
	{

		System.console().flush();
		PreparedStatement pS = null;
		try
		{
			Connection conn = connectionUtil.getConnProp();
            String sql = "SELECT * FROM users where accountID = " + userID ;
            pS = conn.prepareStatement(sql);
            ResultSet rs = pS.executeQuery();
            pS.close();
            sql = "update users account set username = " + newPassword + "where accountID = " + userID ;
	        pS = conn.prepareStatement(sql);
	        pS.executeQuery();
            rs.close();
        } 
		catch(SQLException e) 
		{
            e.printStackTrace();
        } 
		finally 
		{
            if (pS!=null) 
            {
                try {pS.close();} 
                catch(SQLException e) {e.printStackTrace();}
            }
        }
	}

}
