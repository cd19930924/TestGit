package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.member.model.service.MemberDataManageService;
import com.member.model.service.MemberDataSettingService;
import com.member.model.vo.CommonRes;
import com.member.model.vo.MemberDataManageVO;
import com.member.model.vo.MemberDataSettingVO;

@WebServlet("/back-end/member/manage-member")
public class MemberDataManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Gson gson = new Gson();
		String json = JSONParse.jsonParse(req.getReader());
		JsonParser parser = new JsonParser();
		JsonElement element =  parser.parse(json);
		JsonObject root = element.getAsJsonObject();
		String action = root.get("action").getAsString();

		
		//組回傳json
		res.setCharacterEncoding("UTF8");
		res.setContentType("text/html; charset=UTF-8");
		if("getAll".equals(action)) {
			MemberDataManageService mdms = new MemberDataManageService();
			CommonRes<List<MemberDataManageVO>> resp = new CommonRes<>();
			List<MemberDataManageVO> result = mdms.getAllMember();
			if(result.size()>0) {
				resp.setErrorCode("getData-success");
				resp.setErrorMes("取得會員資料成功");
				resp.setBody(result);
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}else {
				resp.setErrorCode("getData-failure");
				resp.setErrorMes("取得會員資料失敗");
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}
			
		}else if("update".equals(action)) {
			MemberDataManageVO memberData = new MemberDataManageVO();
			String status = root.get("status").getAsString();
			System.out.println(status);
			Long id = root.get("memid").getAsLong();
			MemberDataManageService mdms = new MemberDataManageService();
			CommonRes<MemberDataSettingVO> resp = new CommonRes<>();
			int result =mdms.updateMemberStatus(status,id);
			if(result==1) {
				resp.setErrorCode("update-success");
				resp.setErrorMes("更新會員狀態成功");
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}else {
				resp.setErrorCode("update-failure");
				resp.setErrorMes("更新會員狀態失敗");
				String jsonStr = gson.toJson(resp);
				PrintWriter out = res.getWriter();
		        out.print(jsonStr);          
		        out.close();
			}
			
		}
	}

}
