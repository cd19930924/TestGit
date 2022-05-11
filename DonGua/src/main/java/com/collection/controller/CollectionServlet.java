package com.collection.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collection.model.CollectionService;
import com.collection.model.CollectionVO;


@WebServlet("/carersearch/collection")
public class CollectionServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("add".equals(action)) {

			try {
				Integer memId = Integer.valueOf(req.getParameter("memId"));
				Integer carerId = Integer.valueOf(req.getParameter("carerId"));

				CollectionVO vo = new CollectionVO();
				vo.setMemId(memId);
				vo.setCarerId(carerId);

				CollectionService svc = new CollectionService();
				vo = svc.addCollection(memId, carerId);

				res.sendRedirect("../front-end/carersearch/collectedSuccess.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("delete".equals(action)) {

			try {
				Integer memId = Integer.valueOf(req.getParameter("memId"));
				Integer carerId = Integer.valueOf(req.getParameter("carerId"));

//				CollectionVO vo = new CollectionVO();
//				vo.setMemId(memId);
//				vo.setCarerId(carerId);

				CollectionService svc = new CollectionService();
				svc.deleteCollection(memId,carerId);

				res.sendRedirect("../front-end/carersearch/collection.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
