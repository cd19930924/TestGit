package com.membermailbox.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.utils.SQLUtils;

@Repository
public class MemberMailBoxJDBCDAO implements MemberMailBoxDAO {

	static {
		try {
			Class.forName(SQLUtils.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_MAIL = "SELECT MAIL_ID, SEND_MEM_ID, RECEIVE_MEM_ID, MEM_ACCT, MEM_NAME, STATUS, MSG_CONTENT, MSG_TITLE, SEND_TIME FROM MEMBER_MAILBOX MAIL LEFT JOIN MEMBER M ON MAIL.SEND_MEM_ID = M.MEM_ID WHERE RECEIVE_MEM_ID = ? ORDER BY SEND_TIME DESC";
	private static final String INSERT = "INSERT INTO `MEMBER_MAILBOX` (`SEND_MEM_ID`, `RECEIVE_MEM_ID`, `STATUS`, `MSG_CONTENT`, `MSG_TITLE`) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE `MEMBER_MAILBOX` SET `STATUS` = ? WHERE `MAIL_ID` = ?";
	
	@Override
	public void insert(MemberMailBoxVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER, SQLUtils.PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, vo.getSendMemId());
			pstmt.setInt(2, vo.getReceiveMemId());
			pstmt.setString(3, vo.getStatus());
			pstmt.setString(4, vo.getMsgContent());
			pstmt.setString(5, vo.getMsgTitle());

			System.out.println(vo.getSendMemId());
			
			
			pstmt.executeUpdate();

			System.out.println("新增信件：" + vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	}

	@Override
	public void update(Integer mailId, String status) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER, SQLUtils.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, status);
			pstmt.setInt(2, mailId);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

	@Override
	public List<MemberMailBoxVO> getMailByReceiveMemId(Integer receiveMemId) {
		// TODO Auto-generated method stub
		List<MemberMailBoxVO> list = new ArrayList<MemberMailBoxVO>();
		MemberMailBoxVO memberMailBoxVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = DriverManager.getConnection(SQLUtils.URL, SQLUtils.USER, SQLUtils.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_MAIL);
			pstmt.setInt(1, receiveMemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memberMailBoxVO = new MemberMailBoxVO();
				memberMailBoxVO.setMailId(rs.getInt("MAIL_ID"));
				memberMailBoxVO.setSendMemId(rs.getInt("SEND_MEM_ID"));
				memberMailBoxVO.setMemName(rs.getString("MEM_NAME"));
				memberMailBoxVO.setReceiveMemId(rs.getInt("RECEIVE_MEM_ID"));
				memberMailBoxVO.setStatus(rs.getString("STATUS"));
				memberMailBoxVO.setMsgContent(rs.getString("MSG_CONTENT"));
				memberMailBoxVO.setMsgTitle(rs.getString("MSG_TITLE"));
				memberMailBoxVO.setSendTime(rs.getTimestamp("SEND_TIME"));
				list.add(memberMailBoxVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
	
	
	public static void main(String[] args) {
		MemberMailBoxJDBCDAO dao = new MemberMailBoxJDBCDAO();
		
		// 新增
//		MemberMailBoxVO mailBoxVO = new MemberMailBoxVO();
//		mailBoxVO.setSendMemId(1);
//		mailBoxVO.setReceiveMemId(2);
//		mailBoxVO.setStatus("0");
//		mailBoxVO.setMsgContent("MAIN方法測試中");
//		mailBoxVO.setMsgTitle("MAIN方法測試中");
//		dao.insert(mailBoxVO);
		
		// 修改
//		dao.update(6, "1");
		
		//
	}
}


