package com.requesttab.model;

import java.io.Serializable;

public class RequestTabVO implements Serializable {
	private Integer requestId;
	private String serviceTabNo;
	private String serviceNo;

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getServiceTabNo() {
		return serviceTabNo;
	}

	public void setServiceTabNo(String serviceTabNo) {
		this.serviceTabNo = serviceTabNo;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	@Override
	public String toString() {
		return "RequestTabVO [requestId=" + requestId + ", serviceTabNo=" + serviceTabNo + "]";
	}

}
