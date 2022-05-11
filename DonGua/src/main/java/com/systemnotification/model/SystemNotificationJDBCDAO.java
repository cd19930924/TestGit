package com.systemnotification.model;

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
public class SystemNotificationJDBCDAO implements SystemNotificationDAO {

	static {
		try {
			Class.forName(SQLUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT = "insert SYSTEM_NOTIFICATION (MEM_ID, NOT_TYPE_NO, ORDER_ID) values (?, ?, ?)";
	private static final String SELECT_ALL = "select * from SYSTEM_NOTIFICATION natural join NOTIFICATION_TYPE where MEM_ID = ? order by NOT_TIME DESC";

	@Override
	public List<SystemNotificationPVO> selectAll(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SystemNotificationPVO> list = new ArrayList<>();

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(SELECT_ALL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			String no;
			String link = "";

			while (rs.next()) {
				SystemNotificationPVO pvo = new SystemNotificationPVO();

				pvo.setNotNo(rs.getInt("NOT_NO"));
				pvo.setMemId(id);
				pvo.setOrderId(rs.getInt("ORDER_ID"));
				pvo.setNotTypeNo(rs.getString("NOT_TYPE_NO"));
				pvo.setNotTime(rs.getTimestamp("NOT_TIME"));
				pvo.setNotTypeName(rs.getString("NOT_TYPE_NAME"));
				pvo.setNotTextcontent(rs.getString("NOT_TEXTCONTENT"));

				no = pvo.getNotTypeNo();

				System.out.println(no);
				
				if (no.equals("A01") || no.equals("A03")) { // A01 A03 會員角度瀏覽訂單詳細狀態
					link = "front-end/careOrder/careOrder?action=view_order&orderId=" + pvo.getOrderId(); // OK
				} else if (no.equals("A11")) { // A11為 照護員角度瀏覽已成立的單一訂單頁面
					link = "front-end/careOrder/careOrder?action=view_order&orderId=" + pvo.getOrderId(); // OK
				}else if (no.equals("B11")) { // B11為 照護員角度瀏覽單一需求單頁面
					link = "/request/requestsearch?action=displayARequest&requestId=" + pvo.getOrderId(); // OK
				}else if (no.equals("B01")) { // B01為 會員角度瀏覽需求單審查頁面
					link = "front-end/careRequest/requestMgt.jsp"; // OK
				}else if (no.equals("E11")) { // E11為 申請成為照護員審核成功 
					link = "front-end/Carer.jsp"; // OK
				}else if (no.equals("E12")) { // E12為 申請成為照護員審核失敗
					link = "front-end/afterlogin.jsp"; // OK
				}else if (no.equals("C02") || no.equals("C01")) { // C02、C01為 會員角度 瀏覽單一派車訂單
					link = "front-end/driveorder/driveOrderInfo.jsp?driveOrderId=" + pvo.getOrderId(); // OK
				}else if (no.equals("D01")) { // D1為 會員角度看單一餐點訂單
					link = "front-end/meal/memorder.jsp?detail=" + pvo.getOrderId(); //OK
				}else if (no.equals("A12")) { // A12 照護員 瀏覽照護訂單取消通知
					link = "/front-end/careordermgt/careOrder.do?action=view_order&careOrderId=" + pvo.getOrderId(); // OK
				}else if (no.equals("B02")) { // B02 會員 瀏覽照護需求單
					link = "front-end/careRequest/careRequest?action=view_request&requestId=" + pvo.getOrderId(); // OK
				}else {
					link="";
				}

				pvo.setLink(link);
				
				list.add(pvo);

				System.out.println("查詢通知列表：" + pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public void insert(SystemNotificationVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setInt(1, vo.getMemId());
			pstmt.setString(2, vo.getNotTypeNo());
			pstmt.setInt(3, vo.getOrderId());

			pstmt.executeUpdate();

			System.out.println("新增系統通知：" + vo);
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

}
