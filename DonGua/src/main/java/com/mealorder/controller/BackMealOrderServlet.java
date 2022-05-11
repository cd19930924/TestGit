package com.mealorder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mealorder.model.MealOrderService;
import com.mealorder.model.MealOrderVO;
import com.systemnotification.model.SystemNotificationService;

@WebServlet("/back-end/meal/order")
public class BackMealOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_for_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				long memId = Long.valueOf(req.getParameter("memId"));
				long mealOrderId = Long.valueOf(req.getParameter("mealOrderId"));
				String orderStatus = req.getParameter("orderStatus");

				MealOrderService mealOrderSvc = new MealOrderService();
				mealOrderSvc.update(orderStatus, mealOrderId);

				
				if (orderStatus.equals("4")) {
					new SystemNotificationService().saveNotification((int) memId, (int) mealOrderId, "D01");
				}

				String url = "/back-end/meal/mealOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/meal/mealOrder.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
