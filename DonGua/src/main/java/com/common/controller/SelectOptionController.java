package com.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.CountyService;
import com.common.model.service.DistrictService;
import com.common.model.vo.SelectOptionVO;
import com.google.gson.Gson;
import com.member.model.vo.CommonRes;

@WebServlet("/front-end/select-option")
public class SelectOptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson =new Gson();
	    CountyService countyService =new CountyService();  
	    DistrictService districtService = new DistrictService();

		String category = request.getParameter("category");
		String subcategory = request.getParameter("subcategory");
		SelectOptionVO svo = null;
		System.out.println(category);
		switch (category) {
		case "county": {
			svo = countyService.getCountySelectOption();
			break;
		}
		case "district": {
			svo = districtService.getCountySelectOption(subcategory);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + category);
		}
		
		CommonRes<SelectOptionVO> res = new CommonRes<SelectOptionVO>();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF-8");
		res.setBody(svo);
		String jsonStr =gson.toJson(res);
		PrintWriter out = response.getWriter();
        out.print(jsonStr);          
        out.close();
	}


}
