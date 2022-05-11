package com.carerequest.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careapply.model.*;

import com.requesttab.model.*;

@Service
public class CareRequestService {
	
	@Autowired
	private CareRequestDAO dao;

//	public CareRequestService() {
//		dao = new CareRequestDAOImpl();
//	}

	// 取得需求單(需求單ID)
	public CareRequestVO getRequest(Integer id) {
		return dao.select(id);
	}

	// 取得需求單列表(後台用)
	// TODO
//	public List<CareRequestPVO> getRequestList() {
//		CareApplyDAO applyDAO = new CareApplyDAOImpl();
//		List<CareRequestPVO> list = dao.selectAll();
//
//		for (CareRequestPVO pvo : list) {
//			List<CareApplyPVO> applyList = applyDAO.selectAll(pvo.getRequestId());
//
//			for (CareApplyPVO applyPVO : applyList) {
//				applyPVO.setCost(priceCal(pvo.getStartTime(), pvo.getEndTime(), applyPVO.getPriceHour(),
//						applyPVO.getPriceHalfday(), applyPVO.getPriceDay()));
//			}
//
//			pvo.setCareApplyList(applyList);
//		}
//
//		return list;
//	}

	// 取得需求單列表(會員ID)
	public List<CareRequestPVO> getRequestList(Integer id) {
		CareApplyDAO applyDAO = new CareApplyDAOImpl();
		List<CareRequestPVO> list = dao.selectAll(id);

		for (CareRequestPVO pvo : list) {
			List<CareApplyPVO> applyList = applyDAO.selectAll(pvo.getRequestId());

			for (CareApplyPVO applyPVO : applyList) {
				applyPVO.setCost(priceCal(pvo.getStartTime(), pvo.getEndTime(), applyPVO.getPriceHour(),
						applyPVO.getPriceHalfday(), applyPVO.getPriceDay()));
			}

			pvo.setCareApplyList(applyList);
		}

		return list;
	}

	// 刊登需求單
	public int postRequest(Integer memId, String patientName, String patientGender, Integer patientAge,
			String serviceType, String patientAddr, Timestamp startTime, Timestamp endTime, String note,
			String[] requestTabs) {
		RequestTabDAO tabDAO = new RequestTabDAOImpl();
		CareRequestVO vo = new CareRequestVO();
		RequestTabVO tabVO = new RequestTabVO();

		vo.setMemId(memId);
		vo.setPatientName(patientName);
		vo.setPatientGender(patientGender);
		vo.setPatientAge(patientAge);
		vo.setServiceType(serviceType);
		vo.setPatientAddr(patientAddr);
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setNote(note);

		int id = dao.insert(vo);

		if (id != 0) { // DAO回傳0代表需求單新增失敗
			for (String tab : requestTabs) {
				tabVO.setRequestId(id);
				tabVO.setServiceTabNo(tab);

				tabDAO.insert(tabVO);
			}
		}

		return id;
	}

	// 取消需求單(需求單ID)
	public void cancelRequest(Integer id) {
		CareApplyDAO applyDAO = new CareApplyDAOImpl();

		dao.updateStatus(id, "0");
		applyDAO.updateAllStatus(id, "0");
	}

	// 編輯需求單
	public void editRequest(Integer requestId, String serviceType, String patientAddr, Timestamp startTime,
			Timestamp endTime, String note, String[] requestTabs) {
		CareApplyDAO applyDAO = new CareApplyDAOImpl();
		RequestTabDAO tabDAO = new RequestTabDAOImpl();
		CareRequestVO vo = new CareRequestVO();
		RequestTabVO tabVO = new RequestTabVO();

		vo.setRequestId(requestId);
		vo.setServiceType(serviceType);
		vo.setPatientAddr(patientAddr);
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		vo.setNote(note);

		dao.update(vo);
		applyDAO.updateAllStatus(requestId, "0");
		tabDAO.deleteAll(requestId);

		for (String tab : requestTabs) {
			tabVO.setRequestId(requestId);
			tabVO.setServiceTabNo(tab);

			tabDAO.insert(tabVO);
		}
	}

	public List<CareRequestVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public CareRequestVO getOneCareRequest(Integer requestId) {
		return dao.findByRequestId(requestId);
	}

	public List<CareRequestMVO> getAll() {

		return dao.selectAll();
	}

	public List<CareApplyVO> getAppliers(Integer requestId) {
		return dao.findAppliersByRequestId(requestId);
	}

	public List<CareApplyVO> getAppliers() {
		return dao.findAllAppliers();
	}

	public String getAllAppliers() {

		List<CareApplyVO> list = dao.findAllAppliers();
		String result = "<table><tr><th>需求單ID</th><th>應徵照護員ID</th><th>應徵時間</th><th>狀態</th></tr>";
		for (CareApplyVO vo : list) {
			result += "<tr><td>" + vo.getRequestId() + "</td>";
			result += "<td>" + vo.getCarerId() + "</td>";
			result += "<td>" + vo.getApplyTime() + "</td>";
			result += "<td>" + vo.getStatus() + "</td></tr>";
		}
		return result += "</table>";
	}

