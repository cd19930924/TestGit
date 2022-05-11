package com.infomanage.model;

import java.sql.Timestamp;

public class InfoVO {

	private String no;
	private String name;
	private String shortName;
	private String content;
	private String shortContent;
	private Timestamp createTime;
	private String shortCreateTime;
	private Timestamp updateTime;
	private String shortUpdateTime;
	private String status;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getShortContent() {
		return shortContent;
	}
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getShortCreateTime() {
		return shortCreateTime;
	}
	public void setShortCreateTime(String shortCreateTime) {
		this.shortCreateTime = shortCreateTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getShortUpdateTime() {
		return shortUpdateTime;
	}
	public void setShortUpdateTime(String shortUpdateTime) {
		this.shortUpdateTime = shortUpdateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}



}
