package com.carermemapplymgt.model;

import java.io.InputStream;
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

import com.careordermgt.model.CareApplyMgtVO;
import com.carermemapply.model.CarerMemApplyVO;

public class CarerMemApplyMgtDAOImpl implements CarerMemApplyMgtDAO {

	// DataSource
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/group6");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	// 查詢每筆應徵資料
	private static final String GET_ALL_APPLY_DATA = "SELECT * FROM CARER_MEMBER_APPLY CMA LEFT JOIN MEMBER M ON CMA.MEM_ID = M.MEM_ID";

	// 查詢單筆應徵資料
	private static final String GET_ONE_APPLY_DATA = "SELECT * FROM CARER_MEMBER_APPLY CMA LEFT JOIN MEMBER M ON CMA.MEM_ID = M.MEM_ID LEFT JOIN DISTRICT D ON CMA.SERVICE_DIST_NO = D.DIST_NO WHERE APPLY_ID = ?";

	// 審核失敗 更新申請單狀態
	private static final String UPDATE_FAIL_STATUS = "UPDATE CARER_MEMBER_APPLY SET STATUS = 2 ,UPDATE_TIME = CURRENT_TIMESTAMP() WHERE APPLY_ID = ?";

	// 審核成功 新增資料至照護會員
	private static final String INSERT_CARERMEM = "INSERT INTO CARER\r\n" + "(CARER_ID, CARER_ACCT, CARER_PWD, \r\n"
			+ "SERVICE_DIST_NO, BANK_CODE, \r\n" + "BANK_ACCT, SERVICE_TYPE, INTRO, \r\n"
			+ "PRICE_HOUR, PRICE_HALFDAY, PRICE_DAY, \r\n" + "CANCEL_COUNT, CARER_STATUS) \r\n"
			+ "SELECT CMA.MEM_ID, M.MEM_ACCT, M.MEM_PWD, CMA.SERVICE_DIST_NO, \r\n"
			+ "CMA.BANK_CODE, CMA.BANK_ACCT, CMA.SERVICE_TYPE, CMA.INTRO, \r\n"
			+ "CMA.PRICE_HOUR, CMA.PRICE_HALFDAY, CMA.PRICE_DAY, '0', '1' \r\n" + "FROM CARER_MEMBER_APPLY AS CMA \r\n"
			+ "LEFT JOIN MEMBER AS M \r\n" + "ON CMA.MEM_ID = M.MEM_ID \r\n" + "WHERE CMA.APPLY_ID = ?";

	// 審核成功 更改申請單狀態
	private static final String UPDATE_SUCCESS_STATUS = "UPDATE CARER_MEMBER_APPLY SET STATUS = 1 ,UPDATE_TIME = current_timestamp() WHERE APPLY_ID = ?";

	@Override
	public List<CarerMemApplyMgtVO> getAllApplyData() {
		List<CarerMemApplyMgtVO> list = new ArrayList<CarerMemApplyMgtVO>();
		CarerMemApplyMgtVO carerMemApplyMgtVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_APPLY_DATA);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerMemApplyMgtVo = new CarerMemApplyMgtVO();
				carerMemApplyMgtVo.setMemID(rs.getInt("MEM_ID"));
				carerMemApplyMgtVo.setApplyID(rs.getInt("APPLY_ID"));
				carerMemApplyMgtVo.setMemAcct(rs.getString("MEM_ACCT"));
				carerMemApplyMgtVo.setMemPwd(rs.getString("MEM_PWD"));
				carerMemApplyMgtVo.setMemName(rs.getString("MEM_NAME"));
				carerMemApplyMgtVo.setMemGender(rs.getString("MEM_GENDER"));
				carerMemApplyMgtVo.setMemAge(rs.getInt("MEM_AGE"));
				carerMemApplyMgtVo.setMemPhone(rs.getString("MEM_PHONE"));
				carerMemApplyMgtVo.setStatus(rs.getString("STATUS"));

				list.add(carerMemApplyMgtVo);
			}
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
	public CarerMemApplyMgtVO getOneApplyData(Integer applyID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		CarerMemApplyMgtVO carerMemApplyMgtVo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_APPLY_DATA);
			pstmt.setInt(1, applyID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				carerMemApplyMgtVo = new CarerMemApplyMgtVO();
				carerMemApplyMgtVo.setMemID(rs.getInt("MEM_ID"));
				carerMemApplyMgtVo.setApplyID(rs.getInt("APPLY_ID"));
				carerMemApplyMgtVo.setMemAcct(rs.getString("MEM_ACCT"));
				carerMemApplyMgtVo.setMemPwd(rs.getString("MEM_PWD"));
				carerMemApplyMgtVo.setMemName(rs.getString("MEM_NAME"));
				carerMemApplyMgtVo.setMemGender(rs.getString("MEM_GENDER"));
				carerMemApplyMgtVo.setMemAge(rs.getInt("MEM_AGE"));
				carerMemApplyMgtVo.setMemPhone(rs.getString("MEM_PHONE"));
				carerMemApplyMgtVo.setMemEmail(rs.getString("MEM_EMAIL"));
				carerMemApplyMgtVo.setMemAddr(rs.getString("MEM_ADDR"));

				carerMemApplyMgtVo.setServiceType(rs.getString("SERVICE_TYPE"));
				carerMemApplyMgtVo.setServiceDistNo(rs.getString("DIST_NAME"));
				carerMemApplyMgtVo.setPriceHour(rs.getDouble("PRICE_HOUR"));
				carerMemApplyMgtVo.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				carerMemApplyMgtVo.setPriceDay(rs.getDouble("PRICE_DAY"));
				carerMemApplyMgtVo.setIntro(rs.getString("INTRO"));
				carerMemApplyMgtVo.setBankCode(rs.getString("BANK_CODE"));
				carerMemApplyMgtVo.setBankAcct(rs.getString("BANK_ACCT"));
				carerMemApplyMgtVo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				carerMemApplyMgtVo.setStatus(rs.getString("STATUS"));

				System.out.println("查詢訂單列表：\n" + carerMemApplyMgtVo);
			}
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
		return carerMemApplyMgtVo;

	}

	@Override
	public void updateFailStatus(Integer applyID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		System.out.println("================" + applyID);

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FAIL_STATUS);
			pstmt.setInt(1, applyID);
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
	public void insertCarerMem(Integer applyID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CARERMEM);
			pstmt.setInt(1, applyID);
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
	public void updateSuccessStatus(Integer applyID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SUCCESS_STATUS);
			pstmt.setInt(1, applyID);
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

}