	public Integer getAppliersNum(Integer requestId) {
		return dao.numberOfAppliers(requestId);
	}

// 查詢明細方法A
	public RequestTabVO getTabsStr(Integer requestId) {
		return dao.findTabsStrByRequestId(requestId);
	}

// 查詢明細方法B
	public String getTabs(Integer requestId) {
		String result = dao.findTabsStrInStringTypeByRequestId(requestId);
		if (result.equals("")) {
			result = "尚未填寫";
		}
		return result;
	}

// 查詢明細方法C
	public List<RequestTabVO> getRequestTabs(Integer requestId) {
		return dao.findTabsByRequestId(requestId);
	}

// 查詢明細用D:判斷空值 -> jsp forEach失敗
	public List<String> getTabsOfService(Integer requestId, String serviceNo) {
		List<RequestTabVO> result = dao.findTabsOfService(requestId, serviceNo);
		List<String> finalresult = new ArrayList<>(result.size());
		for (RequestTabVO vo : result) {
			finalresult.add(vo != null ? vo.toString() : null);
		}

		if (finalresult.isEmpty()) {
			String str = "";
			finalresult.add(str);
			return finalresult;
		}

		return finalresult;
	}

// display on jsp
	public String displayTabsOfService(Integer requestId, String serviceNo) {
		List<RequestTabVO> list = dao.findTabsOfService(requestId, serviceNo);
		String result = "<span style=\"display:none;\"></span>";
		for (RequestTabVO vo : list) {
			result += "<ul><li>" + vo.getServiceTabNo() + "</li></ul>";
		}
		return result;
	}

	// 查詢如果沒有該服務標籤則不會顯示該服務大項
	public String isNormalServiceExisted(Integer requestId) {
		List<RequestTabVO> normalService = dao.findTabsOfService(requestId, "01");
		List<String> normalServiceResult = new ArrayList<>(normalService.size());
		for (RequestTabVO vo : normalService) {
			normalServiceResult.add(vo != null ? vo.toString() : null);
		}

		if (!normalServiceResult.isEmpty()) {
			return "<b> 【一般照護服務】</b><br>";
		} else {
			return "<span style=\"display:none;\"></span>";
		}

	}

	// 查詢如果沒有該服務標籤則不會顯示該服務大項
	public String isCertiServiceExisted(Integer requestId) {
		List<RequestTabVO> certiService = dao.findTabsOfService(requestId, "02");
		List<String> certiServiceResult = new ArrayList<>(certiService.size());
		for (RequestTabVO vo : certiService) {
			certiServiceResult.add(vo != null ? vo.toString() : null);
		}

		if (!certiServiceResult.isEmpty()) {
			return "<b>【進階照護服務】</b><br>";
		} else {
			return "<span style=\"display:none;\"></span>";
		}
	}

	// 查詢是否應徵過 回傳已應徵字串
	public String hasAppliedStr(Integer carerId, Integer requestId) {
		Integer check = dao.hasApplied(carerId, requestId);
		if (check == 1) {
			return "您已應徵過此張需求單，等待會員審核中";
		} else {
			return "您尚未應徵";
		}
	}

	// 查詢是否應徵過 在還沒審核之下不可再應徵
	public Boolean hasApplied(Integer carerId, Integer requestId) {
		Integer check = dao.hasApplied(carerId, requestId);
		if (check == 1) {
			return true;
		} else {
			return false;
		}
	}

	// 查詢是否應徵但被拒絕
	public Boolean beRejected(Integer carerId, Integer requestId) {
		Integer check = dao.beRejected(carerId, requestId);
		if (check == 1) {
			return true;
		} else {
			return false;
		}
	}

	// 後台強制關單使用
	public CareRequestVO updateRequestStatus(String status, Timestamp time, Integer requestId) {
		CareRequestVO vo = new CareRequestVO();

		vo.setStatus(status);
		vo.setUpdateTime(time);
		vo.setRequestId(requestId);
		dao.updateRequestStatus(vo);
		return vo;
	}

	private static Double priceCal(Timestamp tStart, Timestamp tEnd, Double pHour, Double pHalf, Double pDay) {
		long ms = tEnd.getTime() - tStart.getTime();
		long hour = (ms / (1000 * 60 * 60));
		long min = (ms % (1000 * 60 * 60)) / (1000 * 60);
		double cost;

		if (min > 5) {
			hour++;
			min = 0;
		}

		if (hour >= 24) {
			cost = (pHour + pHalf) * 12 + pDay * (hour - 24);
		} else if (hour >= 12) {
			cost = pHour * 12 + pHalf * (hour - 12);
		} else {
			cost = pHour * hour;
		}

		return cost;
	}

}
