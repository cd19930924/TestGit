package com.service;

import com.service.model.*;

public class Test {

	public static void main(String[] args) {
		ServiceDAO dao = new ServiceDAOImpl();
		ServiceService svc = new ServiceService();
		
//		dao.selectAll();
		svc.getServiceList();
	}

}
