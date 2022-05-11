package com.mealorder.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.mealorderdetail.model.MealOrderDetailService;
import com.mealorderdetail.model.MealOrderDetailVO;

@WebServlet("/front-end/meal/order.do")
public class MemMealOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("cancel_order_onall".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Long mealOrderId = Long.valueOf(req.getParameter("mealOrderId"));
				
				MealOrderService mealOrderSvc = new MealOrderService();
				mealOrderSvc.update("4", mealOrderId);
				
				String url = "/front-end/meal/memorder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/meal/memorder.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("cancel_order_onwait".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Long mealOrderId = Long.valueOf(req.getParameter("mealOrderId"));
				
				MealOrderService mealOrderSvc = new MealOrderService();
				mealOrderSvc.update("4", mealOrderId);
				
				String url = "/front-end/meal/memorderwait.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/meal/memorderwait.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
