package com.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthJNDIDAO implements AuthDAO {

	@Autowired
	private DataSource ds;
	
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DaliyWarm");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private static final String INSERT_STMT = "INSERT INTO AUTH (EMP_AUTH_NO,FUNCTION_NO,EMP_AUTH_NAME) VALUES (?, ?, ?)"; // 新增群組
	private static final String UPDATE_FUNCTION = "UPDATE AUTH SET FUNCTION_NO = ?,WHERE EMP_AUTH_NO = ?"; // 更新群組功能
	private static final String UPDATE_EMP_AUTH_NAME = "UPDATE AUTH SET EMP_AUTH_NAME = ?,WHERE EMP_AUTH_NO = ?"; // 更新群組名稱
	private static final String REMOVE = "DELETE FROM AUTH where EMP_AUTH_NO = ?"; // 刪除群組
	private static final String GET_ALL_AUTH_STMT = "SELECT DISTINCT EMP_AUTH_NAME, EMP_AUTH_NO FROM AUTH"; // 查詢所有群組名稱與編號
	private static final String GET_ALL_EMP_AUTH_NO = "SELECT DISTINCT EMP_AUTH_NO FROM EMP_AUTH"; // 查詢EMP_AUTH群組名稱與編號
	private static final String GET_ONE_STMT_NO = "SELECT FUNCTION_NO FROM AUTH WHERE EMP_AUTH_NO = ?"; // 查詢單一群組所有功能
	private static final String GET_ALL_AUTH = "SELECT * FROM AUTH"; // 查詢整張TABLE

	@Override
	public void insert(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, authVO.getEmpAuthNo());
			pstmt.setString(2, authVO.getFunctionNo());
			pstmt.setString(3, authVO.getEmpAuthName());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<AuthVO> findEmpAuthNo() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_EMP_AUTH_NO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				authVO = new AuthVO();
				authVO.setEmpAuthNo(rs.getString("EMP_AUTH_NO"));

				list.add(authVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
		
	}

	@Override
	public void updateFunction(String empAuthNo, String[] functionNo, String empAuthName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			for (String string : functionNo) {
				pstmt.setString(1, empAuthNo);
				pstmt.setString(2, string);
				pstmt.setString(3, empAuthName);
				pstmt.executeUpdate();
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void updateEmpAuthName(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EMP_AUTH_NAME);
			pstmt.setString(1, authVO.getEmpAuthNo());
			pstmt.setString(2, authVO.getFunctionNo());
			pstmt.setString(3, authVO.getEmpAuthName());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void remove(String empAuthNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(REMOVE);

			pstmt.setString(1, empAuthNo);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public List<AuthVO> findAllEmpAuthNo() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_AUTH_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				authVO = new AuthVO();
				authVO.setEmpAuthNo(rs.getString("EMP_AUTH_NO"));
				authVO.setEmpAuthName(rs.getString("EMP_AUTH_NAME"));

				list.add(authVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<AuthVO> findFunctionByEmpAuthNo(String empAuthNo) {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_NO);

			pstmt.setString(1, empAuthNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				authVO = new AuthVO();
				authVO.setEmpAuthNo(empAuthNo);
				authVO.setFunctionNo(rs.getString("FUNCTION_NO"));
				authVO.setFunctionNoInt(Integer.parseInt(rs.getString("FUNCTION_NO")));
				list.add(authVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<AuthVO> findAuthTable() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_AUTH);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				authVO = new AuthVO();
				authVO.setEmpAuthNo(rs.getString("EMP_AUTH_NO"));
				authVO.setFunctionNo(rs.getString("FUNCTION_NO"));
				authVO.setEmpAuthName(rs.getString("EMP_AUTH_NAME"));

				list.add(authVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}