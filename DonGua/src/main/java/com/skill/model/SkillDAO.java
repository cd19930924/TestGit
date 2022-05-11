package com.skill.model;

import java.util.List;

import com.careskills.model.CareSkillsVO;

public interface SkillDAO {

	// 查詢申請成為照護員頁面顯示之技能
	List<SkillVO> getAll();

	// 查詢單一照護員技能資料
	List<SkillVO> getOneCarerSkills(Integer carerID);
}
