package com.mealorderdetail.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MealOrderDetailVO implements Serializable  {
	private Long mealOrderId;
	private String mealNo;
	private Integer mealCount;
	private BigDecimal mealAmount;
	// meal table
	private String mealName;
	private BigDecimal mealPrice;
	
	public MealOrderDetailVO() {
		super();
	}

	public MealOrderDetailVO(Long mealOrderId, String mealNo, Integer mealCount, BigDecimal mealAmount, String mealName, BigDecimal mealPrice) {
		super();
		this.mealOrderId = mealOrderId;
		this.mealNo = mealNo;
		this.mealCount = mealCount;
		this.mealAmount = mealAmount;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
	}

	public Long getMealOrderId() {
		return mealOrderId;
	}

	public void setMealOrderId(Long mealOrderId) {
		this.mealOrderId = mealOrderId;
	}

	public String getMealNo() {
		return mealNo;
	}

	public void setMealNo(String mealNo) {
		this.mealNo = mealNo;
	}

	public Integer getMealCount() {
		return mealCount;
	}

	public void setMealCount(Integer mealCount) {
		this.mealCount = mealCount;
	}

	public BigDecimal getMealAmount() {
		return mealAmount;
	}

	public void setMealAmount(BigDecimal mealAmount) {
		this.mealAmount = mealAmount;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public BigDecimal getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(BigDecimal mealPrice) {
		this.mealPrice = mealPrice;
	}

	@Override
	public String toString() {
		return "MealOrderDetailVO [mealOrderId=" + mealOrderId + ", mealNo=" + mealNo + ", mealCount=" + mealCount
				+ ", mealAmount=" + mealAmount + ", mealName=" + mealName + ", mealPrice=" + mealPrice + "]";
	}
	
	
}
