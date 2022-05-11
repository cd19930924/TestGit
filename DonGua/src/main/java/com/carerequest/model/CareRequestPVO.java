package com.carerequest.model;

import java.sql.Timestamp;
import java.util.List;

import com.careapply.model.CareApplyPVO;

public class CareRequestPVO {
	private Integer requestId;
	private String patientName;
	private Timestamp startTime;
	private Timestamp endTime;
	private String status;
	private List<CareApplyPVO> careApplyList;

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CareApplyPVO> getCareApplyList() {
		return careApplyList;
	}

	public void setCareApplyList(List<CareApplyPVO> careApplyList) {
		this.careApplyList = careApplyList;
	}

	@Override
	public String toString() {
		return "CareRequestPVO [requestId=" + requestId + ", patientName=" + patientName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", status=" + status + ", careApplyList=" + careApplyList + "]";
	}

}
