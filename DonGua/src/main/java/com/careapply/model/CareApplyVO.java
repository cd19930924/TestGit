package com.careapply.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CareApplyVO implements Serializable {
	private Integer requestId;
	private Integer carerId;
	private Timestamp applyTime;
	private String status;

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

	public Timestamp getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CareApplyVO [requestId=" + requestId + ", carerId=" + carerId + ", applyTime=" + applyTime + ", status="
				+ status + "]";
	}

}
