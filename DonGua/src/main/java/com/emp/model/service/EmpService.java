package com.emp.model.service;

import java.util.List;

import com.emp.model.dao.EmpDAO;
import com.emp.model.dao.impl.EmpJNDIDAOImpl;
import com.emp.model.vo.EmpVO;

public class EmpService {
	private EmpDAO dao;
	
	public EmpService() {
		dao = new EmpJNDIDAOImpl();
	}
	
	public EmpVO addEmp(String empAcct,String empPosition,
			String empPwd,String empName,String empPhone,String empStatus) {

		EmpVO empVO = new EmpVO();
		empVO.setEmpAcct(empAcct);
		empVO.setEmpPosition(empPosition);
		empVO.setEmpPwd(empPwd);
		empVO.setEmpName(empName);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpStatus(empStatus);
		dao.insert(empVO);
		return empVO;
	}
	
	public List<EmpVO> findByEmpStatus(String empStatus) {
		return dao.findByEmpStatus(empStatus);
	}
	
	
	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	
	public String getNewAcct() {
		return dao.getNewAcct();
	}
	
	public EmpVO findByEmpAcct(String empAcct) {
		return dao.findByEmpAcct(empAcct);
	}
	
	public EmpVO findByEmpId(Integer empId) {
		return dao.findByEmpId(empId);
	}
	
	public EmpVO update(EmpVO empVO) {
		dao.update(empVO);
		return empVO;
	}
}
