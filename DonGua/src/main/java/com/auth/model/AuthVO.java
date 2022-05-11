package com.auth.model;

public class AuthVO {
	private String functionNo;
	private String empAuthNo;
	private String empAuthName;
	private Integer functionNoInt;
	
	public String getEmpAuthName() {
		return empAuthName;
	}

	public void setEmpAuthName(String empAuthName) {
		this.empAuthName = empAuthName;
	}

	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public String getEmpAuthNo() {
		return empAuthNo;
	}

	public void setEmpAuthNo(String empAuthNo) {
		this.empAuthNo = empAuthNo;
	}

	public Integer getFunctionNoInt() {
		return functionNoInt;
	}

	public void setFunctionNoInt(Integer functionNoInt) {
		this.functionNoInt = functionNoInt;
	}

}
