package com.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.common.model.service.VerifyIdentityService;
import com.common.model.vo.VerifyIdentityVO;
import com.google.gson.Gson;
import com.member.model.vo.CommonRes;


@WebServlet("/verify-identity")
public class VerifyIdentityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//拆解json
		
		Gson gson = new Gson();
		String json = JSONParse.jsonParse(request.getReader());//取得前端回傳JSON字串並透過Parse方法解析成普通字串
		System.out.println(json);
		//將JSON字串利用VO直接將值寫入到verifyIdentityReq這個物件變數裡，不需要使用setXXX就能把值填入
		VerifyIdentityVO verifyIdentityReq = gson.fromJson(json, VerifyIdentityVO.class);
		//將值傳入方法中
		VerifyIdentityService vis = new VerifyIdentityService();
		boolean isCarerExist = vis.carerIdentity(verifyIdentityReq.getToken());
		
		//組回傳json
		CommonRes<VerifyIdentityVO> res = new CommonRes<>();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF-8");

		//判斷該照護員是否存在
		if(isCarerExist) {
			res.setErrorCode("carer-exist");
			res.setErrorMes("照護員存在");
			String jsonStr = gson.toJson(res);//將填好的物件資料轉成JSON字串
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else {
			res.setErrorCode("carer-nonexist");
			res.setErrorMes("照護員不存在");
			String jsonStr = gson.toJson(res);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}
	}

}
