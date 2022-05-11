package com.mealorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.utils.SQLUtil;

@Repository
public class MealOrderDAOImpl implements MealOrderDAO {
	private static final String INSERT_MEALORDER = "INSERT INTO MEAL_ORDER(MEM_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, ORDER_STATUS) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE MEAL_ORDER SET ORDER_STATUS = ?, UPDATE_TIME = current_timestamp() WHERE MEAL_ORDER_ID = ?";
//	private static final String GET_ONE_ODRERID = "SELECT MEM_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, MEAL_FEEDBACK, ORDER_STATUS, CREATE_TIME, UPDATE_TIME FROM MEAL_ORDER WHERE MEAL_ORDER_ID=?";
//	private static final String GET_ALL_STATUS = "SELECT MEAL_ORDER_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, MEAL_FEEDBACK, CREATE_TIME, UPDATE_TIME FROM MEAL_ORDER WHERE ORDER_STATUS=?";
//	private static final String GET_ALL_MEMID = "SELECT MEAL_ORDER_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, MEAL_FEEDBACK, ORDER_STATUS, CREATE_TIME, UPDATE_TIME FROM MEAL_ORDER WHERE MEM_ID=?";
	private static final String GET_ALL_MEMID = "SELECT MEAL_ORDER_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, ORDER_STATUS, CREATE_TIME, UPDATE_TIME FROM MEAL_ORDER WHERE MEM_ID=?";
	private static final String GET_ALL_MEMIDANDSTATUS = "SELECT MEAL_ORDER_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, ORDER_STATUS, CREATE_TIME, UPDATE_TIME FROM MEAL_ORDER WHERE MEM_ID=? AND ORDER_STATUS=?";
	private static final String GET_ALL_MEMIDANDORDERID = "SELECT MEAL_ORDER_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, ORDER_STATUS, CREATE_TIME, UPDATE_TIME FROM MEAL_ORDER WHERE MEM_ID=? AND MEAL_ORDER_ID=?";
	private static final String GET_ALL = "SELECT MEM_ID, MEAL_ORDER_ID, ORDER_AMOUNT, ADDR, CONTACT_NUMBER, CONTACT_NAME, START_DATE, TOTAL_DAYS, MEAL_TIME, MEAL_FEEDBACK, CASE ORDER_STATUS WHEN 0 THEN '待執行' WHEN 1 THEN '執行中' WHEN 2 THEN '已完成' WHEN 3 THEN '已結單' WHEN 4 THEN '已取消' END AS ORDER_STATUS , CREATE_TIME, UPDATE_TIME FROM MEAL_ORDER";
	

	@Override
	public long insert(MealOrderVO mealOrderVO) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT_MEALORDER);
			stmt = con.createStatement();

			pstmt.setLong(1, mealOrderVO.getMemId());
			pstmt.setBigDecimal(2, mealOrderVO.getOrderAmount());
			pstmt.setString(3, mealOrderVO.getAddr());
			pstmt.setString(4, mealOrderVO.getContactNumber());
			pstmt.setString(5, mealOrderVO.getContactName());
			pstmt.setDate(6, mealOrderVO.getStartDate());
			pstmt.setInt(7, mealOrderVO.getTotalDays());
			pstmt.setString(8, mealOrderVO.getMealTime());
			pstmt.setString(9, mealOrderVO.getOrderStatus());

			pstmt.executeUpdate();
//			======================================================
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			if (rs.next()) {
				long id = rs.getLong(1);
				mealOrderVO.setMealOrderId(id);

				return id;
			}
//			======================================================

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();

				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

			SQLUtil.closeResource(con, pstmt, rs);
//			System.out.println("新增一筆資料" + mealOrderVO);
		}
		return 0;
	}

	@Override
	public void update(MealOrderVO mealOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mealOrderVO.getOrderStatus());
			pstmt.setLong(2, mealOrderVO.getMealOrderId());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
		}
	}

