package com.empauth.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empauth.model.EmpAuthService;
import com.empauth.model.EmpAuthVO;

@WebServlet("/back-end/auth/empAuth")
public class EmpAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		EmpAuthService empAuthSVC = new EmpAuthService();
		List<String> errorMsgs = new ArrayList<>();
		String action = req.getParameter("action");

		req.setAttribute("errorMsgs", errorMsgs);

		// empAuth
		if ("edit_auth_no".equals(action)) {
			try {
				Integer empId = null;
				try {
					empId = Integer.valueOf(req.getParameter("empId"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("員工編號格式錯誤");
				}

				String empAuthNo = req.getParameter("empAuthNo");
				if (empAuthNo == null || "none".equals(empAuthNo)) {
					errorMsgs.add("請選擇權限群組");
				}

				EmpAuthVO empAuthVO = new EmpAuthVO();

				empAuthVO.setEmpId(empId);
				empAuthVO.setEmpAuthNo(empAuthNo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empAuthVO", empAuthVO);

					RequestDispatcher fail = req.getRequestDispatcher("/back-end/auth/empAuth.jsp");
					fail.forward(req, res);

					return;
				}

				empAuthSVC.editAuthNo(empId, empAuthNo);

				RequestDispatcher success = req.getRequestDispatcher("/back-end/auth/empAuth.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("出事了阿伯：" + e.getMessage());

				RequestDispatcher fail = req.getRequestDispatcher("/back-end/auth/empAuth.jsp");
				fail.forward(req, res);
			}
		}
	}

}
