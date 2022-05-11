package com.mealorder.model;

import java.util.List;

import com.mealorderdetail.model.MealOrderDetailVO;

public interface MealOrderDAO {
	
	public long insert(MealOrderVO mealOrderVO); // 新增一筆餐點訂單
	
	public void update(MealOrderVO mealOrderVO); // 修改訂單狀態

//	public MealOrderVO findByOrderId(Long mealOrderId); // 透過餐點訂單ID查詢訂單資料
//	
//	public List<MealOrderVO> findByOrderStatus(String orderStatus); // 透過訂單狀態查詢訂單資料

	public List<MealOrderVO> findByMemId(Long memId); // 透過會員ID查詢所有訂單資料

	public List<MealOrderVO> findByMemIdAndStatus(Long memId, String orderStatus); 
	
	public List<MealOrderVO> findByMemIdAndOrderId(Long memId, Long mealOrderId); 
	
	public List<MealOrderVO> getAll(); // 查詢所有訂單資料 (後台)
	
//	public void orderDetail (MealOrderVO mealOrderVO, List<MealOrderDetailVO> list);

}
