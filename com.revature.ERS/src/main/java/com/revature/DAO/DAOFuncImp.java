package com.revature.DAO;

import java.util.ArrayList;
import java.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.Random;

import com.revature.ConnectionUtil.ConnectionUtil;
import com.revature.ERS.Employee;
import com.revature.ERS.Reimbursement;

import java.sql.*;

@SuppressWarnings("unused")
public class DAOFuncImp implements DAOFunctions {

	@Override
	public List<String> readEmployee(int id) {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<String> employeeInfo = new ArrayList<String>();
		try (Connection conn = ConnectionUtil.getConnection()) {

			final String sql = "SELECT * FROM ers_users WHERE u_id = ?";
			final String sql2 = "SELECT ur_role FROM ers_user_roles WHERE ur_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);

			pstmt.setInt(1, id);
			pstmt2.setInt(1, id);

			rs = pstmt.executeQuery();
			rs2 = pstmt2.executeQuery();

			while (rs.next()) {
				String u_username = rs.getString("u_username");
				String u_firstname = rs.getString("u_firstname");
				String u_lastname = rs.getString("u_lastname");
				String u_email = rs.getString("u_email");

				employeeInfo.add(u_username);
				employeeInfo.add(u_firstname);
				employeeInfo.add(u_lastname);
				employeeInfo.add(u_email);
			}

			while (rs2.next()) {
				String ur_role = rs.getString("ur_role");
				employeeInfo.add(ur_role);
			}

			return employeeInfo;

		} catch (SQLException se) {
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt2.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}

			if (rs != null) {
				try {
					rs.close();
					rs2.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}

			return null;
		}

	}

	@Override
	public void createEmployee(Employee e) {// this method inserts a user and a user role into the database.

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ers_users (u_username, u_password, u_firstname, u_lastname, u_email) "
					+ "VALUES(?, ?, ?, ?, ?)";
			String sql2 = "INSERT INTO ers_user_roles (ur_role) VALUES(?)";
			e.setU_password(e.generateRandomPass());

			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);

			pstmt.setString(1, e.getU_username());
			pstmt.setString(2, e.getU_password());
			pstmt.setString(3, e.getU_firstname());
			pstmt.setString(4, e.getU_lastname());
			pstmt.setString(5, e.getU_email());

			pstmt2.setString(1, e.getUr_role());

			pstmt2.executeUpdate();
			pstmt.executeUpdate();
			
			commit();

