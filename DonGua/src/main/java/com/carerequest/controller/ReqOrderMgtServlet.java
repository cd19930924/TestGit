package com.carerequest.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careorder.model.CareOrderService;
import com.careorder.model.CareOrderVO;
import com.carerequest.model.CareRequestService;
import com.carerequest.model.CareRequestVO;
import com.systemnotification.model.SystemNotificationService;

@WebServlet("/back-end/careorder/management")
public class ReqOrderMgtServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("requestStatusUpdate".equals(action)) {

			try {
				Integer requestId = Integer.valueOf(req.getParameter("requestId"));
				Integer memId = Integer.valueOf(req.getParameter("memId"));
				String status = req.getParameter("status");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));

				CareRequestVO vo = new CareRequestVO();
				vo.setStatus(status);
				vo.setRequestId(requestId);
				vo.setUpdateTime(updateTime);

				CareRequestService svc = new CareRequestService();
				vo = svc.updateRequestStatus(status, updateTime, requestId);
				SystemNotificationService noticeSvc = new SystemNotificationService();
				noticeSvc.saveNotification(memId, requestId, "B02");
				
				res.sendRedirect("careRequest.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("orderStatusUpdate".equals(action)) {
			try {
				Integer orderId = Integer.valueOf(req.getParameter("orderId"));
				Integer memId = Integer.valueOf(req.getParameter("memId"));
				Integer carerId = Integer.valueOf(req.getParameter("carerId"));
				String status = req.getParameter("status");

				CareOrderService svc = new CareOrderService();
				svc.updateOrderStatus(orderId, status);
				
				SystemNotificationService noticeSvc = new SystemNotificationService();
				noticeSvc.saveNotification(memId, orderId, "A03");
				noticeSvc.saveNotification(carerId, orderId, "A13");
				
				res.sendRedirect("careOrder.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}