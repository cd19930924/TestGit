package com.carermem.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carermem.model.CarerMemService;
import com.carermem.model.CarerMemVO;

@WebServlet("/back-end/Carermgt")
public class CarerManageServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("memStatusUpdate".equals(action)) {

			try {
				Integer id = Integer.valueOf(req.getParameter("carerID"));
				String status = req.getParameter("status");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));

				CarerMemVO vo = new CarerMemVO();
				vo.setCarerID(id);
				vo.setCarerStatus(status);
				vo.setUpdateTime(updateTime);

				CarerMemService svc = new CarerMemService();
				vo = svc.updateMemStatus(id, status, updateTime);

				res.sendRedirect("carermgt/carerMgt.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}