package com.servicetab.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTabService {
	@Autowired
	private ServiceTabDAO dao;

//	public ServiceTabService() {
//		dao = new ServiceTabDAOImpl();
//	}

	// 取得服務列表
	public List<ServiceTabVO> getServiceTabList() {
		return dao.selectAll();
	}
}
