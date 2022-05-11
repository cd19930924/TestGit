package com.driverschedule.model;

import java.sql.Date;
import java.util.List;

public interface DriverScheduleDAO {
	public void insert(DriverScheduleVO driverScheduleVO);	//新增班表
	public DriverScheduleVO checkSchedule(Date scheduleDate,Integer startInex,Integer endIndex);	//檢查是否有司機有空
    public void update(DriverScheduleVO driverScheduleVO);	//更新班表
    public void refreshAll(List<Integer> driverId);	//刷新在職中司機今天起算六十天班表
    public List<DriverScheduleVO> findById(Integer driverId);	//透過ID查詢班表
    public List<DriverScheduleVO> findByDate(Date scheduleDate);	//透過日期查詢班表
    public List<DriverScheduleVO> findByIdAndDate(Integer driverId,Date scheduleDate);	//透過ID與日期查詢班表
    public List<Integer>findWorkDriver();	//查詢所有在職司機
    public List<DriverScheduleVO> getAll();	//查詢所有班表
}
