package com.careapply.model;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareApplyService {

	@Autowired
	private CareApplyDAO dao;

//	public CareApplyService() {
//		dao = new CareApplyDAOImpl();
//	}

	// 取消所有應徵(需求單ID)
	public void refuseAllApply(Integer id) {
		dao.updateAllStatus(id, "0");
	}

	// 取消應徵(需求單ID, 照護員ID)
	public void refuseApply(Integer requestId, Integer carerId) {
		dao.updateStatus(requestId, carerId, "0");
	}

	public CareApplyVO applyToRequest(Integer requestId, Integer carerId, String status, Timestamp applyTime) {
		CareApplyVO careApplyVO = new CareApplyVO();

		careApplyVO.setRequestId(requestId);
		careApplyVO.setCarerId(carerId);
		careApplyVO.setStatus(status);
		careApplyVO.setApplyTime(applyTime);
		dao.insert(careApplyVO);

		return careApplyVO;

	}
	public CareApplyVO updateApply(Integer requestId, Integer carerId) {
		  CareApplyVO vo = new CareApplyVO();
		  vo.setRequestId(requestId);
		  vo.setCarerId(carerId);
		  dao.update(requestId, carerId);
		  return vo;
		 }

}
