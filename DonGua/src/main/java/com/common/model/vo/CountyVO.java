package com.common.model.vo;

import java.util.Map;

public class CountyVO {
	private String ctyNo;
	private String ctyName;
	private Map<String, String> distData;
	public String getCtyNo() {
		return ctyNo;
	}
	public void setCtyNo(String ctyNo) {
		this.ctyNo = ctyNo;
	}
	public String getCtyName() {
		return ctyName;
	}
	public void setCtyName(String ctyName) {
		this.ctyName = ctyName;
	}
	public Map<String, String> getDistData() {
		return distData;
	}
	public void setDistData(Map<String, String> distData) {
		this.distData = distData;
	} 
}
