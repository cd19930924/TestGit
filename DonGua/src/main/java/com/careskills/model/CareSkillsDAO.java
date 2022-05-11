package com.careskills.model;

import java.util.List;

public interface CareSkillsDAO {

	// 新增申請者的技能資料
	public void insertApplySkill(CareSkillsVO carerSkillsVo);
	
	// (carerID) 刪除照護員技能
	public void deleteCarerSkills(Integer carerID);
}
