//package com.careorder.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.utils.SQLUtil;
//
//public class CareOrderJNDI implements CareOrderDAO {
//	private static DataSource ds = null;
//
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DaliyWarm");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String GET_ORDER_ID = "select * from CARE_ORDER order by CARE_ORDER_ID desc limit 1";
//	private static final String SELECT_ORDER = "select * from CARE_ORDER A "
//			+ "left join CARE_REQUEST B on A.REQUEST_ID = B.REQUEST_ID where CARE_ORDER_ID = ?";
//	private static final String SELECT_ALL_ORDER = "select * from CARE_ORDER A "
//			+ "left join CARE_REQUEST B on A.REQUEST_ID = B.REQUEST_ID left join MEMBER C on A.CARER_ID = C.MEM_ID where B.MEM_ID = ?";
//	private static final String INSERT_ORDER = "insert into CARE_ORDER "
//			+ "(`REQUEST_ID`, `CARER_ID`, `AMOUNT`, `STATUS`) VALUES (?, ?, ?, '3')";
//
//	@Override
//	public CareOrderSVO select(Integer id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		CareOrderSVO svo = new CareOrderSVO();
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(SELECT_ORDER);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				svo.setCareOrderId(id);
//				svo.setRequestId(rs.getInt("REQUEST_ID"));
//				svo.setCarerId(rs.getInt("CARER_ID"));
//				svo.setPatientName(rs.getString("PATIENT_NAME"));
//				svo.setPatientGender(rs.getString("PATIENT_GENDER"));
//				svo.setPatientAge(rs.getInt("PATIENT_AGE"));
//				svo.setPatientAddr(rs.getString("PATIENT_ADDR"));
//				svo.setStartTime(rs.getTimestamp("START_TIME"));
//				svo.setEndTime(rs.getTimestamp("END_TIME"));
//				svo.setServiceType(rs.getString("SERVICE_TYPE"));
//				svo.setNote(rs.getString("NOTE"));
//				svo.setAmount(rs.getDouble("AMOUNT"));
//				svo.setCareFeedback(rs.getString("CARE_FEEDBACK"));
//				svo.setStatus(rs.getString("STATUS"));
//				svo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//				svo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			SQLUtil.closeResource(con, pstmt, rs);
//		}
//
//		return svo;
//	}
//
//	@Override
//	public List<CareOrderPVO> selectAll(Integer id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<CareOrderPVO> list = new ArrayList<>();
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(SELECT_ALL_ORDER);
//			pstmt.setInt(1, id);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				CareOrderPVO pvo = new CareOrderPVO();
//
//				pvo.setCareOrderId(rs.getInt("CARE_ORDER_ID"));
//				pvo.setPatientName(rs.getString("PATIENT_NAME"));
//				pvo.setStartTime(rs.getTimestamp("START_TIME"));
//				pvo.setEndTime(rs.getTimestamp("END_TIME"));
//				pvo.setAmount(rs.getDouble("AMOUNT"));
//				pvo.setCarerId(rs.getInt("CARER_ID"));
//				pvo.setCarerName(rs.getString("MEM_NAME"));
//				pvo.setStatus(rs.getString("STATUS"));
//
//				list.add(pvo);
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			SQLUtil.closeResource(con, pstmt, rs);
//		}
//
//		return list;
//	}
//
//	@Override
//	public int insert(CareOrderVO vo) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Statement stmt = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_ORDER);
//			stmt = con.createStatement();
//
//			pstmt.setInt(1, vo.getRequestId());
//			pstmt.setInt(2, vo.getCarerId());
//			pstmt.setDouble(3, vo.getAmount());
//
//			pstmt.executeUpdate();
//
//
//			rs = stmt.executeQuery(GET_ORDER_ID);
//
//			rs.next();
//
//			return rs.getInt("CARE_ORDER_ID");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (stmt != null) {
//				try {
//					stmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//
//			SQLUtil.closeResource(con, pstmt, rs);
//		}
//
//		return 0;
//	}
//
//	@Override
//	public void updateFeedback(Integer id) {
//
//	}
//
//}
