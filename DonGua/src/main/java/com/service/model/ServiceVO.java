package com.service.model;

import java.io.Serializable;

public class ServiceVO implements Serializable {
	private String serviceNo;
	private String serviceName;

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "ServiceVO [serviceNo=" + serviceNo + ", serviceName=" + serviceName + "]";
	}

}
