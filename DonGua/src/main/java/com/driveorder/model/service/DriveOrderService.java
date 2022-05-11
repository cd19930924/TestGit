package com.driveorder.model.service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driveorder.model.dao.impl.DriveOrderJNDIDAOImpl;
import com.driveorder.model.vo.DriveOrderVO;

@Service
public class DriveOrderService {
	
	@Autowired
	private DriveOrderJNDIDAOImpl dao;

//	public DriveOrderService() {
//		dao = new DriveOrderJNDIDAOImpl();
//	}

	public DriveOrderVO addDriverOrder(DriveOrderVO driverOrderVO) {
		return dao.insert(driverOrderVO);
	}

	public void updateDriverOrder(DriveOrderVO driveOrderVO) {
		dao.update(driveOrderVO);
	}

	public DriveOrderVO findByOrderId(Integer driverOrderId) {
		return dao.findByOrderId(driverOrderId);
	}

	public List<DriveOrderVO> findByDate(Date sendDriveDate) {
		return dao.findByDate(sendDriveDate);
	}

	public List<DriveOrderVO> findByMemId(Integer memId) {
		return dao.findByMemId(memId);
	}
	public List<DriveOrderVO> findByMemIdOrder(Integer memId) {
		return dao.findByMemIdOrder(memId);
	}
	
	public List<DriveOrderVO> findByMemIdStatus(String orderStatus,Integer memId) {
		return dao.findByMemIdStatus(orderStatus,memId);
	}
	
	public List<DriveOrderVO> findByMemIdDate(Date sendDriveDate,Integer memId) {
		return dao.findByMemIdDate(sendDriveDate,memId);
	}

	public List<DriveOrderVO> findByDriverId(Integer driverId) {
		return dao.findByDriverId(driverId);
	}
	public List<DriveOrderVO> findByStauts(String orderStatus) {
		return dao.findByStatus(orderStatus);
	}

	public List<DriveOrderVO> getAll() {
		return dao.getAll();
	}
	

}
