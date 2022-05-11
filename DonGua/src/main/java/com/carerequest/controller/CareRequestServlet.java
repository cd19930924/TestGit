package com.carerequest.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carerequest.model.CareRequestService;
import com.requesttab.model.RequestTabService;
import com.systemnotification.model.SystemNotificationService;
import com.careapply.model.CareApplyService;
import com.carerequest.model.CareRequestVO;
import com.requesttab.model.RequestTabPVO;

@WebServlet("/front-end/careRequest/careRequest")
public class CareRequestServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		CareRequestService careRequestSVC = new CareRequestService();
		List<String> errorMsgs = new ArrayList<>();
		String action = req.getParameter("action");

		req.setAttribute("errorMsgs", errorMsgs);

		// A1
		if ("goto_post_request".equals(action)) {
			try {
				Integer carerId = Integer.valueOf(req.getParameter("carerId"));
				String carerName = req.getParameter("carerName");

				req.setAttribute("carerId", carerId);
				req.setAttribute("carerName", carerName);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careRequest/postRequest.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/postRequest.jsp");
				fail.forward(req, res);
			}
		}

		// A2-1
		if ("post_request".equals(action)) {
			try {
				String patientName = req.getParameter("patientName");
				String reg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
				if (patientName == null || patientName.trim().length() == 0) {
					errorMsgs.add("請輸入姓名");
				} else if (!patientName.trim().matches(reg)) {
					errorMsgs.add("姓名只能是中英文，且長度在2到20之間");
				} else {
					patientName = patientName.trim();
				}

				String patientGender = req.getParameter("patientGender");
				if (patientGender == null) {
					errorMsgs.add("請選擇性別");
				}

				Integer patientAge = null;
				try {
					patientAge = Integer.valueOf(req.getParameter("patientAge").trim());
				} catch (NumberFormatException ne) {
					errorMsgs.add("請輸入年紀");
				}

				String serviceType = req.getParameter("serviceType");
				if (serviceType == null) {
					errorMsgs.add("請選擇服務地點類型");
				}

				// TODO 改成縣市地區選擇
				String patientAddr = req.getParameter("patientAddr");
				if (patientAddr == null || patientAddr.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				} else {
					patientAddr = patientAddr.trim();
				}

				Timestamp startTime = null;
				Timestamp endTime = null;
				try {
					startTime = Timestamp.valueOf(req.getParameter("startTime") + ":00");
					endTime = Timestamp.valueOf(req.getParameter("endTime") + ":00");
				} catch (IllegalArgumentException ie) {
					errorMsgs.add("請選擇時間");
				}

				String note = req.getParameter("note");

				String[] requestTabs = req.getParameterValues("serviceTabNo");
				if (requestTabs == null) {
					errorMsgs.add("請選擇至少一項服務");
				}

				Integer memId = Integer.valueOf(req.getParameter("memId"));

				Integer carerId = null;
				if (req.getParameter("carerId") != null && req.getParameter("carerId").trim().length() != 0) {
					carerId = Integer.valueOf(req.getParameter("carerId"));
				}

				String carerName = req.getParameter("carerName");

				CareRequestVO careRequestVO = new CareRequestVO();

				careRequestVO.setPatientName(patientName);
				careRequestVO.setPatientGender(patientGender);
				careRequestVO.setPatientAge(patientAge);
				careRequestVO.setServiceType(serviceType);
				careRequestVO.setPatientAddr(patientAddr);
				careRequestVO.setStartTime(startTime);
				careRequestVO.setEndTime(endTime);
				careRequestVO.setNote(note);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("careRequestVO", careRequestVO);
					req.setAttribute("requestTabArray", requestTabs);
					req.setAttribute("carerId", carerId);
					req.setAttribute("carerName", carerName);

					RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/postRequest.jsp");
					fail.forward(req, res);

					return;
				}

				Integer requestId = careRequestSVC.postRequest(memId, patientName, patientGender, patientAge,
						serviceType, patientAddr, startTime, endTime, note, requestTabs);

				if (carerId != null) {
					new SystemNotificationService().saveNotification(carerId, requestId, "B11");
				}

				careRequestVO = careRequestSVC.getRequest(requestId);
				List<RequestTabPVO> requestTabList = new RequestTabService().getRequestTabList(requestId);

				req.setAttribute("careRequestVO", careRequestVO);
				req.setAttribute("requestTabList", requestTabList);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careRequest/viewRequest.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/postRequest.jsp");
				fail.forward(req, res);
			}
		}

		// A2-2
		if ("edit_request".equals(action)) {
			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));
				String patientName = req.getParameter("patientName");
				String patientGender = req.getParameter("patientGender");
				Integer patientAge = Integer.valueOf(req.getParameter("patientAge"));

				String serviceType = req.getParameter("serviceType");
				if (serviceType == null) {
					errorMsgs.add("請選擇服務地點類型");
				}

				// TODO 改成縣市地區選擇
				String patientAddr = req.getParameter("patientAddr");
				if (patientAddr == null || patientAddr.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				} else {
					patientAddr = patientAddr.trim();
				}

				Timestamp startTime = null;
				Timestamp endTime = null;
				try {
					startTime = Timestamp.valueOf(req.getParameter("startTime") + ":00");
					endTime = Timestamp.valueOf(req.getParameter("endTime") + ":00");
				} catch (IllegalArgumentException ie) {
					errorMsgs.add("請選擇時間");
				}

				String note = req.getParameter("note");

				String[] requestTabs = req.getParameterValues("serviceTabNo");
				if (requestTabs == null) {
					errorMsgs.add("請選擇至少一項服務");
				}

				CareRequestVO careRequestVO = new CareRequestVO();

				careRequestVO.setRequestId(requestId);
				careRequestVO.setPatientName(patientName);
				careRequestVO.setPatientGender(patientGender);
				careRequestVO.setPatientAge(patientAge);
				careRequestVO.setServiceType(serviceType);
				careRequestVO.setPatientAddr(patientAddr);
				careRequestVO.setStartTime(startTime);
				careRequestVO.setEndTime(endTime);
				careRequestVO.setNote(note);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("careRequestVO", careRequestVO);
					req.setAttribute("requestTabArray", requestTabs);

					RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/editRequest.jsp");
					fail.forward(req, res);

					return;
				}

				careRequestSVC.editRequest(requestId, serviceType, patientAddr, startTime, endTime, note, requestTabs);

				careRequestVO = careRequestSVC.getRequest(requestId);
				List<RequestTabPVO> requestTabList = new RequestTabService().getRequestTabList(requestId);

				req.setAttribute("careRequestVO", careRequestVO);
				req.setAttribute("requestTabList", requestTabList);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careRequest/viewRequest.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/editRequest.jsp");
				fail.forward(req, res);
			}
		}

		// A4-1
		if ("goto_payment".equals(action)) {
			Integer requestId = Integer.valueOf(req.getParameter("requestId")); // ?? 為什麼抓的到
			Integer carerId = Integer.valueOf(req.getParameter("carerId"));
			Double amount = Double.valueOf(req.getParameter("amount"));

			HttpSession session = req.getSession();

			session.setAttribute("requestId", requestId);
			session.setAttribute("carerId", carerId);
			session.setAttribute("amount", amount);
			req.setAttribute("location", "finish_care");

			RequestDispatcher success = req.getRequestDispatcher("/front-end/payment.jsp");
			success.forward(req, res);
		}

		// A4-1
		if ("view_request".equals(action)) {
			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));

				CareRequestVO careRequestVO = careRequestSVC.getRequest(requestId);
				List<RequestTabPVO> requestTabList = new RequestTabService().getRequestTabList(requestId);

				req.setAttribute("careRequestVO", careRequestVO);
				req.setAttribute("requestTabList", requestTabList);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careRequest/viewRequest.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/requestMgt.jsp");
				fail.forward(req, res);
			}
		}

		// A4-1
		if ("cancel_request".equals(action)) {
			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));

				careRequestSVC.cancelRequest(requestId);

				res.sendRedirect("requestMgt.jsp");
				return;
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/requestMgt.jsp");
				fail.forward(req, res);
			}
		}

//A4-1
		if ("refuse_apply".equals(action)) {
			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));
				Integer carerId = Integer.valueOf(req.getParameter("carerId"));

				new CareApplyService().refuseApply(requestId, carerId);

				res.sendRedirect("requestMgt.jsp");
				return;
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/requestMgt.jsp");
				fail.forward(req, res);
			}
		}

		// A4-1
		if ("refuse_all_apply".equals(action)) {
			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));

				new CareApplyService().refuseAllApply(requestId);

				res.sendRedirect("requestMgt.jsp");
				return;
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/requestMgt.jsp");
				fail.forward(req, res);
			}
		}

		// A4-1
		if ("goto_edit_request".equals(action)) {
			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));

				CareRequestVO careRequestVO = careRequestSVC.getRequest(requestId);
				List<RequestTabPVO> requestTabList = new RequestTabService().getRequestTabList(requestId);

				req.setAttribute("careRequestVO", careRequestVO);
				req.setAttribute("requestTabList", requestTabList);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careRequest/editRequest.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careRequest/requestMgt.jsp");
				fail.forward(req, res);
			}
		}
	}

}