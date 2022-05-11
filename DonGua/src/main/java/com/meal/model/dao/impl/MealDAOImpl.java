package com.meal.model.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meal.model.dao.MealDAO;
import com.meal.model.vo.MealVO;
import com.utils.SQLUtil;
import com.utils.SQLUtils;

@Repository
public class MealDAOImpl implements MealDAO {
	
	@Autowired
	private static DataSource ds ;
	

	private static final String CREATE_MEAL = "INSERT INTO MEAL(MEAL_NO, MEAL_NAME, MEAL_PRICE, MEAL_INTRODUCE)" + "VALUES(?,?,?,?)";
	private static final String CREATE_MEAL_PIC = "INSERT INTO MEAL_IMAGE(MEAL_IMG_FILE, MEAL_NO)" + "VALUES(?,?)";
	private static final String GET_ALL_MEAL = "SELECT * FROM MEAL WHERE MEAL_STATUS =1 ";
	private static final String GET_ALL_MEAL_NO = "SELECT * FROM MEAL ";
	private static final String GET_MEAL_BYMEALNO = "SELECT * FROM MEAL WHERE MEAL_NO = ? ";
	private static final String GET_MEAL_PIC_BYMEALNO = "SELECT * FROM MEAL_IMAGE WHERE MEAL_NO = ? ";
	private static final String UPDATE_MEAL = "UPDATE MEAL SET MEAL_NAME= ?, MEAL_PRICE= ?, MEAL_INTRODUCE= ?,MEAL_STATUS=?,UPDATE_TIME = ? WHERE MEAL_NO =?;";
	private static final String UPDATE_MEAL_PIC_STRING = "UPDATE MEAL_IMAGE SET MEAL_IMG_FILE=? ,UPDATE_TIME=? WHERE MEAL_IMG_ID=?";
	private static final String DELETE_MEAL = "DELETE FROM MEAL WHERE MEAL_NO=?";
	private static final String DELETE_MEAL_PIC = "DELETE FROM MEAL_IMAGE WHERE MEAL_NO=?";
	private static final String GET_ONE_MEALNO = "SELECT MEAL_PRICE FROM MEAL WHERE MEAL_NO = ?";
	private static final String IS_MEAL_BE_USED = "SELECT * FROM MEAL_ORDER_DETAIL WHERE MEAL_NO=?";
	
