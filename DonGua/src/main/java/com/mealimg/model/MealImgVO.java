package com.mealimg.model;

import java.sql.Timestamp;

public class MealImgVO {
	private Long mealImgId;
	private String mealImgFile;
	private String mealNo;
	private Timestamp createTime;
	private Timestamp updateTime;

	public MealImgVO() {
		super();
	}

	public MealImgVO(Long mealImgId, String mealImgFile, String mealNo, Timestamp createTime, Timestamp updateTime) {
		super();
		this.mealImgId = mealImgId;
		this.mealImgFile = mealImgFile;
		this.mealNo = mealNo;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getMealImgId() {
		return mealImgId;
	}

	public void setMealImgId(Long mealImgId) {
		this.mealImgId = mealImgId;
	}

	public String getMealImgFile() {
		return mealImgFile;
	}

	public void setMealImgFile(String mealImgFile) {
		this.mealImgFile = mealImgFile;
	}

	public String getMealNo() {
		return mealNo;
	}

	public void setMealNo(String mealNo) {
		this.mealNo = mealNo;
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
	
	

}
