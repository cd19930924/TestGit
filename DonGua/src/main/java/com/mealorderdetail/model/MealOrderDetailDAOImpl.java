package com.mealorderdetail.model;

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
public class MealOrderDetailDAOImpl implements MealOrderDetailDAO {
	private static final String INSERT= "INSERT INTO MEAL_ORDER_DETAIL(MEAL_ORDER_ID, MEAL_NO, MEAL_COUNT, MEAL_AMOUNT) VALUES(?,?,?,?)";
	private static final String GET_ALL_ORDERID= "SELECT a.MEAL_NO, a.MEAL_COUNT, a.MEAL_AMOUNT, b.MEAL_NAME, b.MEAL_PRICE\r\n"
			+ "FROM MEAL_ORDER_DETAIL a\r\n"
			+ "JOIN MEAL b\r\n"
			+ "ON a.MEAL_NO = b.MEAL_NO\r\n"
			+ "WHERE a.MEAL_ORDER_ID = ?";
	private static final String GET_ALL= "SELECT a.MEAL_ORDER_ID, a.MEAL_NO, a.MEAL_COUNT, a.MEAL_AMOUNT, b.MEAL_NAME, b.MEAL_PRICE\r\n"
			+ "FROM MEAL_ORDER_DETAIL a\r\n"
			+ "JOIN MEAL b\r\n"
			+ "ON a.MEAL_NO = b.MEAL_NO\r\n";
	
	
	@Override
	public void insert(MealOrderDetailVO mealOrderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setLong(1, mealOrderDetailVO.getMealOrderId());
			pstmt.setString(2, mealOrderDetailVO.getMealNo());
			pstmt.setInt(3, mealOrderDetailVO.getMealCount());
			pstmt.setBigDecimal(4, mealOrderDetailVO.getMealAmount());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, null);
//			System.out.println("新增一筆資料" + mealOrderDetailVO);
		}
	}

	@Override
	public List<MealOrderDetailVO> findByOrderId(Long mealOrderId) {
		List<MealOrderDetailVO> list = new ArrayList<MealOrderDetailVO>();
		MealOrderDetailVO mealOrderDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_ORDERID);
			pstmt.setLong(1, mealOrderId);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mealOrderDetailVO = new MealOrderDetailVO();
				mealOrderDetailVO.setMealNo(rs.getString("MEAL_NO"));
				mealOrderDetailVO.setMealCount(rs.getInt("MEAL_COUNT"));
				mealOrderDetailVO.setMealAmount(rs.getBigDecimal("MEAL_AMOUNT"));
				mealOrderDetailVO.setMealName(rs.getString("MEAL_NAME"));
				mealOrderDetailVO.setMealPrice(rs.getBigDecimal("MEAL_PRICE"));
				
				list.add(mealOrderDetailVO);
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
	public List<MealOrderDetailVO> getAll() {
		List<MealOrderDetailVO> list = new ArrayList<MealOrderDetailVO>();
		MealOrderDetailVO mealOrderDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mealOrderDetailVO = new MealOrderDetailVO();
				mealOrderDetailVO.setMealOrderId(rs.getLong("MEAL_ORDER_ID"));
				mealOrderDetailVO.setMealNo(rs.getString("MEAL_NO"));
				mealOrderDetailVO.setMealCount(rs.getInt("MEAL_COUNT"));
				mealOrderDetailVO.setMealAmount(rs.getBigDecimal("MEAL_AMOUNT"));
				mealOrderDetailVO.setMealName(rs.getString("MEAL_NAME"));
				mealOrderDetailVO.setMealPrice(rs.getBigDecimal("MEAL_PRICE"));
				
				list.add(mealOrderDetailVO);
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
	
}
