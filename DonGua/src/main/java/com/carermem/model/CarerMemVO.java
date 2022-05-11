package com.carermem.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CarerMemVO implements Serializable {

	private static final long serialVersionUID = 7914779104261483611L;

	// 照護員會員
	private Integer carerID; // memID
	private String carerAcct; // memAcct
	private String serviceDistNo;
	private String bankCode;
	private String bankAcct;
	private String serviceType;
	private String intro;
	private Double priceHour;
	private Double priceHalfday;
	private Double priceDay;
	private Integer cancelCount;
	private String carerStatus;
	private Timestamp createTime; // currentTimeStamp
	private Timestamp updateTime; 
	private String canSearch; // 履歷開啟狀態
	private String searchName; //搜尋顯示例如陳小姐
	private String searchGender; //搜尋顯示例如陳小姐
	private String searchDist; //搜尋顯示服務地區
	private String searchCounty; //搜尋顯示服務地區
	private String phoneNumber; //後台管理:電話
	private String emailAccount; //後台管理:email
	private String searchAge; //後台管理:AGE
	private String searchAddr; //後台管理:addr
	private String name; //串接至刊登需求單
	
	private String bankName; //以下26
	private String carerPwd; // memPwd
	// 會員資料
	private String memName;
	private String memGender;
	private Integer memAge;
	private String memPhone;
	private String memEmail;
	

	public CarerMemVO() {
		super();
	}

	public Integer getCarerID() {
		return carerID;
	}

	public void setCarerID(Integer carerID) {
		this.carerID = carerID;
	}

	public String getCarerAcct() {
		return carerAcct;
	}

	public void setCarerAcct(String carerAcct) {
		this.carerAcct = carerAcct;
	}

	public String getCanSearch() {
		return canSearch;
	}

	public void setCanSearch(String canSearch) {
		this.canSearch = canSearch;
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

	public Integer getCancelCount() {
		return cancelCount;
	}

	public void setCancelCount(Integer cancelCount) {
		this.cancelCount = cancelCount;
	}

	public String getCarerStatus() {
		return carerStatus;
	}

	public void setCarerStatus(String carerStatus) {
		this.carerStatus = carerStatus;
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchDist() {
		return searchDist;
	}

	public void setSearchDist(String searchDist) {
		this.searchDist = searchDist;
	}

	public String getSearchCounty() {
		return searchCounty;
	}

	public void setSearchCounty(String searchCounty) {
		this.searchCounty = searchCounty;
	}

	public String getSearchGender() {
		return searchGender;
	}

	public void setSearchGender(String searchGender) {
		this.searchGender = searchGender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAccount() {
		return emailAccount;
	}

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	public String getSearchAge() {
		return searchAge;
	}

	public void setSearchAge(String searchAge) {
		this.searchAge = searchAge;
	}

	public String getSearchAddr() {
		return searchAddr;
	}

	public void setSearchAddr(String searchAddr) {
		this.searchAddr = searchAddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCarerPwd() {
		return carerPwd;
	}

	public void setCarerPwd(String carerPwd) {
		this.carerPwd = carerPwd;
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

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	
	
}
