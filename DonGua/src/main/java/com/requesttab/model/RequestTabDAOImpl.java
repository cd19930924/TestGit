package com.requesttab.model;

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
public class RequestTabDAOImpl implements RequestTabDAO {

	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ALL_TAB = "select * from REQUEST_TAB natural join SERVICE_TAB where REQUEST_ID = ?";
	private static final String INSERT_TAB = "insert into REQUEST_TAB values (?, ?)";
	private static final String DELETE_ALL_TAB = "delete from REQUEST_TAB where REQUEST_ID = ?";

	@Override
	public List<RequestTabPVO> selectAll(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RequestTabPVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_TAB);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RequestTabPVO pvo = new RequestTabPVO();

				pvo.setRequestId(id);
				pvo.setServiceTabNo(rs.getString("SERVICE_TAB_NO"));
				pvo.setServiceTabName(rs.getString("SERVICE_TAB_NAME"));
				pvo.setServiceNo(rs.getString("SERVICE_NO"));

				list.add(pvo);

				System.out.println("查詢需求明細：" + pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public void insert(RequestTabVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_TAB);

			pstmt.setInt(1, vo.getRequestId());
			pstmt.setString(2, vo.getServiceTabNo());

			pstmt.executeUpdate();

			System.out.println("新增需求明細：" + vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public void deleteAll(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(DELETE_ALL_TAB);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

			System.out.println("刪除需求單" + id + "所有需求明細");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

	@Override
	public RequestTabVO findByRequestId(Integer requestId) {
		final String SELECT_ALL = "SELECT * FROM `REQUEST_TAB`";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RequestTabVO vo = new RequestTabVO();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				vo.setRequestId(rs.getInt("REQUEST_ID"));
				vo.setServiceTabNo(rs.getString("SERVICE_TAB_NO"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return vo;
	}

	@Override
	public List<RequestTabVO> selectAll() {
		final String SELECT_ALL = "SELECT * FROM `REQUEST_TAB`";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RequestTabVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RequestTabVO vo = new RequestTabVO();

				vo.setRequestId(rs.getInt("REQUEST_ID"));
				vo.setServiceTabNo(rs.getString("SERVICE_TAB_NO"));
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}
}
