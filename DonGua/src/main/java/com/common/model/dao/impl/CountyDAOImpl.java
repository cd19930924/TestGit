package com.common.model.dao.impl;

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

import com.common.model.dao.CountyDAO;
import com.common.model.vo.CountyVO;
import com.utils.ConnectionInstance;

public class CountyDAOImpl implements CountyDAO {
	private static final String GET_COUNTY = "SELECT * FROM COUNTY;";
	
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
	public  List<CountyVO> getCountyList() {
		
		List<CountyVO> countyList = new ArrayList<>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_COUNTY);
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					CountyVO county = new CountyVO(); 
					county.setCtyNo(rs.getString("CTY_NO"));
					county.setCtyName(rs.getString("CTY_NAME"));
					countyList.add(county);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return countyList;
	}
}
