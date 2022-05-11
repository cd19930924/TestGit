package com.common.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.common.model.dao.DistrictDAO;
import com.common.model.vo.CountyVO;
import com.common.model.vo.DistrictVO;
import com.utils.ConnectionInstance;

public class DistrictDAOImpl implements DistrictDAO{
	
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
	public List<DistrictVO> getDistrictList(String countyNo) {
		final String GET_DISTRICT = "SELECT * FROM DISTRICT WHERE CTY_NO = ?;";
		List<DistrictVO> districtList = new ArrayList<>();
		try {
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_DISTRICT);
			pstmt.setString(1, countyNo);
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					DistrictVO district = new DistrictVO(); 
					district.setDistNO(rs.getString("DIST_NO"));
					district.setDistName(rs.getString("DIST_NAME"));
					districtList.add(district);
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
		return districtList;
	}
	
	@Override
	public CountyVO getParentCountyAndDistrictName(String distNo) {
		final String GET_ParentCountyAndDistrictName = "SELECT d.DIST_NO,d.DIST_NAME,d.CTY_NO,c.CTY_NAME FROM DISTRICT as d LEFT JOIN COUNTY as c on d.CTY_NO = c.CTY_NO WHERE d.DIST_NO = ?;";

		CountyVO county = new CountyVO(); 
		try {
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(GET_ParentCountyAndDistrictName);
			pstmt.setString(1, distNo);
			
			Map<String, String> dist = new HashMap<String, String>();
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					county.setCtyNo(rs.getString("CTY_NO"));
					county.setCtyName(rs.getString("CTY_NAME"));
					dist.put("distNo",rs.getString("DIST_NO"));
					dist.put("distName",rs.getString("DIST_Name"));
					county.setDistData(dist);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return county;
	}
	
}
