package com.mealorderdetail.model;

import java.util.List;

import com.mealorder.model.MealOrderVO;

public interface MealOrderDetailDAO {
	public void insert(MealOrderDetailVO mealOrderDetailVO); // 新增訂單明細

	public List<MealOrderDetailVO> findByOrderId(Long mealOrderId); // 透過訂單ID查詢明細
	
	public List<MealOrderDetailVO> getAll();
	
}
