package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.model.service.JSONParse;
import com.common.util.CommonUtil;
import com.google.gson.Gson;
import com.member.model.service.MemberLoginService;
import com.member.model.vo.CommonRes;
import com.member.model.vo.MemberLoginRes;
import com.member.model.vo.MemberLoginVO;
import com.mysql.cj.util.StringUtils;



@WebServlet("/front-end/member/member-login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberLoginService memService ;
	
	@Override
	public void init() throws ServletException {
		memService = CommonUtil.getBean(getServletContext(), MemberLoginService.class);
	}
    public MemberLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//拆解json
		Gson gson = new Gson();
		String json = JSONParse.jsonParse(request.getReader());
		MemberLoginVO memberLogin = new MemberLoginVO();
		memberLogin = gson.fromJson(json, MemberLoginVO.class);
		String account = memberLogin.getaccount();
		String password = memberLogin.getpassword();
		
		//組回傳json
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		CommonRes<MemberLoginRes> res = new CommonRes<>();
		MemberLoginRes memberLoginRes = memService.getMemberToken(account,password);
//		if("".equals(token)) 把不會發生nullPointerException的物件放前面
		if("StatusError".equals(memberLoginRes.getToken())) {
			res.setErrorCode("account-suspension");
			res.setErrorMes("帳號已被停權，請聯繫客服!!");
			String jsonStr = gson.toJson(res);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else if("NonVerify".equals(memberLoginRes.getToken())) {
			res.setErrorCode("account-noverify");
			res.setErrorMes("帳號尚未驗證，請先進行驗證後登入!!");
			String jsonStr = gson.toJson(res);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else if(StringUtils.isNullOrEmpty(memberLoginRes.getToken())) {/*回傳空字串表示沒有token*/
			res.setErrorCode("login-failure");
			res.setErrorMes("帳號或密碼錯誤");
			String jsonStr = gson.toJson(res);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}else {
			res.setErrorCode("login-success");
			res.setErrorMes("會員登入成功");
			res.setBody(memberLoginRes);
			String jsonStr = gson.toJson(res);
			PrintWriter out = response.getWriter();
	        out.print(jsonStr);          
	        out.close();
		}
	}
}
