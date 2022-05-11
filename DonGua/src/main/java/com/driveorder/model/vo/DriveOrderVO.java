package com.driveorder.model.vo;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;

public class DriveOrderVO {
	private Integer drverOrderId;	//派車訂單ID
	private Integer memId;			//會員ID
	private Integer driverId;		//司機ID
	private String driverName;
	private String driverPhone;
	private String carNumber;
	private String startPoint;		//出發點
	private String endPoint;		//目的地
	private Double distance;		//預計里程數
	private Date sendDriveDate;		//派車日期
	private Time sendDriveTime;		//派車時間
	private Double orderAmount;		//訂單金額
	private String contactName;		//聯絡人姓名
	private String contactNumber;	//連絡電話
	private String orderStatus;		//訂單狀態
	private String driveFeedback;	//意見回饋
	private Timestamp createTime;	//訂單建立時間
	private Timestamp updateTime;	//訂單修改時間
	
	
	public Integer getDrverOrderId() {
		return drverOrderId;
	}
	public void setDrverOrderId(Integer drverOrderId) {
		this.drverOrderId = drverOrderId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Date getSendDriveDate() {
		return sendDriveDate;
	}
	public void setSendDriveDate(Date sendDriveDate) {
		this.sendDriveDate = sendDriveDate;
	}
	public Time getSendDriveTime() {
		return sendDriveTime;
	}
	public void setSendDriveTime(Time sendDriveTime) {
		this.sendDriveTime = sendDriveTime;
	}
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getDriveFeedback() {
		return driveFeedback;
	}
	public void setDriveFeedback(String driveFeedback) {
		this.driveFeedback = driveFeedback;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
