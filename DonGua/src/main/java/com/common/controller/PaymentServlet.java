package com.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
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
import com.mealorder.model.MealOrderService;
import com.mealorder.model.MealOrderVO;
import com.mealorderdetail.model.MealOrderDetailService;
import com.mealorderdetail.model.MealOrderDetailVO;
import com.requesttab.model.RequestTabPVO;
import com.requesttab.model.RequestTabService;
import com.systemnotification.model.SystemNotificationService;

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

		if ("finish_care".equals(action)) {
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

				// 照護開始
				HttpSession session = req.getSession();

				Integer requestId = (Integer) session.getAttribute("requestId");
				Integer carerId = (Integer) session.getAttribute("carerId");
				Double amount = (Double) session.getAttribute("amount");

				CareOrderService careOrderSVC = new CareOrderService();

				Integer orderId = careOrderSVC.establishOrder(requestId, carerId, amount);

				CareOrderSVO careOrderSVO = careOrderSVC.getOrder(orderId);
				CarerMemVO carerVO = new CarerMemService().memGetCarer(careOrderSVO.getCarerId());
				FileVO fileVO = new FileService().getHeadShot(careOrderSVO.getCarerId());
				List<RequestTabPVO> requestTabList = new RequestTabService()
						.getRequestTabList(careOrderSVO.getRequestId());

				req.setAttribute("careOrderSVO", careOrderSVO);
				req.setAttribute("carerVO", carerVO);
				req.setAttribute("fileVO", fileVO);
				req.setAttribute("requestTabList", requestTabList);

				new SystemNotificationService().saveNotification(carerId, orderId, "A11");

				RequestDispatcher success = req.getRequestDispatcher("/front-end/careOrder/viewOrder.jsp");
				success.forward(req, res);
				// 照護結束
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("finish_meal".equals(action)) {
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
//				java.sql.Date startdate = java.sql.Date.valueOf(req.getParameter("startdate"));
//				Integer totaldays = new Integer(req.getParameter("totaldays"));
//				String mealtime = req.getParameter("mealtime");
//				String contactname = req.getParameter("contactname");
//				String contactnumber = req.getParameter("contactnumber");
//				String addr = req.getParameter("addr");
//				String[] mealNoList = req.getParameterValues("meal");
//				String[] mealQtyList = req.getParameterValues("qty");
//				BigDecimal total = new BigDecimal(req.getParameter("total"));

				HttpSession session = req.getSession();

				java.sql.Date startdate = (Date) session.getAttribute("startdate");
				Integer totaldays = (Integer) session.getAttribute("totaldays");
				String mealtime = (String) session.getAttribute("mealtime");
				String contactname = (String) session.getAttribute("contactname");
				String contactnumber = (String) session.getAttribute("contactnumber");
				String addr = (String) session.getAttribute("addr");
				String[] mealNoList = (String[]) session.getAttribute("mealNoList");
				String[] mealQtyList = (String[]) session.getAttribute("mealQtyList");
				BigDecimal total = (BigDecimal) session.getAttribute("amount");
				Long memId = (Long) session.getAttribute("memId");

				MealOrderVO mealOrderVO = new MealOrderVO();
				MealOrderDetailVO mealOrderDetailVO = new MealOrderDetailVO();

				MealOrderService mealOrderSvc = new MealOrderService();
				mealOrderVO = mealOrderSvc.addMealOrder(memId, total, addr, contactnumber, contactname, startdate,
						totaldays, mealtime, "0");

				MealOrderDetailService mealOrderdetailSvc = new MealOrderDetailService();
				mealOrderDetailVO = mealOrderdetailSvc.addOrderDetail(mealOrderVO.getMealOrderId(), mealNoList,
						mealQtyList);

				req.setAttribute("mealOrderId", mealOrderVO.getMealOrderId());

				String url = "/front-end/meal/orderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

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

				DriverScheduleVO driverScheduleVO = (DriverScheduleVO) session.getAttribute("driverScheduleVO");
				DriveOrderVO driveOrderVO = (DriveOrderVO) session.getAttribute("driveOrderVO");

				DriverScheduleService driverScheduleSvc = new DriverScheduleService();
				DriveOrderService driveOrderSvc = new DriveOrderService();

				driverScheduleSvc.updateSchedule(driverScheduleVO);
				driveOrderVO = driveOrderSvc.addDriverOrder(driveOrderVO);

				new SystemNotificationService().saveNotification(driveOrderVO.getMemId(), driveOrderVO.getDrverOrderId(), "C01");
			    res.sendRedirect(req.getContextPath()+"/front-end/driveorder/MyDriveOrder.html");

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
