package com.meal.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.meal.model.vo.MealVO;

public interface MealDAO {
//	後台新增、修改、刪除餐點
	int insertMeal(Connection con, MealVO mealVO); // 新增一筆餐點
	
	int insertMealPic(Connection con, MealVO mealVO); // 新增餐點圖片

	int updateMeal(MealVO mealVO) throws SQLException; // 修改一筆餐點資料

	int deleteMeal(String mealNo) throws SQLException; // 刪除一筆餐點資料
	
	MealVO getPrice(String mealNo); // 透過餐點編號查詢一筆餐點資料
	MealVO findByMealNo(String mealNo); // 透過餐點編號查詢一筆餐點資料

	List<MealVO> getAll(); // 查詢所有餐點資料(前台瀏覽)

	List<MealVO> getAllMeal();

	

	

}
