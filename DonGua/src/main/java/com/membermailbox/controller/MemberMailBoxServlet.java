package com.membermailbox.controller;

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
import com.membermailbox.model.MemberMailBoxService;
import com.membermailbox.model.MemberMailBoxVO;

@WebServlet("/front-end/mailbox/mail.do")
public class MemberMailBoxServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		// 依收件者ID 瀏覽所有信件、瀏覽單一信件
//		if("memberMailBoxVO".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer receiveMemId = new  Integer(req.getParameter("receiveMemId"));
//				
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				/***************************2.開始查詢資料*****************************************/
//				MemberMailBoxService mailSvc = new MemberMailBoxService();
//				List<MemberMailBoxVO> mailVO = mailSvc.getMemberMaillBoxVO(receiveMemId);
//				if (mailVO == null) {
//					errorMsgs.add("無通知");
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("mailVO", mailVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/notMailBox/notMail.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//				
//		}
		
		// 寄信 存入信件(寄件者ID,收件者ID,信件狀態,內文,標題)
		if("sendMail".equals(action)) {
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer sendMemId = new Integer(req.getParameter("sendMemId").trim());
				
				Integer receiveMemId = new Integer(req.getParameter("receiveMemId").trim());
				if (receiveMemId == null || receiveMemId == 0 ) {
					errorMsgs.add("請輸入收件者");
				}
				
				String msgTitle = req.getParameter("msgTitle").trim();
				if (msgTitle == null || msgTitle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				if (msgTitle.trim().length() >= 100) {
					errorMsgs.add("標題不可超過100字");
				}
				
				String msgContent = req.getParameter("msgContent").trim();
				if (msgContent == null || msgContent.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				if (msgContent.trim().length() >= 1500) {
					errorMsgs.add("內容不可超過1500字");
				}
				
				MemberMailBoxVO memberMailBoxVO = new MemberMailBoxVO();
				memberMailBoxVO.setSendMemId(sendMemId);
				memberMailBoxVO.setReceiveMemId(receiveMemId);
				memberMailBoxVO.setStatus("0");
				memberMailBoxVO.setMsgContent(msgContent);
				memberMailBoxVO.setMsgTitle(msgTitle);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberMailBoxVO", memberMailBoxVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("mailCenter.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				MemberMailBoxService memberMailBoxSvc = new MemberMailBoxService();
				memberMailBoxSvc.sendMail(sendMemId, receiveMemId, "0", msgContent, msgTitle);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "mailCenter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("mailCenter.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("sendNewMail".equals(action)) {
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			System.out.println("===========================");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer sendMemId = new Integer(req.getParameter("sendMemId").trim());
				
				Integer receiveMemId = 0;
						
				String recMemId = req.getParameter("newreceiveMemId").trim();
				if (recMemId == null || recMemId.trim().length() == 0) {
					errorMsgs.add("請輸入收件者");
				} else {
					receiveMemId = new Integer(recMemId);
				}
				
				String msgTitle = req.getParameter("newmsgTitle").trim();
				if (msgTitle == null || msgTitle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				if (msgTitle.trim().length() >= 100) {
					errorMsgs.add("標題不可超過100字");
				}
				
				String msgContent = req.getParameter("newmsgContent").trim();
				if (msgContent == null || msgContent.trim().length() == 0) {
					errorMsgs.add("內容請勿空白");
				}
				if (msgContent.trim().length() >= 1500) {
					errorMsgs.add("內容不可超過1500字");
				}
				
				MemberMailBoxVO memberMailBoxVO = new MemberMailBoxVO();
				memberMailBoxVO.setSendMemId(sendMemId);
				memberMailBoxVO.setReceiveMemId(receiveMemId);
				memberMailBoxVO.setStatus("0");
				memberMailBoxVO.setMsgContent(msgContent);
				memberMailBoxVO.setMsgTitle(msgTitle);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberMailBoxVO", memberMailBoxVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("mailCenter.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				MemberMailBoxService memberMailBoxSvc = new MemberMailBoxService();
				memberMailBoxSvc.sendMail(sendMemId, receiveMemId, "0", msgContent, msgTitle);
				memberMailBoxSvc.sendMail(sendMemId, sendMemId, "2", msgContent, msgTitle);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "mailCenter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("mailCenter.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 修改信件狀態(成"1"已讀)
//		if("update".equals(action)) {
//			try {
//				String no = req.getParameter("driverIdStr");
//				String status = req.getParameter("serviceStatus");
//				
//				DriverVO vo = new DriverVO();
//				vo.setDriverIdStr(no);
//				vo.setServiceStatus(status);
//
//				DriverService driverService = new DriverService();
//				vo = driverService.serviceStatusUpdate(no, status);
//
//				res.sendRedirect("driver.jsp");
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
	}
}
