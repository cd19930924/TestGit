package com.systemnotification.model;

import java.sql.Timestamp;

public class SystemNotificationVO {
 private Integer notNo;
 private Integer memId;
 private Integer orderId;
 private String notTypeNo;
 private Timestamp notTime;

 public Integer getNotNo() {
  return notNo;
 }

 public void setNotNo(Integer notNo) {
  this.notNo = notNo;
 }

 public Integer getMemId() {
  return memId;
 }

 public void setMemId(Integer memId) {
  this.memId = memId;
 }

 public Integer getOrderId() {
  return orderId;
 }

 public void setOrderId(Integer orderId) {
  this.orderId = orderId;
 }

 public String getNotTypeNo() {
  return notTypeNo;
 }

 public void setNotTypeNo(String notTypeNo) {
  this.notTypeNo = notTypeNo;
 }

 public Timestamp getNotTime() {
  return notTime;
 }

 public void setNotTime(Timestamp notTime) {
  this.notTime = notTime;
 }

 @Override
 public String toString() {
  return "SystemNotificationVO [notNo=" + notNo + ", memId=" + memId + ", orderId=" + orderId + ", notTypeNo="
    + notTypeNo + ", notTime=" + notTime + "]";
 }

}