//	@Override
//	public MealOrderVO findByOrderId(Long mealOrderId) {
//		MealOrderVO mealOrderVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(SQLUtil.DRIVER);
//			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_ODRERID);
//
//			pstmt.setLong(1, mealOrderId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				mealOrderVO = new MealOrderVO();
//				mealOrderVO.setMemId(rs.getLong("MEM_ID"));
//				mealOrderVO.setOrderAmount(rs.getBigDecimal("ORDER_AMOUNT"));
//				mealOrderVO.setAddr(rs.getString("ADDR"));
//				mealOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
//				mealOrderVO.setContactName(rs.getString("CONTACT_NAME"));
//				mealOrderVO.setStartDate(rs.getDate("START_DATE"));
//				mealOrderVO.setTotalDays(rs.getInt("TOTAL_DAYS"));
//				mealOrderVO.setMealTime(rs.getString("MEAL_TIME"));
//				mealOrderVO.setMealFeedback(rs.getString("MEAL_FEEDBACK"));
//				mealOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
//				mealOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//				mealOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
//			}
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			SQLUtil.closeResource(con, pstmt, rs);
//		}
//		return mealOrderVO;
//
//	}
//
//	@Override
//	public List<MealOrderVO> findByOrderStatus(String orderStatus) {
//		List<MealOrderVO> list = new ArrayList<MealOrderVO>();
//		MealOrderVO mealOrderVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(SQLUtil.DRIVER);
//			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL_STATUS);
//			pstmt.setString(1, orderStatus);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				mealOrderVO = new MealOrderVO();
//				mealOrderVO.setMealOrderId(rs.getLong("MEAL_ORDER_ID"));
//				mealOrderVO.setOrderAmount(rs.getBigDecimal("ORDER_AMOUNT"));
//				mealOrderVO.setAddr(rs.getString("ADDR"));
//				mealOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
//				mealOrderVO.setContactName(rs.getString("CONTACT_NAME"));
//				mealOrderVO.setStartDate(rs.getDate("START_DATE"));
//				mealOrderVO.setTotalDays(rs.getInt("TOTAL_DAYS"));
//				mealOrderVO.setMealTime(rs.getString("MEAL_TIME"));
//				mealOrderVO.setMealFeedback(rs.getString("MEAL_FEEDBACK"));
//				mealOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//				mealOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
//
//				list.add(mealOrderVO);
//			}
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//		} finally {
//			SQLUtil.closeResource(con, pstmt, rs);
//		}
//
//		return list;
//	}

	@Override
	public List<MealOrderVO> findByMemId(Long memId) {
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();
		MealOrderVO mealOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_MEMID);
			pstmt.setLong(1, memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealOrderVO = new MealOrderVO();
				mealOrderVO.setMealOrderId(rs.getLong("MEAL_ORDER_ID"));
				mealOrderVO.setOrderAmount(rs.getBigDecimal("ORDER_AMOUNT"));
				mealOrderVO.setAddr(rs.getString("ADDR"));
				mealOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				mealOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				mealOrderVO.setStartDate(rs.getDate("START_DATE"));
				mealOrderVO.setTotalDays(rs.getInt("TOTAL_DAYS"));
				mealOrderVO.setMealTime(rs.getString("MEAL_TIME"));
//				mealOrderVO.setMealFeedback(rs.getString("MEAL_FEEDBACK"));
				mealOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				mealOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				mealOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));

				list.add(mealOrderVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<MealOrderVO> findByMemIdAndStatus(Long memId, String orderStatus) {
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();
		MealOrderVO mealOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_MEMIDANDSTATUS);
			pstmt.setLong(1, memId);
			pstmt.setString(2, orderStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealOrderVO = new MealOrderVO();
				mealOrderVO.setMealOrderId(rs.getLong("MEAL_ORDER_ID"));
				mealOrderVO.setOrderAmount(rs.getBigDecimal("ORDER_AMOUNT"));
				mealOrderVO.setAddr(rs.getString("ADDR"));
				mealOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				mealOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				mealOrderVO.setStartDate(rs.getDate("START_DATE"));
				mealOrderVO.setTotalDays(rs.getInt("TOTAL_DAYS"));
				mealOrderVO.setMealTime(rs.getString("MEAL_TIME"));
//				mealOrderVO.setMealFeedback(rs.getString("MEAL_FEEDBACK"));
				mealOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				mealOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				mealOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));

				list.add(mealOrderVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}
	
	@Override
	public List<MealOrderVO> findByMemIdAndOrderId(Long memId, Long mealOrderId){
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();
		MealOrderVO mealOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_MEMIDANDORDERID);
			pstmt.setLong(1, memId);
			pstmt.setLong(2, mealOrderId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealOrderVO = new MealOrderVO();
				mealOrderVO.setMealOrderId(rs.getLong("MEAL_ORDER_ID"));
				mealOrderVO.setOrderAmount(rs.getBigDecimal("ORDER_AMOUNT"));
				mealOrderVO.setAddr(rs.getString("ADDR"));
				mealOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				mealOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				mealOrderVO.setStartDate(rs.getDate("START_DATE"));
				mealOrderVO.setTotalDays(rs.getInt("TOTAL_DAYS"));
				mealOrderVO.setMealTime(rs.getString("MEAL_TIME"));
//				mealOrderVO.setMealFeedback(rs.getString("MEAL_FEEDBACK"));
				mealOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				mealOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				mealOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));

				list.add(mealOrderVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

	@Override
	public List<MealOrderVO> getAll() {
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();
		MealOrderVO mealOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealOrderVO = new MealOrderVO();
				mealOrderVO.setMemId(rs.getLong("MEM_ID"));
				mealOrderVO.setMealOrderId(rs.getLong("MEAL_ORDER_ID"));
				mealOrderVO.setOrderAmount(rs.getBigDecimal("ORDER_AMOUNT"));
				mealOrderVO.setAddr(rs.getString("ADDR"));
				mealOrderVO.setContactNumber(rs.getString("CONTACT_NUMBER"));
				mealOrderVO.setContactName(rs.getString("CONTACT_NAME"));
				mealOrderVO.setStartDate(rs.getDate("START_DATE"));
				mealOrderVO.setTotalDays(rs.getInt("TOTAL_DAYS"));
				mealOrderVO.setMealTime(rs.getString("MEAL_TIME"));
				mealOrderVO.setMealFeedback(rs.getString("MEAL_FEEDBACK"));
				mealOrderVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				mealOrderVO.setCreateTime(rs.getTimestamp("CREATE_TIME"));
				mealOrderVO.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));

				list.add(mealOrderVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}

		return list;
	}

