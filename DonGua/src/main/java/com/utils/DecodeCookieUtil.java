package com.utils;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.auth.model.AuthService;
import com.auth.model.AuthVO;
import com.common.model.service.JWTokenUtils;

public class DecodeCookieUtil {

	public static long getMemId(HttpServletRequest req) throws ClassNotFoundException {
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie ck : cookies) {
				if (ck.getName().equals("token")) {
					return JWTokenUtils.isTokenValid(ck.getValue());
				}
			}
		}

		return 0L;
	}

	public static List<AuthVO> getFunctionList(HttpServletRequest req) throws ClassNotFoundException {
		Cookie[] cookies = req.getCookies();
		Boolean check = false;
		String identity = null;

		if (cookies != null) {
			for (Cookie ck : cookies) {
				if (ck.getName().equals("token")) {
					check = (JWTokenUtils.isEmpTokenValid(ck.getValue()) != 0);
				}
				if (ck.getName().equals("identity")) {
					identity = ck.getValue();
				}
			}

			if (check && identity != null) {
				return new AuthService().findFunctionByEmpAuthNo(identity);
			}
		}else {
			System.out.println("Cookie為空值");
		}

		return null;
	}
}
