package com.empauth.model;

public interface EmpAuthDAO {

	// (員工ID)
	String selectAuthNo(Integer id);

	// (員工ID)
	String init(Integer id);

	void update(EmpAuthVO vo);

}