			pstmt.close();
			pstmt2.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}

		//////////// Get and Set Employee ID //////////////////
		pstmt = null;
		int u_id = 0;

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT U_ID FROM ERS_USERS WHERE U_USERNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getU_username());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				u_id = rs.getInt("U_ID");

			}
			e.setId(u_id);

			rs.close();
		} catch (SQLException se) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}
		}

	}

	@Override
	public void deleteEmployee(int id) {

		PreparedStatement pstmt = null, pstmt2 = null;

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM ers_users WHERE u_id = ?";
			String sql2 = "DELETE FROM ers_user_roles WHERE ur_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);

			pstmt.setInt(1, id);
			pstmt2.setInt(1, id);

			int nDelete = pstmt.executeUpdate();
			int nDelete2 = pstmt2.executeUpdate();

			System.out.println("Rows affected: " + nDelete + " and " + nDelete2);

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@Override
	public void updateEmployee(Employee e, int id) {
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE ers_users SET " + "u_username  = ?, " + "u_password = ?, " + "u_firstname = ?, "
					+ "u_lastname = ?, " + "u_email = ? " + "WHERE u_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getU_username());
			pstmt.setString(2, e.getU_password());
			pstmt.setString(3, e.getU_firstname());
			pstmt.setString(4, e.getU_lastname());
			pstmt.setString(5, e.getU_email());
			pstmt.setInt(6, id);

			pstmt.executeQuery();

			pstmt.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@Override
	public int userLogin(String user, String pass) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int u_id = 0;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT u_id FROM ers_users WHERE u_username = ? AND u_password = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				u_id = rs.getInt("u_id");
				
			}
			
			pstmt.close();
			rs.close();
			
			
			
		}catch(SQLException se) {
			if(pstmt != null) {
				try{
					pstmt.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
			
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException seq) {
					seq.printStackTrace();
				}
			}
		}
		
		return u_id;
	}

	@Override
	public List<Employee> readAllEmp() {
		PreparedStatement pstmt = null;
		List<Employee> employee = new ArrayList<>();

		String u_username, u_firstname, u_lastname, u_email, u_pass;
		int u_id;

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ERS_USERS";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				u_id = rs.getInt("U_ID");
				u_username = rs.getString("U_USERNAME");
				u_firstname = rs.getString("U_FIRSTNAME");
				u_lastname = rs.getString("U_LASTNAME");
				u_email = rs.getString("U_EMAIL");
				u_pass = rs.getString("U_PASSWORD");

				Employee e = new Employee();
				e.setU_username(u_username);
				e.setU_firstname(u_firstname);
				e.setU_lastname(u_lastname);
				e.setU_email(u_email);
				e.setId(u_id);
				e.setU_password(u_pass);

				employee.add(e);
			}
			rs.close();
		} catch (SQLException se) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}
		}

		return employee;
	}

	@Override
	public void saveReimbursementWithImage(int id, Reimbursement r, byte[] blobByte) {
		Blob blob = null;

		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement stmt = null;
		Random rand = new Random();
		int key = rand.nextInt(99999);

		try (Connection conn = ConnectionUtil.getConnection()) {

			r.setR_submited(new Timestamp(System.currentTimeMillis()));

			String sql = "INSERT INTO ers_reimbursements (r_amount, r_description, r_receipt, r_submitted, u_id_author, rt_type, rs_status) VALUES(?,?,?,CURRENT_TIMESTAMP,?,?,?)";

			String sql2 = "INSERT INTO ers_reimbursement_status (rs_id, rs_status) VALUES(?,?)";

			String sql3 = "INSERT INTO ers_reimbursement_type (rt_id, rt_type) VALUES (?,?)";

			stmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);

			stmt.setDouble(1, r.getR_amount());
			stmt.setString(2, r.getR_description());
			blob = conn.createBlob();
			blob.setBytes(1, blobByte);
			stmt.setBlob(3, blob);
			stmt.setInt(4, id);
			stmt.setInt(5, key);
			stmt.setInt(6, key);

			pstmt2.setInt(1, key);
			pstmt2.setString(2, r.getRs_status());

			pstmt3.setInt(1, key);
			pstmt3.setString(2, r.getRt_type());

			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
			stmt.executeUpdate();

			r.setF_id(key);

			pstmt3.close();
			pstmt2.close();
			stmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
			if (stmt != null) {
				try {
					stmt.close();
					pstmt2.close();
				} catch (SQLException seq) {

				}
			}
		}

	}

	@Override
	public List<Reimbursement> readReimbursement() {

		Statement stmt = null;
		ResultSet rs = null;
		List<Reimbursement> reList = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM ers_reimbursements";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Reimbursement r = new Reimbursement();

				r.setR_id(rs.getInt("r_id"));
				r.setR_amount(rs.getDouble("r_amount"));
				r.setR_description(rs.getString("r_description"));

				Blob blob = rs.getBlob("r_receipt");

				if (blob != null) {
					int blobLength = (int) blob.length();
					byte[] blobAsBytes = blob.getBytes(1, blobLength);
					r.set_blobBytes(blobAsBytes);
					// release the blob and free up memory
					blob.free();
				}

				r.set_blob(rs.getBlob("r_receipt"));
				r.setR_submited(rs.getTimestamp(5));
				r.setR_resolved(rs.getTimestamp(6));
				r.setU_id_author(rs.getInt("u_id_author"));
				r.setU_id_resolver(rs.getInt("u_id_resolver"));
				r.setRt_type(rs.getString("rt_type"));
				r.setRs_status(rs.getString("rs_status"));

				reList.add(r);
			}

			rs.close();

			return reList;
		} catch (SQLException se) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return reList;
	}

	@Override
	public void saveReimbursement(int id, Reimbursement r) {// this will create a reimbursement linked to the type and
															// status
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement stmt = null;
		Random rand = new Random();
		int key = rand.nextInt(99999);

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO ers_reimbursements(r_amount, r_description, r_submitted, r_resolved, u_id_author, rt_type, rs_status) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

			String sql2 = "INSERT INTO ers_reimbursement_status (rs_id, rs_status) VALUES(?,?)";

			String sql3 = "INSERT INTO ers_reimbursement_type (rt_id, rt_type) VALUES (?,?)";

			pstmt2 = conn.prepareStatement(sql2);
			pstmt3 = conn.prepareStatement(sql3);

			r.setR_submited(new Timestamp(System.currentTimeMillis()));

			String s_sql = "INSERT INTO ers_reimbursements(r_amount, r_description, r_submitted, r_resolved, u_id_author, rt_type, rs_status) "
					+ " VALUES(" + r.getR_amount() + ", '" + r.getR_description() + "', CURRENT_TIMESTAMP, NULL, " + id
					+ ", " + key + ", " + key + ")";

			pstmt2.setInt(1, key);
			pstmt2.setString(2, r.getRs_status());

			pstmt3.setInt(1, key);
			pstmt3.setString(2, r.getRt_type());

			r.setF_id(key);

			pstmt2.executeUpdate();
			pstmt3.executeUpdate();

			stmt = conn.prepareStatement(s_sql);
			stmt.executeUpdate();

			pstmt3.close();
			pstmt2.close();
			stmt.close();
		} catch (SQLException se) {
			if (pstmt != null) {
				try {
					pstmt.close();
					pstmt2.close();
				} catch (SQLException seq) {

				}
			}
		}

	}

	@Override
	public void reimbursementStatus(int r_id, int rs_id, String status) {

		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE ers_reimbursement_status " + "SET rs_status = ? WHERE rs_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, rs_id);

			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException se) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}
		}

	}

	@Override
	public List<Reimbursement> readAllPendingRmbs() {

		int r_id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Reimbursement> reimList = new ArrayList<>();
		Reimbursement r = new Reimbursement();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT rs_id  FROM ERS_REIMBURSEMENT_STATUS WHERE rs_status = 'pending' OR rs_status = 'Pending' ";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int rs_id = rs.getInt("rs_id");

				sql = "SELECT * FROM ers_reimbursements WHERE rs_status = " + rs_id;

				stmt = conn.createStatement();
				rs2 = stmt.executeQuery(sql);

				while (rs2.next()) {
					r.setR_amount(rs2.getDouble("r_amount"));
					r.setR_description(rs2.getString("r_description"));
					r.setR_submited(rs2.getTimestamp("r_submitted"));
					r.setR_resolved(rs2.getTimestamp("r_resolved"));
					r.setU_id_author(rs2.getInt("u_id_author"));
					r.setU_id_resolver(rs2.getInt("u_id_resolver"));
					r.setRt_type(rs2.getString("rt_type"));
					r.setRs_status(rs2.getString("rs_status"));

					reimList.add(r);
				}

			}

			stmt.close();
			rs.close();
			if (rs2 != null) {
				rs2.close();
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return reimList;
	}

	@Override
	public List<Reimbursement> readAllResolvedRmbs() {
		int r_id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Reimbursement> reimList = new ArrayList<>();
		Reimbursement r = new Reimbursement();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT rs_id FROM ERS_REIMBURSEMENT_STATUS WHERE rs_status = 'approved' OR rs_status = 'Approved' ";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int rs_id = rs.getInt("rs_id");

				sql = "SELECT * FROM ers_reimbursements WHERE rs_status = " + rs_id;

				stmt = conn.createStatement();
				rs2 = stmt.executeQuery(sql);

				while (rs2.next()) {
					r.setR_amount(rs2.getDouble("r_amount"));
					r.setR_description(rs2.getString("r_description"));
					r.setR_submited(rs2.getTimestamp("r_submitted"));
					r.setR_resolved(rs2.getTimestamp("r_resolved"));
					r.setU_id_author(rs2.getInt("u_id_author"));
					r.setU_id_resolver(rs2.getInt("u_id_resolver"));
					r.setRt_type(rs2.getString("rt_type"));
					r.setRs_status(rs2.getString("rs_status"));

					reimList.add(r);
				}

			}

			stmt.close();
			rs.close();
			rs2.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return reimList;

	}

	@Override
	public void commit() {
		PreparedStatement pstmt = null;

		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "COMMIT";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}
		}

	}

	@Override
	public String readUserRole(int id) {
		String userRole = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT ur_role FROM ers_user_roles WHERE ur_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				userRole = rs.getString("ur_role");
			}

			pstmt.close();
			rs.close();

		} catch (SQLException se) {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException seq) {
					seq.printStackTrace();
				}
			}
		}
		return userRole;
	}

	@Override
	public byte[] imageToByteArr(File f) throws IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		BufferedImage img = ImageIO.read(f);
		ImageIO.write(img, "jpg", baos);
		baos.flush();

		String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
		baos.close();

		byte[] bytearray = Base64.getDecoder().decode(base64String);

		return bytearray;

	}

	@Override
	public BufferedImage byteArrToImage(byte[] bytearray) throws IOException {

		BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytearray));
		return imag;
	}

	@Override
	public List<Reimbursement> readPendingRmbs(int u_id) {

		int r_id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Reimbursement> reimList = new ArrayList<>();
		Reimbursement r = new Reimbursement();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT rs_id  FROM ERS_REIMBURSEMENT_STATUS WHERE rs_status = 'pending' OR rs_status = 'Pending' ";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int rs_id = rs.getInt("rs_id");

				sql = "SELECT * FROM ers_reimbursements WHERE rs_status = " + rs_id + " AND U_ID_AUTHOR = " + u_id;

				stmt = conn.createStatement();
				rs2 = stmt.executeQuery(sql);

				while (rs2.next()) {
					r.setR_amount(rs2.getDouble("r_amount"));
					r.setR_description(rs2.getString("r_description"));
					r.setR_submited(rs2.getTimestamp("r_submitted"));
					r.setR_resolved(rs2.getTimestamp("r_resolved"));
					r.setU_id_author(rs2.getInt("u_id_author"));
					r.setU_id_resolver(rs2.getInt("u_id_resolver"));
					r.setRt_type(rs2.getString("rt_type"));
					r.setRs_status(rs2.getString("rs_status"));

					reimList.add(r);
				}

			}

			stmt.close();
			rs.close();
			if (rs2 != null) {
				rs2.close();
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return reimList;
	}

	@Override
	public List<Reimbursement> readResolvedRmbs(int u_id) {
		int r_id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Reimbursement> reimList = new ArrayList<>();
		Reimbursement r = new Reimbursement();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT rs_id  FROM ERS_REIMBURSEMENT_STATUS WHERE rs_status = 'approved' OR rs_status = 'Approved' ";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int rs_id = rs.getInt("rs_id");

				sql = "SELECT * FROM ers_reimbursements WHERE rs_status = " + rs_id + " AND U_ID_AUTHOR = " + u_id;

				stmt = conn.createStatement();
				rs2 = stmt.executeQuery(sql);

				while (rs2.next()) {
					r.setR_amount(rs2.getDouble("r_amount"));
					r.setR_description(rs2.getString("r_description"));
					r.setR_submited(rs2.getTimestamp("r_submitted"));
					r.setR_resolved(rs2.getTimestamp("r_resolved"));
					r.setU_id_author(rs2.getInt("u_id_author"));
					r.setU_id_resolver(rs2.getInt("u_id_resolver"));
					r.setRt_type(rs2.getString("rt_type"));
					r.setRs_status(rs2.getString("rs_status"));

					reimList.add(r);
				}

			}

			stmt.close();
			rs.close();
			if (rs2 != null) {
				rs2.close();
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return reimList;
	}

	@Override
	public List<Reimbursement> readEmployeeRequests(int u_id) {
		int r_id = 0;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Reimbursement> reimList = new ArrayList<>();
		Reimbursement r = new Reimbursement();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT rs_id  FROM ERS_REIMBURSEMENT_STATUS";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int rs_id = rs.getInt("rs_id");

				sql = "SELECT * FROM ers_reimbursements WHERE rs_status = " + rs_id + " AND U_ID_AUTHOR = " + u_id;

				stmt = conn.createStatement();
				rs2 = stmt.executeQuery(sql);

				while (rs2.next()) {
					r.setR_amount(rs2.getDouble("r_amount"));
					r.setR_description(rs2.getString("r_description"));
					r.setR_submited(rs2.getTimestamp("r_submitted"));
					r.setR_resolved(rs2.getTimestamp("r_resolved"));
					r.setU_id_author(rs2.getInt("u_id_author"));
					r.setU_id_resolver(rs2.getInt("u_id_resolver"));
					r.setRt_type(rs2.getString("rt_type"));
					r.setRs_status(rs2.getString("rs_status"));

					reimList.add(r);
				}

			}

			stmt.close();
			rs.close();
			if (rs2 != null) {
				rs2.close();
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return reimList;
	}

}
