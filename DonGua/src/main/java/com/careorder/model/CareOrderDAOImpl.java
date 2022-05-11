package com.careorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.utils.SQLUtil;

@Repository
public class CareOrderDAOImpl implements CareOrderDAO {
	
	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ORDER_ID = "select last_insert_id()";
	private static final String SELECT_ORDER = "select * from CARE_ORDER A "
			+ "left join CARE_REQUEST B on A.REQUEST_ID = B.REQUEST_ID where CARE_ORDER_ID = ?";
	private static final String SELECT_ALL_ORDER = "select * , substring(`START_TIME`,6,11) AS `SHORT_START`, substring(`END_TIME`,6,11) AS `SHORT_END`, substring(`PATIENT_ADDR`,1,6) AS `SHORT_ADDR`, CASE `A`.`STATUS` WHEN 0 THEN '取消' WHEN 1 THEN '待執行' WHEN 2 THEN '執行中' WHEN 3 THEN '已結單' WHEN 4 THEN '訂單完成' END AS `STATUS_STR`, `B`.`MEM_ID` AS `MEMBER_ID` from CARE_ORDER A left join CARE_REQUEST B on A.REQUEST_ID = B.REQUEST_ID left join MEMBER C on A.CARER_ID = C.MEM_ID";
	private static final String SELECT_ALL_MEM_ORDER = "select * from CARE_ORDER A "
			+ "left join CARE_REQUEST B on A.REQUEST_ID = B.REQUEST_ID left join MEMBER C on A.CARER_ID = C.MEM_ID where B.MEM_ID = ?";
	private static final String SELECT_ALL_CARER_ORDER = "select * from CARE_ORDER A "
			+ "left join CARE_REQUEST B on A.REQUEST_ID = B.REQUEST_ID left join MEMBER C on A.CARER_ID = C.MEM_ID where A.CARER_ID = ?";
	private static final String INSERT_ORDER = "insert into CARE_ORDER "
			+ "(`REQUEST_ID`, `CARER_ID`, `AMOUNT`, `STATUS`) VALUES (?, ?, ?, '1')";
	private static final String UPDATE_STATUS = "update CARE_ORDER set STATUS = ?, UPDATE_TIME = current_timestamp() where CARE_ORDER_ID = ?";
	private static final String UPDATE_FEEDBACK = "update CARE_ORDER set CARE_FEEDBACK = ? where CARE_ORDER_ID = ?";

	@Override
	public CareOrderSVO select(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CareOrderSVO svo = new CareOrderSVO();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ORDER);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				svo.setCareOrderId(id);
				svo.setRequestId(rs.getInt("REQUEST_ID"));
				svo.setCarerId(rs.getInt("CARER_ID"));
				svo.setPatientName(rs.getString("PATIENT_NAME"));
				svo.setPatientGender(rs.getString("PATIENT_GENDER"));
				svo.setPatientAge(rs.getInt("PATIENT_AGE"));
				svo.setPatientAddr(rs.getString("PATIENT_ADDR"));
				svo.setStartTime(rs.getTimestamp("START_TIME"));
				svo.setEndTime(rs.getTimestamp("END_TIME"));
				svo.setServiceType(rs.getString("SERVICE_TYPE"));
				svo.setNote(rs.getString("NOTE"));
				svo.setAmount(rs.getDouble("AMOUNT"));
				svo.setCareFeedback(rs.getString("CARE_FEEDBACK"));
				svo.setStatus(rs.getString("STATUS"));
				svo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				svo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return svo;
	}

	@Override
	public List<CareOrderPVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CareOrderPVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_ORDER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CareOrderPVO pvo = new CareOrderPVO();

				pvo.setCareOrderId(rs.getInt("CARE_ORDER_ID"));
				pvo.setPatientName(rs.getString("PATIENT_NAME"));
				pvo.setStartTime(rs.getTimestamp("START_TIME"));
				pvo.setShortStartTime(rs.getString("SHORT_START"));
				pvo.setEndTime(rs.getTimestamp("END_TIME"));
				pvo.setShortEndTime(rs.getString("SHORT_END"));
				pvo.setPatientAddr(rs.getString("PATIENT_ADDR"));
				pvo.setShortPatientAddr(rs.getString("SHORT_ADDR"));
				pvo.setAmount(rs.getDouble("AMOUNT"));
				pvo.setCarerId(rs.getInt("CARER_ID"));
				pvo.setCarerName(rs.getString("MEM_NAME"));
				pvo.setStatus(rs.getString("STATUS"));
				pvo.setStatusString(rs.getString("STATUS_STR"));
				pvo.setMemId(rs.getInt("MEMBER_ID"));

				list.add(pvo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<CareOrderPVO> selectAll(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CareOrderPVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_MEM_ORDER);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CareOrderPVO pvo = new CareOrderPVO();

				pvo.setCareOrderId(rs.getInt("CARE_ORDER_ID"));
				pvo.setPatientName(rs.getString("PATIENT_NAME"));
				pvo.setStartTime(rs.getTimestamp("START_TIME"));
				pvo.setEndTime(rs.getTimestamp("END_TIME"));
				pvo.setPatientAddr(rs.getString("PATIENT_ADDR"));
				pvo.setAmount(rs.getDouble("AMOUNT"));
				pvo.setCarerId(rs.getInt("CARER_ID"));
				pvo.setCarerName(rs.getString("MEM_NAME"));
				pvo.setStatus(rs.getString("STATUS"));
				pvo.setCareFeedback(rs.getString("CARE_FEEDBACK"));

				list.add(pvo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<CareOrderPVO> selectAll2(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CareOrderPVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_CARER_ORDER);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CareOrderPVO pvo = new CareOrderPVO();

				pvo.setCareOrderId(rs.getInt("CARE_ORDER_ID"));
				pvo.setPatientName(rs.getString("PATIENT_NAME"));
				pvo.setStartTime(rs.getTimestamp("START_TIME"));
				pvo.setEndTime(rs.getTimestamp("END_TIME"));
				pvo.setAmount(rs.getDouble("AMOUNT"));
				pvo.setStatus(rs.getString("STATUS"));
				pvo.setMemId(rs.getInt("MEM_ID"));

				list.add(pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}
	
	@Override
	public int insert(CareOrderVO vo) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_ORDER);
			stmt = con.createStatement();

			pstmt.setInt(1, vo.getRequestId());
			pstmt.setInt(2, vo.getCarerId());
			pstmt.setDouble(3, vo.getAmount());

			pstmt.executeUpdate();

			rs = stmt.executeQuery(GET_ORDER_ID);

			if (rs.next()) {
				int id = rs.getInt(1);

				vo.setCareOrderId(id);


				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();

				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			SQLUtil.closeResource(con, pstmt, rs);
		}

		return 0;
	}

	@Override
	public void updateStatus(Integer id, String type) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setString(1, type);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public void updateFeedback(Integer id, String feedback) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_FEEDBACK);

			pstmt.setString(1, feedback);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}
	//後台強制關單使用
	@Override
	public void updateOrderStatus(Integer id, String status) {
		final String UPDATE_ORDER_STATUS = "UPDATE `CARE_ORDER` set `STATUS`=?, `UPDATE_TIME`= current_timestamp where `CARE_ORDER_ID` = ? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ORDER_STATUS);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}		
	}


}
