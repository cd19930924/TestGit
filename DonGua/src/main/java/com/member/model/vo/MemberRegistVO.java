package com.member.model.vo;

import java.util.Date;

public class MemberRegistVO {
	private String memAcct;
	private String memPwd;
	private String memName;
	private String memPhone;
	private String distNo;
	private String memAddr;
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
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public Integer getMemAge() {
		return memAge;
	}
	public void setMemAge(Integer memAge) {
		this.memAge = memAge;
	}
	private String memGender;
	private String memEmail;
	private Date memBirth;
	private Integer memAge;
}
