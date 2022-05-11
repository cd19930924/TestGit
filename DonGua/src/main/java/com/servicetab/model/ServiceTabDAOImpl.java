package com.servicetab.model;

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
public class ServiceTabDAOImpl implements ServiceTabDAO {

	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_ALL_SERVICE_TAB = "select * from SERVICE_TAB";

	@Override
	public List<ServiceTabVO> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ServiceTabVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL_SERVICE_TAB);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ServiceTabVO vo = new ServiceTabVO();

				vo.setServiceTabNo(rs.getString("SERVICE_TAB_NO"));
				vo.setServiceNo(rs.getString("SERVICE_NO"));
				vo.setServiceTabName(rs.getString("SERVICE_TAB_NAME"));

				list.add(vo);

				System.out.println("查詢服務列表" + vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

}
