package com.servicetab.model;

import java.io.Serializable;

public class ServiceTabVO implements Serializable {
	private String serviceTabNo;
	private String serviceTabName;
	private String serviceNo;

	public String getServiceTabNo() {
		return serviceTabNo;
	}

	public void setServiceTabNo(String serviceTabNo) {
		this.serviceTabNo = serviceTabNo;
	}

	public String getServiceTabName() {
		return serviceTabName;
	}

	public void setServiceTabName(String serviceTabName) {
		this.serviceTabName = serviceTabName;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	@Override
	public String toString() {
		return "ServiceTabVO [serviceTabNo=" + serviceTabNo + ", serviceTabName=" + serviceTabName + ", serviceNo="
				+ serviceNo + "]";
	}

}
