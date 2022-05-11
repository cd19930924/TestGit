package com.careordermgt.model;

import java.util.List;

public interface CareApplyMgtDAO {

	// (照護員ID) 查詢每筆應徵單所有資料
	List<CareApplyMgtVO> getAll(Integer carerID);
	
	// (需求單ID) 查詢單一需求單
	public CareApplyMgtVO getByReqId(Integer requestID);
}
