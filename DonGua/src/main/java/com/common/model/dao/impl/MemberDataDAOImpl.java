package com.common.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.common.model.dao.MemberDataDAO;
import com.member.model.vo.MemberVO;
import com.utils.SQLUtils;

public class MemberDataDAOImpl implements MemberDataDAO {

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
	public List<MemberVO> getOneMember(Long memberId) {
		try {
			Class.forName(SQLUtils.DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? and MEM_STATUS in (1,2)";
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			Connection conn = ds.getConnection(); 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					MemberVO member = new MemberVO();
					member.setMemId(memberId);
					member.setMemAcct(rs.getString("MEM_ACCT"));
					member.setMemPwd(rs.getNString("MEM_PWD"));
					member.setMemName(rs.getNString("MEM_NAME"));
					member.setMemGender(rs.getString("MEM_GENDER"));
					member.setMemAge(rs.getInt("MEM_AGE"));
					member.setMemPhone(rs.getString("MEM_PHONE"));
					member.setMemEmail(rs.getString("MEM_EMAIL"));
					member.setMemAddr(rs.getString("MEM_ADDR"));
					memberList.add(member);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public List<MemberVO> getSeveralMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getAllMember() {
		try {
			Class.forName(SQLUtils.DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "SELECT * FROM MEMBER WHERE MEM_STATUS in (1,2) ";
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		try {
			Connection conn = ds.getConnection(); 
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					MemberVO member = new MemberVO();
					member.setMemId(rs.getLong("MEM_ID"));
					member.setMemAcct(rs.getString("MEM_ACCT"));
					member.setMemPwd(rs.getNString("MEM_PWD"));
					member.setMemName(rs.getNString("MEM_NAME"));
					member.setMemGender(rs.getString("MEM_GENDER"));
					member.setMemAge(rs.getInt("MEM_AGE"));
					member.setMemPhone(rs.getString("MEM_PHONE"));
					member.setMemEmail(rs.getString("MEM_EMAIL"));
					member.setMemAddr(rs.getString("MEM_ADDR"));
					memberList.add(member);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberList;
	}

}
