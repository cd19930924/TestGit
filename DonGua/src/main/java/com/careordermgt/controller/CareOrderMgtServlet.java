package com.careordermgt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careorder.model.CareOrderSVO;
import com.careorder.model.CareOrderService;
import com.careordermgt.model.CareApplyMgtService;
import com.careordermgt.model.CareApplyMgtVO;
import com.requesttab.model.RequestTabPVO;
import com.requesttab.model.RequestTabService;
import com.systemnotification.model.SystemNotificationService;

@WebServlet("/front-end/careordermgt/careOrder.do")
public class CareOrderMgtServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		CareOrderService careOrderSVC = new CareOrderService();
		CareApplyMgtService careApplyMgtSVC = new CareApplyMgtService();
		List<String> errorMsgs = new ArrayList<>();
		
		// 送出取消訂單的請求
		if("cancel_order".equals(action)) {
			try {
				Integer careOrderId = Integer.valueOf(req.getParameter("careOrderId"));
				Integer memId = Integer.valueOf(req.getParameter("memId"));
				
				careOrderSVC.cancelOrder(careOrderId);
				
				new SystemNotificationService().saveNotification(memId, careOrderId, "A01");
				
				RequestDispatcher success = req.getRequestDispatcher("/front-end/careordermgt/CareOrderMgt.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("取消訂單失敗：" + e.getMessage());
				e.printStackTrace();

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careordermgt/CareOrderMgt.jsp");
				fail.forward(req, res);
			}
		}
		
		// 送出瀏覽單一照護訂單請求
		if ("view_order".equals(action)) {
			try {
				Integer careOrderId = Integer.valueOf(req.getParameter("careOrderId"));

				CareOrderSVO careOrderSVO = careOrderSVC.getOrder(careOrderId);
				List<RequestTabPVO> requestTabList = new RequestTabService()
						.getRequestTabList(careOrderSVO.getRequestId());

				req.setAttribute("careOrderSVO", careOrderSVO);
				req.setAttribute("requestTabList", requestTabList);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careordermgt/ListOneCareOrder.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("訂單跳轉失敗：" + e.getMessage());
				e.printStackTrace();
					
				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careordermgt/CareOrderMgt.jsp");
				fail.forward(req, res);
			}
		}
		
		// 送出瀏覽單一應徵單請求
		if ("view_apply".equals(action)) {
			try {
//				Integer carerId = Integer.valueOf(req.getParameter("carerId"));
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));

				CareApplyMgtVO careApplyMgtVo = careApplyMgtSVC.getByReqId(requestId);
				
				List<RequestTabPVO> requestTabList = new RequestTabService()
						.getRequestTabList(careApplyMgtVo.getRequestId());

				req.setAttribute("careApplyMgtVo", careApplyMgtVo);
				req.setAttribute("requestTabList", requestTabList);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careordermgt/ListOneApplyOrder.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("訂單跳轉失敗：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careordermgt/CareApplyMgt.jsp");
				fail.forward(req, res);
			}
		}
		
		
	}
	
}
