package com.emp.model.service;

import com.common.model.service.JWTokenUtils;
import com.emp.model.dao.EmpDAO;
import com.emp.model.dao.impl.EmpJNDIDAOImpl;
import com.emp.model.vo.EmpVO;
import com.empauth.model.EmpAuthDAO;
import com.empauth.model.EmpAuthDAOImpl;
import com.google.gson.Gson;

public class EmpLoginService {
	public EmpLoginRes getEmpToken(String account, String password) {
		Gson gson = new Gson();
		EmpDAO dao = new EmpJNDIDAOImpl();
		EmpAuthDAO authDao = new EmpAuthDAOImpl();
		String token = "";
		EmpLoginRes empLoginRes = new EmpLoginRes("", "");
		if (account != null && password != null) {
			try {
				EmpVO empVO = dao.isEmpExist(account, password);

				if (empVO.getEmpId() != 0) {
					JWTokenUtils.createToken(empVO.getEmpId());
					token = JWTokenUtils.createToken(empVO.getEmpId()).getToken();
					empLoginRes.setToken(token);
					empLoginRes.setIdentity(authDao.selectAuthNo(empVO.getEmpId()));/* 設定會員身分 */
					return empLoginRes;
				} else {
					return empLoginRes;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return empLoginRes;

	}
}
