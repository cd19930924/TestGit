package com.member.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.member.model.dao.MemberDao;
import com.member.model.vo.MemberDataSettingVO;
import com.member.model.vo.MemberRegistVO;
import com.member.model.vo.MemberVO;
import com.utils.SQLUtils;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Override
	public boolean isMemberAcctExist(String memberAccount) throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		String sql = "SELECT * FROM MEMBER WHERE MEM_ACCT = ?";
		boolean ok = false;
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {  /*使用try with resource 不須特別close*/
			pstmt.setString(1, memberAccount);

			try (ResultSet rs = pstmt.executeQuery()) {/**/
				ok = rs.next();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public long isMemberExist(String memberAccount,String password) throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		String sql = "SELECT * FROM MEMBER WHERE MEM_ACCT = ? and MEM_PWD = ?" ;
		long memberId = 0;
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberAccount);
			pstmt.setString(2, password);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					if("2".equals(rs.getString("MEM_STATUS"))) {//判斷是否停權
						memberId=-1;
					}else if("0".equals(rs.getString("MEM_STATUS"))){
						memberId=-2;
					}else {
						memberId = rs.getLong("MEM_ID");
					}
					return memberId;
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
	public boolean isMemberIdExist(long memberId) throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ?";
		boolean ok = false;
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {/*使用try with resource 不須特別close*/
			pstmt.setLong(1, memberId);

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

	@Override
	public boolean activeMemberStatus(String memberAccount) throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		boolean ok = false;
		String sql = "UPDATE MEMBER SET MEM_STATUS = 1,UPDATE_TIME =? WHERE MEM_ACCT = ? and MEM_STATUS = 0";
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, Date.valueOf(LocalDate.now()));
			pstmt.setString(2, memberAccount);
			int result = pstmt.executeUpdate();
			ok = result == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public int insertMember(MemberRegistVO registVO) throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		int result = 0;
		String sql = "INSERT INTO MEMBER(MEM_ACCT, MEM_PWD, MEM_NAME, MEM_PHONE, DIST_NO, MEM_ADDR, MEM_GENDER, MEM_EMAIL, MEM_BIRTH, MEM_AGE,MEM_STATUS,CANCEL_COUNT)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,registVO.getMemAcct());
			pstmt.setString(2,registVO.getMemPwd());
			pstmt.setString(3,registVO.getMemName());
			pstmt.setString(4,registVO.getMemPhone());
			pstmt.setString(5,registVO.getDistNo());
			pstmt.setString(6,registVO.getMemAddr());
			pstmt.setString(7,registVO.getMemGender());
			pstmt.setString(8,registVO.getMemEmail());
			pstmt.setDate(9,new java.sql.Date(registVO.getMemBirth().getTime()));
			pstmt.setInt(10,registVO.getMemAge());
			pstmt.setString(11, "0");
			pstmt.setInt(12, 0);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public MemberVO getMemberData(long memberId) throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		MemberVO member = new MemberVO();
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? and MEM_STATUS in (1,3)";
		
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, memberId);
			try (ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					member.setMemAcct(rs.getString("MEM_ACCT"));
					member.setMemPwd(rs.getNString("MEM_PWD"));
					member.setMemName(rs.getNString("MEM_NAME"));
					member.setMemGender(rs.getString("MEM_GENDER"));
					member.setDistNo(rs.getString("DIST_NO"));
					member.setMemBirth(rs.getDate("MEM_BIRTH"));
					member.setMemAge(rs.getInt("MEM_AGE"));
					member.setMemPhone(rs.getString("MEM_PHONE"));
					member.setMemEmail(rs.getString("MEM_EMAIL"));
					member.setMemAddr(rs.getString("MEM_ADDR"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	@Override
	public MemberDataSettingVO getMemberDataByToken(long memberId) throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		MemberDataSettingVO member = new MemberDataSettingVO();
		String sql = "SELECT * FROM MEMBER LEFT JOIN (SELECT d.DIST_NO,d.CTY_NO FROM DISTRICT as d LEFT JOIN COUNTY as c ON c.CTY_NO = d.CTY_NO)as dic ON MEMBER.DIST_NO = dic.DIST_NO WHERE MEMBER.MEM_ID = ?";
		
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, memberId);
			try (ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					member.setMemAcct(rs.getString("MEM_ACCT"));
					member.setMemPwd(rs.getNString("MEM_PWD"));
					member.setMemName(rs.getNString("MEM_NAME"));
					member.setMemGender(rs.getString("MEM_GENDER"));
					member.setDistNo(rs.getString("DIST_NO"));
					member.setCountyNo((rs.getString("CTY_NO")));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					member.setMemBirth(sdf.format(rs.getDate("MEM_BIRTH")));
					member.setMemAge(rs.getInt("MEM_AGE"));
					member.setMemPhone(rs.getString("MEM_PHONE"));
					member.setMemEmail(rs.getString("MEM_EMAIL"));
					member.setMemAddr(rs.getString("MEM_ADDR"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	@Override
	public List<MemberVO> getAllMember() throws ClassNotFoundException {
		Class.forName(SQLUtils.DRIVER);
		String sql = "SELECT * FROM MEMBER WHERE MEM_STATUS in (1,2)";
		List<MemberVO> memberList = null;
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					MemberVO member = new MemberVO();
					member.setMemAcct(rs.getString("MEM_ACCT"));
					member.setMemPwd(rs.getNString("MEM_PWD"));
					member.setMemName(rs.getNString("MEM_NAME"));
					member.setMemGender(rs.getString("MEM_GENDER"));
					member.setDistNo(rs.getString("DIST_NO"));
					member.setMemBirth(rs.getDate("MEM_BIRTH"));
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
	public int updateMember(MemberDataSettingVO memberData) {
		int result = 0;
		String sql = "UPDATE MEMBER SET MEM_PWD=?, MEM_NAME=?, MEM_PHONE=?, DIST_NO=?, MEM_ADDR=?, MEM_GENDER=?, MEM_EMAIL=?, MEM_BIRTH=?, MEM_AGE=? WHERE MEM_ACCT=?";
		try (Connection conn = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER,SQLUtils.PASSWORD); 
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			pstmt.setString(1,memberData.getMemPwd());
			pstmt.setString(2,memberData.getMemName());
			pstmt.setString(3,memberData.getMemPhone());
			pstmt.setString(4,memberData.getDistNo());
			pstmt.setString(5,memberData.getMemAddr());
			pstmt.setString(6,memberData.getMemGender());
			pstmt.setString(7,memberData.getMemEmail());
			pstmt.setDate(8,new java.sql.Date(memberData.getMemBirthDate().getTime()));
			pstmt.setInt(9,memberData.getMemAge());
			pstmt.setString(10,memberData.getMemAcct());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
		
	
}
