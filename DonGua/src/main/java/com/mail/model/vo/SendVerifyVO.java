package com.mail.model.vo;

public class SendVerifyVO {
	private String memAcct;
	private String memEmail;
	private String code;
	
	
	public SendVerifyVO(String name, String email, String code) {
		this.memEmail = email;
		this.memAcct = name;
		this.code = code;
		
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemAcct() {
		return memAcct;
	}
	public void setMemAcct(String memAcct) {
		this.memAcct = memAcct;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
