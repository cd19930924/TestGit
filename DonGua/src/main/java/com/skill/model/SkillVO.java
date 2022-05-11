package com.skill.model;

import java.io.Serializable;

public class SkillVO implements Serializable {

	private static final long serialVersionUID = 1761802715655521502L;

	// 申請成為照護員頁面顯示之技能
	private String skillNo;
	private String skillName;
	private String skillType;
	private Integer carerID; // memID

	public SkillVO() {
		super();
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

	public Integer getCarerID() {
		return carerID;
	}

	public void setCarerID(Integer carerID) {
		this.carerID = carerID;
	}

}
