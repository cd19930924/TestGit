package com.driveorder.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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

@WebServlet("/front-end/driveorder/driveorder.do")
public class DriveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
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
					memId = JWTokenUtils.isTokenValid(token);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		List<DriveOrderVO> res = new ArrayList<DriveOrderVO>();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		
		if("Get_Order_Member".equals(action)) {
			res = driveOrderService.findByMemIdOrder((int)memId);
		}
		
		
		
		if("Get_Order_Member_Status".equals(action)) {
			
			String orderStatus = request.getParameter("orderStatus");
			
			if ("7".equals(orderStatus)) {
				res = driveOrderService.findByMemIdOrder((int)memId);
			} else {
				res = driveOrderService.findByMemIdStatus(orderStatus,(int)memId);
			}
		}
		
		if("Get_Order_Member_Date".equals(action)) {
			
			Date sendDriveDate = java.sql.Date.valueOf(request.getParameter("sendDriveDate"));
			
			res = driveOrderService.findByMemIdDate(sendDriveDate, (int)memId);
		}
		
		if("Get_Order_Info".equals(action)) {
			
			Integer drverOrderId = Integer.parseInt(request.getParameter("drverOrderId"));
			res.add(driveOrderService.findByOrderId(drverOrderId));
		}
		
		
		if("Get_Order_Feedback".equals(action)) {
			
			Integer drverOrderId = Integer.parseInt(request.getParameter("drverOrderId"));
			res.add(driveOrderService.findByOrderId(drverOrderId));
		}
		
		if("Change_Order_Feedback".equals(action)) {
			
			Integer drverOrderId = Integer.parseInt(request.getParameter("drverOrderId"));
			String driveFeedback = request.getParameter("driveFeedback");
			
			DriveOrderVO driveOrderVO = driveOrderService.findByOrderId(drverOrderId);
			driveOrderVO.setDriveFeedback(driveFeedback);
			driveOrderVO.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			driveOrderService.updateDriverOrder(driveOrderVO);
			
		}
		
		if("CancelApply_Order_Id".equals(action)) {
			Integer drverOrderId = Integer.parseInt(request.getParameter("drverOrderId"));
			
			DriveOrderVO driveOrderVO = driveOrderService.findByOrderId(drverOrderId);
			driveOrderVO.setOrderStatus("4");
			driveOrderService.updateDriverOrder(driveOrderVO);
		}

		String jsonStr = gson.toJson(res);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.close();
	}
}
