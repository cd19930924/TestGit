package com.careorder.model;

import java.sql.Timestamp;

public class CareOrderPVO {
	private Integer careOrderId;
	private String patientName;
	private Timestamp startTime;
	private String shortStartTime;
	private Timestamp endTime;
	private String shortEndTime;
	private String patientAddr;
	private String shortPatientAddr;
	private Double amount;
	private Integer carerId;
	private String carerName;
	private String status;
	private String careFeedback;
	private String statusString;
	private Integer memId;

	public String getShortStartTime() {
		return shortStartTime;
	}

	public void setShortStartTime(String shortStartTime) {
		this.shortStartTime = shortStartTime;
	}

	public String getShortEndTime() {
		return shortEndTime;
	}

	public void setShortEndTime(String shortEndTime) {
		this.shortEndTime = shortEndTime;
	}

	public String getShortPatientAddr() {
		return shortPatientAddr;
	}

	public void setShortPatientAddr(String shortPatientAddr) {
		this.shortPatientAddr = shortPatientAddr;
	}

	public Integer getCareOrderId() {
		return careOrderId;
	}

	public void setCareOrderId(Integer careOrderId) {
		this.careOrderId = careOrderId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getPatientAddr() {
		return patientAddr;
	}

	public void setPatientAddr(String patientAddr) {
		this.patientAddr = patientAddr;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getCarerId() {
		return carerId;
	}

	public void setCarerId(Integer carerId) {
		this.carerId = carerId;
	}

	public String getCarerName() {
		return carerName;
	}

	public void setCarerName(String carerName) {
		this.carerName = carerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCareFeedback() {
		return careFeedback;
	}

	public void setCareFeedback(String careFeedback) {
		this.careFeedback = careFeedback;
	}
	
	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	@Override
	public String toString() {
		return "CareOrderPVO [careOrderId=" + careOrderId + ", patientName=" + patientName + ", startTime=" + startTime
				+ ", shortStartTime=" + shortStartTime + ", endTime=" + endTime + ", shortEndTime=" + shortEndTime
				+ ", patientAddr=" + patientAddr + ", shortPatientAddr=" + shortPatientAddr + ", amount=" + amount
				+ ", carerId=" + carerId + ", carerName=" + carerName + ", status=" + status + ", careFeedback="
				+ careFeedback + "]";
	}

}