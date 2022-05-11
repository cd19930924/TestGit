package com.driver.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
	
	@Autowired
	private DriverJNDIDAO dao;

//	新增
	public DriverVO addDriver(String driverName, String driverPhone, String carNumber, String driverEmail,
			String serviceStatus) {

		DriverVO driverVO = new DriverVO();
		driverVO.setDriverName(driverName);
		driverVO.setDriverPhone(driverPhone);
		driverVO.setCarNumber(carNumber);
		driverVO.setDriverEmail(driverEmail);
		driverVO.setServiceStatus(serviceStatus);
		dao.insert(driverVO);
		return driverVO;
	}

//	修改		
	public DriverVO updateDriver(Integer driverId, String driverName, String driverPhone, String carNumber,
			String driverEmail, String serviceStatus) {
		DriverVO driverVO = new DriverVO();
		driverVO.setDriverId(driverId);
		driverVO.setDriverName(driverName);
		driverVO.setDriverPhone(driverPhone);
		driverVO.setCarNumber(carNumber);
		driverVO.setDriverEmail(driverEmail);
		driverVO.setServiceStatus(serviceStatus);
		dao.update(driverVO);
		
		return driverVO;
	}

//	透過ID查詢
	public DriverVO findDriverById(Integer driverId) {
		return dao.findById(driverId);
	}

//	透過姓名查詢
	public List<DriverVO> findDriverByName(String driverName) {
		return dao.findByName(driverName);
	}

//	查詢全部
	public List<DriverVO> getAllDriver(){
		return dao.getAll();
	}

	// 修改司機服務狀態
	public DriverVO serviceStatusUpdate(String no, String serviceStatus) {
		// TODO Auto-generated method stub
		DriverVO driverVO = new DriverVO();
		driverVO.setDriverIdStr(no);
		driverVO.setServiceStatus(serviceStatus);
		dao.serviceStatusUpdate(driverVO);
		return driverVO;
	}
	
	
}
