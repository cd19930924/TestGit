package com.mealorder.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.model.service.JWTokenUtils;
import com.meal.model.service.MealService;
import com.mealorder.model.MealOrderService;
import com.mealorder.model.MealOrderVO;
import com.mealorderdetail.model.MealOrderDetailService;
import com.mealorderdetail.model.MealOrderDetailVO;

@WebServlet("/front-end/meal/order")
public class MealOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 取得訂單資料
				Long memId = new Long(req.getParameter("memId"));
				if (memId == 0) {
					errorMsgs.add("未登入會員");
				}
				
				java.sql.Date startdate = null;
				try {
					startdate = java.sql.Date.valueOf(req.getParameter("startdate").trim()); //1
				} catch (IllegalArgumentException e) {
					startdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入起始日");
				}

				Integer totaldays = null;
				try {
					totaldays = new Integer(req.getParameter("totaldays").trim()); //2
					if (totaldays <= 0 || totaldays > 60) {
						errorMsgs.add("天數不在服務範圍內");
					}
				} catch (NumberFormatException e) {
					totaldays = 0;
					errorMsgs.add("請輸入天數");
				}

				String[] meal = req.getParameterValues("mealtime"); //3
				String mealtime = "";

				if (meal != null) {
					for (int i = 0; i < meal.length; i++) {
						mealtime = mealtime + meal[i];
					}
					if (mealtime.equals("123")) {
						mealtime = "111";
					} else if (mealtime.equals("23")) {
						mealtime = "011";
					} else if (mealtime.equals("12")) {
						mealtime = "110";
					} else if (mealtime.equals("13")) {
						mealtime = "101";
					} else if (mealtime.equals("1")) {
						mealtime = "100";
					} else if (mealtime.equals("2")) {
						mealtime = "010";
					} else if (mealtime.equals("3")) {
						mealtime = "001";
					}

				} else {
					errorMsgs.add("請選擇餐點時間");
				}

				String contactname = req.getParameter("contactname"); //4
				if (contactname == null || contactname.trim().length() == 0) {
					errorMsgs.add("請輸入姓名");
				}

				String contactnumber = req.getParameter("contactnumber"); //5
				if (contactnumber == null || contactnumber.trim().length() == 0) {
					errorMsgs.add("請輸入電話");
				}

				String addr = req.getParameter("addr"); //6
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("請輸入地址");
				}
				
				BigDecimal total = new BigDecimal(req.getParameter("orderamount"));

				// 取得明細資料
				String[] mealQtyList = req.getParameterValues("qty"); //8
				if (mealQtyList == null) {
					errorMsgs.add("請選擇餐點數量");
				}
				
				
				String[] mealNoList = req.getParameterValues("meal"); //7
				if (mealNoList == null) {
					errorMsgs.add("請選擇餐點");
				} 
				

				// 回傳到service

				MealOrderVO mealOrderVO = new MealOrderVO();
				MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mealOrderVO", mealOrderVO);
					req.setAttribute("mealOrderDetailVO", mealOrderDetailVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/meal/orderMeal.jsp");
					failureView.forward(req, res);
					return;
				}

				
				HttpSession session = req.getSession();
				
				session.setAttribute("startdate", startdate);
				session.setAttribute("totaldays", totaldays);
				session.setAttribute("mealtime", mealtime);
				session.setAttribute("contactname", contactname);
				session.setAttribute("contactnumber", contactnumber);
				session.setAttribute("addr", addr);
				session.setAttribute("mealNoList", mealNoList);
				session.setAttribute("mealQtyList", mealQtyList);
				session.setAttribute("amount", total);
				session.setAttribute("location", "finish_meal");
				session.setAttribute("memId", memId);
				

				String url = "/front-end/payment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/meal/orderMeal.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
