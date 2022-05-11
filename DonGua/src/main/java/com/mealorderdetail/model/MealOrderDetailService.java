package com.mealorderdetail.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meal.model.service.MealService;

@Service
public class MealOrderDetailService {
	@Autowired
	private MealOrderDetailDAO dao;
	@Autowired
	private MealService mealSvc;
	
	BigDecimal price;
	BigDecimal count;
	
	public MealOrderDetailVO addOrderDetail(Long orderId, String[] mealNoList, String[] mealQtyList) {
		MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();
		for(int i = 0; i < mealNoList.length; i++) {
			if(Integer.valueOf(mealQtyList[i]) != 0) {
				price = mealSvc.getPrice(mealNoList[i]).getMealPrice();
				count = new BigDecimal(mealQtyList[i]);
				
				mealOrderDetailVO.setMealOrderId(orderId);
				mealOrderDetailVO.setMealNo(mealNoList[i]);
				mealOrderDetailVO.setMealCount(Integer.valueOf(mealQtyList[i]));
				mealOrderDetailVO.setMealAmount(price.multiply(count));
				dao.insert(mealOrderDetailVO);
			}
		}
		
		return mealOrderDetailVO;
	}
	
	public List<MealOrderDetailVO> findByMealOrderId(Long mealOrderId) {
		return dao.findByOrderId(mealOrderId);
	}
	
	public List<MealOrderDetailVO> getAll(){
		return dao.getAll();
	}
}
