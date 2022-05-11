package com.member.model.dao.impl;

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

import com.member.model.dao.BackendMemberDao;
import com.member.model.vo.MemberDataManageVO;

public class BackendMemberDaoImpl implements BackendMemberDao{
	private final String GET_ALL_MEMBER = "SELECT * FROM MEMBER LEFT JOIN DISTRICT on MEMBER.DIST_NO = DISTRICT.DIST_NO;";
	private final String UPDATE_MEMBER_STATUS = "UPDATE MEMBER SET MEM_STATUS = ? WHERE MEM_ID=?";

	
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
	public List<MemberDataManageVO> getAllMember() throws SQLException {
		List<MemberDataManageVO> memberList = new ArrayList<MemberDataManageVO>(); 
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_MEMBER);) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDataManageVO member = new MemberDataManageVO();
				member.setMemId(rs.getLong("MEM_ID"));
				member.setMemAcct(rs.getString("MEM_ACCT"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemEmail(rs.getString("MEM_EMAIL"));
				if("0".equals(rs.getString("MEM_GENDER"))) {
					member.setMemGender("男");
				}else {
					member.setMemGender("女");
				}
				member.setMemBirth(rs.getString("MEM_BIRTH"));
				member.setMemPhone(rs.getString("MEM_PHONE"));
				member.setDistName(rs.getString("DIST_NAME"));
				member.setMemAddr(rs.getString("MEM_ADDR"));
				member.setMemAge(rs.getInt("MEM_AGE"));
				member.setMemStatus(rs.getString("MEM_STATUS"));
				memberList.add(member);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return memberList;
	}
	public int updateMemberStatus(String status,long id) {
		int result = 0;
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE_MEMBER_STATUS);) {
			pstmt.setString(1, status);
			pstmt.setLong(2, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
