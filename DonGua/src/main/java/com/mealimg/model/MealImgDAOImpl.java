package com.mealimg.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.utils.SQLUtil;

@Repository
public class MealImgDAOImpl implements MealImgDAO {
	private static final String GET_ALL = "SELECT MEAL_IMG_ID, MEAL_IMG_FILE, MEAL_NO, CREATE_TIME, UPDATE_TIME FROM MEAL_IMAGE";

	@Override
	public List<MealImgVO> getAll() {
		List<MealImgVO> list = new ArrayList<MealImgVO>();
		MealImgVO mealImgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				mealImgVO = new MealImgVO();
				Long mealImgId = rs.getLong("MEAL_IMG_ID");
				Blob blob = rs.getBlob("MEAL_IMG_FILE");
				String mealNo = rs.getString("MEAL_NO");
				Timestamp creatTime = rs.getTimestamp("CREATE_TIME");
				Timestamp updateTime = rs.getTimestamp("UPDATE_TIME");
				
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while ((bytesRead = inputStream.read(buffer))!= -1) {
					outputStream.write(buffer,0 , bytesRead);
				}
				
				byte[] imageBytes = outputStream.toByteArray();
				String mealImgFile = Base64.getEncoder().encodeToString(imageBytes);
				
				inputStream.close();
				outputStream.close();
				
				mealImgVO.setMealImgId(mealImgId);
				mealImgVO.setMealImgFile(mealImgFile);
				mealImgVO.setMealNo(mealNo);
				mealImgVO.setCreateTime(creatTime);
				mealImgVO.setUpdateTime(updateTime);
				
				list.add(mealImgVO);
			}	
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} catch (IOException se) {
			System.out.println(se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return list;
	}
	
}
