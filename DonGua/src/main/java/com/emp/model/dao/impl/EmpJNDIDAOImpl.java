package com.emp.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.dao.EmpDAO;
import com.emp.model.vo.EmpVO;
import com.utils.Util;


public class EmpJNDIDAOImpl implements EmpDAO {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DaliyWarm");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO EMP(EMP_ACCT,EMP_POSITION,EMP_PWD,EMP_NAME,EMP_PHONE,EMP_STATUS)VALUES(? ,? ,? ,? ,? ,? )";
//	private static final String GET_ALL_STMT = "SELECT EMP_ID,EMP_ACCT,EMP_POSITION,EMP_PWD,EMP_NAME,EMP_PHONE,EMP_STATUS FROM EMP";
	private static final String GET_ALL_STMT = "select distinct EMP_ID, AUTH.EMP_AUTH_NO, EMP_AUTH_NAME, EMP_ACCT, EMP_POSITION, EMP_PWD, EMP_NAME, EMP_PHONE, EMP_STATUS from EMP natural join EMP_AUTH natural join AUTH";
	private static final String GET_ONE_STMT = "SELECT EMP_ID,EMP_ACCT,EMP_POSITION,EMP_PWD,EMP_NAME,EMP_PHONE,EMP_STATUS FROM EMP WHERE EMP_ACCT = ?";
	private static final String GET_ONE_STMT_ID = "SELECT EMP_ID,EMP_ACCT,EMP_POSITION,EMP_PWD,EMP_NAME,EMP_PHONE,EMP_STATUS FROM EMP WHERE EMP_ID = ?";
	private static final String GET_ONE_STMT_STATUS = "SELECT EMP_ID,EMP_ACCT,EMP_POSITION,EMP_PWD,EMP_NAME,EMP_PHONE,EMP_STATUS FROM EMP WHERE EMP_STATUS = ?";
	private static final String UPDATE = "UPDATE EMP SET EMP_POSITION = ?,EMP_PWD = ?,EMP_NAME = ?,EMP_PHONE = ?,EMP_STATUS = ? WHERE EMP_ACCT = ?";
	private static final String GET_FINAL_ID = "SELECT EMP_ID FROM EMP ORDER BY EMP_ID DESC LIMIT 0 , 1";
	private static final String EMP_IS_EXIST = "SELECT * FROM EMP WHERE EMP_ACCT = ? and EMP_PWD = ? and EMP_STATUS = 1";
	private static final String EMP_IS_EXIST_BY_ID = "select * from EMP where EMP_ID = ?";
	@Override
	public void insert(EmpVO empVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpAcct());
			pstmt.setString(2, empVO.getEmpPosition());
			pstmt.setString(3, empVO.getEmpPwd());
			pstmt.setString(4, empVO.getEmpName());
			pstmt.setString(5, empVO.getEmpPhone());
			pstmt.setString(6, empVO.getEmpStatus());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			throw new RuntimeException(se.getMessage());
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
	public void update(EmpVO empVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmpPosition());
			pstmt.setString(2, empVO.getEmpPwd());
			pstmt.setString(3, empVO.getEmpName());
			pstmt.setString(4, empVO.getEmpPhone());
			pstmt.setString(5, empVO.getEmpStatus());
			pstmt.setString(6, empVO.getEmpAcct());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException(se.getMessage());
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
	public EmpVO findByEmpAcct(String empAcct) {
		// TODO Auto-generated method stub
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, empAcct);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("EMP_ID"));
				empVO.setEmpAcct(rs.getString("EMP_ACCT"));
				empVO.setEmpPosition(rs.getString("EMP_POSITION"));
				empVO.setEmpPwd(rs.getString("EMP_PWD"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpPhone(rs.getString("EMP_PHONE"));
				empVO.setEmpStatus(rs.getString("EMP_STATUS"));
			}

			// Handle any SQL errors
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
		return empVO;
	}

	@Override
	public EmpVO findByEmpId(Integer empId) {
		// TODO Auto-generated method stub
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_ID);

			pstmt.setInt(1, empId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("EMP_ID"));
				empVO.setEmpAcct(rs.getString("EMP_ACCT"));
				empVO.setEmpPosition(rs.getString("EMP_POSITION"));
				empVO.setEmpPwd(rs.getString("EMP_PWD"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpPhone(rs.getString("EMP_PHONE"));
				empVO.setEmpStatus(rs.getString("EMP_STATUS"));
			}

			// Handle any SQL errors
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
		return empVO;
	}
	
	@Override
	public List<EmpVO> findByEmpStatus(String empStatus) {
		// TODO Auto-generated method stub
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT_STATUS);
			
			pstmt.setString(1, empStatus);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("EMP_ID"));
				empVO.setEmpAcct(rs.getString("EMP_ACCT"));
				empVO.setEmpPosition(rs.getString("EMP_POSITION"));
				empVO.setEmpPwd(rs.getString("EMP_PWD"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpPhone(rs.getString("EMP_PHONE"));
				empVO.setEmpStatus(rs.getString("EMP_STATUS"));
				list.add(empVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public List<EmpVO> getAll() {
		// TODO Auto-generated method stub
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("EMP_ID"));
				empVO.setEmpAuthNo(rs.getString("EMP_AUTH_NO"));
				empVO.setEmpAuthName(rs.getString("EMP_AUTH_NAME"));
				empVO.setEmpAcct(rs.getString("EMP_ACCT"));
				empVO.setEmpPosition(rs.getString("EMP_POSITION"));
				empVO.setEmpPwd(rs.getString("EMP_PWD"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpPhone(rs.getString("EMP_PHONE"));
				empVO.setEmpStatus(rs.getString("EMP_STATUS"));
				list.add(empVO);
			}

			// Handle any SQL errors
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
	public String getNewAcct() {
		// TODO Auto-generated method stub
		String feebackAcct = "";
		Integer lastId = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FINAL_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				lastId =rs.getInt("EMP_ID");
				lastId +=1;
			}
			
			feebackAcct = "dailywarm"+String.format("%04d", lastId);

			// Handle any SQL errors
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
		return feebackAcct;
	}

	@Override
	public EmpVO isEmpExist(String empAcct, String empPwd) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(EMP_IS_EXIST);
			
			pstmt.setString(1, empAcct);
			pstmt.setString(2, empPwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("EMP_ID"));
			}else {
				empVO = new EmpVO();
				empVO.setEmpId(0);
			}

			// Handle any SQL errors
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
		return empVO;
	}
	
	@Override
	public Boolean isEmpExistById(Long empId) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(EMP_IS_EXIST_BY_ID);
			
			pstmt.setLong(1, empId);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpId(rs.getInt("EMP_ID"));
			}else {
				return false;
			}
			
			// Handle any SQL errors
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
		return true;
	}


}
