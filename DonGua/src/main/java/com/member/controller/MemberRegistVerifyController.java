package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.service.MemberRegistService;


@WebServlet("/front-end/member/member-verify")
public class MemberRegistVerifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append("驗證成功，請稍後為您導回登入頁面 ");
		response.setHeader("Refresh","2; URL=http://localhost:8081/CFA104G6/front-end/login.html");
		String memAcct = request.getParameter("member");
		MemberRegistService mrs = new MemberRegistService();
		System.out.println(memAcct);
		mrs.activeMember(memAcct);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
