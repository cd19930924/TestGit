package com.careorder.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CareOrderVO implements Serializable {
	private Integer careOrderId;
	private Integer requestId;
	private Integer carerId;
	private Double amount;
	private String careFeedback;
	private String status;
	private Timestamp creatTime;
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

	public Timestamp getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "CareOrderVO [careOrderId=" + careOrderId + ", requestId=" + requestId + ", carerId=" + carerId
				+ ", amount=" + amount + ", careFeedback=" + careFeedback + ", status=" + status + ", creatTime="
				+ creatTime + ", updateTime=" + updateTime + "]";
	}

}
