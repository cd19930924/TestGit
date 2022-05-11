package com.careordermgt.model;

import java.util.List;

import com.careapply.model.CareApplyVO;

public class CareApplyMgtService {

	private CareApplyMgtDAO camdao;

	public CareApplyMgtService() {
		camdao = new CareApplyMgtDAOImpl();
	}
	
	// 取得所有資料列表 應徵訂單
	public List<CareApplyMgtVO> getAll(Integer carerID) {
		return camdao.getAll(carerID);
	}
	
	// 查詢單一需求單
	public CareApplyMgtVO getByReqId(Integer requestID) {
		return camdao.getByReqId(requestID);
	}
}
