package com.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.common.model.service.JWTokenUtils;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class BackendLoginFilter
 */
@WebServlet("/back-end/BackendLoginFilter")

public class BackEndFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();
		String token = null;
		long empId = 0;
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("token".equals(cookies[i].getName())) {
					token = cookies[i].getValue();
				}
			}
		}

		if (token != null) {
				try {
					empId = JWTokenUtils.isTokenValid(token);
					chain.doFilter(request, response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					res.sendRedirect(req.getContextPath() + "/back-end/login.html");
					return;
				}
		} else {
			res.sendRedirect(req.getContextPath() + "/back-end/login.html");
			return;
		}
		
	}
}
