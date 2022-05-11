package com.driveorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.careorder.model.CareOrderSVO;
import com.careorder.model.CareOrderService;
import com.carermem.model.CarerMemService;
import com.carermem.model.CarerMemVO;
import com.driveorder.model.service.DriveOrderService;
import com.driveorder.model.vo.DriveOrderVO;
import com.driverschedule.model.DriverScheduleService;
import com.driverschedule.model.DriverScheduleVO;
import com.file.model.FileService;
import com.file.model.FileVO;
import com.requesttab.model.RequestTabPVO;
import com.requesttab.model.RequestTabService;

public class PaymentServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String successMsgs = "";
		Set<String> errorMsgs = new HashSet<String>();
		String action = req.getParameter("action");

		req.setAttribute("successMsgs", successMsgs);
		req.setAttribute("errorMsgs", errorMsgs);
		
//		if ("goto_payment".equals(action)) {
//			Integer requestId = Integer.valueOf(req.getParameter("requestId")); // ?? 為什麼抓的到
//			Integer carerId = Integer.valueOf(req.getParameter("carerId"));
//			Double amount = Double.valueOf(req.getParameter("amount"));
//			
//			req.setAttribute("requestId", requestId);
//			req.setAttribute("carerId", carerId);
//			req.setAttribute("amount", amount);
//			
//			RequestDispatcher success = req.getRequestDispatcher("/front-end/payment.jsp");
//			success.forward(req, res);
//		}

		if ("finish_car".equals(action)) {
			try {
				String str = req.getParameter("memName");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入姓名");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment.jsp");
					failureView.forward(req, res);
					return;
				}

				List<String> creditCard = new ArrayList<String>();
				creditCard.add(req.getParameter("firstBlockOfCard"));
				creditCard.add(req.getParameter("secondBlockOfCard"));
				creditCard.add(req.getParameter("thirdBlockOfCard"));
				creditCard.add(req.getParameter("fourthBlockOfCard"));
				String regexOfCard = "^\\d{4}$";
				String regexOfAuth = "^\\d{3}$";

				for (String e : creditCard) {
					if (e == null || (e.trim()).length() == 0) {
						errorMsgs.add("信用卡欄位不得為空");
					} else if (!e.matches(regexOfCard)) {
						errorMsgs.add("信用卡卡號格式不正確");
					}
				}

				String authCode = req.getParameter("expire_safe");
				if (authCode == null || (authCode.trim()).length() == 0) {
					errorMsgs.add("信用卡安全驗證碼欄位不得為空");
				} else if (!authCode.matches(regexOfAuth)) {
					errorMsgs.add("信用卡安全驗證碼格式不正確");
				}

				// TODO: 時間檢核
				String expiryMonth = req.getParameter("expiryMonth");
				String expiryYear = req.getParameter("expiryYear");

				if (!errorMsgs.isEmpty()) {
					System.out.println("出4");
					
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/payment.jsp");
					failureView.forward(req, res);
					return;
				}
				
				// 轉交頁面
				HttpSession session = req.getSession();
				
				DriverScheduleVO driverScheduleVO= (DriverScheduleVO)session.getAttribute("driverScheduleVO");
				DriveOrderVO driveOrderVO = (DriveOrderVO)session.getAttribute("driveOrderVO");
				
				DriverScheduleService driverScheduleSvc = new DriverScheduleService();
				DriveOrderService driveOrderSvc = new DriveOrderService();
				
				driverScheduleSvc.updateSchedule(driverScheduleVO);
				driveOrderSvc.addDriverOrder(driveOrderVO);
				
				RequestDispatcher success = req.getRequestDispatcher("/front-end/driveorder/MyDriveOrder.html");
				success.forward(req, res);

//				//JS付款確認
//				String payConfirm = "<script type=\"text/javascript\">"
//						+ "if (confirm('是否確定付款？')) {\r\n"
//						+ "  console.log('付款成功，新增訂單');\r\n"
//						+ "} else {\r\n"
//						+ "  console.log('付款失敗');\r\n"
//						+ " "
//						+ "}"
//						+ "</script>";
//				out.println(payConfirm);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
