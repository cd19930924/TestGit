package com.membermailbox.model;

import java.sql.Timestamp;

public class MemberMailBoxVO {
	private Integer mailId;
	private Integer sendMemId;
	private Integer receiveMemId;
	private String status;
	private String msgContent;
	private String msgTitle;
	private Timestamp sendTime;
	private String memName;
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	private String memberId;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemBerName() {
		return memBerName;
	}
	public void setMemBerName(String memBerName) {
		this.memBerName = memBerName;
	}
	private String memBerName;
	
	public Integer getMailId() {
		return mailId;
	}
	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}
	public Integer getSendMemId() {
		return sendMemId;
	}
	public void setSendMemId(Integer sendMemId) {
		this.sendMemId = sendMemId;
	}
	public Integer getReceiveMemId() {
		return receiveMemId;
	}
	public void setReceiveMemId(Integer receiveMemId) {
		this.receiveMemId = receiveMemId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp timestamp) {
		this.sendTime = timestamp;
	}
}
