package com.service.model;

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
public class ServiceDAOImpl implements ServiceDAO {

	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ALL_SERVICE = "select * from SERVICE";

	@Override
	public List<ServiceVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ServiceVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_SERVICE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ServiceVO vo = new ServiceVO();

				vo.setServiceNo(rs.getString("SERVICE_NO"));
				vo.setServiceName(rs.getString("SERVICE_NAME"));

				list.add(vo);

				System.out.println("查詢服務種類列表：" + vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

}
