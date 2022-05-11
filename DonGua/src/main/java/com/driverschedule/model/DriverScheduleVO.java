package com.driverschedule.model;

import java.sql.Date;

public class DriverScheduleVO {
	private Integer driverId;
	private Date scheduleDate;
	private String scheduleStatus;
	
public Integer getDriverId() {
		return driverId;
	}
public void setDriverId(Integer driverId) {
	this.driverId = driverId;
}
public Date getScheduleDate() {
	return scheduleDate;
}
public void setScheduleDate(Date scheduleDate) {
	this.scheduleDate = scheduleDate;
}
public String getScheduleStatus() {
	return scheduleStatus;
}
public void setScheduleStatus(String scheduleStatus) {
	this.scheduleStatus = scheduleStatus;
}
}