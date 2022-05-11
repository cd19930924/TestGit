package com.carerequest.model;

import java.util.List;
import java.util.Map;

import com.careapply.model.CareApplyVO;
import com.requesttab.model.RequestTabVO;

public interface CareRequestDAO {

	// (需求單ID)
	CareRequestVO select(Integer id);

	// (無參數)
//	List<CareRequestPVO> selectAll();

	// (會員ID)
	List<CareRequestPVO> selectAll(Integer id);

	// (return 需求單ID)
	int insert(CareRequestVO vo);

	void update(CareRequestVO vo);

	// (需求單ID, 狀態代碼)
	void updateStatus(Integer id, String type);

	// search request with conditions
	public List<CareRequestVO> getAll(Map<String, String[]> map);

	public List<CareRequestMVO> selectAll();

	/********* 測試查詢方法區***START ******/

	// 查詢明細(字串串接)方法A
	public RequestTabVO findTabsStrByRequestId(Integer requestId);

	// 查詢明細(字串串接)方法B
	public String findTabsStrInStringTypeByRequestId(Integer requestId);

	// 查詢明細用C
	public List<RequestTabVO> findTabsByRequestId(Integer requestId);

	// 查詢明細用D
	public List<RequestTabVO> findTabsOfService(Integer requestId, String serviceNo);
	// 查詢明細用E
//	public List<String> test(Integer requestId, String serviceNo);

	/********* 測試查詢方法區***END ******/

	public CareRequestVO findByRequestId(Integer requestId);

	// 查看應徵明細(後台)
	public List<CareApplyVO> findAppliersByRequestId(Integer requestId);

	public List<CareApplyVO> findAllAppliers();

	public Boolean isApplied(Integer requestId);

	public CareRequestMVO selectAllIncludeAppliers();

	// 應徵人數
	public Integer numberOfAppliers(Integer requestId);

	//確認該照護員是否應徵過此張需求單
	public Integer hasApplied(Integer carerId, Integer requestId);
	
	//確認該照護員是否應徵過但被拒絕
	public Integer beRejected(Integer carerId, Integer requestId);

	//後台強制關單使用
	public void updateRequestStatus(CareRequestVO careRequestVO);
}
