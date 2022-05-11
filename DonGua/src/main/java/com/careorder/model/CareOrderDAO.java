package com.careorder.model;

import java.util.List;

public interface CareOrderDAO {
	
	// (訂單ID)
	CareOrderSVO select(Integer id);

	// (無參數)
	List<CareOrderPVO> selectAll();
	
	// (會員ID)
	List<CareOrderPVO> selectAll(Integer id);

	// (照護員ID)
	List<CareOrderPVO> selectAll2(Integer id);
	
	// (return 訂單ID)
	int insert(CareOrderVO vo);

	// (訂單ID, 狀態代碼)
	void updateStatus(Integer id, String type);

	// (訂單ID, 回饋)
	void updateFeedback(Integer id, String feedback);

	//後臺強制關單使用
		public void updateOrderStatus(Integer id, String status);
}
