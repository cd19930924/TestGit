package com.carermemapplymgt.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarerMemApplyMgtVO implements Serializable {

	private static final long serialVersionUID = 8071448699349717330L;

	// 照護員申請審核
	// 列表(會員表)
	private Integer memID;
	private Integer applyID; // AI
	private String memAcct;
	private String memPwd;
	private String memName;
	private String memGender;
	private Integer memAge;
	private String memPhone;
	// 詳細資料(會員表)
	private String memEmail;
	private String memAddr;
	// 詳細資料(申請表)
	private String serviceType;
	private String serviceDistNo;
	private Double priceHour;
	private Double priceHalfday;
	private Double priceDay;
	private String intro;
	private String bankCode;
	private String bankAcct;
	private Timestamp createTime; // currentTimeStamp 申請時間
	private String status; // 申請狀態

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public Integer getApplyID() {
		return applyID;
	}

	public void setApplyID(Integer applyID) {
		this.applyID = applyID;
	}

	public String getMemAcct() {
		return memAcct;
	}

	public void setMemAcct(String memAcct) {
		this.memAcct = memAcct;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}

	public Integer getMemAge() {
		return memAge;
	}

	public void setMemAge(Integer memAge) {
		this.memAge = memAge;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDistNo() {
		return serviceDistNo;
	}

	public void setServiceDistNo(String serviceDistNo) {
		this.serviceDistNo = serviceDistNo;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CarerMemApplyMgtVO [memID=" + memID + ", applyID=" + applyID + ", memAcct=" + memAcct + ", memPwd="
				+ memPwd + ", memName=" + memName + ", memGender=" + memGender + ", memAge=" + memAge + ", memPhone="
				+ memPhone + ", memEmail=" + memEmail + ", memAddr=" + memAddr + ", serviceType=" + serviceType
				+ ", serviceDistNo=" + serviceDistNo + ", priceHour=" + priceHour + ", priceHalfday=" + priceHalfday
				+ ", priceDay=" + priceDay + ", intro=" + intro + ", bankCode=" + bankCode + ", bankAcct=" + bankAcct
				+ ", createTime=" + createTime + ", status=" + status + "]";
	}

}
