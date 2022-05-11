package com.auth.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
	
	// 刪除建構子 Ctrl+shift+o(整理沒用到的import)
	@Autowired
	private AuthDAO authdao;
	
	// 新增群組
	public AuthVO addAuth(String empAuthNo,String functionNo,String empAuthName) {
		AuthVO authVO = new AuthVO();
		
		authVO.setEmpAuthNo(empAuthNo);
		authVO.setFunctionNo(functionNo);
		authVO.setEmpAuthName(empAuthName);
		authdao.insert(authVO);
		
		return authVO;
	}
	
	// 修改功能
	public void updateFunctionNo(String empAuthNo,String[] functionNo, String empAuthName) {
		authdao.updateFunction(empAuthNo,functionNo,empAuthName);
	}
	
	// 修改群組名稱
	public AuthVO updateEmpAuthName(String empAuthNo,String empAuthName) {
		
		AuthVO authVO = new AuthVO();
		authVO.setEmpAuthNo(empAuthNo);
		authVO.setEmpAuthName(empAuthName);
		authdao.updateEmpAuthName(authVO);
		
		return authVO;
	}
	
	// 刪除群組
	public void removeAuth(String empAuthNo) {
		authdao.remove(empAuthNo);
	}
	
	// 查詢所有群組與群組編號
	public List<AuthVO> getAll() {
		return authdao.findAllEmpAuthNo();
	}
	
	// 查詢群組編號
	public List<AuthVO> getAllEmpAuthNo() {
		return authdao.findEmpAuthNo();
	}
	
	// 透過群組編號查詢所有功能
	public List<AuthVO> findFunctionByEmpAuthNo(String empAuthNo) {
		return authdao.findFunctionByEmpAuthNo(empAuthNo) ;
	}
	
	// 查詢整張TABLE
	public List<AuthVO> findAuthTable() {
		return authdao.findAuthTable();
	}
}
