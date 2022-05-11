package com.careskills.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareSkillsService {

	@Autowired
	private CareSkillsDAO csdao;
	
//	public CareSkillsService () {
//		csdao = new CarerSkillsDAOImpl();
//	}
	
	// 刪除單一照護員所有技能
	public void deleteCarerSkills(Integer carerID) {
		csdao.deleteCarerSkills(carerID);
	}
}