	@Override
	public int insertMeal(Connection con,MealVO mealVO) {
		try {
			Class.forName(SQLUtils.DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int result = 0;
		try (
				PreparedStatement pstmt = con.prepareStatement(CREATE_MEAL)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			pstmt.setString(1, mealVO.getMealNo());
			pstmt.setString(2, mealVO.getMealName());
			pstmt.setBigDecimal(3, mealVO.getMealPrice());
			pstmt.setString(4, mealVO.getMealIntroduce());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public int insertMealPic(Connection con ,MealVO mealVO) {
		int result = 0;
		try (
			PreparedStatement pstmt = con.prepareStatement(CREATE_MEAL_PIC)) {
			for(int index=0;index<mealVO.getFileContent().size();index++) {
				pstmt.setBytes(1, mealVO.getFileContent().get(index));
				pstmt.setString(2, mealVO.getMealNo());	
				result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} 

	

	@Override
	public int updateMeal(MealVO mealVO) throws SQLException {
		Connection con = ds.getConnection();
		try(PreparedStatement pstmt = con.prepareStatement(UPDATE_MEAL)) {
			pstmt.setString(1, mealVO.getMealName());
			pstmt.setBigDecimal(2, mealVO.getMealPrice());
			pstmt.setString(3, mealVO.getMealIntroduce());
			pstmt.setString(4, mealVO.getMealStatus());
			Timestamp d = new Timestamp(System.currentTimeMillis()); 
			System.out.println(mealVO.getMealStatus());
			pstmt.setTimestamp(5, d);
			pstmt.setString(6, mealVO.getMealNo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int result = 0;
		if(mealVO.getFileContent().size()==3) {
			try(PreparedStatement pstmt = con.prepareStatement(DELETE_MEAL_PIC)) {
				pstmt.setString(1,mealVO.getMealNo());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			MealDAOImpl dao = new MealDAOImpl();
			result = dao.insertMealPic(con, mealVO);
		}else {
			List<Integer> count= new ArrayList<Integer>() ;
			try(PreparedStatement pstmt = con.prepareStatement(GET_MEAL_PIC_BYMEALNO)){//取得目前所有餐點照片ID
				pstmt.setString(1,mealVO.getMealNo());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					int a= rs.getInt("MEAL_IMG_ID");
					count.add(a);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			String[] changePic = mealVO.getUpdatePics();
			System.out.println(changePic[0]);
			System.out.println(changePic[1]);
			
			if(changePic !=null) {
				for(int i =0;i<changePic.length;i++) {
					if("addimg1".equals(changePic[i])&& count.size()==0) {
						try(PreparedStatement pstmt = con.prepareStatement(CREATE_MEAL_PIC)) {
								pstmt.setBytes(1, mealVO.getFileContent().get(i));
								pstmt.setString(2, mealVO.getMealNo());	
								result = pstmt.executeUpdate();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if("addimg1".equals(changePic[i])){
						try(PreparedStatement pstmt = con.prepareStatement(UPDATE_MEAL_PIC_STRING)) {
								pstmt.setBytes(1, mealVO.getFileContent().get(i));
								Timestamp d = new Timestamp(System.currentTimeMillis()); 
								System.out.println(mealVO.getMealStatus());
								pstmt.setTimestamp(2, d);
								pstmt.setInt(3,count.get(i));	
								result = pstmt.executeUpdate();					
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if("addimg2".equals(changePic[i])&& count.size()<2) {
						try(PreparedStatement pstmt = con.prepareStatement(CREATE_MEAL_PIC)) {
							pstmt.setBytes(1, mealVO.getFileContent().get(i));
							pstmt.setString(2, mealVO.getMealNo());	
							result = pstmt.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}
					}else if("addimg2".equals(changePic[i])) {
						try(PreparedStatement pstmt = con.prepareStatement(UPDATE_MEAL_PIC_STRING)) {
							pstmt.setBytes(1, mealVO.getFileContent().get(i));
							Timestamp d = new Timestamp(System.currentTimeMillis()); 
							System.out.println(mealVO.getMealStatus());
							pstmt.setTimestamp(2, d);
							pstmt.setInt(3,count.get(i));	
							result = pstmt.executeUpdate();					
					} catch (Exception e) {
						e.printStackTrace();
					}
					}else if("addimg3".equals(changePic[i])&& count.size()<3) {
						try(PreparedStatement pstmt = con.prepareStatement(CREATE_MEAL_PIC)) {
							pstmt.setBytes(1, mealVO.getFileContent().get(i));
							pstmt.setString(2, mealVO.getMealNo());	
							result = pstmt.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}
					}else if ("addimg3".equals(changePic[i])) {
						try(PreparedStatement pstmt = con.prepareStatement(UPDATE_MEAL_PIC_STRING)) {
							pstmt.setBytes(1, mealVO.getFileContent().get(i));
							Timestamp d = new Timestamp(System.currentTimeMillis()); 
							System.out.println(mealVO.getMealStatus());
							pstmt.setTimestamp(2, d);
							pstmt.setInt(3,count.get(i));	
							result = pstmt.executeUpdate();					
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						break;
					}
				}
			}
		}
		con.close();
		return result;
	}

	@Override
	public int deleteMeal(String mealNo) throws SQLException {
		Connection con = ds.getConnection();
		con.setAutoCommit(false);
		int result =1;
		try(PreparedStatement pstmt = con.prepareStatement(IS_MEAL_BE_USED)) {
			pstmt.setString(1, mealNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 0;/*表示該餐點已被使用過*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result==1) {
			try(PreparedStatement pstmt = con.prepareStatement(DELETE_MEAL)) {
				pstmt.setString(1, mealNo);
				result  = pstmt.executeUpdate();	
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
			try(PreparedStatement pstmt = con.prepareStatement(DELETE_MEAL_PIC)) {
				pstmt.setString(1, mealNo);
				result  = pstmt.executeUpdate();
				System.out.println(result);
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
		}
		con.commit();
		con.close();
		return result;
	}

	@Override
	public MealVO findByMealNo(String mealNo) {
		Connection con = null;
		Blob image = null;  
		byte[] imgData = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MealVO mealVO = new MealVO();
		try (PreparedStatement pstmt = con.prepareStatement(GET_MEAL_BYMEALNO)){
			pstmt.setString(1, mealNo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				mealVO.setMealNo(mealNo);
				mealVO.setMealName(rs.getString("MEAL_NAME"));
				mealVO.setMealPrice(rs.getBigDecimal("MEAL_PRICE"));
				mealVO.setMealIntroduce(rs.getString("MEAL_INTRODUCE"));
				mealVO.setMealStatus(rs.getString("MEAL_STATUS"));
				
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (PreparedStatement pstmt = con.prepareStatement(GET_MEAL_PIC_BYMEALNO)){
			pstmt.setString(1, mealNo);
			ResultSet rs = pstmt.executeQuery();
			List<String> picList = new ArrayList<String>();
			while(rs.next()) {
//				byte[] picBytes = rs.getBytes("MEAL_IMG_FILE");
				image = rs.getBlob("MEAL_IMG_FILE");  
				imgData = image.getBytes(1, (int) image.length());
				String fileContent = Base64.getEncoder().encodeToString(imgData);
				picList.add(fileContent);
			}
			mealVO.setOutputFileContent(picList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mealVO;
	}

	@Override
	public List<MealVO> getAll() {
		List<MealVO> mealList = new ArrayList<MealVO>();

		try (Connection con = DriverManager.getConnection(SQLUtils.URL,SQLUtils.USER ,SQLUtils.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_MEAL)) {
			
			try {
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					MealVO mealVo = new MealVO();
					mealVo.setMealNo(rs.getString("MEAL_NO"));
					mealVo.setMealName(rs.getString("MEAL_NAME"));
					mealVo.setMealPrice(rs.getBigDecimal("MEAL_PRICE"));
					mealVo.setMealStatus(rs.getString("MEAL_STATUS"));
					mealVo.setMealIntroduce(rs.getString("MEAL_INTRODUCE"));
					mealVo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
					mealVo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
					mealList.add(mealVo);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mealList;
	}
	
	@Override
	public List<MealVO> getAllMeal() {
		List<MealVO> mealList = new ArrayList<MealVO>();

		try (Connection con = DriverManager.getConnection(SQLUtils.URL,SQLUtils.USER ,SQLUtils.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_MEAL_NO)) {
			
			try {
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					MealVO mealVo = new MealVO();
					mealVo.setMealNo(rs.getString("MEAL_NO"));
					mealVo.setMealName(rs.getString("MEAL_NAME"));
					mealVo.setMealPrice(rs.getBigDecimal("MEAL_PRICE"));
					if("0".equals(rs.getString("MEAL_STATUS"))) {
						mealVo.setMealStatus("下架");
					}else if("1".equals(rs.getString("MEAL_STATUS"))){
						mealVo.setMealStatus("上架");
					}
					mealVo.setMealIntroduce(rs.getString("MEAL_INTRODUCE"));
					mealVo.setCreateTime(rs.getTimestamp("CREATE_TIME"));
					mealVo.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
					mealList.add(mealVo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mealList;
	}

	@Override
	public MealVO getPrice(String mealNo) {
		MealVO mealVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(SQLUtil.DRIVER);
			con = DriverManager.getConnection(SQLUtil.URL, SQLUtil.USER, SQLUtil.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_MEALNO);
			
			pstmt.setString(1, mealNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mealVO = new MealVO();
				mealVO.setMealPrice(rs.getBigDecimal("MEAL_PRICE"));
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			SQLUtil.closeResource(con, pstmt, rs);
		}
		return mealVO;
	}

}
