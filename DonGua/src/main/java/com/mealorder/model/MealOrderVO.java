package com.mealorder.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class MealOrderVO implements Serializable {
	private Long mealOrderId;
	private Long memId;
	private BigDecimal orderAmount;
	private String addr;
	private String contactNumber;
	private String contactName;
	private Date startDate;
	private Integer totalDays;
	private String mealTime;
	private String mealFeedback;
	private String orderStatus;
	private Timestamp createTime;
	private Timestamp updateTime;

	public MealOrderVO() {
		super();
	}

	public MealOrderVO(Long mealOrderId, Long memId, BigDecimal orderAmount, String addr, String contactNumber,
			String contactName, Date startDate, Integer totalDays, String mealTime, String mealFeedback,
			String orderStatus, Timestamp createTime, Timestamp updateTime) {
		super();
		this.mealOrderId = mealOrderId;
		this.memId = memId;
		this.orderAmount = orderAmount;
		this.addr = addr;
		this.contactNumber = contactNumber;
		this.contactName = contactName;
		this.startDate = startDate;
		this.totalDays = totalDays;
		this.mealTime = mealTime;
		this.mealFeedback = mealFeedback;
		this.orderStatus = orderStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getMealOrderId() {
		return mealOrderId;
	}

	public void setMealOrderId(Long mealOrderId) {
		this.mealOrderId = mealOrderId;
	}

	public Long getMemId() {
		return memId;
	}

	public void setMemId(Long memId) {
		this.memId = memId;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	public String getMealTime() {
		return mealTime;
	}

	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}

	public String getMealFeedback() {
		return mealFeedback;
	}

	public void setMealFeedback(String mealFeedback) {
		this.mealFeedback = mealFeedback;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}



	@Override
	public String toString() {
		return "MealOrderVO [mealOrderId=" + mealOrderId + ", memId=" + memId + ", orderAmount=" + orderAmount
				+ ", addr=" + addr + ", contactNumber=" + contactNumber + ", contactName=" + contactName
				+ ", startDate=" + startDate + ", totalDays=" + totalDays + ", mealTime=" + mealTime + ", mealFeedback="
				+ mealFeedback + ", orderStatus=" + orderStatus + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
	

}
