package com.carer.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.carer.model.dao.CarerDAO;
import com.utils.SQLUtils;

@Repository
public class CarerDAOImpl implements CarerDAO {

	@Override
	public long findCarerByAcctAndPwd(String account, String password) {
		try {
			Class.forName(SQLUtils.DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "SELECT * FROM CARER WHERE CARER_ACCT = ? and CARER_PWD = ? and CARER_STATUS in (1,2)" ;
		long carerId = 0;
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					carerId = rs.getLong("CARER_ID");
					return carerId;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean isCarerExist(String account) {
		try {
			Class.forName(SQLUtils.DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "SELECT * FROM CARER WHERE CARER_ACCT = ? and CARER_STATUS in (1,2)" ;
		boolean ok = false;
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, account);
			try (ResultSet rs = pstmt.executeQuery()) {
				ok = rs.next();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}
}
