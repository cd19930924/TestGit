package com.carermemapplymgt.model;

import java.util.List;

public class CarerMemApplyMgtService {

	private CarerMemApplyMgtDAO cmamdao;

	public CarerMemApplyMgtService() {
		cmamdao = new CarerMemApplyMgtDAOImpl();
	}

	// 查詢每筆應徵資料
	public List<CarerMemApplyMgtVO> getAllApplyData() {
		return cmamdao.getAllApplyData();
	}

	// (ApplyID) 查詢一筆應徵詳細資料
	public CarerMemApplyMgtVO getOneApplyData(Integer applyID) {
		return cmamdao.getOneApplyData(applyID);
	}

	// (ApplyID) 審核失敗 更新狀態
	public void updateFailStatus(Integer applyID) {
		cmamdao.updateFailStatus(applyID);
	}
	
	// (ApplyID) 審核成功 新增資料至照護會員
	public void insertCarerMem(Integer applyID) {
		cmamdao.insertCarerMem(applyID);
	}
	
	// (ApplyID) 審核成功 更改申請單狀態
	public void updateSuccessStatus(Integer applyID) {
		cmamdao.updateSuccessStatus(applyID);
	}

}