//	@Override
//	public void orderDetail (MealOrderVO mealOrderVO, List<MealOrderDetailVO> list) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(SQLUtil.DRIVER);
//			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
//			
//			// 1 設定於 pstm.executeUpdate()之前
//    		con.setAutoCommit(false);
//			
//    		// 先新增訂單
//			String cols[] = {"MEALORDER"};
//			pstmt = con.prepareStatement(INSERT_MEALORDER , cols);			
//			pstmt.setLong(1, mealOrderVO.getMemId());
//			pstmt.setBigDecimal(2, mealOrderVO.getOrderAmount());
//			pstmt.setString(3, mealOrderVO.getAddr());
//			pstmt.setString(4, mealOrderVO.getContactNumber());
//			pstmt.setString(5, mealOrderVO.getContactName());
//			pstmt.setDate(6, mealOrderVO.getStartDate());
//			pstmt.setInt(7, mealOrderVO.getTotalDays());
//			pstmt.setString(8, mealOrderVO.getMealTime());
//			pstmt.setString(9, mealOrderVO.getOrderStatus());
//			
//			pstmt.executeUpdate();
//			
//			// 掘取對應的自增主鍵值
//			String next = null;
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				next = rs.getString(1);
//				System.out.println("自增主鍵值= " + next +"(剛新增成功的訂單ID)");
//			} else {
//				System.out.println("未取得自增主鍵值");
//			}
//			rs.close();
//			
//			// 再同時新增明細
//			MealOrderDetailDAOImpl dao = new MealOrderDetailDAOImpl();
//			System.out.println("list.size()-A="+list.size());
//			for (MealOrderDetailVO detail : list) {
//				detail.setMealOrderId(new Long(next)) ;
//				dao.insert(detail,con);
//			}
//			// 2 設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("list.size()-B="+list.size());
//			System.out.println("新增部門編號" + next + "時,共有明細" + list.size()
//								+ "筆同時被新增");
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3 設定於當有exception發生時之catch區塊內
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back mealOrder");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}



}
