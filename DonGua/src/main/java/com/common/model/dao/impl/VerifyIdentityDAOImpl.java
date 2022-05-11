package com.common.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.common.model.dao.VerifyIdentityDAO;
import com.utils.SQLUtils;

public class VerifyIdentityDAOImpl implements VerifyIdentityDAO {
	private static DataSource ds = null ;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/group6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Boolean isCarerExist(Long carerId) {
		try {
			Class.forName(SQLUtils.DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "SELECT * FROM CARER WHERE CARER_ID = ?";
		boolean ok = false;
		try {
			Connection conn = ds.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, carerId);
			
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
