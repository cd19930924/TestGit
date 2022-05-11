package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.emp.model.service.EmpLoginRes;
import com.emp.model.service.EmpLoginService;
import com.emp.model.vo.EmpVO;
import com.google.gson.Gson;
import com.member.model.vo.CommonRes;
import com.mysql.cj.util.StringUtils;


@WebServlet("/back-end/login")
public class EmpLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//拆解json
		Gson gson = new Gson();
		String json = JSONParse.jsonParse(request.getReader());
		EmpVO empVO = new EmpVO();
		empVO = gson.fromJson(json, EmpVO.class);
		String account = empVO.getEmpAcct();
		String password = empVO.getEmpPwd();
		
		
		//組回傳json
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF-8");
		
		EmpLoginService empLoginService = new EmpLoginService();
		CommonRes<EmpLoginRes> res = new CommonRes<>();
		EmpLoginRes empLoginRes = empLoginService.getEmpToken(account,password);
//		if("".equals(token)) 把不會發生nullPointerException的物件放前面
		if(StringUtils.isNullOrEmpty(empLoginRes.getToken())) {/*回傳空字串表示沒有token*/
			res.setErrorCode("login-failure");
			res.setErrorMes("員工登入失敗");
			String jsonStr = gson.toJson(res);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else {
			System.out.println(empLoginRes.getIdentity());
			res.setErrorCode("login-success");
			res.setErrorMes("員工登入成功");
			res.setBody(empLoginRes);
			String jsonStr = gson.toJson(res);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}
	}
}
