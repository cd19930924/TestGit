package com.common.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CommonUtil {
	public static <T> T getBean(ServletContext sc,Class<T> clazz) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
		return context.getBean(clazz);
	}
}
