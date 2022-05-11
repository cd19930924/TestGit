package com.common.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.common.model.vo.GetMemberDataVO;
import com.google.gson.Gson;
import com.member.model.vo.MemberLoginVO;

@WebServlet("/front-end/common/get-memberid")
public class GetMemberIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
		
		Gson gson = new Gson();
		String json = JSONParse.jsonParse(req.getReader());
		GetMemberDataVO memberData = new GetMemberDataVO();
		memberData = gson.fromJson(json, GetMemberDataVO.class);
		String token = memberData.getToken();
		
	
	}

}
