package com.common.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.common.model.service.JWTokenUtils;
import com.common.model.service.MemberDataService;
import com.common.model.vo.GetMemberDataVO;
import com.common.model.vo.MemberDataListVO;
import com.google.gson.Gson;
import com.member.model.vo.CommonRes;
import com.member.model.vo.MemberVO;

@WebServlet("/handle-member-data")
public class MemberDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String json = JSONParse.jsonParse(request.getReader());// 取得前端回傳JSON字串並透過Parse方法解析成普通字串
		System.out.println(json);
		// 將JSON字串利用VO直接將值寫入到verifyIdentityReq這個物件變數裡，不需要使用setXXX就能把值填入
		GetMemberDataVO gmdv = gson.fromJson(json, GetMemberDataVO.class);
		String condition = gmdv.getCondition();/* 定義執行哪知ServiceFunction */
		String action = gmdv.getAction();
		MemberDataService mds = new MemberDataService();
		CommonRes<MemberDataListVO> listRes = new CommonRes<MemberDataListVO>();

		
		
		//組JSON
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF-8");
		if (("".equals(condition)) || (condition.trim()==null) || ("".equals(action)) || (action.trim()==null) ) {
			listRes.setErrorCode("error");
			listRes.setErrorMes("取得資料失敗");
			String jsonStr = gson.toJson(listRes);
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.close();
		}else if ("GetData".equals(action) &&"All".equals(condition)) {
			listRes.setErrorCode("get-alldata-sucess");
			listRes.setErrorMes("取得成功");
			listRes.setBody(mds.getAllMember());
			String jsonStr = gson.toJson(listRes);
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.close();
		}else if ("GetData".equals(action) &&"One".equals(condition)) {
			long memberId = JWTokenUtils.getTokenId(gmdv.getToken());
			listRes.setErrorCode("get-data-sucess");
			listRes.setErrorMes("取得成功");
			listRes.setBody(mds.getOneMember(memberId));
			String jsonStr = gson.toJson(listRes);
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.close();
		}else {
			listRes.setErrorCode("get-data-failure");
			listRes.setErrorMes("取得失敗");
			String jsonStr = gson.toJson(listRes);
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.close();
		}
}
}
