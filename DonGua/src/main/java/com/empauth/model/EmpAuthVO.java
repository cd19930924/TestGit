package com.empauth.model;

public class EmpAuthVO {
	private Integer empId;
	private String empAuthNo;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpAuthNo() {
		return empAuthNo;
	}

	public void setEmpAuthNo(String empAuthNo) {
		this.empAuthNo = empAuthNo;
	}

	@Override
	public String toString() {
		return "EmpAuthVO [empId=" + empId + ", empAuthNo=" + empAuthNo + "]";
	}

}