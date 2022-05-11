package com.careapply.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.utils.SQLUtil;

@Repository
public class CareApplyDAOImpl implements CareApplyDAO {

	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ALL_APPLY = "select * from CARE_APPLY "
			+ "natural join CARER A left join MEMBER B on A.CARER_ID = B.MEM_ID where REQUEST_ID = ? and STATUS = 1";
	private static final String UPDATE_STATUS = "update CARE_APPLY set STATUS = ? where REQUEST_ID = ? and CARER_ID = ?";
	private static final String UPDATE_ALL_STATUS = "update CARE_APPLY set STATUS = ? where REQUEST_ID = ?";

	@Override
	public List<CareApplyPVO> selectAll(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CareApplyPVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_APPLY);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CareApplyPVO pvo = new CareApplyPVO();

				pvo.setCarerId(rs.getInt("CARER_ID"));
				pvo.setMemName(rs.getString("MEM_NAME"));
				pvo.setMemGender(rs.getString("MEM_GENDER"));
				pvo.setMemPhone(rs.getString("MEM_PHONE"));
				pvo.setMemEmail(rs.getString("MEM_EMAIL"));
				pvo.setPriceHour(rs.getDouble("PRICE_HOUR"));
				pvo.setPriceHalfday(rs.getDouble("PRICE_HALFDAY"));
				pvo.setPriceDay(rs.getDouble("PRICE_DAY"));

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
	public void updateStatus(Integer requestId, Integer carerId, String type) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS);
			pstmt.setString(1, type);
			pstmt.setInt(2, requestId);
			pstmt.setInt(3, carerId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}

	}

	@Override
	public void updateAllStatus(Integer id, String type) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ALL_STATUS);
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
	public void insert(CareApplyVO careApplyVO) {
		final String INSERT = "INSERT INTO `CARE_APPLY` (`REQUEST_ID`,`CARER_ID`,`STATUS`,`APPLY_TIME`) VALUES (?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, careApplyVO.getRequestId());
			pstmt.setInt(2, careApplyVO.getCarerId());
			pstmt.setString(3, careApplyVO.getStatus());
			pstmt.setTimestamp(4, careApplyVO.getApplyTime());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}
	@Override
	 public void update(Integer requestId, Integer carerId) {
	  final String UPDATE = "UPDATE CARE_APPLY SET STATUS = '1' WHERE `CARE_APPLY`.`REQUEST_ID` = ? AND `CARE_APPLY`.`CARER_ID` = ? ";
	  Connection con = null;
	  PreparedStatement pstmt = null;

	  try {
	   con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
	   pstmt = con.prepareStatement(UPDATE);
	   pstmt.setInt(1, requestId);
	   pstmt.setInt(2, carerId);

	   pstmt.executeUpdate();

	  } catch (SQLException e) {
	   e.printStackTrace();
	  } finally {
	   SQLUtil.closeResource(con, pstmt, null);
	  }
	 }
}
