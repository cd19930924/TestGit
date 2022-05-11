package com.careorder.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careapply.model.*;
import com.carerequest.model.*;

@Service
public class CareOrderService {

	@Autowired
	private CareOrderDAO dao;

//	public CareOrderService() {
//		dao = new CareOrderDAOImpl();
//	}

	// 取得訂單(訂單ID)
	public CareOrderSVO getOrder(Integer id) {
		return dao.select(id);
	}

	// 取得訂單列表(後台用)
	public List<CareOrderPVO> getOrderList() {
		return dao.selectAll();
	}

	// 取得訂單列表(會員ID)
	public List<CareOrderPVO> getOrderList(Integer id) {
		return dao.selectAll(id);
	}

	// 取得訂單列表(照護員ID)
	public List<CareOrderPVO> selectAll2(Integer id) {
		return dao.selectAll2(id);
	}

	// 成立訂單
	public int establishOrder(Integer requestId, Integer carerId, Double amount) {
		CareApplyDAO applyDAO = new CareApplyDAOImpl();
		CareRequestDAO requestDAO = new CareRequestDAOImpl();
		CareOrderVO vo = new CareOrderVO();

		vo.setRequestId(requestId);
		vo.setCarerId(carerId);
		vo.setAmount(amount);

		int id = dao.insert(vo);

		if (id != 0) { // 回傳0代表訂單新增失敗，就不汙染其它表了
			applyDAO.updateAllStatus(requestId, "0");
			applyDAO.updateStatus(requestId, carerId, "2");
			requestDAO.updateStatus(requestId, "1");
		}

		return id;
	}

	// 填寫回饋(訂單ID, 回饋)
	public void fillInFeedback(Integer id, String feedback) {
		dao.updateFeedback(id, feedback);
		dao.updateStatus(id, "4");
	}

	// 取消訂單(訂單ID)
	public void cancelOrder(Integer id) {
		dao.updateStatus(id, "0");
	}
	//後臺強制關單使用
	public void updateOrderStatus(Integer id, String status) {
		dao.updateOrderStatus(id, status);
	}

}
