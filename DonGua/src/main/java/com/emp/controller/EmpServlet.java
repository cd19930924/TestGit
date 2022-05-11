package com.emp.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.common.model.service.JWTokenUtils;
import com.driverschedule.model.DriverScheduleService;
import com.driverschedule.model.DriverScheduleVO;
import com.emp.model.service.EmpService;
import com.emp.model.vo.EmpVO;
import com.empauth.model.*;

@WebServlet("/back-end/emp/emp.do")
public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Cookie[] cookies = req.getCookies();
		String token = null;
		long empId = 0;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("token".equals(cookies[i].getName())) {
					token = cookies[i].getValue();
				}
			}
		}

		if (token != null) {
				try {
					empId = JWTokenUtils.isEmpTokenValid(token);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String empName = req.getParameter("empName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!empName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String empPosition = req.getParameter("empPosition").trim();
				if (empPosition == null || empPosition.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}

				String empPhone = req.getParameter("empPhone").trim();
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				}else if(!empPhone.trim().matches("^09[0-9]{8}$")) {
					errorMsgs.add("手機號碼請以09開頭，後8碼輸入數字");
				}

				EmpVO empVO = new EmpVO();
				empVO.setEmpPosition(empPosition);
				empVO.setEmpName(empName);
				empVO.setEmpPhone(empPhone);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmpService empSvc = new EmpService();
				EmpAuthDAO authDao = new EmpAuthDAOImpl();
				empVO = empSvc.addEmp(empSvc.getNewAcct(), empPosition, "P@ssw0rd", empName, empPhone, "1");
				authDao.init(empSvc.findByEmpAcct(empVO.getEmpAcct()).getEmpId());
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("changelist".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				EmpService empSvc = new EmpService();
				List<EmpVO> list = new ArrayList<EmpVO>();
				String empStatus = req.getParameter("empStatus");
				
				if( "0".equals(empStatus)) {
					list = empSvc.getAll();
					req.setAttribute("list", list);
				}else {
					list = empSvc.findByEmpStatus(empStatus);
					req.setAttribute("list", list);
				}
				
				req.setAttribute("status", empStatus);
				String url = "/back-end/emp/emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/emp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String empAcct = req.getParameter("empAcct");

				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.findByEmpAcct(empAcct);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/emp.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("get_My_Info".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.findByEmpId((int)empId);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/get_emp_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/index.html");
				failureView.forward(req, res);
			}
		}
		
		if ("change_The_Password".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.findByEmpId((int)empId);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/changePassword.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("請登入帳號！");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/index.html");
				failureView.forward(req, res);
			}
		}
		
		if ("check_change".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String enameReg = "^[(\\d$@$!%*#?&)(a-zA-Z0-9_)]{8,14}$";
				
				String oldPassword = req.getParameter("oldPassword");
				if (oldPassword == null || oldPassword.trim().length() == 0) {
					errorMsgs.add("舊密碼請勿空白");
				} else if (!oldPassword.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("舊密碼只能是英文字母、數字或特殊字符 , 且長度必需在8到14之間");
				}
				
				String newPassword = req.getParameter("newPassword");
				if (newPassword == null || newPassword.trim().length() == 0) {
					errorMsgs.add("新密碼請勿空白");
				} else if (!newPassword.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("新密碼只能是英文字母、數字或特殊字符 , 且長度必需在8到14之間");
				}
				
				String newPassword2 = req.getParameter("newPassword2");
				if (newPassword2 == null || newPassword2.trim().length() == 0) {
					errorMsgs.add("確認新密碼請勿空白");
				} else if (!newPassword2.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("確認新密碼只能是英文字母、數字或特殊字符 , 且長度必需在8到14之間");
				}
				
				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.findByEmpId((int)empId);
				if(oldPassword.equals(empVO.getEmpPwd())) {
					if(newPassword.equals(newPassword2)) {
						empVO.setEmpPwd(newPassword);
						empSvc.update(empVO);
					}else {
						errorMsgs.add("新密碼與確認新密碼不相同");
					}
				}else {
					errorMsgs.add("舊密碼輸入錯誤");
				}
				
				if(errorMsgs.size() == 0) {
					res.sendRedirect(req.getContextPath()+"/back-end/index.html");
				}else {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/changePassword.jsp");
					failureView.forward(req, res);
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/changePassword.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String empAcct = req.getParameter("empAcct").trim();

				String empName = req.getParameter("empName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名請勿空白");
				} else if (!empName.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String empPosition = req.getParameter("empPosition").trim();
				if (empPosition == null || empPosition.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}

				String empPhone = req.getParameter("empPhone").trim();
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				}else if(!empPhone.trim().matches("^09[0-9]{8}$")) {
					errorMsgs.add("手機號碼請以09開頭，後8碼輸入數字");
				}
				
				String empStatus = req.getParameter("empStatus").trim();
				if (empStatus == null || empStatus.trim().length() == 0) {
					errorMsgs.add("會員狀態請勿空白");
				}


				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.findByEmpAcct(empAcct);
				empVO.setEmpPosition(empPosition);
				empVO.setEmpName(empName);
				empVO.setEmpPhone(empPhone);
				empVO.setEmpStatus(empStatus);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				empVO = empSvc.update(empVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/emp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
