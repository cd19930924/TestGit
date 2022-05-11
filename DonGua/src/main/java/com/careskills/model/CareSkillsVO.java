package com.careskills.model;

import java.io.Serializable;

public class CareSkillsVO implements Serializable{

	private static final long serialVersionUID = 7746096779146134692L;

	// 照護技能
	private Integer carerID; // memID
	private String skillNo;
	private String skillName;
	private String skillType;
	
	public CareSkillsVO() {
		super();
	}

	public Integer getCarerID() {
		return carerID;
	}

	public void setCarerID(Integer carerID) {
		this.carerID = carerID;
	}

	public String getSkillNo() {
		return skillNo;
	}

	public void setSkillNo(String skillNo) {
		this.skillNo = skillNo;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

}
