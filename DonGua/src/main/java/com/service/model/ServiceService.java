package com.service.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {
	@Autowired
	private ServiceDAO dao;
	
//	public ServiceService() {
//		dao = new ServiceDAOImpl();
//	}
	
	// 取得服務種類列表
	public List<ServiceVO> getServiceList() {
		return dao.selectAll();
	}
}
