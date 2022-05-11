package com.carerequest.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.careapply.model.CareApplyVO;

public class CareRequestMVO implements Serializable {
	private Integer requestId;
	private Integer memId;
	private String patientName;
	private String patientGender;
	private Integer patientAge;
	private String patientAddr;
	private String shortPatientAddr;
	private Timestamp startTime;
	private Timestamp endTime;
	private String shortStartTime;
	private String shortEndTime;
	private String serviceType;
	private String note;
	private String status;
	private Timestamp createTime;
	private Timestamp updateTime;
	private List<CareApplyVO> appliers;
	
	
	public String getShortPatientAddr() {
		return shortPatientAddr;
	}
	public void setShortPatientAddr(String shortPatientAddr) {
		this.shortPatientAddr = shortPatientAddr;
	}

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
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
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
	public List<CareApplyVO> getAppliers() {
		return appliers;
	}
	public void setAppliers(List<CareApplyVO> appliers) {
		this.appliers = appliers;
	}



}
