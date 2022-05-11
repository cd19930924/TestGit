package com.empauth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.SQLUtil;

public class EmpAuthDAOImpl implements EmpAuthDAO {

	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_AUTH_NO = "select * from EMP_AUTH where EMP_ID = ?";
	private static final String INSERT = "insert into EMP_AUTH values (?, 'A02')";
	private static final String UPDATE = "update EMP_AUTH set EMP_AUTH_NO = ? where EMP_ID = ?";

	@Override
	public String selectAuthNo(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String no = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_AUTH_NO);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				no = rs.getString("EMP_AUTH_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return no;
	}

	@Override
	public String init(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			System.out.println("將新員工" + id + "移至預設群組A02");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}

		return "A02";
	}

	@Override
	public void update(EmpAuthVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getEmpAuthNo());
			pstmt.setInt(2, vo.getEmpId());
			pstmt.executeUpdate();

			System.out.println("更新員工權限群組：" + vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

}
