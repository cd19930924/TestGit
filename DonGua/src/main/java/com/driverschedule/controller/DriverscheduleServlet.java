package com.driverschedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.driverschedule.model.*;

/**
 * Servlet implementation class DriverscheduleServlet
 */
@WebServlet("/back-end/driverschedule/driverschedule.do")
public class DriverscheduleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		if ("getSchedule_Search".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				DriverScheduleService driverScheduleService = new DriverScheduleService();
				List<DriverScheduleVO> list = new ArrayList<DriverScheduleVO>();
				
				if(req.getParameter("driverId")== "") {
					Date scheduleDate = java.sql.Date.valueOf(req.getParameter("scheduleDate"));
					list = driverScheduleService.findScheduleByDate(scheduleDate);
				}else if(req.getParameter("scheduleDate")== ""){
					Integer driverId = Integer.parseInt(req.getParameter("driverId"));
					list = driverScheduleService.findScheduleById(driverId);
				}else if(req.getParameter("driverId")!= "" && req.getParameter("scheduleDate") != "") {
					Integer driverId = Integer.parseInt(req.getParameter("driverId"));
					Date scheduleDate = java.sql.Date.valueOf(req.getParameter("scheduleDate"));
					list = driverScheduleService.findScheduleByIdAndDate(driverId,scheduleDate);
				}
				
				
				req.setAttribute("list", list);
				String url = "/back-end/driverschedule/driverSchedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (NumberFormatException e) {
				errorMsgs.add("請輸入正確的司機ID");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driverschedule/driverSchedule.jsp");
				failureView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("查詢時搜尋欄位不可空白");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driverschedule/driverSchedule.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("refresh_Schedule".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				DriverScheduleService driverScheduleService = new DriverScheduleService();
				driverScheduleService.refreshAll();
				
				String url = "/back-end/driverschedule/driverSchedule.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/driverschedule/driverSchedule.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
