package com.carermemapply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarerMemApplyDAOImpl implements CarerMemApplyDAO {

	// DataSource
//	private static DataSource ds = null;
	
	@Autowired
	private DataSource ds;
	
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/group6");
//		} catch (NamingException ne) {
//			ne.printStackTrace();
//		}
//	}

	// 新增申請者的照護資料
	private static final String INSERT_DATA_STMT = "INSERT INTO CARER_MEMBER_APPLY (MEM_ID, SERVICE_DIST_NO, BANK_CODE, BANK_ACCT, SERVICE_TYPE, INTRO, PRICE_HOUR, PRICE_HALFDAY, PRICE_DAY) VALUES (?,?,?,?,?,?,?,?,?)";
	// 查詢
	private static final String GET_ALL_STMT = "SELECT APPLY_ID, MEM_ID, SERVICE_DIST_NO, BANK_CODE, BANK_ACCT, SERVICE_TYPE, INTRO, PRICE_HOUR, PRICE_HALFDAY, PRICE_DAY, STATUS, CREATE_TIME, UPDATE_TIME FROM CARER_MEMBER_APPLY";
	
	
	
	@Override
	public void insertApplyData(CarerMemApplyVO carerMemApplyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_DATA_STMT);

			pstmt.setInt(1, carerMemApplyVO.getMemID());
			pstmt.setString(2, carerMemApplyVO.getServiceDistNo());
			pstmt.setString(3, carerMemApplyVO.getBankCode());
			pstmt.setString(4, carerMemApplyVO.getBankAcct());
			pstmt.setString(5, carerMemApplyVO.getServiceType());
			pstmt.setString(6, carerMemApplyVO.getIntro());
			pstmt.setDouble(7, carerMemApplyVO.getPriceHour());
			pstmt.setDouble(8, carerMemApplyVO.getPriceHalfday());
			pstmt.setDouble(9, carerMemApplyVO.getPriceDay());

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
	public List<CarerMemApplyVO> getAll() {
		List<CarerMemApplyVO> list = new ArrayList<CarerMemApplyVO>();
		CarerMemApplyVO carerMemApplyVo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				carerMemApplyVo = new CarerMemApplyVO();
				carerMemApplyVo.setApplyID(rs.getInt("APPLY_ID"));
				carerMemApplyVo.setMemID(rs.getInt("MEM_ID"));
				carerMemApplyVo.setServiceDistNo(rs.getString("SERVICE_DIST_NO"));
				carerMemApplyVo.setBankCode(rs.getString("BANK_CODE"));
				carerMemApplyVo.setBankAcct(rs.getString("BANK_ACCT"));
				carerMemApplyVo.setServiceDistNo(rs.getString("SERVICE_TYPE"));
				carerMemApplyVo.setIntro(rs.getString("INTRO"));
				carerMemApplyVo.setPriceHour(rs.getDouble("PRICE_HOUR"));
				carerMemApplyVo.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				carerMemApplyVo.setPriceDay(rs.getDouble("PRICE_DAY"));
				carerMemApplyVo.setStatus(rs.getString("STATUS"));
				carerMemApplyVo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				carerMemApplyVo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				
				list.add(carerMemApplyVo);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
