package com.systemnotification.model;

import java.sql.Timestamp;

public class SystemNotificationPVO {
	private Integer notNo;
	private Integer memId;
	private String notTypeNo;
	private Timestamp notTime;
	private Integer orderId;
	private String notTypeName;
	private String notTextcontent;
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getNotNo() {
		return notNo;
	}

	public void setNotNo(Integer notNo) {
		this.notNo = notNo;
	}

	public Integer getMemId() {
		return memId;
	}

	public void setMemId(Integer memId) {
		this.memId = memId;
	}

	public String getNotTypeNo() {
		return notTypeNo;
	}

	public void setNotTypeNo(String notTypeNo) {
		this.notTypeNo = notTypeNo;
	}

	public Timestamp getNotTime() {
		return notTime;
	}

	public void setNotTime(Timestamp notTime) {
		this.notTime = notTime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getNotTypeName() {
		return notTypeName;
	}

	public void setNotTypeName(String notTypeName) {
		this.notTypeName = notTypeName;
	}

	public String getNotTextcontent() {
		return notTextcontent;
	}

	public void setNotTextcontent(String notTextcontent) {
		this.notTextcontent = notTextcontent;
	}

	@Override
	public String toString() {
		return "SystemNotificationPVO [notNo=" + notNo + ", memId=" + memId + ", notTypeNo=" + notTypeNo + ", notTime="
				+ notTime + ", orderId=" + orderId + ", notTypeName=" + notTypeName + ", notTextcontent="
				+ notTextcontent + "]";
	}

}
