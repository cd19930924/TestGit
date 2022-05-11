package com.driveorder.model.dao;

import java.sql.Date;
import java.util.List;

import com.driveorder.model.vo.DriveOrderVO;


public interface DriveOrderDAO {
    public DriveOrderVO insert(DriveOrderVO driveOrderVO);									//新增派車訂單
    public void update(DriveOrderVO driveOrderVO);									//修改派車訂單
    public DriveOrderVO findByOrderId(Integer driverOrderId);						//透過派車訂單ID搜尋派車訂單
    public List<DriveOrderVO> findByDate(Date sendDriveDate);						//透過派車訂單日期搜尋訂單
    public List<DriveOrderVO> findByMemId(Integer memId);							//透過會員ID搜尋派車訂單
    public List<DriveOrderVO> findByMemIdOrder(Integer memId);						//透過會員ID搜尋派車訂單並透過訂單狀態排序
    public List<DriveOrderVO> findByMemIdStatus(String orderStatus,Integer memId);	//透過會員ID與訂單狀態搜尋訂單
    public List<DriveOrderVO> findByMemIdDate(Date sendDriveDate,Integer memId);	//透過會員ID與訂單狀態搜尋訂單
    public List<DriveOrderVO> findByDriverId(Integer driverId);						//透過司機ID搜尋派車訂單
    public List<DriveOrderVO> findByStatus(String orderStatus);						//透過訂單狀態搜尋派車訂單
    public List<DriveOrderVO> getAll();												//搜尋所有派車訂單
}
