package com.carermemapplymgt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carermem.model.CarerMemService;
import com.carermemapplymgt.model.CarerMemApplyMgtService;
import com.carermemapplymgt.model.CarerMemApplyMgtVO;
import com.file.model.FileService;
import com.file.model.FileVO;
import com.systemnotification.model.SystemNotificationService;

@WebServlet("/back-end/carermemapplymgt/carerMemApplyMgt.do")
public class CarerMemApplyMgtServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		List<String> errorMsgs = new ArrayList<>();

		// 送出查看單一申請單請求
		if ("getoneapplydata".equals(action)) {

			req.setAttribute("errorMsgs", errorMsgs);

			FileService fileSvc = new FileService();
			Integer carerID = Integer.parseInt(req.getParameter("carerID"));

			try {
				String str = req.getParameter("applyID");
				int id = Integer.valueOf(str);
				Integer applyID = id;

				// 讀照片
				List<FileVO> list = fileSvc.getPic(carerID);
				req.setAttribute("photoresult", list);

				CarerMemApplyMgtService carerMemApplyMgtSVC = new CarerMemApplyMgtService();
				CarerMemApplyMgtVO carerMemApplyMgtVo = carerMemApplyMgtSVC.getOneApplyData(applyID);
				CarerMemService svc = new CarerMemService();

				req.setAttribute("carerMemApplyMgtVo", carerMemApplyMgtVo);

				String normalSkill = svc.getCarerSkills(carerID, "0");
				String proSkill = svc.getCarerSkills(carerID, "1");

				req.setAttribute("normalSkill", normalSkill);
				req.setAttribute("proSkill", proSkill);

				String url = "/back-end/carermemapplymgt/ListOneCarerApply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/carermemapplymgt/CarerMemApplyMgt.jsp");
				failureView.forward(req, res);
			}

		}

		// 審核失敗請求 更改狀態
		if ("fail".equals(action)) {
			try {
				System.out.println("近來ㄌ");
				
				Integer applyID = Integer.valueOf(req.getParameter("applyID"));
				Integer memID = Integer.valueOf(req.getParameter("memID"));
				CarerMemApplyMgtService cmamSVC = new CarerMemApplyMgtService();
				cmamSVC.updateFailStatus(applyID);
				
				new SystemNotificationService().saveNotification(memID, applyID, "E12");

				RequestDispatcher success = req.getRequestDispatcher("/back-end/carermemapplymgt/CarerMemApplyMgt.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("失敗：" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/carermemapplymgt/CarerMemApplyMgt.jsp");
				fail.forward(req, res);
			}

		}
		
		// 審核成功請求 新增資料至照護會員及更改申請單狀態
		if("success".equals(action)) {
			try {
				Integer applyID = Integer.valueOf(req.getParameter("applyID"));
				Integer memID = Integer.valueOf(req.getParameter("memID"));
				CarerMemApplyMgtService cmamSVC = new CarerMemApplyMgtService();
				cmamSVC.insertCarerMem(applyID);
				cmamSVC.updateSuccessStatus(applyID);
				
				new SystemNotificationService().saveNotification(memID, applyID, "E11");

				RequestDispatcher success = req.getRequestDispatcher("/back-end/carermemapplymgt/CarerMemApplyMgt.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("失敗：" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/carermemapplymgt/CarerMemApplyMgt.jsp");
				fail.forward(req, res);
			}
			
		}

	}

}
