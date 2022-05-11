package com.driver.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.driver.model.DriverService;
import com.driver.model.DriverVO;

@WebServlet("/back-end/driver/driver.do")
public class DriverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		// 更新司機資料
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數 - 輸入格式的錯誤處理

				// 司機ID
				Integer driverId = Integer.valueOf(req.getParameter("driverId"));
				
				// 司機姓名
				String driverName = req.getParameter("driverName");
				String driverNameReq = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (driverName == null || (driverName.trim()).length() == 0) {
					errorMsgs.add("司機姓名:請勿空白");
				} else if (!driverName.trim().matches(driverNameReq)) {
					errorMsgs.add("司機姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				// 司機電話
				String driverPhone = req.getParameter("driverPhone").trim();
				String driverPhoneReq = "^09[0-9]{8}$";
				if (driverPhone == null || driverPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				} else if (!driverPhone.trim().matches(driverPhoneReq)) {
					errorMsgs.add("手機號碼只能為09開頭+8碼數字");
				}

				// 車牌號碼
				String carNumber = req.getParameter("carNumber");
				String carNumberReq = "^[A-Z0-9-]{6,8}$";
				if (driverPhone == null || driverPhone.trim().length() == 0) {
					errorMsgs.add("請輸入車牌號碼");
				} else if (!driverPhone.trim().matches(driverPhoneReq)) {
					errorMsgs.add("車牌號碼只能輸入大寫英文、數字，且長度為5到7之間");
				}

				// 司機Email
				String driverEmail = req.getParameter("driverEmail");
				String driverEmailReq = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (driverEmail == null || driverEmailReq.trim().length() == 0) {
					errorMsgs.add("請輸入EMAIL");
				} else if (!driverEmail.trim().matches(driverEmailReq)) {
					errorMsgs.add("EMAIL格式錯誤");
				}

				// 司機在職狀態
				String serviceStatus = req.getParameter("serviceStatus");
				String serviceStatusReq = "^[0-1]{1}$";
				if (serviceStatus == null || serviceStatusReq.trim().length() == 0) {
					errorMsgs.add("請選擇在職狀態");
				}

				DriverVO driverVO = new DriverVO();
				driverVO.setDriverId(driverId);
				driverVO.setDriverName(driverName);
				driverVO.setDriverPhone(driverPhone);
				driverVO.setCarNumber(carNumber);
				driverVO.setDriverEmail(driverEmail);
				driverVO.setServiceStatus(serviceStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("driverVO", driverVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driver/driver.jsp");
					failureView.forward(req, res);
					return;
				}
				// 開始新增資料
				DriverService DriverSvc = new DriverService();
				driverVO = DriverSvc.updateDriver(driverId, driverName, driverPhone, carNumber, driverEmail,
						serviceStatus);

				// 新增完成 準備轉交
				String url = "/back-end/driver/driver.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交/back-end/driver.jsp
				successView.forward(req, res);

			} catch (Exception e) {
//				System.out.println("出示了阿伯");
				
//				e.printStackTrace();
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driver/driver.jsp");
				failureView.forward(req, res);
			}

		}

		// 新增司機基本資料
		if ("insert".equals(action)) { // 來自addDriver.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				// 司機姓名
				String driverName = req.getParameter("driverName");
				String driverNameReq = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (driverName == null || (driverName.trim()).length() == 0) {
					errorMsgs.add("司機姓名:請勿空白");
				} else if (!driverName.trim().matches(driverNameReq)) {
					errorMsgs.add("司機姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				// 司機電話
				String driverPhone = req.getParameter("driverPhone").trim();
				String driverPhoneReq = "^09\\d{2}-?\\d{3}-?\\d{3}$";
				if (driverPhone == null || driverPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				} else if (!driverPhone.trim().matches(driverPhoneReq)) {
					errorMsgs.add("手機號碼只能為09開頭+8碼數字");
				}

				// 車牌號碼
				String carNumber = req.getParameter("carNumber");
				String carNumberReq = "^([A-Z]{2,3}-\\d{4}|\\d{3,4}-[A-Z]{2})$";
				if (carNumber == null || carNumber.trim().length() == 0) {
					errorMsgs.add("請輸入車牌號碼");
				} else if (!carNumber.trim().matches(carNumberReq)) {
					errorMsgs.add("車牌號碼只能輸入大寫英文、數字，且長度為5到7之間");
				}

				// 司機Email
				String driverEmail = req.getParameter("driverEmail");
				String driverEmailReq = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (driverEmail == null || driverEmailReq.trim().length() == 0) {
					errorMsgs.add("請輸入EMAIL");
				} else if (!driverEmail.trim().matches(driverEmailReq)) {
					errorMsgs.add("EMAIL格式錯誤");
				}

				// 司機在職狀態
				String serviceStatus = req.getParameter("serviceStatus");
				String serviceStatusReq = "^[0-1]{1}$";
				if (serviceStatus == null || serviceStatusReq.trim().length() == 0) {
					errorMsgs.add("請輸入在職狀態");
				}

				DriverVO driverVO = new DriverVO();
				driverVO.setDriverName(driverName);
				driverVO.setDriverPhone(driverPhone);
				driverVO.setCarNumber(carNumber);
				driverVO.setDriverEmail(driverEmail);
				driverVO.setServiceStatus(serviceStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("driverVO", driverVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driver/driver.jsp");
					failureView.forward(req, res);
					return;
				}
				// 開始新增資料
				DriverService DriverSvc = new DriverService();
				driverVO = DriverSvc.addDriver(driverName, driverPhone, carNumber, driverEmail, serviceStatus);

				// 新增完成 準備轉交
				String url = "/back-end/driver/driver.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交/back-end/driver.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driver/driver.jsp");
				failureView.forward(req, res);
			}

		}
		
		if ("serviceStatusUpdate".equals(action)) {

			try {
				String no = req.getParameter("driverIdStr");
				String status = req.getParameter("serviceStatus");
				
				DriverVO vo = new DriverVO();
				vo.setDriverIdStr(no);
				vo.setServiceStatus(status);

				DriverService driverService = new DriverService();
				vo = driverService.serviceStatusUpdate(no, status);

				res.sendRedirect("driver.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		

	}

}
