package com.skill.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SkillService {
	@Autowired
	private SkillDAO sdao;

//	public SkillService() {
//		sdao = new SkillDAOImpl();
//	}

	// (carerID) 修改照護員資料前顯示已會的技能
	public List<SkillVO> getOneCarerSkills(Integer carerID) {
		return sdao.getOneCarerSkills(carerID);
	}
}
