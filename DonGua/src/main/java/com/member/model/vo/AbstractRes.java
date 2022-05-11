package com.member.model.vo;

public abstract class AbstractRes {/*先留著*/
	private String errorCode;
	private String errorMes;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMes() {
		return errorMes;
	}
	public void setErrorMes(String errorMes) {
		this.errorMes = errorMes;
	}
	
}
