package com.driveorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JWTokenUtils;
import com.driveorder.model.service.DriveOrderService;
import com.driveorder.model.vo.DriveOrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.systemnotification.model.SystemNotificationService;

@WebServlet("/back-end/driveorder/driveorder.do")
public class DriveOrderBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create(); // new GSON物件
		DriveOrderService driveOrderService = new DriveOrderService(); // new CountyService物件

		Cookie[] cookies = request.getCookies();
		String token = null;
		long memId = 0;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("token".equals(cookies[i].getName())) {
					token = cookies[i].getValue();
				}
			}
		}

		if (token != null) {
				try {
					memId = JWTokenUtils.isEmpTokenValid(token);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		List<DriveOrderVO> res = new ArrayList<DriveOrderVO>();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		
		if("Get_All_Order".equals(action)) {
			res = driveOrderService.getAll();
		}
		
		if("Get_Order_Status".equals(action)) {
			String orderStatus = request.getParameter("orderStatus");
			
			if("7".equals(orderStatus)) {
				res = driveOrderService.getAll();
			}else {
				res = driveOrderService.findByStauts(orderStatus);
			}
		}
		
		if("Get_Order_Date".equals(action)) {
			Date sendDriveDate = java.sql.Date.valueOf(request.getParameter("sendDriveDate"));
			res = driveOrderService.findByDate(sendDriveDate);
			
		}
		
		if("Get_Order_By_Id".equals(action)) {
			
			String searchType = request.getParameter("searchType");
			Integer searchId = null;
			
			if(request.getParameter("searchId") != null && request.getParameter("searchId").trim().length() != 0 && request.getParameter("searchId").trim().matches("^[0-9]*$")) {
				searchId = Integer.parseInt(request.getParameter("searchId"));
				if("driverOrderId".equals(searchType)) {
					res.add(driveOrderService.findByOrderId(searchId));
				}else if("memId".equals(searchType)) {
					res = driveOrderService.findByMemId(searchId);
				}else {
					res = driveOrderService.findByDriverId(searchId);
				}
			}
		}
		
		if("Set_Order_Status".equals(action)) {
			Integer drverOrderId = Integer.parseInt(request.getParameter("drverOrderId"));
			String orderStatus = request.getParameter("orderStatus");
			
			DriveOrderVO driveOrderVO = driveOrderService.findByOrderId(drverOrderId);
			driveOrderVO.setOrderStatus(orderStatus);
			driveOrderService.updateDriverOrder(driveOrderVO);
			if("5".equals(orderStatus)) {
				new SystemNotificationService().saveNotification(driveOrderVO.getMemId(), driveOrderVO.getDrverOrderId(), "C02");
			}
		}
		
		if("Get_Order_Info".equals(action)) {
			
			Integer drverOrderId = Integer.parseInt(request.getParameter("drverOrderId"));
			res.add(driveOrderService.findByOrderId(drverOrderId));
		}
		
		String jsonStr = gson.toJson(res);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.close();
	}
}
