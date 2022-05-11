package com.careorder.model;

import java.sql.Timestamp;

public class CareOrderSVO {
	private Integer careOrderId;
	private Integer requestId;
	private Integer carerId;
	private String patientName;
	private String patientGender;
	private Integer patientAge;
	private String patientAddr;
	private Timestamp startTime;
	private Timestamp endTime;
	private String serviceType;
	private String note;
	private Double amount;
	private String careFeedback;
	private String status;
	private Timestamp createTime;
	private Timestamp updateTime;

	public Integer getCareOrderId() {
		return careOrderId;
	}

	public void setCareOrderId(Integer careOrderId) {
		this.careOrderId = careOrderId;
	}

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCareFeedback() {
		return careFeedback;
	}

	public void setCareFeedback(String careFeedback) {
		this.careFeedback = careFeedback;
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

	@Override
	public String toString() {
		return "CareOrderSVO [careOrderId=" + careOrderId + ", requestId=" + requestId + ", carerId=" + carerId
				+ ", patientName=" + patientName + ", patientGender=" + patientGender + ", patientAge=" + patientAge
				+ ", patientAddr=" + patientAddr + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", serviceType=" + serviceType + ", note=" + note + ", amount=" + amount + ", careFeedback="
				+ careFeedback + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
