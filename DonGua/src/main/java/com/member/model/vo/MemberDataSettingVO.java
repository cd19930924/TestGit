package com.member.model.vo;

import java.util.Date;

public class MemberDataSettingVO {
	private String action;
	private String memAcct;
	private String memPwd;
	private String memName;
	private String memPhone;
	private String distNo;
	private String memAddr;
	private String countyNo;
	private String memGender;
	private String memEmail;
	private String memBirth;
	private Date memBirthDate;
	private Integer memAge;
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
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getDistNo() {
		return distNo;
	}
	public void setDistNo(String distNo) {
		this.distNo = distNo;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(String string) {
		this.memBirth = string;
	}
	public Integer getMemAge() {
		return memAge;
	}
	public void setMemAge(Integer memAge) {
		this.memAge = memAge;
	}
	public String getCountyNo() {
		return countyNo;
	}
	public void setCountyNo(String countyNo) {
		this.countyNo = countyNo;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public Date getMemBirthDate() {
		return memBirthDate;
	}
	public void setMemBirthDate(Date memBirthDate) {
		this.memBirthDate = memBirthDate;
	}


}
