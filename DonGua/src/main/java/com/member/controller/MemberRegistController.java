package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.google.gson.Gson;
import com.member.model.service.MemberRegistService;
import com.member.model.vo.CommonRes;
import com.member.model.vo.MemberResVO;


@WebServlet("/front-end/member/member-regist")
public class MemberRegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String json = "";
		
		json = JSONParse.jsonParse(request.getReader());/*拆解JSON字串*/
		System.out.println(json);
		MemberRegistService memService = new MemberRegistService();
		int result = memService.insertMemberRegist(json);
		
		
		/*組回傳JSON字串*/
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF-8");
		
		MemberResVO error = new MemberResVO();
		CommonRes<MemberResVO> res = new CommonRes<>();
		Gson gson = new Gson();
		if(result==0) {
			error.setErrorCode("accout-dulpicate");
			error.setErrorMes("會員帳號重複");
			String jsonStr = gson.toJson(error);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else if(result ==1) {
			error.setErrorCode("enroll-sucess");
			error.setErrorMes("會員註冊成功");
			String jsonStr = gson.toJson(error);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else if(result ==2) {
			error.setErrorCode("data-error");
			error.setErrorMes("註冊資料錯誤，請重新輸入註冊資料");
			String jsonStr = gson.toJson(error);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else {
			error.setErrorCode("regist-failure");
			error.setErrorMes("註冊資料有誤，會員註冊失敗");
			String jsonStr = gson.toJson(error);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}
	}
}
