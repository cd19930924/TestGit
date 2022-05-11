package com.collection.model;

import java.sql.Timestamp;

public class CollectionVO {
	private Integer memId;
	private Integer carerId;
	private String carerPhoto;
	private String carerSurname;
	private String carerGender;
	private String carerCounty;
	private String carerDist;
	private Timestamp collTime;
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getCarerId() {
		return carerId;
	}
	public void setCarerId(Integer carerId) {
		this.carerId = carerId;
	}
	public String getCarerPhoto() {
		return carerPhoto;
	}
	public void setCarerPhoto(String carerPhoto) {
		this.carerPhoto = carerPhoto;
	}
	public String getCarerSurname() {
		return carerSurname;
	}
	public void setCarerSurname(String carerSurname) {
		this.carerSurname = carerSurname;
	}
	public String getCarerGender() {
		return carerGender;
	}
	public void setCarerGender(String carerGender) {
		this.carerGender = carerGender;
	}
	public String getCarerCounty() {
		return carerCounty;
	}
	public void setCarerCounty(String carerCounty) {
		this.carerCounty = carerCounty;
	}
	public String getCarerDist() {
		return carerDist;
	}
	public void setCarerDist(String carerDist) {
		this.carerDist = carerDist;
	}
	public Timestamp getCollTime() {
		return collTime;
	}
	public void setCollTime(Timestamp collTime) {
		this.collTime = collTime;
	}
	
	
	
}
