package com.infomanage.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.infomanage.model.InfoService;
import com.infomanage.model.InfoVO;

@WebServlet("/infomanage")
public class InfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("newsPost".equals(action)) {
			try {

				String no = req.getParameter("infoNo");
				String name = req.getParameter("infoName");
				String content = req.getParameter("infoContent");
				String status = req.getParameter("infoSatus");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp createTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));

				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setCreateTime(createTime);
				vo.setStatus(status);

				InfoService infoSvc = new InfoService();
				vo = infoSvc.addNews(no, name, content, createTime, status);

				res.sendRedirect("back-end/infomanage/news.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("newsUpdate".equals(action)) {

			try {
				String no = req.getParameter("infoNo");
				String name = req.getParameter("infoName");
				String content = req.getParameter("infoContent");
				String status = req.getParameter("infoStatus");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));

				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setUpdateTime(updateTime);
				vo.setStatus(status);

				InfoService infoSvc = new InfoService();
				vo = infoSvc.updateNews(no, name, content, status, updateTime);

				res.sendRedirect("back-end/infomanage/news.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("newsStatusUpdate".equals(action)) {

			try {
				String no = req.getParameter("infoNo");
				String status = req.getParameter("infoStatus");

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));

				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setUpdateTime(updateTime);
				vo.setStatus(status);

				InfoService infoSvc = new InfoService();
				vo = infoSvc.updateNewsStatus(no, status, updateTime);

				res.sendRedirect("back-end/infomanage/news.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("introPost".equals(action)) {
			try {
				
				String no = req.getParameter("infoNo");
				String name = req.getParameter("infoName");
				String content = req.getParameter("infoContent");
				String status = req.getParameter("infoSatus");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp createTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));
				
				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setCreateTime(createTime);
				vo.setStatus(status);
				
				InfoService infoSvc = new InfoService();
				vo = infoSvc.addIntro(no, name, content, createTime, status);
				
				res.sendRedirect("back-end/infomanage/intro.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("introUpdate".equals(action)) {
			
			try {
				String no = req.getParameter("infoNo");
				String name = req.getParameter("infoName");
				String content = req.getParameter("infoContent");
				String status = req.getParameter("infoStatus");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));
				
				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setUpdateTime(updateTime);
				vo.setStatus(status);
				
				InfoService infoSvc = new InfoService();
				vo = infoSvc.updateIntro(no, name, content, status, updateTime);
				
				res.sendRedirect("back-end/infomanage/intro.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("introStatusUpdate".equals(action)) {
			
			try {
				String no = req.getParameter("infoNo");
				String status = req.getParameter("infoStatus");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));
				
				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setUpdateTime(updateTime);
				vo.setStatus(status);
				
				InfoService infoSvc = new InfoService();
				vo = infoSvc.updateIntroStatus(no, status, updateTime);
				
				res.sendRedirect("back-end/infomanage/intro.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("faqPost".equals(action)) {
			try {
				
				String no = req.getParameter("infoNo");
				String name = req.getParameter("infoName");
				String content = req.getParameter("infoContent");
				String status = req.getParameter("infoSatus");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp createTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));
				
				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setCreateTime(createTime);
				vo.setStatus(status);
				
				InfoService infoSvc = new InfoService();
				vo = infoSvc.addFAQ(no, name, content, createTime, status);
				
				res.sendRedirect("back-end/infomanage/faq.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if ("faqUpdate".equals(action)) {
			
			try {
				String no = req.getParameter("infoNo");
				String name = req.getParameter("infoName");
				String content = req.getParameter("infoContent");
				String status = req.getParameter("infoStatus");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));
				
				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setUpdateTime(updateTime);
				vo.setStatus(status);
				
				InfoService infoSvc = new InfoService();
				vo = infoSvc.updateFAQ(no, name, content, status, updateTime);
				
				res.sendRedirect("back-end/infomanage/faq.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("faqStatusUpdate".equals(action)) {
			
			try {
				String no = req.getParameter("infoNo");
				String status = req.getParameter("infoStatus");
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp updateTime = java.sql.Timestamp.valueOf(dtf.format(LocalDateTime.now()));
				
				InfoVO vo = new InfoVO();
				vo.setNo(no);
				vo.setUpdateTime(updateTime);
				vo.setStatus(status);
				
				InfoService infoSvc = new InfoService();
				vo = infoSvc.updateFAQStatus(no, status, updateTime);
				
				res.sendRedirect("back-end/infomanage/faq.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
