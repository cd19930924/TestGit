package com.careordermgt.model;

import java.io.Serializable;
import java.sql.Timestamp;

// 照護訂單專用VO
public class CareApplyMgtVO implements Serializable {

	private static final long serialVersionUID = 5797917434185674113L;

	// 照護應徵Table
	private Integer requestId;
	private Integer carerId;
	private String status;
	private Timestamp applyTime;

	// 照護需求單Table
//	private Integer requestId;
	private Integer memId;
	private String patientName;
	private String patientGender;
	private Integer patientAge;
	private String patientAddr;
	private Timestamp startTime;
	private Timestamp endTime;
	private String serviceType;
	private String note;
//	private String status;
	private Timestamp createTime;
	private Timestamp updateTime;

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getCarerId() {
		return carerId;
	}

	public void setCarerId(Integer carerId) {
		this.carerId = carerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public Integer getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientAddr() {
		return patientAddr;
	}

	public void setPatientAddr(String patientAddr) {
		this.patientAddr = patientAddr;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp creatTime) {
		this.createTime = creatTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "CareApplyMgtVO [requestId=" + requestId + ", carerId=" + carerId + ", status=" + status + ", applyTime="
				+ applyTime + ", memId=" + memId + ", patientName=" + patientName + ", patientGender=" + patientGender
				+ ", patientAge=" + patientAge + ", patientAddr=" + patientAddr + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", serviceType=" + serviceType + ", note=" + note + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}

	
}
