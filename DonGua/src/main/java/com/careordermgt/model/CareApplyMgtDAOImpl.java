package com.careordermgt.model;

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

public class CareApplyMgtDAOImpl implements CareApplyMgtDAO {

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

	private static final String GET_ALL_STMT = "SELECT * FROM CARE_APPLY CA LEFT JOIN CARE_REQUEST CE ON CA.REQUEST_ID = CE.REQUEST_ID LEFT JOIN MEMBER CM ON CA.CARER_ID = CM.MEM_ID WHERE CA.CARER_ID = ?";
	private static final String GET_ONE_REQ_STMT = "SELECT * FROM CARE_APPLY CA LEFT JOIN CARE_REQUEST CE ON CA.REQUEST_ID = CE.REQUEST_ID WHERE CA.REQUEST_ID = ?";
	
	@Override
	public List<CareApplyMgtVO> getAll(Integer carerID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<CareApplyMgtVO> list = new ArrayList<CareApplyMgtVO>();
		CareApplyMgtVO careApplyMgtVo = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, carerID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				careApplyMgtVo = new CareApplyMgtVO();
				careApplyMgtVo.setRequestId(rs.getInt("REQUEST_ID"));
				careApplyMgtVo.setCarerId(rs.getInt("CARER_ID"));
				careApplyMgtVo.setStatus(rs.getString("STATUS"));
				careApplyMgtVo.setApplyTime(rs.getTimestamp("APPLY_TIME"));

				careApplyMgtVo.setMemId(rs.getInt("MEM_ID"));
				careApplyMgtVo.setPatientName(rs.getString("PATIENT_NAME"));
				careApplyMgtVo.setPatientGender(rs.getString("PATIENT_GENDER"));
				careApplyMgtVo.setPatientAge(rs.getInt("PATIENT_AGE"));
				careApplyMgtVo.setPatientAddr(rs.getString("PATIENT_ADDR"));
				careApplyMgtVo.setStartTime(rs.getTimestamp("START_TIME"));
				careApplyMgtVo.setEndTime(rs.getTimestamp("END_TIME"));
				careApplyMgtVo.setServiceType(rs.getString("SERVICE_TYPE"));
				careApplyMgtVo.setNote(rs.getString("NOTE"));
				careApplyMgtVo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				careApplyMgtVo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));

				list.add(careApplyMgtVo);
				
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
	public CareApplyMgtVO getByReqId(Integer requestID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		CareApplyMgtVO careApplyMgtVo = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_REQ_STMT);
			pstmt.setInt(1, requestID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				careApplyMgtVo = new CareApplyMgtVO();
				careApplyMgtVo.setRequestId(rs.getInt("REQUEST_ID"));
				careApplyMgtVo.setCarerId(rs.getInt("CARER_ID"));
				careApplyMgtVo.setStatus(rs.getString("STATUS"));
				careApplyMgtVo.setApplyTime(rs.getTimestamp("APPLY_TIME"));

				careApplyMgtVo.setMemId(rs.getInt("MEM_ID"));
				careApplyMgtVo.setPatientName(rs.getString("PATIENT_NAME"));
				careApplyMgtVo.setPatientGender(rs.getString("PATIENT_GENDER"));
				careApplyMgtVo.setPatientAge(rs.getInt("PATIENT_AGE"));
				careApplyMgtVo.setPatientAddr(rs.getString("PATIENT_ADDR"));
				careApplyMgtVo.setStartTime(rs.getTimestamp("START_TIME"));
				careApplyMgtVo.setEndTime(rs.getTimestamp("END_TIME"));
				careApplyMgtVo.setServiceType(rs.getString("SERVICE_TYPE"));
				careApplyMgtVo.setNote(rs.getString("NOTE"));
				careApplyMgtVo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				careApplyMgtVo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
				
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
		return careApplyMgtVo;
	}

}
