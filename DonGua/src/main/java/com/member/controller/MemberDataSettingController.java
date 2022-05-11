package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.common.model.service.JWTokenUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.member.model.service.MemberDataSettingService;
import com.member.model.vo.CommonRes;
import com.member.model.vo.MemberDataSettingVO;
import com.member.model.vo.MemberLoginRes;
import com.member.model.vo.MemberLoginVO;
import com.member.model.vo.MemberVO;


@WebServlet("/front-end/member/member-data-setting")
public class MemberDataSettingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//拆解json
		Gson gson = new Gson();
		String json = JSONParse.jsonParse(req.getReader());
		JsonParser parser = new JsonParser();
		JsonElement element =  parser.parse(json);
		JsonObject root = element.getAsJsonObject();
		String action = root.get("action").getAsString();
		if("update".equals(action)) {
			MemberDataSettingVO memberData = new MemberDataSettingVO();
			memberData = gson.fromJson(json, MemberDataSettingVO.class);
			MemberDataSettingService mdss = new MemberDataSettingService();
			CommonRes<MemberDataSettingVO> resp = new CommonRes<>();
			System.out.println(memberData.getDistNo());
			int result =mdss.updateMemberData(memberData);
			if(result==1) {
				resp.setErrorCode("update-success");
				resp.setErrorMes("更新會員資料成功");
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}else {
				resp.setErrorCode("update-success");
				resp.setErrorMes("更新會員資料失敗");
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}
			
		}else {
			MemberLoginRes memberToken = new MemberLoginRes();
			memberToken = gson.fromJson(json, MemberLoginRes.class);
			MemberDataSettingService mdss = new MemberDataSettingService();
			CommonRes<MemberDataSettingVO> resp = new CommonRes<>();
			MemberDataSettingVO memberVO = new MemberDataSettingVO();
			try {
				memberVO = mdss.getMemberDataByToken((JWTokenUtils.isTokenValid(memberToken.getToken())));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//組回傳json
			res.setCharacterEncoding("UTF8");
			res.setContentType("text/html; charset=UTF-8");
			if(memberVO !=null) {
				resp.setErrorCode("getdata-success");
				resp.setErrorMes("取得會員資料成功");
				resp.setBody(memberVO);
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}else {
				resp.setErrorCode("getdata-failure");
				resp.setErrorMes("取得會員資料失敗");
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}
		}
		
	}

}
