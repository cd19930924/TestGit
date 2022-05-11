package com.auth.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth.model.AuthService;
import com.auth.model.AuthVO;
import com.empauth.model.EmpAuthVO;

@WebServlet("/back-end/auth/auth.do")
public class AuthServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// 新增權限群組
		if ("insert".equals(action)) { // 來自driver.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 群組編號
				String empAuthNo = req.getParameter("empAuthNo");
				String empAuthNoReq = "^A[0-9]{2}$";
				if (empAuthNo == null || empAuthNo.trim().length() == 0) {
					errorMsgs.add("請填寫群組編號");
				} else if (!empAuthNo.trim().matches(empAuthNoReq)) {
					errorMsgs.add("權限群組編號僅能輸入開頭為A+2碼數字");
				}
				// 新增service 以取得資料庫的群組編號
				AuthService authService = new AuthService();
				
				// 檢查群組編號是否重複 
				List<AuthVO> list = authService.getAll();// 取得資料庫中的群組編號與名稱
				for(AuthVO emp : list ) { // 比對群組編號
					if(empAuthNo.equals(emp.getEmpAuthNo())) {
						errorMsgs.add("群組編號重複");
						break;
					}
				}

				String empAuthName = req.getParameter("empAuthName");
				if (empAuthName == null || empAuthName.trim().length() == 0) {
					errorMsgs.add("請填寫群組名稱");
				}
				
				// 檢查至少有一個功能被勾選
				String[] functionNo = req.getParameterValues("functionNo");
				if(functionNo == null) {
					errorMsgs.add("請至少勾選一個功能");
				}

				AuthVO authVO = new AuthVO();
				authVO.setEmpAuthNo(empAuthNo);
				authVO.setEmpAuthName(empAuthName);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authVO", authVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/addAuth.jsp");
					failureView.forward(req, res);
					return;
				}
				
				// 開始新增資料
				AuthService authSvc = new AuthService();

				// 新增多個PK與不同的FunctionNo
				for (String function : req.getParameterValues("functionNo")) {
					authVO = authSvc.addAuth(req.getParameter("empAuthNo"), function,
							req.getParameter("empAuthName"));
				}

				// 新增完成 準備轉交
				String url = "/back-end/auth/auth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/addAuth.jsp");
				failureView.forward(req, res);
			}

		}
		
		// 修改權限功能 (刪除後新增)
		if ("updateFunctionNo".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 權限群組編號
				String empAuthNo = req.getParameter("empAuthNo");
				
				// 功能編號
				String[] functionNo = req.getParameterValues("functionNo");
				if(functionNo == null){ // 至少要有一個
					errorMsgs.add("請至少勾選一個權限，若不需要權限請刪除此群組");
				}
				
				// 群組名稱
				String empAuthName = req.getParameter("empAuthName").trim();
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/auth.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				AuthService authSvc = new AuthService();
				authSvc.removeAuth(empAuthNo);
				if (functionNo != null) {
					authSvc.updateFunctionNo(empAuthNo, functionNo, empAuthName);
				}
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/back-end/auth/auth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/auth.jsp");
				failureView.forward(req, res);
			}
		}

		// 刪除
		if ("deleteAuth".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String empAuthNo = req.getParameter("empAuthNo");
				// 檢查是否為預設群組
				if ("A02".equals(empAuthNo)) {
					errorMsgs.add("預設欄位不可刪除");
				}
				
				// 檢查是否有員工使用此群組 有的話不可刪除
				List<AuthVO> authNo = new AuthService().getAllEmpAuthNo();
				for(AuthVO no : authNo) {
					if(empAuthNo.equals(no.getEmpAuthNo())) {
						errorMsgs.add("此群組目前有員工使用中，無法刪除");
					}
				}
				
				// if ("empAuthNo".equals(action))

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/auth.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				AuthService authSvc = new AuthService();
				authSvc.removeAuth(empAuthNo);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = "/back-end/auth/auth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/auth/auth.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
