package com.carermemapplymgt.model;

import java.util.List;

public interface CarerMemApplyMgtDAO {

	// 查詢每筆應徵資料
	List<CarerMemApplyMgtVO> getAllApplyData();

	// (ApplyID) 查詢一筆應徵單
	public CarerMemApplyMgtVO getOneApplyData(Integer applyID);
	
	// (ApplyID) 審核失敗 更新狀態
	public void updateFailStatus(Integer applyID);
	
	// (ApplyID) 審核成功 新增資料至照護會員
	public void insertCarerMem(Integer applyID);
	
	// (ApplyID) 審核成功 更改申請單狀態
	public void updateSuccessStatus(Integer applyID);
	
}
