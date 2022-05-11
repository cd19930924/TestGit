package com.emp.model.dao;

import java.util.List;

import com.emp.model.vo.EmpVO;

public interface EmpDAO {
	public void insert(EmpVO empVO);	//新增員工資料
    public void update(EmpVO empVO);	//更新員工資料
    public EmpVO findByEmpAcct(String empAcct);	//透過帳號查詢員工資料
    public EmpVO findByEmpId(Integer empId);	//透過ID查詢員工資料
    public List<EmpVO> findByEmpStatus(String empStatus);	//透過員工狀態查詢員工資料
    public List<EmpVO> getAll();	//查詢所有員工資料
    public String getNewAcct();	//獲得最新的員工帳號
    public EmpVO isEmpExist(String empAcct,String empPwd) throws ClassNotFoundException;
    public Boolean isEmpExistById(Long empId) throws ClassNotFoundException;
}
