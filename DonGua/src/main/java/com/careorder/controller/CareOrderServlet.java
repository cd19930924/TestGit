package com.careorder.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careorder.model.CareOrderService;
import com.carermem.model.CarerMemService;
import com.carermem.model.CarerMemVO;
import com.file.model.FileService;
import com.file.model.FileVO;
import com.requesttab.model.RequestTabService;
import com.systemnotification.model.SystemNotificationService;
import com.requesttab.model.RequestTabPVO;
import com.careorder.model.CareOrderSVO;

@WebServlet("/front-end/careOrder/careOrder")
public class CareOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		CareOrderService careOrderSVC = new CareOrderService();
		List<String> errorMsgs = new ArrayList<>();
		String action = req.getParameter("action");

		req.setAttribute("errorMsgs", errorMsgs); // ?? 為什麼可以放這麼前面

		if ("view_order".equals(action)) {
			try {
				Integer orderId = Integer.valueOf(req.getParameter("orderId"));

				CareOrderSVO careOrderSVO = careOrderSVC.getOrder(orderId);
				CarerMemVO carerVO = new CarerMemService().memGetCarer(careOrderSVO.getCarerId());
				FileVO fileVO = new FileService().getHeadShot(careOrderSVO.getCarerId());
				List<RequestTabPVO> requestTabList = new RequestTabService()
						.getRequestTabList(careOrderSVO.getRequestId());

				req.setAttribute("careOrderSVO", careOrderSVO);
				req.setAttribute("carerVO", carerVO);
				req.setAttribute("fileVO", fileVO);
				req.setAttribute("requestTabList", requestTabList);

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careOrder/viewOrder.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("出事了阿婆：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careOrder/orderMgt.jsp");
				fail.forward(req, res);
			}
		}

		// A4-2
		if ("cancel_order".equals(action)) {
			try {
				Integer orderId = Integer.valueOf(req.getParameter("orderId"));
				Integer carerId = Integer.valueOf(req.getParameter("carerId"));

				careOrderSVC.cancelOrder(orderId);

				new SystemNotificationService().saveNotification(carerId, orderId, "A12");
				
				res.sendRedirect("orderMgt.jsp");
				return;
			} catch (Exception e) {
				errorMsgs.add("出事了阿婆：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careOrder/orderMgt.jsp");
				fail.forward(req, res);
			}
		}

		// A4-2
		if ("fill_in_feedback".equals(action)) {
			try {
				Integer orderId = Integer.valueOf(req.getParameter("orderId"));

				String feedback = req.getParameter("feedback");
				if (feedback == null || feedback.trim().length() == 0) {
					errorMsgs.add("請填寫回饋");

					RequestDispatcher fail = req.getRequestDispatcher("/front-end/careOrder/orderMgt.jsp");
					fail.forward(req, res);

					return;
				}

				careOrderSVC.fillInFeedback(orderId, feedback);

				res.sendRedirect("orderMgt.jsp");
				return;

			} catch (Exception e) {
				errorMsgs.add("出事了阿婆：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/front-end/careOrder/orderMgt.jsp");
				fail.forward(req, res);
			}
		}
	}

}