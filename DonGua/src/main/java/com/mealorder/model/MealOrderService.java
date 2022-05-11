package com.mealorder.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealOrderService {
	@Autowired
	private MealOrderDAO dao;

	public MealOrderVO addMealOrder(Long memId, BigDecimal orderAmount, String addr, String contactNumber,
			String contactName, Date startDate, Integer totalDays, String mealTime, String orderStatus) {
		MealOrderVO mealOrderVO = new MealOrderVO();
		mealOrderVO.setMemId(memId);
		mealOrderVO.setOrderAmount(orderAmount);
		mealOrderVO.setAddr(addr);
		mealOrderVO.setContactNumber(contactNumber);
		mealOrderVO.setContactName(contactName);
		mealOrderVO.setStartDate(startDate);
		mealOrderVO.setTotalDays(totalDays);
		mealOrderVO.setMealTime(mealTime);
		mealOrderVO.setOrderStatus(orderStatus);

//		========================================

		Long orderId = 0L;
		orderId = dao.insert(mealOrderVO);
		mealOrderVO.setMealOrderId(orderId);

//		========================================
		return mealOrderVO;
	}

	public MealOrderVO update(String orderStatus, Long mealOrder) {
		MealOrderVO mealOrderVO = new MealOrderVO();
		mealOrderVO.setOrderStatus(orderStatus);
		mealOrderVO.setMealOrderId(mealOrder);

		dao.update(mealOrderVO);
		return mealOrderVO;
	}

	public List<MealOrderVO> findByMemId(Long memId) {
		return dao.findByMemId(memId);
	}

	public List<MealOrderVO> findByMemIdAndStatus(Long memId, String orderStatus) {
		return dao.findByMemIdAndStatus(memId, orderStatus);
	}

	public List<MealOrderVO> findByMemIdAndOrderId(Long memId, Long orderId) {
		return dao.findByMemIdAndOrderId(memId, orderId);
	}

	public List<MealOrderVO> getAll() {
		return dao.getAll();
	}
}
