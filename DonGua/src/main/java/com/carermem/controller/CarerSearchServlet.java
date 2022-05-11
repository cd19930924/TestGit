package com.carermem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carermem.model.CarerMemService;
import com.carermem.model.CarerMemVO;
import com.file.model.FileService;
import com.file.model.FileVO;


@WebServlet("/carer/carersearch")
public class CarerSearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO : add JWT

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("multipleCarersSearch".equals(action)) {

			try {
				Map<String, String[]> map = req.getParameterMap();

				CarerMemService carerSvc = new CarerMemService();
				List<CarerMemVO> list = carerSvc.getAll(map);

				req.setAttribute("carerSearchResult", list);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/carersearch/searchResult.jsp");
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("displayACarer".equals(action)) {

			try {
				String str = req.getParameter("carerId");
				System.out.println(str);
				Integer carerId = Integer.valueOf(str);
				System.out.println(carerId);
				CarerMemService carerSvc = new CarerMemService();
				CarerMemVO carerVO = carerSvc.getOneCarer(carerId);
				FileService fileSvc = new FileService();
				FileVO fileVO = fileSvc.getHeadShot(carerId);
				String certi = carerSvc.getCarerCerti(carerId);
				String normalSkill = carerSvc.getCarerSkills(carerId, "0");
				String proSkill = carerSvc.getCarerSkills(carerId, "1");
				

				req.setAttribute("carerVO", carerVO);
				req.setAttribute("fileVO", fileVO);
				req.setAttribute("certi", certi);
				req.setAttribute("normalSkill", normalSkill);
				req.setAttribute("proSkill", proSkill);

				String url = "/front-end/carersearch/displayACarer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
