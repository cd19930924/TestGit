package com.skill.model;

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

import org.springframework.stereotype.Repository;

import com.careskills.model.CareSkillsVO;

@Repository
public class SkillDAOImpl implements SkillDAO {

	// DataSource
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/group6");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	// 查詢技能 申請照護員checkbox
	private static final String GET_ALL_STMT = "SELECT SKILL_NO, SKILL_NAME, SKILL_TYPE FROM SKILL";

	// 查詢單一照護員技能資料
	private static final String GET_ONE_CARER_SKILLS = "SELECT * FROM CARE_SKILLS AS CS LEFT JOIN SKILL AS S ON CS.SKILL_NO = S.SKILL_NO WHERE CS.CARER_ID = ?";

	@Override
	public List<SkillVO> getAll() {
		List<SkillVO> list = new ArrayList<SkillVO>();
		SkillVO skillVo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				skillVo = new SkillVO();
				skillVo.setSkillNo(rs.getString("SKILL_NO"));
				skillVo.setSkillName(rs.getString("SKILL_NAME"));
				skillVo.setSkillType(rs.getString("SKILL_TYPE"));

				list.add(skillVo);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<SkillVO> getOneCarerSkills(Integer carerID) {
		List<SkillVO> list = new ArrayList<SkillVO>();
		SkillVO skillVo = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CARER_SKILLS);
			pstmt.setInt(1, carerID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				skillVo = new SkillVO();
				skillVo.setCarerID(rs.getInt("CARER_ID"));
				skillVo.setSkillNo(rs.getString("SKILL_NO"));
				skillVo.setSkillName(rs.getString("SKILL_NAME"));
				skillVo.setSkillType(rs.getString("SKILL_TYPE"));

				list.add(skillVo);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
