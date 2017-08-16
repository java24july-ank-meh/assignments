package com.ers.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import com.ers.exceptions.UnsuccessfulConnectionException;
import com.ers.main.ERSUser;
import com.ers.main.ReimbursementRequest;
import com.ers.util.ConnectionUtil;

public class DAOImplementation implements DAO {

	
	/*
	 * Attempts to log in with a username and password.
	 * If successful, returns the user role id number of the user.
	 * (Manager=1, Employee=2)
	 */
	@Override
	public int login(String username, String password) {
		int userRoleId = -1;
		PreparedStatement pst = null;
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			String sql = "SELECT UR_ID FROM ers.ERS_USERS WHERE U_USERNAME = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				userRoleId = rs.getInt("UR_ID");
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
			e.printStackTrace();
			throw new UnsuccessfulConnectionException();
			//return false; // login failed, unsuccessful connection
		}
		return userRoleId;
	}

	/*
	 * Logs the user out. Returns true on success.
	 */
	@Override
	public Boolean logout(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			return true; // successful connection
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
			//return false; // logout failed, unsuccessful connection
		}
	}
	
	
	// Employee methods

	@Override
	public int submitReimbursementRequest(String username, String password, ReimbursementRequest req) {
		PreparedStatement pst = null;
		int status = -1;
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			String sql = "INSERT INTO ers.ERS_REIMBURSEMENTS (R_AMOUNT, R_DESCRIPTION, R_RECEIPT, "
					+ "R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);
			
			pst.setDouble(1, req.getAmount());
			pst.setString(2, req.getDescription());
			
			
			// Converts the BufferedImage to a png format, writes it to an output stream, then sends
			// it into an InputStream to write it out to the database.
			if (req.getReceiptImg() != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(req.getReceiptImg(), "png", baos);
				InputStream imgStream = new ByteArrayInputStream(baos.toByteArray());
				pst.setBinaryStream(3, imgStream);
			} else {
				pst.setBinaryStream(3, null);
			}
			
			if (req.getDateSubmitted() != null) {
				pst.setTimestamp(4, new java.sql.Timestamp(req.getDateSubmitted().getTime()));
			} else pst.setTimestamp(4, null);
			
			if (req.getDateResolved() != null) {
				pst.setTimestamp(5, new java.sql.Timestamp(req.getDateResolved().getTime()));
			} else pst.setTimestamp(5, null);
			
			if (req.getAuthorId() != 0) {
				pst.setInt(6, req.getAuthorId());
			} else pst.setInt(6, 2000000); // default to ersnulluser
			
			if (req.getResolverId() != 0) {
				pst.setInt(7, req.getResolverId());
			} else pst.setInt(7, 2000000); // default to ersnulluser
			
			pst.setInt(8, req.getType());
			
			if (req.getStatus() != 0) {
				pst.setInt(9, req.getStatus());
			} else  pst.setInt(9, 1); // default to 1 (pending)
			
			status = pst.executeUpdate(); // Executes the query
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UnsuccessfulConnectionException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int uploadReceiptImage(String username, String password, BufferedImage imgbuf, int rid) {
		PreparedStatement pst = null;
		int status = -1;
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			String sql = "UPDATE ers.ERS_REIMBURSEMENTS SET R_RECEIPT = ? WHERE R_ID = ?";
			pst = conn.prepareStatement(sql);
			
			
			// Converts the BufferedImage to a png format, writes it to an output stream, then sends
			// it into an InputStream to write it out to the database.
			if (imgbuf != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(imgbuf, "png", baos);
				InputStream imgStream = new ByteArrayInputStream(baos.toByteArray());
				pst.setBinaryStream(1, imgStream);
			} else pst.setBinaryStream(1, null);
			
			
			pst.setInt(2, rid);
			
			status = pst.executeUpdate(); // Executes the update
			
			
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public List<ReimbursementRequest> viewPendingRequests(String username, String password) {
		PreparedStatement pst = null;
		List<ReimbursementRequest> pendingRequests = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			// Create the query
			String sql = "SELECT * FROM ers.ERS_REIMBURSEMENTS WHERE RT_STATUS = 1 AND U_ID_AUTHOR = (SELECT U_ID FROM ers.ERS_USERS WHERE U_USERNAME = ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery(); // Execute the query
			
			// Get the results
			while(rs.next()) {
				// Convert the BLOB to BufferedImage
				Blob blob = rs.getBlob("R_RECEIPT");
				InputStream in = blob.getBinaryStream();
				BufferedImage img = null;
				img = ImageIO.read(in);
				
				// Create ReimbursementRequest
				ReimbursementRequest rr = new ReimbursementRequest(rs.getInt("R_ID"), 
						rs.getDouble("R_AMOUNT"), rs.getString("R_DESCRIPTION"), img,
						rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), 
						rs.getInt("U_ID_AUTHOR"), rs.getInt("U_ID_RESOLVER"), 
						rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS"));
				
				// Add request object to list
				pendingRequests.add(rr);
			}
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
			//return null; // Connection was not successful, so return null
		} catch (IOException ie) {
			ie.printStackTrace(); // Some sort of I/O exception occurred, print stack trace
		}
		
		return pendingRequests;
	}

	@Override
	public List<ReimbursementRequest> viewResolvedRequests(String username, String password) {
		PreparedStatement pst = null;
		List<ReimbursementRequest> pendingRequests = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			// Create the query
			String sql = "SELECT * FROM ers.ERS_REIMBURSEMENTS WHERE RT_STATUS != 1 AND U_ID_AUTHOR = (SELECT U_ID FROM ers.ERS_USERS WHERE U_USERNAME = ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery(); // Execute the query
			
			// Get the results
			while(rs.next()) {
				// Convert the BLOB to BufferedImage
				Blob blob = rs.getBlob("R_RECEIPT");
				InputStream in = blob.getBinaryStream();
				BufferedImage img = null;
				img = ImageIO.read(in);
				
				// Create ReimbursementRequest
				ReimbursementRequest rr = new ReimbursementRequest(rs.getInt("R_ID"), 
						rs.getDouble("R_AMOUNT"), rs.getString("R_DESCRIPTION"), img,
						rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), 
						rs.getInt("U_ID_AUTHOR"), rs.getInt("U_ID_RESOLVER"), 
						rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS"));
				
				// Add request object to list
				pendingRequests.add(rr);
			}
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
			//return null; // Connection was not successful, so return null
		} catch (IOException ie) {
			ie.printStackTrace(); // Some sort of I/O exception occurred, print stack trace
		}
		
		return pendingRequests;

	}

	@Override
	public ERSUser viewEmployeeInfo(String username, String password) {
		PreparedStatement pst = null;
		ERSUser userInfo = null;
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			String sql = "SELECT * FROM ers.ERS_USERS WHERE U_USERNAME = ?";
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				userInfo = new ERSUser(rs.getInt("U_ID"), rs.getString("U_USERNAME"), rs.getString("U_PASSWORD"),
						rs.getString("U_FIRSTNAME"), rs.getString("U_LASTNAME"), 
						rs.getString("U_EMAIL"), rs.getInt("UR_ID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UnsuccessfulConnectionException();
		}
		
		return userInfo;
	}

	@Override
	public int updateEmployeeInfo(String username, String password, String field, String value) {
		PreparedStatement pst = null;
		int status = -1;
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			String sql = "";
			
			switch (field.toLowerCase()) {
			case "firstname":
				sql = "UPDATE ers.ERS_USERS SET U_FIRSTNAME = ? WHERE U_USERNAME = ?";
				break;
			case "lastname":
				sql = "UPDATE ers.ERS_USERS SET U_LASTNAME = ? WHERE U_USERNAME = ?";
				break;
			case "email":
				sql = "UPDATE ers.ERS_USERS SET U_EMAIL = ? WHERE U_USERNAME = ?";
				break;
			default:
				throw new IllegalArgumentException();
			}
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, value);
			pst.setString(2, username);
			
			status = pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
		}
		
		return status;
	}
	
	
	
	// Manager methods
	
	@Override
	public int changeRequestStatus(String username, String password, int reqId, String status) {
		PreparedStatement pst = null;
		int statusCode = -1;
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			String sql = "UPDATE ers.ERS_REIMBURSEMENTS "
					+ "SET RT_STATUS = ? WHERE R_ID = ?";
			
			pst = conn.prepareStatement(sql);
			pst.setInt(2, reqId);

			switch (status.toLowerCase()) {
			case "pending":
				pst.setInt(1, 1);
				break;
			case "approved":
				pst.setInt(1, 2);
				break;
			case "denied":
				pst.setInt(1, 3);
				break;
			default:
				throw new IllegalArgumentException();
			}
			
			statusCode = pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
		}
		
		return statusCode;

	}

	@Override
	public List<ReimbursementRequest> viewAllPendingRequests(String username, String password) {
		PreparedStatement pst = null;
		List<ReimbursementRequest> pendingRequests = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			// Create the query
			String sql = "SELECT * FROM ers.ERS_REIMBURSEMENTS WHERE RT_STATUS = 1";
			pst = conn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(); // Execute the query
			
			// Get the results
			while(rs.next()) {
				// Convert the BLOB to BufferedImage
				Blob blob = rs.getBlob("R_RECEIPT");
				InputStream in = blob.getBinaryStream();
				BufferedImage img = null;
				img = ImageIO.read(in);
				
				// Create ReimbursementRequest
				ReimbursementRequest rr = new ReimbursementRequest(rs.getInt("R_ID"), 
						rs.getDouble("R_AMOUNT"), rs.getString("R_DESCRIPTION"), img,
						rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), 
						rs.getInt("U_ID_AUTHOR"), rs.getInt("U_ID_RESOLVER"), 
						rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS"));
				
				// Add request object to list
				pendingRequests.add(rr);
			}
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
			//return null; // Connection was not successful, so return null
		} catch (IOException ie) {
			ie.printStackTrace(); // Some sort of I/O exception occurred, print stack trace
		}
		
		return pendingRequests;

	}

	@Override
	public List<ReimbursementRequest> viewAllResolvedRequests(String username, String password) {
		PreparedStatement pst = null;
		List<ReimbursementRequest> pendingRequests = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			// Create the query
			String sql = "SELECT * FROM ers.ERS_REIMBURSEMENTS WHERE RT_STATUS != 1";
			pst = conn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(); // Execute the query
			
			// Get the results
			while(rs.next()) {
				// Convert the BLOB to BufferedImage
				Blob blob = rs.getBlob("R_RECEIPT");
				InputStream in = blob.getBinaryStream();
				BufferedImage img = null;
				img = ImageIO.read(in);
				
				// Create ReimbursementRequest
				ReimbursementRequest rr = new ReimbursementRequest(rs.getInt("R_ID"), 
						rs.getDouble("R_AMOUNT"), rs.getString("R_DESCRIPTION"), img,
						rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), 
						rs.getInt("U_ID_AUTHOR"), rs.getInt("U_ID_RESOLVER"), 
						rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS"));
				
				// Add request object to list
				pendingRequests.add(rr);
			}
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
			//return null; // Connection was not successful, so return null
		} catch (IOException ie) {
			ie.printStackTrace(); // Some sort of I/O exception occurred, print stack trace
		}
		
		return pendingRequests;

	}

	@Override
	public List<ReimbursementRequest> viewEmployeeRequests(String username, String password, String employeeId) {
		PreparedStatement pst = null;
		List<ReimbursementRequest> pendingRequests = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			// Create the query
			String sql = "SELECT * FROM ers.ERS_REIMBURSEMENTS WHERE U_ID_AUTHOR = (SELECT U_ID FROM ers.ERS_USERS WHERE U_USERNAME = ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			
			ResultSet rs = pst.executeQuery(); // Execute the query
			
			// Get the results
			while(rs.next()) {
				// Convert the BLOB to BufferedImage
				Blob blob = rs.getBlob("R_RECEIPT");
				InputStream in = blob.getBinaryStream();
				BufferedImage img = null;
				img = ImageIO.read(in);
				
				// Create ReimbursementRequest
				ReimbursementRequest rr = new ReimbursementRequest(rs.getInt("R_ID"), 
						rs.getDouble("R_AMOUNT"), rs.getString("R_DESCRIPTION"), img,
						rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), 
						rs.getInt("U_ID_AUTHOR"), rs.getInt("U_ID_RESOLVER"), 
						rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS"));
				
				// Add request object to list
				pendingRequests.add(rr);
			}
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
			//return null; // Connection was not successful, so return null
		} catch (IOException ie) {
			ie.printStackTrace(); // Some sort of I/O exception occurred, print stack trace
		}
		
		return pendingRequests;

	}

	@Override
	public BufferedImage viewReceiptImage(String username, String password, int reqId) {
		BufferedImage img = null;
		PreparedStatement pst = null;
		Blob blob = null;
		
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			String sql = "SELECT R_RECEIPT FROM ers.ERS_REIMBURSEMENTS WHERE R_ID = ?";
			
			pst = conn.prepareStatement(sql);
			pst.setInt(1, reqId);
			
			ResultSet rs = pst.executeQuery(); // Execute the query
			
			if(rs.next()) {
				blob = rs.getBlob("R_RECEIPT");
				InputStream in = blob.getBinaryStream();
				img = ImageIO.read(in);
			}
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
			//return null; // Unsuccessful connection
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return img;
	}

	@Override
	public List<ERSUser> viewAllEmployees(String username, String password) {
		PreparedStatement pst = null;
		List<ERSUser> usersArr = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection(DAO.url, username, password)) {
			
			String sql = "SELECT * FROM ers.ERS_USERS";
			pst = conn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				usersArr.add(new ERSUser(rs.getInt("U_ID"), rs.getString("U_USERNAME"), rs.getString("U_PASSWORD"),
						rs.getString("U_FIRSTNAME"), rs.getString("U_LASTNAME"), rs.getString("U_EMAIL"),
						rs.getInt("UR_ID")));
			}
			
		} catch (SQLException e) {
			throw new UnsuccessfulConnectionException();
		}
		
		return usersArr;
	}

}
