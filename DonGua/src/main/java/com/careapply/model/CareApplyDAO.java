package com.careapply.model;

import java.util.List;

public interface CareApplyDAO {
	
	// (需求單ID)
	List<CareApplyPVO> selectAll(Integer id);
	
	// (需求單ID, 照護員ID, 狀態代碼)
	void updateStatus(Integer requestId, Integer carerId, String type);
	
	// (需求單ID, 狀態代碼)
	void updateAllStatus(Integer id, String type);
	
	public void insert(CareApplyVO careApplyVO);

	void update(Integer requestId, Integer carerId);
}
