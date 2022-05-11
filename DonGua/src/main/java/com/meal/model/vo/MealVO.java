package com.meal.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class MealVO implements Serializable {
//	meal table
	private String mealNo;
	private String mealName;
	private BigDecimal mealPrice;
	private String mealStatus;
	private String mealIntroduce;
	private Timestamp createTime;
	private Timestamp updateTime;
//	meal_image table
	private List<byte[]> fileContent;
	private List<String> outputFileContent;
	private String[] updatePics ;
	
	public MealVO() {
		super();
	}
	public MealVO(String mealNo, String mealName, BigDecimal mealPrice, String mealStatus, String mealIntroduce,
			Timestamp createTime, Timestamp updateTime, Long mealImgId, byte[] mealImgFile) {
		super();
		this.mealNo = mealNo;
		this.mealName = mealName;
		this.mealPrice = mealPrice;
		this.mealStatus = mealStatus;
		this.mealIntroduce = mealIntroduce;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public String getMealNo() {
		return mealNo;
	}

	public void setMealNo(String mealNo) {
		this.mealNo = mealNo;
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

	public String getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(String mealStatus) {
		this.mealStatus = mealStatus;
	}

	public String getMealIntroduce() {
		return mealIntroduce;
	}

	public void setMealIntroduce(String mealIntroduce) {
		this.mealIntroduce = mealIntroduce;
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
	public List<byte[]> getFileContent() {
		return fileContent;
	}
	public void setFileContent(List<byte[]> mealPicListString) {
		this.fileContent = mealPicListString;
	}
	public List<String> getOutputFileContent() {
		return outputFileContent;
	}
	public void setOutputFileContent(List<String> outputFileContent) {
		this.outputFileContent = outputFileContent;
	}
	public String[] getUpdatePics() {
		return updatePics;
	}
	public void setUpdatePics(String[] updatePics) {
		this.updatePics = updatePics;
	}

	
	
}

