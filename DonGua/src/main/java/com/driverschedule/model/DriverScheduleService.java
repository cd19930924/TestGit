package com.driverschedule.model;

import java.sql.Date;
import java.util.List;

public class DriverScheduleService {
	private DriverScheduleJNDIDAOImpl dao;

	public DriverScheduleService() {
		dao = new DriverScheduleJNDIDAOImpl();
	}
	
	public void updateSchedule(DriverScheduleVO driverScheduleVO) {
		dao.update(driverScheduleVO);
	}
	
	public List<DriverScheduleVO> findScheduleById(Integer driverId) {
		return dao.findById(driverId);
	}
	
	public List<DriverScheduleVO> findScheduleByDate(Date scheduleDate) {
		return dao.findByDate(scheduleDate);
	}
	
	public List<DriverScheduleVO> findScheduleByIdAndDate(Integer driverId,Date scheduleDate) {
		return dao.findByIdAndDate(driverId,scheduleDate);
	}
	
	public DriverScheduleVO checkAndUpdate(Date scheduleDate,Integer startIndex,Integer endIndex) {
		DriverScheduleVO driverScheduleVO = dao.checkSchedule(scheduleDate,startIndex,endIndex);
		return driverScheduleVO;
	}
	
	public void refreshAll() {
		List<Integer> list = dao.findWorkDriver();
		dao.refreshAll(list);
	}
	
	public List<DriverScheduleVO> getAllSchedule() {
		return dao.getAll();
	}
}
