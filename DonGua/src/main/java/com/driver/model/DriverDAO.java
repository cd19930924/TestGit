package com.driver.model;

import java.util.List;


public interface DriverDAO {
    public void insert(DriverVO driveVO);	//新增一筆司機資料
    public void update(DriverVO driveVO);	//修改司機資料
    public DriverVO findById(Integer driverId);	//透過司機ID搜尋司機資料
    public List<DriverVO> findByName(String driverName);	//透過司機姓名搜尋資料
    public List<DriverVO> getAll();	//搜尋所有司機資料
    public void serviceStatusUpdate(DriverVO driverVO); // 修改司機服務狀態
		
}