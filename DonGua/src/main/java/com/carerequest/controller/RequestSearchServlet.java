package com.carerequest.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.careapply.model.CareApplyService;
import com.careapply.model.CareApplyVO;
import com.carerequest.model.CareRequestService;
import com.carerequest.model.CareRequestVO;
import com.systemnotification.model.SystemNotificationService;

@WebServlet("/request/requestsearch")
public class RequestSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("multipleRequestsSearch".equals(action)) {
			// TODO: deal with error of time input from front-end
			try {
				Map<String, String[]> map = req.getParameterMap();

				CareRequestService careRequestSvc = new CareRequestService();
				List<CareRequestVO> list = careRequestSvc.getAll(map);
				
				req.setAttribute("requestSearchResult", list);
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/careRequest/searchResult.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("displayARequest".equals(action)) {
			// TODO: deal with error of null address & request tab
			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));

				CareRequestService careRequestSvc = new CareRequestService();
				CareRequestVO careRequestVO = careRequestSvc.getOneCareRequest(requestId);

				req.setAttribute("careRequestVO", careRequestVO);

				String url = "/front-end/careRequest/displayARequest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("applyToRequest".equals(action)) {
			// TODO: block the apply button after applying once
			try {

				Integer requestId = Integer.valueOf(req.getParameter("requestId"));
				Integer carerId = Integer.valueOf(req.getParameter("carerId"));
				Integer memId = Integer.valueOf(req.getParameter("memId"));
//				String status = req.getParameter("status").trim(); //default = 1
				String status = "1"; // default = 1

				CareRequestService requestSvc = new CareRequestService();
				if (!requestSvc.hasApplied(carerId, requestId) && !requestSvc.beRejected(carerId, requestId)) {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					java.sql.Timestamp applyTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));
					
					CareApplyVO careApplyVO = new CareApplyVO();
					careApplyVO.setRequestId(requestId);
					careApplyVO.setCarerId(carerId);
					careApplyVO.setStatus(status);
					careApplyVO.setApplyTime(applyTime);
					
					CareApplyService careApplySvc = new CareApplyService();
					careApplyVO = careApplySvc.applyToRequest(requestId, carerId, status, applyTime);
				} else if (requestSvc.beRejected(carerId, requestId)) {
					
					CareApplyVO careApplyVO = new CareApplyVO();
					careApplyVO.setRequestId(requestId);
					careApplyVO.setCarerId(carerId);
					
					CareApplyService careApplySvc = new CareApplyService();
					careApplyVO = careApplySvc.updateApply(requestId, carerId);
				} 
				
				new SystemNotificationService().saveNotification(memId, requestId, "B01");
				
				res.sendRedirect("../front-end/careRequest/appliedSuccess.jsp");
//				String url = "/front-end/careRequest/appliedSuccess.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res); // TODO: css failed to import

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
