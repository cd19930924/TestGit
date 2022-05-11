package com.auth.model;

import java.util.List;

public interface AuthDAO {
	public void insert(AuthVO authVO); // 新增權限群組

	public void updateFunction(String empAuthNo, String[] functionNo, String empAuthName); // 修改權限群組功能

	public void updateEmpAuthName(AuthVO authVO); // 修改權限群組名稱
	
	public void remove(String empAuthNo); // 刪除權限群組
	
	public List<AuthVO> findAllEmpAuthNo(); // 查詢所有群組與群組編號
	
	public List<AuthVO> findEmpAuthNo(); // 查詢所有群組與群組編號
	
	public List<AuthVO> findFunctionByEmpAuthNo(String empAuthNo); // 查詢單一群組所有功能
	
	public List<AuthVO> findAuthTable(); // 查詢整張TABLE
	
}
