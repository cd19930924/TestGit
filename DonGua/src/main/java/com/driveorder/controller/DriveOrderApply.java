package com.driveorder.controller;

import java.io.IOException;
import java.sql.Time;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.common.model.service.JWTokenUtils;
import com.driveorder.model.service.DriveOrderService;
import com.driveorder.model.vo.DriveOrderVO;
import com.driverschedule.model.DriverScheduleService;
import com.driverschedule.model.DriverScheduleVO;

@WebServlet("/front-end/driveorder/driveorderApply.do")
public class DriveOrderApply extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Cookie[] cookies = req.getCookies();
		String token = null;
		String identity = null;
		long memId = 0;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("token".equals(cookies[i].getName())) {
					token = cookies[i].getValue();
				}
				if("identity".equals(cookies[i].getName())) {
					identity = cookies[i].getValue();
				}
			}
		}

		if (token != null) {
				try {
					memId = JWTokenUtils.isTokenValid(token);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("want_Apply".equals(action)) { // 來自addEmp.jsp的請求

			if(identity==null) {
				res.sendRedirect(req.getContextPath()+"/front-end/login.html");
			}else {
				res.sendRedirect(req.getContextPath()+"/front-end/driveorder/DriveApply.jsp");
			}
		}

		if ("apply".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				
				//讀取乘車地點
				String startPoint = req.getParameter("startPoint");
				if (startPoint == null || startPoint.trim().length() == 0) {
					errorMsgs.add("乘車地點請勿空白");
				} 
				
				//讀取目的地
				String endPoint = req.getParameter("endPoint");
				if (endPoint == null || endPoint.trim().length() == 0) {
					errorMsgs.add("目的地請勿空白");
				} 
				
				//讀取乘車距離
				Double distance = 0.0;
				if(req.getParameter("distance")!=null && req.getParameter("distance").trim().length() != 0) {
					distance = Double.parseDouble((req.getParameter("distance")).split(" 公里")[0]);
				}
				
				//讀取乘車日期
				java.sql.Date sendDriveDate = null;
				if(req.getParameter("sendDriveDate")!=null && req.getParameter("sendDriveDate").trim().length() != 0) {
					sendDriveDate = java.sql.Date.valueOf(req.getParameter("sendDriveDate"));
				}else {
					errorMsgs.add("請選擇日期");
				}
				
				//讀取乘車時間
				Time sendDriveTime = valTransTime(req.getParameter("sendDriveTime"));
				
				//讀取乘車金額
				Double orderAmount = 0.0;
				if(req.getParameter("orderAmount")!=null && req.getParameter("orderAmount").trim().length() != 0) {
					orderAmount = Double.parseDouble(req.getParameter("orderAmount"));
				}
				
				//讀取聯絡人姓名
				String contactName = req.getParameter("contactName");
				if (contactName == null || contactName.trim().length() == 0) {
					errorMsgs.add("聯絡人姓名請勿空白");
				} 
				
				//讀取聯絡人電話
				String contactNumber = req.getParameter("contactNumber").trim();
				if (contactNumber == null || contactNumber.trim().length() == 0) {
					errorMsgs.add("連絡電話請勿空白");
				}else if(!contactNumber.trim().matches("^09[0-9]{8}$")) {
					errorMsgs.add("手機號碼請以09開頭，後8碼輸入數字");
				}
				
				//讀取訂單開始時間
				Integer startInedx = Integer.parseInt(req.getParameter("sendDriveTime"));
				
				//讀取訂單結束時間
				Integer endIndex = 0;
				if(req.getParameter("mayBeTime")!=null && req.getParameter("mayBeTime").trim().length() != 0) {
					endIndex = timeTransVal(req.getParameter("mayBeTime"));
				}
				
				DriverScheduleService driverScheduleSvc = new DriverScheduleService();
				
				DriverScheduleVO driverScheduleVO =driverScheduleSvc.checkAndUpdate(sendDriveDate, startInedx, endIndex);

				DriveOrderVO driveOrderVO = new DriveOrderVO();
				driveOrderVO.setMemId((int)memId);
				driveOrderVO.setDriverId(driverScheduleVO.getDriverId());
				driveOrderVO.setStartPoint(startPoint);
				driveOrderVO.setEndPoint(endPoint);
				driveOrderVO.setDistance(distance);
				driveOrderVO.setSendDriveDate(sendDriveDate);
				driveOrderVO.setSendDriveTime(sendDriveTime);
				driveOrderVO.setOrderAmount(orderAmount);
				driveOrderVO.setContactName(contactName);
				driveOrderVO.setContactNumber(contactNumber);
				
				HttpSession session = req.getSession();
				
				session.setAttribute("driverScheduleVO", driverScheduleVO);
				session.setAttribute("driveOrderVO", driveOrderVO);
				session.setAttribute("amount", orderAmount);
				req.setAttribute("location", "finish_car");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("driveOrderVO", driveOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driveorder/DriveApply.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/driveorder/payment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (NullPointerException e) {
				// TODO: handle exception
				if(errorMsgs.size() == 0) {
					errorMsgs.add("您所預約的時間已無空班司機");
				}
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driveorder/DriveApply.jsp");
				failureView.forward(req, res);
			}catch (StringIndexOutOfBoundsException e) {
			// TODO: handle exception
				errorMsgs.add("此次服務時間已超出本服務提供服務時段");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driveorder/DriveApply.jsp");
				failureView.forward(req, res);
			}catch (Exception e) {
				e.printStackTrace(System.err);
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/driveorder/DriveApply.jsp");
				failureView.forward(req, res);
			}
		}

	}
	
	
	public Time valTransTime(String t) {
		// TODO Auto-generated constructor stub
		if (t == "0") {
			t = "05:00:00";
		} else if (Integer.parseInt(t) < 10) {
			t= "0" + (5+(Integer.parseInt(t))/2)+":" + (Integer.parseInt(t)%2==0?"00:00":"30:00");
		} else {
			t = (5+(Integer.parseInt(t))/2)+":" + (Integer.parseInt(t)%2==0?"00:00":"30:00");
		}
		return java.sql.Time.valueOf(t);
	}
	
	public Integer timeTransVal(String t) {
		// TODO Auto-generated constructor stub
		if(t.contains("小時")) {
			
			Integer totolTime = Integer.parseInt(t.split(" ")[0])*60+Integer.parseInt(t.split(" ")[2]);
			return ((totolTime/30)+1)*2 ;
			}else {
				Integer totolTime = Integer.parseInt(t.split(" ")[0]);
				return ((totolTime/30)+1)*2;
			}
	}
}

