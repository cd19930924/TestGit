package com.infomanage.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infomanage.model.InfoService;
import com.infomanage.model.InfoVO;

@WebServlet("/front-end/info")
public class InfoClientServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");

		String action = req.getParameter("action");

//http://localhost:8081/CFA104G6_Care/infoClient?action=news&info=20220213180455		
//		if ("news".equals(action)) {
//			InfoService svc = new InfoService();
//			String infoTimeId = req.getParameter("info");
//
//			try {
//				InfoVO vo = svc.getOneNews(infoTimeId);
//				req.setAttribute("infoVO", vo);
//
//				String page = "/front-end/news/news.jsp";
//				RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
//				requestDispatcher.forward(req, res);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}
		
		
//http://localhost:8081/CFA104G6_Care/infoClient?info=20220202203654
		InfoService svc = new InfoService();
		String infoTimeId = req.getParameter("news");

		try {
			InfoVO vo = svc.getOneNews(infoTimeId);
			req.setAttribute("infoVO", vo);

			String page = "/front-end/news/news.jsp";
			RequestDispatcher requestDispatcher = req.getRequestDispatcher(page);
			requestDispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
