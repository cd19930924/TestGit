package com.carermemapply.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarerMemApplyVO implements Serializable {

	private static final long serialVersionUID = 6607030622744713137L;

	// 照護員申請
	private Integer applyID; // AI
	private Integer memID;
	private String serviceDistNo;
	private String bankCode;
	private String bankAcct;
	private String serviceType;
	private String intro;
	private Double priceHour;
	private Double priceHalfday;
	private Double priceDay;
	private String status;
	private Timestamp createTime; // currentTimeStamp
	private Timestamp updateTime; 

	public CarerMemApplyVO() {
	}

	public Integer getApplyID() {
		return applyID;
	}

	public void setApplyID(Integer applyID) {
		this.applyID = applyID;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public String getServiceDistNo() {
		return serviceDistNo;
	}

	public void setServiceDistNo(String serviceDistNo) {
		this.serviceDistNo = serviceDistNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Double getPriceHour() {
		return priceHour;
	}

	public void setPriceHour(Double priceHour) {
		this.priceHour = priceHour;
	}

	public Double getPriceHalfday() {
		return priceHalfday;
	}

	public void setPriceHalfday(Double priceHalfday) {
		this.priceHalfday = priceHalfday;
	}

	public Double getPriceDay() {
		return priceDay;
	}

	public void setPriceDay(Double priceDay) {
		this.priceDay = priceDay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
